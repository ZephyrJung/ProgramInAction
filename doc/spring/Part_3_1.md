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

##### Setter-based dependency injection

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

##### Dependency resolution process

容器执行bean依赖的解析过程如下：

- ApplicationContext通过描述了所有bean的配置元信息创建并初始化。
- 对于每个bean，它的依赖通过属性，参数（构造器参数，静态工厂方法参数）表现出来。这些依赖在bean真正创建后提供给bean
- 每个属性或构造器参数应当是值的真正定义，或容器中另一个bean的引用。
- 每个属性或构造器参数的值将从其特定格式转换为实际类型。默认情况下，Spring能够将值从string类型转换为任意内置类型，如int,long,String,boolean等。

Spring容器在容器创建时便校验每个bean的配置。bean属性本身只有在bean真正创建的时候才会赋值。
被设置为预先实例化的单例模式的bean在容器创建时就会创建。否则bean只有在被请求时才会创建。
一个bean的创建可能引起一个bean图的创建，因为这些bean的依赖，和依赖的依赖都会创建并分配。

> Circular dependencies
> 如果主要使用构造器注入，则可能创建一个无法解析的循环依赖场景
> 如A类依赖B类作为构造器参数，B依赖A作为构造器参数，此时就会引起BeanCurrentlyInCreationException
> 一种解决办法是通过setter注入，但并不建议如此。

Spring在创建Bean是尽可能迟的处理依赖关系。这意味着Spring容器可能正确的加载了，但在之后请求对象时，如果依赖有缺失或错误的参数仍然会产生异常。
这种延迟某些配置问题可见性的可能，正是ApplicationContext实现使单例bean默认预先实例化的原因。
你仍然可以用懒加载（lazy-initialize）覆盖这个默认预加载行为。

如果没有循环依赖，则当一个或多个协作bean在注入到依赖的bean之前，每个协作bean已经完全配置好了。

##### Examples of dependency injection

下面是基于xml配置的setter注入

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- setter injection using the nested ref element -->
    <property name="beanOne">
    <ref bean="anotherExampleBean"/>
    </property>
    <!-- setter injection using the neater ref attribute -->
    <property name="beanTwo" ref="yetAnotherBean"/>
    <property name="integerProperty" value="1"/>
</bean>
<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

```java
public class ExampleBean {
    private AnotherBean beanOne;
    private YetAnotherBean beanTwo;
    private int i;
    public void setBeanOne(AnotherBean beanOne) {
        this.beanOne = beanOne;
    }
    public void setBeanTwo(YetAnotherBean beanTwo) {
        this.beanTwo = beanTwo;
    }
    public void setIntegerProperty(int i) {
        this.i = i;
    }
}
```

下面是使用构造器注入

```xml
<bean id="exampleBean" class="examples.ExampleBean">
    <!-- constructor injection using the nested ref element -->
    <constructor-arg>
    <ref bean="anotherExampleBean"/>
    </constructor-arg>
    <!-- constructor injection using the neater ref attribute -->
    <constructor-arg ref="yetAnotherBean"/>
    <constructor-arg type="int" value="1"/>
</bean>
<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

```java
public class ExampleBean {
    private AnotherBean beanOne;
    private YetAnotherBean beanTwo;
    private int i;
    public ExampleBean(
    AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
    this.beanOne = anotherBean;
    this.beanTwo = yetAnotherBean;
    this.i = i;
    }
}
```

下面是通过静态工厂方法返回对象的实例

```xml
<bean id="exampleBean" class="examples.ExampleBean" factory-method="createInstance">
    <constructor-arg ref="anotherExampleBean"/>
    <constructor-arg ref="yetAnotherBean"/>
    <constructor-arg value="1"/>
</bean>
<bean id="anotherExampleBean" class="examples.AnotherBean"/>
<bean id="yetAnotherBean" class="examples.YetAnotherBean"/>
```

```java
public class ExampleBean {
    // a private constructor
    private ExampleBean(...) {
        ...
    }
    // a static factory method; the arguments to this method can be
    // considered the dependencies of the bean that is returned,
    // regardless of how those arguments are actually used.
    public static ExampleBean createInstance (
        AnotherBean anotherBean, YetAnotherBean yetAnotherBean, int i) {
        ExampleBean eb = new ExampleBean (...);
        // some other operations...
        return eb;
    }
}
```


#### Dependencies and configuration in detail

##### Straight values (primitives, Strings, and so on)

Spring使用转换服务来把值从String转换为实际需要的类型

```xml
<bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    <!-- results in a setDriverClassName(String) call -->
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="root"/>
    <property name="password" value="masterkaoli"/>
