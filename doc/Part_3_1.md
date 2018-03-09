## The IoC container

### Introduction to the Spring IoC container and beans

Inversion of Control (IoC)又被称为Dependency Injection (DI)。
这是一个，对象定义它们的依赖，即要使用的对象，仅通过构造器参数，工厂方法参数或给构造后或工厂返回的对象设置实例属性，
由容器在bean创建时注入这些依赖的过程。
这个过程将bean自己管理实例或直接通过类的构造器或某种机制如Service Locator模式定位依赖反转过来。

BeanFactory接口提供了一个高级的配置机制，能够管理任何类型的对象。ApplicationContext是BeanFactory的子接口。
它更易于与Spring AOP特性集成，消息资源处理（用于国际化），事件发布，以及应用层特定上下文，如web应用所使用的WebApplicationContext。

在Spring中，那些用来形成应用骨架，并由Spring IoC容器来管理的对象称为Bean。Bean是一个实例化的，组装好的，并由Spring IoC容器管理的对象。

### Container overview

org.spring.framework.context.ApplicationContext接口代表了Spring IoC容器，它主要负责了实例化，配置，组装bean。
容器通过读取配置元信息获取如何实例化、配置和组装。配置元信息可以是XML，Java注解或Java代码。

一些SpringApplicationContext接口的实现在Spring之外。在一个独立的应用中，常创建一个ClassPathXmlApplicationContext
或FileSystemXmlApplicationContext实例。

####　Configuration metadata

Spring配置通常包含至少一个通常是多个必须由容器管理的bean定义。基于XML配置的元信息，通过顶级元素`<beans/>`中的`<bean/>`来展现这些。
Java配置通常使用由@Configuration标记的类中的@Bean注解的方法来表示。

如下示例显示了如何通过XML配置元信息：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
 <bean id="..." class="...">
 <!-- collaborators and configuration for this bean go here -->
 </bean>
 <bean id="..." class="...">
 <!-- collaborators and configuration for this bean go here -->
 </bean>
 <!-- more bean definitions go here -->
</beans>
```

属性`id`是用来标识bean个体定义的字符串。`class`使用完全限定类名标明bean的类型。
id属性的值被协作的对象引用。

#### Instantiating a container

```java
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"services.xml", "daos.xml"})
```

可以通过不同的外部资源来配置元信息，如文件系统，Java classpath等。

下面是服务层对象配置文件示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
 <!-- services -->
 <bean id="petStore" class="org.springframework.samples.jpetstore.services.PetStoreServiceImpl">
	 <property name="accountDao" ref="accountDao"/>
	 <property name="itemDao" ref="itemDao"/>
 <!-- additional collaborators and configuration for this bean go here -->
 </bean>
 <!-- more bean definitions for services go here -->
</beans>
```

下面是数据访问对象文件示例：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
 <bean id="accountDao"
 class="org.springframework.samples.jpetstore.dao.jpa.JpaAccountDao">
 <!-- additional collaborators and configuration for this bean go here -->
 </bean>
 <bean id="itemDao" class="org.springframework.samples.jpetstore.dao.jpa.JpaItemDao">
 <!-- additional collaborators and configuration for this bean go here -->
 </bean>
 <!-- more bean definitions for data access objects go here -->
</beans>
```

##### Composing XML-based configuration metadata

多个配置文件可以代表多个逻辑模块划分，可以像上面那样传入多个资源位置，也可以在一个文件中引入多个配置文件，如：

```xml
<beans>
 <import resource="services.xml"/>
 <import resource="resources/messageSource.xml"/>
 <import resource="/resources/themeSource.xml"/>
 <bean id="bean1" class="..."/>
 <bean id="bean2" class="..."/>
