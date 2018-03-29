### 创建和销毁对象

#### 考虑用静态工厂方法代替构造器

```java
public static Boolean valueOf(boolean b){
  return b ? Boolean.TRUE : Boolean.FALSE;
}
```

- 静态工程方法可以有含义明确的名称
- 不必每次调用时都创建一个新对象
- 可以返回原返回类型的任何子类型对象
- 简化参数化类型实例创建

```java
//原始写法
Map<String,List<String>> m = new HashMap<String,List<String>>();
//Maps静态工厂：
public static <K,V> HashMap<K,V> newHashMap(){
  return new HashMap<K,V>();
}
//静态工厂实例化：
Map<String,List<String>> m = Maps.newHashMap();
```

- 类如果不含有共有的或后保护的构造器，就不能被子类化，
- 与其他静态方法没有区别，无法像构造器一样明确标识出来

#### 遇到多个构造器参数时要考虑用构建器

```java
public class NutritionFacts{
  private final int servingSize;
  private final int servings;
  private final int calories;
  private final int fat;
  private final int sodium;
  private final int carbohydrate;
  public static class Builder{
    //必须赋值的字段
    private final int servingSize;
    private final int servings;
    //可选字段，初始化为默认值
    private int calories = 0;
    private int fat = 0;
    private int carbohydrate = 0;
    private int sodium = 0;
    public Builder(int servingSize,int servings){
      this.servingSize = servingSize;
      this.servings = servings;
    }
    
    public Builder calories(int val){
      calories = val;
      return this;
    }
    public Builder fat(int val){
      fat = val;
      return this;
    }
    public Builder carbohydrate(int val){
      carbohydrate = val;
      return this;
    }
    public Builder sodium(int val){
      sodium = val;
      return this;
    }
    public NutritionFacts build(){
      return new NuritionFacts(this);
    }
  }
  
  private NutritionFacts(Builder builder){
    servingSize = builder.servingSize;
    servings = builder.servings;
    calories = builder.calories;
    fat = builder.fat;
    sodium = builder.sodium;
    carbohydrate = builder.carbohydrate;
  }
}

//调用
NutritionFacts cocaCola = new NutritionFacts.Builder(240,8).calories(100).sodium(35).carbohydrate(27).build();
```

#### :question: 用私有构造器或者枚举类型强化Singleton属性

####通过私有构造器强化不可实例化的能力

####　避免创建不必不要的对象

对于同时提供了静态工厂方法和构造器的不可变类，通常可以使用静态工厂方法而不是构造器，以避免创建不必要的对象。

除了重用不可变的对象之外，也可以重用那些已知不会被修改的可变对象。

```java
public class Person {
  private final Date birthDate;
  //检验是否生于１９４６年到１９６４年期间婴儿潮
  public boolean isBabyBoomer(){
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeone("GMT"));
    gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
    Date boomStart = gmtCal.getTime();
    gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
    Date boomEnd = gmtCal.getTime();
    return birthDate.compareTo(boomＳｔａrt)>=0 && birthDate.compareTo(boomEnd) < 0;
  }
}
class Person {
  private final Date birthDate;
  private static final Date BOOM_START;
  private static final Date BOOM_END;
  static {
    Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
    BOOM_START = gmtCal.getTime();
    gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
    BOOM_END = gmtCal.getTime();
  }
  public boolean isBabyBoomer() {
    return birthDate.compareTo(boomＳｔａrt)>=0 && birthDate.compareTo(boomEnd) < 0;
  }
}
```

优先使用基本类型而不是装箱基本类型，当心无意识的自动装箱

```java
public static void main(String[] args){
  Long sum = 0L; //应当声明为long sum = 0L;
  for(long i=0;i<Integer.MAX_VALUE;i++){
    sum+=i;
  }
  System.out.println(sum);
}
```

#### 消除过期的对象引用

#### 避免使用finalizer方法

finalizer方法并不能保证会被及时的执行。及时地执行终结方法是垃圾回收算法的一个主要功能，在不同的JVM实现中会大相径庭。

### 对于所有对象都通用的方法

#### 覆盖equals时请遵守通用约定

无需覆盖的情况：

- 类的每个实例本质上都是唯一的，关心活动实体而不是值
- 不关心类是否提供了“逻辑相等”的测试功能，
- 超类已经覆盖了equals，从超类继承过来的行为对于子类也是合适的
- 类是私有的或包级私有的，可以确定它的equals方法永远不会被调用

equals等价关系：

- 自反性，对于任何非null的引用值x，x.equals(x)必须返回true
- 对称性，对于任何非null的引用值x，y，当且仅当y.equals(x)返回true时，x.equals(y)必须返回true
- 传递性，对于任何非null的引用值x，y，z，如果x.equals(y) 返回true，并且y.equals(z)也返回true，那么x.equals(z)必须也返回true
- 一致性，对于任何非null的引用值x，y，只要equals的比较操作在对象中所用的信息没有被修改，多次调用x.equals(y)就会一致的返回true，或者一致的返回false。
- 对于任何非null的引用值x，x.equals(null)必须返回false。

#### 覆盖equals时总要覆盖hashCode

#### 始终要覆盖toString

#### 谨慎地覆盖clone

#### 考虑实现Comparable接口

### 类和接口

#### 使类和成员的可访问性最小化

#### 在公有类中使用访问方法而非公有域

#### 使可变性最小化

不可变类只是其实例不能被修改的类。每个实例中包含的所有信息都必须在创建该实例的时候就提供，并在对象的整个生命周期内固定不变。如String、基本类型的包装类，BigInteger和BigDecimal。

要成为不可变类：

- 不要提供任何会修改对象状态的方法
- 保证类不会被扩展
- 使所有的域都是 final的
- 使所有的域成为私有的
- :question: 确保对于任何可变组件的互斥访问

#### 复合优先于继承

在包的内部使用继承是非常安全的，在那里，子类和超类的实现都处在同一个程序员的控制之下。对于专门为了继承而设计，并且具有很好文档说明的类来说，使用继承也是非常安全的。然而，对于普通的具体类进行跨越包边界的继承，则是非常危险的。

#### 要么为继承而设计，并提供文档说明，要么就禁止继承

#### 接口优于抽象类

- 现有的类很容易被更新，以实现新的接口
- 接口是定义mixin(混合类型)的理想选择
- 接口允许我们构造非层次结构的类型框架

#### 接口只用于定义类型