</bean>
```

使用p命名空间可以使XML配置更简洁：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="myDataSource" class="org.apache.commons.dbcp.BasicDataSource"
    destroy-method="close"
    p:driverClassName="com.mysql.jdbc.Driver"
    p:url="jdbc:mysql://localhost:3306/mydb"
    p:username="root"
    p:password="masterkaoli"/>
</beans>
```

还可以如下配置一个java.util.Properties实例：

```xml
<bean id="mappings"
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <!-- typed as a java.util.Properties -->
    <property name="properties">
    <value>
    jdbc.driver.className=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/mydb
    </value>
    </property>
</bean>
```

Spring容器通过JavaBeans的`PropertyEditor`机制转换<value/>标签内的文本为java.util.Properties实例。

###### The idref element

```xml
<bean id="theTargetBean" class="..."/>
<bean id="theClientBean" class="...">
    <property name="targetName">
        <idref bean="theTargetBean" />
    </property>
</bean>
```

上面的定义和下面在运行时是相同的：

```xml
<bean id="theTargetBean" class="..." />
<bean id="client" class="...">
    <property name="targetName" value="theTargetBean" />
</bean>
```

第一种形式比第二种形式要好，因为使用idref标签允许容器在部署时验证引用的命名bean实际存在。 
在第二种变体中，不会对传递给客户机bean的targetName属性的值执行验证。 
当客户端bean实际实例化时，才会发现错别字（最致命的可能结果）。 
如果客户端bean是原型bean，则只能在部署容器后很长时间才能发现该错字和产生的异常。

##### References to other beans (collaborators)

ref标签指定属性的值设置为容器管理的另一个bean的引用。
引用的范围和验证取决于通过bean，local或parent属性指定
属性的值可以与目标bean的id属性相同，或者是name属性中的一个值。

通过`<ref/>`标签的bean属性指定目标bean是最常见的形式，允许创建对同一容器或父容器中的任何bean的引用，不论是否在同一xml中。

```xml
<ref bean = "someBean"/>
```

通过parent属性指定的目标bean必须位于当前bean的父容器中。主要在具有层次结构的容器中使用，以包装父容器bean中存在的bean作为代理。

```xml
<!-- in the parent context -->
<bean id="accountService" class="com.foo.SimpleAccountService">
    <!-- insert dependencies as required as here -->
</bean>
```

```xml
<!-- in the child (descendant) context -->
<!-- bean name is the same as the parent bean -->
<bean id="accountService" 
    class="org.springframework.aop.framework.ProxyFactoryBean">
    <property name="target">
        <ref parent="accountService"/> <!-- notice how we refer to the parent bean -->
    </property>
    <!-- insert other configuration and dependencies as required here -->
</bean>
```

local属性在4.0规范中不在支持，替换为bean即可。

##### inner beans

在`<property/>`或`<constructor-arg/>`中定义的`<bean/>`就是所谓的内部bean

```xml
<bean id="outer" class="...">
    <!-- instead of using a reference to a target bean, simply define the target bean inline -->
    <property name="target">
        <bean class="com.example.Person"> <!-- this is the inner bean -->
            <property name="name" value="Fiona Apple"/>
            <property name="age" value="25"/>
        </bean>
    </property>
</bean>
```

内部bean的定义不需要标识，始终匿名，始终由使用的外部bean创建，不可能将内部bean注入到其他bean，也不能独立访问。

##### Collections

在`<list/>`，`<set/>`，`<map/>`和`<props/>`标签内，可以相应的设置Java集合类型List，Set，Map和Properties。

