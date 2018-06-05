## The IoC container

#### Composing Java-based configurations

##### Using the @Import annotation

类似于用于模块化配置文件的`<import/>`标签，@Import注解允许加载另一个配置类里的@Bean定义：

```java
@Configuration
public class ConfigA {
    @Bean
    public A a() {
        return new A();
    }
}
@Configuration
@Import(ConfigA.class)
public class ConfigB {
    @Bean
    public B b() {
        return new B();
    }
}
```
如此，在实例化上下文时，只需要显示的声明ConfigB：

```java
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);
    // now both beans A and B will be available...
    A a = ctx.getBean(A.class);
    B b = ctx.getBean(B.class);
}
```

###### Injecting dependencies on imported @Bean definitions

上面的方法有效，但是太简单。在大多数场景，bean总会有许多其他配置类里的依赖。如果使用XML，其本身没有编译器包含，可以简单地声明一个ref="someBean"，
然后相信Spring能够在容器初始化时处理好此事。而如果使用@Configuration类，Java编译器将关注配置模型，关联的Bean必须满足正确的Java语法。

下面的例子，每一个都依赖于另一个地方声明的bean：
```java
@Configuration
public class ServiceConfig {
    @Bean
    public TransferService transferService(AccountRepository accountRepository) {
        return new TransferServiceImpl(accountRepository);
    }
}
@Configuration
public class RepositoryConfig {
    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        return new JdbcAccountRepository(dataSource);
    }
}
@Configuration
@Import({ServiceConfig.class, RepositoryConfig.class})
public class SystemTestConfig {
    @Bean
    public DataSource dataSource() {
        // return new DataSource
    }
}
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SystemTestConfig.class);
    // everything wires up across configuration classes...
    TransferService transferService = ctx.getBean(TransferService.class);
    transferService.transfer(100.00, "A123", "C456");
}
```

@Configuration类也是容器中的一个bean，这意味着可以利用@Autowired和@Value注入。

```java
@Configuration
public class ServiceConfig {
@Autowired
private AccountRepository accountRepository;
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl(accountRepository);
    }
}
@Configuration
public class RepositoryConfig {
    private final DataSource dataSource;
    @Autowired
    public RepositoryConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public AccountRepository accountRepository() {
        return new JdbcAccountRepository(dataSource);
    }
}
@Configuration
@Import({ServiceConfig.class, RepositoryConfig.class})
public class SystemTestConfig {
    @Bean
    public DataSource dataSource() {
        // return new DataSource
    }
}
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SystemTestConfig.class);
    // everything wires up across configuration classes...
    TransferService transferService = ctx.getBean(TransferService.class);
    transferService.transfer(100.00, "A123", "C456");
}
```

在这种不明确性不可接受的情况下，如果希望从IDE内的一个@Configuration类直接导航到另一个类，请考虑自动装配配置类本身：

```java
@Configuration
public class ServiceConfig {
    @Autowired
    private RepositoryConfig repositoryConfig;
    @Bean
    public TransferService transferService() {
    // navigate 'through' the config class to the @Bean method!
        return new TransferServiceImpl(repositoryConfig.accountRepository());
    }
}
```

在上面的情况中，它完全明确了AccountRepository的定义。 但是，ServiceConfig现在与RepositoryConfig紧密耦合; 这是权衡。 
通过使用基于接口的或基于抽象的基于类的@Configuration类，可以稍微缓解这种紧密耦合。 考虑以下：

```java
@Configuration
public class ServiceConfig {
    @Autowired
    private RepositoryConfig repositoryConfig;
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl(repositoryConfig.accountRepository());
    }
}
@Configuration
public interface RepositoryConfig {
    @Bean
    AccountRepository accountRepository();
}
@Configuration
public class DefaultRepositoryConfig implements RepositoryConfig {
    @Bean
    public AccountRepository accountRepository() {
        return new JdbcAccountRepository(...);
    }
}
@Configuration
@Import({ServiceConfig.class, DefaultRepositoryConfig.class}) // import the concrete config!
public class SystemTestConfig {
    @Bean
    public DataSource dataSource() {
        // return DataSource
    }
}
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(SystemTestConfig.class);
    TransferService transferService = ctx.getBean(TransferService.class);
    transferService.transfer(100.00, "A123", "C456");
}
```
现在ServiceConfig与具体的DefaultRepositoryConfig松散耦合，并且内置IDE工具仍然有用：开发人员可以轻松获得RepositoryConfig实现的类型层次结构。 
通过这种方式，浏览@Configuration类及其依赖关系与导航基于接口的代码的常见过程无异。

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