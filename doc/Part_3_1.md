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