```xml
<bean id="moreComplexObject" class="example.ComplexObject">
    <!-- results in a setAdminEmails(java.util.Properties) call -->
    <property name="adminEmails">
        <props>
            <prop key="administrator">administrator@example.org</prop>
            <prop key="support">support@example.org</prop>
            <prop key="development">development@example.org</prop>
        </props>
    </property>
    <!-- results in a setSomeList(java.util.List) call -->
    <property name="someList">
        <list>
            <value>a list element followed by a reference</value>
            <ref bean="myDataSource" />
        </list>
    </property>
    <!-- results in a setSomeMap(java.util.Map) call -->
    <property name="someMap">
        <map>
            <entry key="an entry" value="just some string"/>
            <entry key ="a ref" value-ref="myDataSource"/>
        </map>
    </property>
    <!-- results in a setSomeSet(java.util.Set) call -->
    <property name="someSet">
        <set>
            <value>just some string</value>
            <ref bean="myDataSource" />
        </set>
    </property>
</bean>
```

对于map键或值，或set的值，也可以同样是如下元素：

`bean | ref | idref | list | set | map | props | value | null`

###### Collection merging

```xml
<beans>
    <bean id="parent" abstract="true" class="example.ComplexObject">
        <property name="adminEmails">
            <props>
                <prop key="administrator">administrator@example.com</prop>
                <prop key="support">support@example.com</prop>
            </props>
        </property>
    </bean>
    <bean id="child" parent="parent">
        <property name="adminEmails">
            <!-- the merge is specified on the child collection definition -->
            <props merge="true">
                <prop key="sales">sales@example.com</prop>
                <prop key="support">support@example.co.uk</prop>
            </props>
        </property>
    </bean>
<beans>
```

子属性将集成父属性的所有值，并覆盖已有内容。
合并的行为在list，map，set集合类型上表现相似。
list集合的顺序在继承后，父项的值都会放到子项之前

###### Limitations of collection merging

无法合并不同类型的集合，否则将抛出异常。merge属性必须定义在继承的低级的子定义中。

###### Strongly-typed collection

如果集合有类型限定，Spring在注入时自动转换为相应的类型。

```java
public class Foo {
    private Map<String, Float> accounts;
        public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }
}
```

```xml
<beans>
    <bean id="foo" class="x.y.Foo">
        <property name="accounts">
            <map>
                <entry key="one" value="9.99"/>
                <entry key="two" value="2.75"/>
                <entry key="six" value="3.99"/>
            </map>
        </property>
    </bean>
</beans>
```

##### Null and empty string values

Spring将空参数作为空字符串处理，使用`<null/>`标签来处理null值。

```xml
<bean class="ExampleBean">
    <property name="email" value=""/>
    <property name="phone">
        <null/>
    </property>
</bean>
```

##### XML shortcut with the p-namespace

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:p="http://www.springframework.org/schema/p"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean name="john-classic" class="com.example.Person">
        <property name="name" value="John Doe"/>
        <property name="spouse" ref="jane"/>
    </bean>
    <bean name="john-modern" class="com.example.Person"
        p:name="John Doe"
        p:spouse-ref="jane"/>
    <bean name="jane" class="com.example.Person">
        <property name="name" value="Jane Doe"/>
    </bean>
</beans>
```

上面两个bean都定义了两个属性，其中一个是对另一个bean的引用。
可以看到，p后面制定了name的值，对于引用属性，-前面是name值，ref表示引用。

##### XML shortcut with the c-namespace

c命名空间标签主要用于简化构造器参数的配置

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:c="http://www.springframework.org/schema/c"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="bar" class="x.y.Bar"/>
    <bean id="baz" class="x.y.Baz"/>
    <!-- traditional declaration -->
    <bean id="foo" class="x.y.Foo">
        <constructor-arg ref="bar"/>
        <constructor-arg ref="baz"/>
        <constructor-arg value="foo@bar.com"/>
    </bean>
    <!-- c-namespace declaration -->
    <bean id="foo" class="x.y.Foo" c:bar-ref="bar" c:baz-ref="baz" c:email="foo@bar.com"/>
</beans>
```
在少数情况下无法得到构造器名称，可以降级使用参数索引：

```xml
<!-- c-namespace index declaration -->
<bean id="foo" class="x.y.Foo" c:_0-ref="bar" c:_1-ref="baz"/>
```

##### Compound property names

如果对象调用路径上的对象均不为null，则可以使用复合名称：

```xml
<bean id="foo" class="foo.Bar">
    <property name="fred.bob.sammy" value="123" />
</bean>
```

