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

#### Using depends-on

#### Lazy-initialized beans

#### Autowiring collaborators

##### Limitations and disadvantages of autowiring

##### Excluding a bean from autowiring

#### Method injection

##### Lookup method injection

##### Arbitary method replacement

### Bean scopes