</beans>
```

##### The Groovy Bean Definition DSL

可以使用 Spring的Groovy Bean Definition DSL，来自Grails框架。通常配置在以".groovy"后缀的文件中，有如下格式：

```groovy
beans {
 dataSource(BasicDataSource) {
		driverClassName = "org.hsqldb.jdbcDriver"
		url = "jdbc:hsqldb:mem:grailsDB"
		username = "sa"
		password = ""
		settings = [mynew:"setting"]
	 }
	 sessionFactory(SessionFactory) {
	 	dataSource = dataSource
	 }
	 myService(MyService) {
		nestedBean = { AnotherBean bean ->
		dataSource = dataSource
	 }
 }
}
```

它同样支持导入bean配置，通过"importBeans"

#### Using the container

使用`T getBean(String name, Class<T> requiredType)`方法可以遍历容器中的bean实例。

`ApplicationContext`允许你如下读取Bean定义和访问它们：

```java
// create and configure beans
ApplicationContext context = new ClassPathXmlApplicationContext("services.xml", "daos.xml");
// retrieve configured instance
PetStoreService service = context.getBean("petStore", PetStoreService.class);
// use configured instance
List<String> userList = service.getUsernameList();
```

使用Groovy文件定义的，只需要换一个上下文实现类：

```java
ApplicationContext context = new GenericGroovyApplicationContext("services.groovy", "daos.groovy");
```

最灵活的变种是结合读取代理的`GenericApplicationContext`，对于xml配置文件：

```java
GenericApplicationContext context = new GenericApplicationContext();
new XmlBeanDefinitionReader(ctx).loadBeanDefinitions("services.xml", "daos.xml");
context.refresh();
```

对于groovy文件：

```java
GenericApplicationContext context = new GenericApplicationContext();
new GroovyBeanDefinitionReader(ctx).loadBeanDefinitions("services.groovy", "daos.groovy");
context.refresh();
```

### Bean overview

在容器内部，bean以`BeanDefinition`对象的形式存在。它包含如下信息：

- bean所定义的实现类的完整名
- bean行为配置元素，bean在容器中表现的状态（范围，生命周期，回调等）
- bean完成工作所需要的其他bean的引用
- 新创建对象的其他配置设置

作为补充，ApplicationContext实现也允许在容器外所创建的已存在的对象的注册。
这通过ApplicationContext的`getBeanFactory()`得到的BeanFactory实现`DFefaultListableBeanFactory`完成。
`DefaultListableBeanFactory`通过`registerSingleton(..)`和`registerBeanDefinition(..)`来支持这种注册。
然而，一般应用仅通过bean定义元信息来工作。

#### Naming Beans

每个bean有一个或多个标识，这些标志在容器中必须唯一。一个bean通常只有一个标识，如果有多个，一般被认为是别名。
标识可以在xml中的id或name属性定义，id是唯一的，name可以指定多个别名，通过逗号`,`，分号`;`，空格` `来分隔。
如果不提供name或id，容器会为bean生成一个唯一名称。如果想要通过name来引用bean，用ref元素或Service Locator风格查找，则必须提供name。

##### Aliasing a bean outside the bean definition

不同的组件可以对相同的依赖指定自己的别名。在xml配置中，可以使用如下标签：
```xml
<alias name="fromName" alias="toName"/>
```

如果使用Java配置，@Bean注解可以用来提供别名。

#### Instantiating beans

bean定义本质上是创建一个或多个对象的配方。当需要的时候，容器按图索骥，使用bean定义封装好的配置元信息来创建或获取实际的对象。

xml配置中，class属性在如下两种方法之一中使用：

- 容器自己直接通过反射的调用构造函数创建指定类的bean。有些类似于Java代码使用new操作符
- 包含静态工厂方法的类将被调用以创建对象。容器调用一个类的静态工厂方法创建bean的情况不太常见。通过静态工厂方法返回的对象类型可能是同一个类，也可能完全是另一个类。

内部类的类名应当类似于`com.example.Foo$Bar`，Bar是Foo的静态内部类。

##### Instantiation with a constructor

通过构造器方式创建bean，任何普通的类都能与Spring适用。即类无需实现特定接口或以某种特别的方式编码。仅仅指定bean类就足够。
根据所使用的IoC类型，可能需要一个默认（空）构造函数。

bean定义如下：

```xml
<bean id="exampleBean" class="examples.ExampleBean"/>
<bean name="anotherExample" class="examples.ExampleBeanTwo"/>
```
##### Instantiation with a static factory method

class属性指定包含静态工厂方法的类，factory-method属性指定工厂方法本身。
这个定义没有指定返回对象的类型，而是包含工厂方法的类。在下面这个例子中，createInstance()方法必须是静态方法：

```xml
<bean id="clientService" class="examples.ClientService" factory-method="createInstance"/>
```

```java
public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService() {}
    public static ClientService createInstance() {
        return clientService;
    }
}
```

##### Instantiation using an instance factory method

类似于静态工厂方法，实例工厂方法调用已存在的bean的非静态方法来创建bean。一个工厂类可以包含多个工厂方法。

```xml
<bean id="serviceLocator" class="examples.DefaultServiceLocator">
 <!-- inject any dependencies required by this locator bean -->