foo有个fred属性，fred有个bob属性，bob有个sammy属性，只要fred，bob不为空，就可以为sammy设置值123.

#### Using depends-on

使用depends-on属性可以严格强制一个或多个bean在使用的bean之前初始化。

```xml
<bean id="beanOne" class="ExampleBean" depends-on="manager"/>
<bean id="manager" class="ManagerBean" />
```

#### Lazy-initialized beans

延迟加载：

```xml
<bean id="lazy" class="com.foo.ExpensiveToCreateBean" lazy-init="true"/>
<bean name="not.lazy" class="com.foo.AnotherBean"/>
```

如果设置延迟加载的bean是另一个非延迟加载bean的依赖，则仍然会在容器初始化时实例化。
可以设置默认值为延迟加载：

```xml
<beans default-lazy-init="true">
    <!-- no beans will be pre-instantiated... -->
</beans>
```

#### Autowiring collaborators

bean标签上设置autowire属性，有4种模式：

- no：默认值，无自动装配。Bean引用必须通过ref元素。对于较大的部署，建议不要开启自动，明确的配置易于控制，在一定程度上反映了系统结构。
- byName：通过属性名进行装配。
- byType：通过属性类型进行装配。如果有多个相同类型的bean，会抛出异常。如果没有，则无注入。
- constructor：类似于byType，但用于构造器参数。

##### Limitations and disadvantages of autowiring

- 显示的属性和构造器参数的依赖会覆盖自动装配。不能通过自动装配注入简单属性，如基本数据类型，String和Class（以及简单属性的数组）。这个设计之初的限制。
- 自动装配不如直接指定明确。尽管Spring十分小心的避免可能导致无法预料结果的歧义，然而Spring管理的对象关系无法书面化的明显了。
- 一些Spring容器的工具可能无法生成装配信息的文档了。
- 对于只需要但一值的依赖，如果有多个bean通过自动装配匹配到，会抛出异常

对于后面的场景，可以有如下选择：

- 放弃自动装配，使用显式指定
- 在bean定义上设置`autowire-candidate`属性值为false，避免自动装配
- 指定bean为首要候选者，设置`primary`属性值为true
- 通过基于注释的配置，实现更加精细的控制，见后面章节。

##### Excluding a bean from autowiring

autowire-candidate = false

可以在beans标签上设置`default-autowire-candidate`的值指定匹配的模式
如将autowire候选者限制为名称以Repository结尾的bean，则指定值为`*Repository`
多种模式用逗号分隔。
这个优先级要低于bean的autowire-candidate属性

这些属性使bean本身不能作为自动装配的bean，但不意味着bean本身不能通过自动装配注入依赖。


#### Method injection

单例bean注入另一个单例bean，或者非单例bean注入另一个非单例bean，可以配置为一个依赖。
然而当一个非单例bean注入到一个单例bean时，由于单例bean在容器创建时只有一次实例化的机会，容器不可能每次都能在单例bean需要的时候有对应的非单例bean

一个解决办法是放弃一些控制反转。可以通过实现`ApplicationContextAware`接口来使得单例bean被容器所感知，并在需要的时候调用getBean方法获取需要的实例。
下面是这个方式的一个例子：

```java
// a class that uses a stateful Command-style class to perform some processing
package fiona.apple;
// Spring-API imports
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class CommandManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    public Object process(Map commandState) {
        // grab a new instance of the appropriate Command
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }
    protected Command createCommand() {
        // notice the Spring API dependency!
        return this.applicationContext.getBean("command", Command.class);
    }
    public void setApplicationContext(
        ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
```

这样的代码不是很好，这使得业务代码耦合到了Spring框架中。

##### Lookup method injection

查找方法注入是容器覆盖容器管理的bean上方法的能力，以返回容器中另一个命名bean的查找结果。 
查找通常包含一个原型bean，如前一节所述。 
Spring Framework通过使用CGLIB库中的字节码生成来动态生成覆盖该方法的子类，从而实现了此方法注入。

- 为了使动态子类工作，Spring bean容器要子类化的类不能是final，要覆盖的方法也不能是final
- 对具有抽象方法的类进行单元测试需要自己对该类子类化并提供抽象方法的基础实现
- 组件扫描也需要具体的方法，这需要具体的类来实现。
- 另一个关键的限制是查找方法不适用于工厂方法，特别是不用于配置类中的@Bean方法，因为在这种情况下容器不负责创建实例，因此不能在运行中创建运行时生成的子类。

