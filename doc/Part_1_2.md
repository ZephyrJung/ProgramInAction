## Introduction to the Spring Framework

### Dependency Injection and Inversion of Control

Java应用中的对象相互依赖，Java平台本身缺乏组织基本构建模块的能力，虽然有如Factory，AbstractFactory，Builder，Decorator等设计模式及服务定位器
但是他需要自己去实现。Spring框架的IoC正是为了解决这个问题

### Modules

```text
    +---------------------------+ +---------------------------+
    |  Data Access/Integration  | |           Web             |
    | +----------+ +----------+ | |                           |
    | |   JDBC   | |    ORM   | | | +-----------+ +---------+ |
    | +----------+ +----------+ | | | WebSocket | | Servlet | |
    | +----------+ +----------+ | | +-----------+ +---------+ |
    | |    OXM   | |    JMS   | | |                           |
    | +----------+ +----------+ | | +---------+   +---------+ |
    | +-----------------------+ | | |   Web   |   | Portlet | |
    | |     Transactions      | | | +-------=-+   +---------+ |
    | +-----------------------+ | |                           |
    +---------------------------+ +---------------------------+
    +-----+   +---------+   +-----------------+   +-----------+
    | AOP |   | Aspects |   | Instrumentation |   | Messaging |
    +-----+   +---------+   +-----------------+   +-----------+
    +---------------------------------------------------------+
    |                       Core Container                    |
    |  +---------+  +----------+  +-----------+  +----------+ |
    |  |  Beans  |  |   Core   |  |  Context  |  |   SpEL   | |
    |  +---------+  +----------+  +-----------+  +----------+ |
    +---------------------------------------------------------+
    +---------------------------------------------------------+
    |                           Test                          |
    +---------------------------------------------------------+
```

#### Core Container

spring-core和spring-beans组成了框架的基础，包含IoC（控制反转）和DI（依赖注入）。BeanFactory是工程模式的一个复杂实现

spring-context建立在core和beans提供的坚实基础之上。它继承了Beans模块的功能，并增加了对它的国际化（如使用资源包），事件传播，资源加载透明的创建上下文，例如通过一个Servlet容器。
也支持Javas EE 特性，如EJB，JMX和基本的远程调用。ApplicationContext接口是context的焦点。

spring-context-support为集成常见的第三方库提供支持，如缓存（EhCache，Guava，JCache），邮件（JavaMail），计划（CommonJ，Quartz）以及模板引擎（FreeMarker，JasperReports，Velocity）

spring-expression是JSP 2.1中定义的统一表达式语言的扩展

#### AOP and Instrumentation

spring-aop提供了面向切面编程的能力，spring-aspects提供了对AspectJ的集成。
spring-instrument提供了类的仪表盘支持和在特定容器中使用的类加载器实现。spring-instrument-tomcat提供了Tomcat的Spring仪表盘代理

#### Messaging

spring-messaging，包含关键抽象如Message，MessageChannel，MesageHandler等其他内容，来为基于消息的引用提供基础服务
同时包含了注解映射消息到方法，类似于Spring MVC基于注解编程模型

#### Data Access/Integration

spring-jdbc 提供了JDBC抽象层，从而无需JDBC的繁琐代码和对数据库提供商定义的错误码的解析

spring-tx 对实现特定接口的类和所有POJOs提供编程式和声明式事务管理

spring-orm 提供常见对象关系映射API的集成层，包括JPA，JDO和Hibernate。使用spring-orm可以将所有这些O/R mapping框架和其他Spring提供的特性结合使用，例如简单的声明式事务管理

spring-oxm 模块提供了对象/XML映射实现的抽象层，如JAXB，Castor，XMLBeans，JiBX和XStream

spring-jms 包含了生产和消费消息的能力，从4.1版本，在spring-messaging模块里集成

#### Web

spring-web 提供了基本的web向集成，如多文件上传，使用Servlet监听器初始化IoC容器及web向的应用上下文。同事也包括HTTP客户端和web相关的spring远程调用支持。

spring-webmvc模块（也称为web-servlet模块）包含包含了spring的model-view-controller和REST Web Services实现。

spring-webmvc-portlet（也称为web-portlet模块）提供了在Portlet环境中的MVC实现和spring-webmvc模块的镜像功能

#### Test

spring-test提供了单元测试和Spring组件与JUnit或TestNG测试的集成。提供加载Spring应用上下文和上下文缓存，提供模拟对象用来单独的测试代码