</bean>
<bean id="clientService"
 factory-bean="serviceLocator"
 factory-method="createClientServiceInstance"/>
<bean id="accountService"
 factory-bean="serviceLocator"
 factory-method="createAccountServiceInstance"/>
```

```java
public class DefaultServiceLocator {
    private static ClientService clientService = new ClientServiceImpl();
    private static AccountService accountService = new AccountServiceImpl();
    private DefaultServiceLocator() {}
    public ClientService createClientServiceInstance() {
        return clientService;
    }
    public AccountService createAccountServiceInstance() {
        return accountService;
    }
}
```

### Dependencies

#### Dependency Injection

依赖注入存在两种主要的变体， 构造器注入和Setter注入

##### Constructor-based dependency injection

```java
public class SimpleMovieLister {
    // the SimpleMovieLister has a dependency on a MovieFinder
    private MovieFinder movieFinder;
    // a constructor so that the Spring container can inject a MovieFinder
    public SimpleMovieLister(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
    }
    // business logic that actually uses the injected MovieFinder is omitted...
}
```

###### Constructor argument resolution

构造函数参数通过参数类型进行解析，如果bean定义的构造器参数没有潜在的歧义，那么bean定义的构造器参数的顺序，就是bean实例化过程中参数提供的顺序。

```java
package x.y;
public class Foo {
    public Foo(Bar bar, Baz baz) {
        // ...
    }
}
```

构造器参数类型没有歧义，下面的配置可以正常工作，无需明确的指定参数类型的顺序或类型

```xml
<beans>
    <bean id="foo" class="x.y.Foo">
        <constructor-arg ref="bar"/>
        <constructor-arg ref="baz"/>
    </bean>
    <bean id="bar" class="x.y.Bar"/>
    <bean id="baz" class="x.y.Baz"/>
</beans>
```

而如下类：

```java
package examples;
public class ExampleBean {
    // Number of years to calculate the Ultimate Answer
    private int years;
    // The Answer to Life, the Universe, and Everything
    private String ultimateAnswer;
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```

Spring无法判断值的类型，需要明确指定：

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg type="int" value="7500000"/>
    <constructor-arg type="java.lang.String" value="42"/>
</bean>
```

或者使用`index`属性：

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg index="0" value="7500000"/>
    <constructor-arg index="1" value="42"/>
</bean>
```

通过index可以对相同类型进行指定。0是索引的开始。
还可以通过指定名称来消除歧义：

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <constructor-arg name="years" value="7500000"/>
    <constructor-arg name="ultimateAnswer" value="42"/>
</bean>
```

需要知道的是，编译需要开启debug，才能让Spring通过构造器查找参数名称。如果无法开启或者不想开启debug，
则可以使用`@ConstructorProperties`JDK注解来显式指定，示例类可能会类似于下面这样：

```java
package examples;
public class ExampleBean {
    // Fields omitted
    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }
}
```

###### Setter-based dependency injection

Setter注入由容器在调用无参构造函数或无参静态工厂方法实例化bean后调用setter方法来完成

```java
public class SimpleMovieLister {
    // the SimpleMovieLister has a dependency on the MovieFinder
    private MovieFinder movieFinder;
    // a setter method so that the Spring container can inject a MovieFinder
    public void setMovieFinder(MovieFinder movieFinder) {
        this.movieFinder = movieFinder;
    }
    // business logic that actually uses the injected MovieFinder is omitted...
}
```

混用构造函数注入强制依赖和setter注入可选依赖，是一个很好的经验法则。
setter方法上添加@Required注解，则也会成为强制依赖。
构造器注入，能够使应用程序组件成为不可变对象，并确保依赖关系不是null。
而且，构造器注入的组件总是返回客户端处于完全初始化状态的代码。另一方面，大量的构造器参数意味着不好的代码，这意味着这个类可能负载太多，应当重构进行分离。
setter注入应当主要引用于有合理默认值的可选依赖，否则使用依赖的任何地方都应当进行非空检查。
使用setter注入的一个好处是，类的对象能够在之后重配置或重注入。

###### Dependency resolution process

81