上面的`CommandManager`代码中，它将不会有Spring依赖，Spring容器将动态实现`createCommand()`方法：

```java
package fiona.apple;
// no more Spring imports!
public abstract class CommandManager {
    public Object process(Object commandState) {
        // grab a new instance of the appropriate Command interface
        Command command = createCommand();
        // set the state on the (hopefully brand new) Command instance
        command.setState(commandState);
        return command.execute();
    }
    // okay... but where is the implementation of this method?
    protected abstract Command createCommand();
}
```

要进行方法注入的方法应当有如下形式的签名：

```java
<public|protected> [abstract] <return-type> theMethodName(no-arguments);
```

如果方法是抽象方法，动态生成的子类将实现方法。否则，动态生成子类将覆盖原类中具体的方法，例如：

```xml
<!-- a stateful bean deployed as a prototype (non-singleton) -->
<bean id="myCommand" class="fiona.apple.AsyncCommand" scope="prototype">
    <!-- inject dependencies here as required -->
</bean>
<!-- commandProcessor uses statefulCommandHelper -->
<bean id="commandManager" class="fiona.apple.CommandManager">
    <lookup-method name="createCommand" bean="myCommand"/>
</bean>
```

commandManager在需要myCommand的新实例时，调用起自己的方法createCommand。
如果需要，必须小心的将myCommand设为prototype，如果是singleton，则每次返回的都是myCommand的相同实例。

作为可选项，在基于注解的配置，可以通过@Lookup注解声明查找方法：

```java
public abstract class CommandManager {
    public Object process(Object commandState) {
        Command command = createCommand();
        command.setState(commandState);
        return command.execute();
    }
    @Lookup("myCommand")
    protected abstract Command createCommand();
}
```

或者，更习惯上，依赖目标bean针对查找方法声明返回类型进行解析：

```java
public abstract class CommandManager {
    public Object process(Object commandState) {
        MyCommand command = createCommand();
        command.setState(commandState);
        return command.execute();
    }
    @Lookup
    protected abstract MyCommand createCommand();
}
```


##### Arbitrary method replacement

略

### Bean scopes

- singleton：（默认）限定一个bean定义的范围是在一个Spring IoC容器中，只有一个对象实例。
- prototype：限定一个bean定义的范围可以有任意数量的对象实例。
- request：限定一个bean定义的范围在一个http请求的生命周期内；这意味着每个HTTP请求有其单独的实例。仅在web相关的Spring上下文生效
- session：限定一个bean定义的范围在一个http会话的生命周期内；仅在web相关的Spring上下文生效
- globalSession：在全局HTTP会话的生命周期范围内定义一个单一的bean定义。通常仅在Portlet上下文中使用时才有效。只有在Web感知的Spring ApplicationContext的上下文中才有效。
- application：
- websocket

#### The singleton scope

只管理单个bean的一个共享实例，并且具有与该bean定义匹配的id或id的bean的所有请求都会导致Spring容器返回一个特定的bean实例。
Spring的单例bean与设计模式中的单例模式有所区别。设计模式通过编码保证在类加载器中只能有一个特定的类的实例。Spring则针对于每一个容器的每一个Bean
singleton是默认作用域。

#### The prototype scope

作为规范，对所有有状态的bean使用prototype，对无状态的bean使用singleton。
prototype的定义如下：
```xml
<bean id="accountService" class="com.foo.DefaultAccountService" scope="prototype"/>
```
与其他范围相比，Spring不管理prototype bean的完整生命周期：容器实例化，配置并以其他方式组装prototype对象，并将其交给客户端，而不再记录该prototype实例。 
因此，尽管不论什么范围，所有对象上初始化生命周期的回调方法都会调用，但在prototype的情况下，不调用配置的销毁生命周期回调。 
客户端代码必须清理prototype范围的对象并释放原型bean持有的昂贵资源。 
为了让Spring容器释放prototype范围bean所拥有的资源，可以尝试使用一个自定义bean后处理器，该后处理器保存对需要清理的bean的引用。

#### Singleton beans with prototype-bean dependencies

无法为单例bean注入prototype的bean依赖，因为单例bean只在容器初始化的时候解决并注入依赖一次。
如果在运行时需要多次新的实例，参见“Method injection”

#### Request, session, global session, application, and WebSocket scopes

`request`，`session`，`globalSession`，`application`和`weboskcet`只有在有web能力的Spring上下文实现中有效
如XmlWebApplicationContext，否则会抛出关于未知bean作用域的IllegalStateException异常。

##### Initial Web configuration

在支持这些作用域之前需要一些小的配置（singleton和prototype则不需要）
如何完成这些初始化取决于你所使用的Servlet环境。

如果在Spring Web MVC下访问这些作用域的bean，在Spring的`DispatcherServlet`或`DispatcherPorlet`处理的请求中，不需要特殊的设置。
他们已经暴露了所有相关的状态。

如果使用了一个Servlet 2.5的web容器，在`DispatcherServlet`以外处理请求（如JSF或Struts），你需要注册`org.springframework.web.context.request.RequestContextListenerServletRequestListener`
在Servlet 3.0+的容器中，可以通过`WebApplicationInitializer`接口编程实现。
作为可选项，对于以前的容器，在web.xml文件中添加如下声明：

```xml
<web-app>
    ...
    <listener>
        <listener-class>
            org.springframework.web.context.request.RequestContextListener
        </listener-class>
    </listener>
    ...
</web-app>
```

如果监听器设置有问题，考虑使用Spring的`RequestContextFilter`。过滤器映射取决于所处的web应用配置，因此要做相应的修改。

```xml
<web-app>
    ...
    <filter>
        <filter-name>requestContextFilter</filter-name>
        <filter-class>org.springframework.web.filter.RequestContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>requestContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    ...
</web-app>
```

DispatcherServlet, RequestContextListener和RequestContextFilter都做了相同的事情，
即将HTTP请求对象绑定到服务这些请求的线程上。这使得request和session范围的bean能够在调用链上下沉。

##### Request Scope

假设有下面这样的XML文件配置bean：

```xml
<bean id="loginAction" class="com.foo.LoginAction" scope="request"/>
```

对于每个HTTP请求，Spring容器都会创建一个LoginAction的新实例。你可以任意修改实例内部的状态而不用担心影响其他loginAction的实例。
当请求完成时，作用在这个请求的bean将被丢弃。

如果使用Java注解方式配置，`@RequestScope`注解可以用来指定一个component为`reqest`作用域

```java
@RequestScope
@Component
public class LoginAction {
    //...
}
```

##### Session scope

```xml
<bean id="userPreferences" class="com.foo.UserPreferences" scope="session"/>
```

```java
@SessionScope
@Component
public class UserPreferences {
    // ...
}
```

##### Global session scope

```xml
<bean id="userPreferences" class="com.foo.UserPreferences" scope="globalSession"/>
```

globalSession作用域类似于HTTP session作用域，只应用在基于portlet的web应用上下文中。
Portlet规范定义了全局会话的概念：在构成单个Portlet Web应用程序的所有Portlet之间共享。

如果你在标准Servlet容器中对一个或多个bean定义了globalSession作用域，标准HTTP会话作用域将被使用，不会引起错误。

##### Application scope

```xml
<bean id="appPreferences" class="com.foo.AppPreferences" scope="application"/>
```

在整个Web应用中，Spring容器通过appPreferences Bean定义创建一次AppPreferences的新实例。
这意味着appPreferences是作用域在ServletContext级别的bean，存储为一个ServletContext属性。
这与Spring的单例bean类似，但有两个重要的区别：

- 它是在每个ServletContext下的单个bean而非Spring的ApplicationContext
- 它实际上已经暴露出来，作为一个ServletContext的可见属性

使用注解定义如下：

```java
@ApplicationScope
@Component
public class AppPreferences {
    // ...
}
```

##### Scoped beans as dependencies

如果想要注入如一个HTTP请求作用域的bean到一个作用于更长的bean中，你可以选择注入一个AOP代理作为替代。
这意味着，你需要注入一个代理对象来暴露那个作用域相同的公共接口，但也可以从相关作用域（如HTTP请求）中检索实际目标对象，并将方法调用委托给实际对象。

您也可以在作用域为singleton的bean之间使用`<aop：scoped-proxy />`，然后引用通过一个可序列化的中间代理，因此可以在反序列化中重新获得目标单例bean。
当针对范围原型的bean声明`<aop：scoped-proxy/>`时，共享代理上的每个方法调用都将导致创建一个新的目标实例，然后将该调用转发给该实例。

此外，作用域化代理不是以生命周期安全的方式从较短范围访问Bean的唯一方式。 
您也可以简单地将注入点（即构造函数/setter参数或自动注入字段）声明为`ObjectFactory<MyTargetBean>`，
从而允许getObject()调用在每次需要时按需检索当前实例 - 而不必保留实例或单独存储它。

JSR-330变体称为Provider，与`Provider<MyTargetBean>`一起使用声明和每个检索尝试的相应get()调用。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:aop="http://www.springframework.org/schema/aop"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd
 http://www.springframework.org/schema/aop
 http://www.springframework.org/schema/aop/spring-aop.xsd">
 <!-- an HTTP Session-scoped bean exposed as a proxy -->
    <bean id="userPreferences" class="com.foo.UserPreferences" scope="session">
        <!-- instructs the container to proxy the surrounding bean -->
        <aop:scoped-proxy/>
    </bean>
    <!-- a singleton-scoped bean injected with a proxy to the above bean -->
    <bean id="userService" class="com.foo.SimpleUserService">
        <!-- a reference to the proxied userPreferences bean -->
        <property name="userPreferences" ref="userPreferences"/>
    </bean>
</beans>
```

###### Choosing the type of proxy to create

默认情况下，使用`<aop:scoped-proxy/>`元素创建bean代理时，一个基于CGLIB的类代理被创建。
CGLIB代理仅拦截公共方法调用，非公共方法不会被代理到实际作用域的目标对象

作为可选，也可以配置容器创建一个标准的JDK接口代理，只要设置`proxy-target-class`属性为false。
这同时也意味着作用域bean至少有一个接口实现，更多细节见后。

```xml
<!-- DefaultUserPreferences implements the UserPreferences interface -->
<bean id="userPreferences" class="com.foo.DefaultUserPreferences" scope="session">
    <aop:scoped-proxy proxy-target-class="false"/>
</bean>
<bean id="userManager" class="com.foo.UserManager">
    <property name="userPreferences" ref="userPreferences"/>
</bean>
```

#### Custom scopes

bean的作用域机制是可扩展的。可以定义自己的作用域，甚至覆盖已有的作用域，尽管这样是不好的实践。无法覆盖内置的singleton和prototype作用域。

##### Creating a custom scope

要集成自定义作用域到Spring容器中，需要实现接口`org.springframework.beans.factory.config.Scope`。

- Object get(String name, ObjectFactory objectFactory) 返回相应作用域的bean
- Object remove(String name) 从作用域中移除对象
- void registerDestructionCallback(String name,Runnable destructionCallback) 注册作用域销毁或作用域内的对象销毁时应当执行的回调
- String getConversationId() 返回作用域的对话标识符

##### Using a custom scope

- void registerScope(String scopeName,Scope scope) 在Spring容器中注册自定义作用域

这个方法在ConfigurableBeanFactory接口中声明，大部分ApplicationContext实现都存在。

下面以`SimpleThreadScope`为例，它包含在Spring中，但默认没有注册。

```java
Scope threadScope = new SimpleThreadScope();
beanFactory.registerScope("thread", threadScope);
```

然后即可使用这个作用域：

```xml
<bean id="..." class="..." scope="thread">
```

可以不通过代码来注册：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:aop="http://www.springframework.org/schema/aop"
 xsi:schemaLocation="http://www.springframework.org/schema/beans
 http://www.springframework.org/schema/beans/spring-beans.xsd
 http://www.springframework.org/schema/aop
 http://www.springframework.org/schema/aop/spring-aop.xsd">
    <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">
        <property name="scopes">
            <map>
                <entry key="thread">
                    <bean class="org.springframework.context.support.SimpleThreadScope"/>
                </entry>
            </map>
        </property>
    </bean>
    <bean id="bar" class="x.y.Bar" scope="thread">
        <property name="name" value="Rick"/>
        <aop:scoped-proxy/>
    </bean>
    <bean id="foo" class="x.y.Foo">
        <property name="bar" ref="bar"/>
    </bean>
</beans>
```

### Customizing the nature of a bean

#### Lifecycle callbacks

##### Initialization callbacks

##### Destruction callbacks

##### Default initialization and destroy methods

##### Combining lifecycle mechanisms

##### Startup and shutdown callbacks

##### Shutting down the Spring IoC container gracefully in non-web applications

#### ApplicationContextAware and BeanNameAware

#### Other Aware interfaces

### Bean definition inheritance

### Container Extension Points

#### Customizing beans using a BeanPostProcessor

##### Example: Hello World, BeanPostProcessor-style

##### Example: The RequiredAnnotationBeanPostProcessor

#### Customizing configuration metadata with a BeanFactoryPostProcessor

##### Example: the Class name substitution PropertyPlaceholderConfigurer

##### Example: the PropertyOverrideConfigurer

#### Customizing instantiation logic with a FactoryBean

### Annotation-based container configuration

#### @Required

#### @Autowired

#### Fine-tuning annotation-based autowiring with @Primary

#### Fine-tuning annotation-based autowiring with qualifiers

#### Using generics as autowiring qualifiers

#### CustomAutowireConfigurer

#### @Resource

#### PostConstruct and @PreDestroy

### Classpath scanning and managed components

#### @Component and further stereotype annotations

#### Meta-annotations

#### Automatically detecting classes and registering bean definitions

#### Using filters to customize scanning

#### Defining bean metadata within components

#### Naming autodetected components

#### Providing a scope for autodetected components

#### Providing a qualifier metadata with annotations

### Using JSR 330 Standard Annotations

#### Dependency Injection with @Inject and @Named

#### @Named and @ManagedBean: standard equivalents to the @Component annotation

#### Limitations of JSR-330 standard annotations

### Java-based container configuration

#### Basic concepts: @Bean and @Configuration

#### Instantiating the Spring container using AnnotationConfigApplicationContext Simple construction

##### Simple construction

##### Building the container programmatically using register(Class<?>...)

##### Enabling component scanning with scan(String...)

##### Support for web applications with AnnotationConfigWebApplicationContext

#### Using the @Bean annotation

##### Declaring a bean

##### Bean dependencies

##### Receiving lifecycle callbacks

##### Specifying bean scope

##### Customizing bean naming

##### Bean aliasing

##### Bean description

#### Using the @Configuration annotation

##### Injecting inter-bean dependencies

##### Lookup method injection

##### Further information about how Java-based configuration works internally

#### Composing Java-based configurations

##### Using the @Import annotation

##### Conditionally include @Configuration classes or @Bean methods

##### Combining Java and XML configuration

### Environment abstraction

#### Bean definition profiles

##### @Profile

#### XML bean definition profiles

##### Activating a profile

##### Default profile

#### PropertySource abstraction

#### @PropertySource

#### Placeholder resolution in statements

### Registering a LoadTimeWeaver

### Additional Capabilities of the ApplicationContext

#### Internationalization using MessageSource

#### Standard and Custom Events

##### Annotation-based Event Listeners

##### Asynchronous Listeners

##### Ordering Listeners

##### Generic Events

#### Convenient access to low-level resources

#### Convenient ApplicationContext instantiation for web applications

#### Deploying a Spring ApplicationContext as a Java EE RAR file

### The BeanFactory

#### BeanFactory or ApplicationContext

#### Glue code and the evil singleton

## Resources

### Introduction

### The Resource interface

### Built-in Resource implementations

#### UrlResource

#### ClassPathResource

#### FileSystemResource

#### ServletContextResource

#### Input StreamResource

#### ByteArrayResource

### The ResourceLoader

### The ResourceLoaderAware interface

### Resources as dependencies

### Application contexts and Resource paths

#### Constructing application contexts

##### Constructing ClassPathXmlApplicationContext instances - shortcuts

#### Wildcards in application context constructor resource paths

##### Ant-style Patterns

##### The Classpath*: portability classpath*: prefix

##### Other notes relating to wildcards

#### FileSystemResource caveats

## Validation, Data Binding, and Type Conversion

## Spring Expression Language (SpEL)

## Aspect Oriented Programming with Spring

## Spring AOP APIs