## Architectural

### API Gateway

>  将微服务的调用集中在API Gateway中。使用者只需要调用API Gateway，API Gateway负责调用各自相关的微服务

![Architectural_1](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_1.jpg)

![Architectural_2](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_2.png)

### Aggregator Microservices



### CQRS

> Command Query Responsibility Segregation 命令与查询责任隔离



![Architectural_3](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_3.png)

![Architectural_4](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_4.png)

如有如下接口方法：

```java
void MakeCustomerPreferred（CustomerId） 
Customer GetCustomer（CustomerId） 
CustomerSet GetCustomersWithName（Name） 
CustomerSet GetPreferredCustomers（） 
void ChangeCustomerLocale（CustomerId，NewLocale） 
void CreateCustomer（Customer） 
void EditCustomerDetails（CustomerDetails）
```

在此基础上应用CQRS将产生两种服务：

**CustomerWriteService**

```java
void MakeCustomerPreferred（CustomerId） 
void ChangeCustomerLocale（CustomerId，NewLocale） 
void CreateCustomer（Customer） 
void EditCustomerDetails（CustomerDetails）
```

**CustomerReadService**

```java
Customer GetCustomer（CustomerId） 
CustomerSet GetCustomersWithName（Name） 
CustomerSet GetPreferredCustomers（）
```

这样允许我们以不同方式托管两种服务。命令和查询的处理从根本上说是不对称的，对称的扩展服务没有多大意义

### Data Bus

> 数据总线，允许应用程序的组件之间发送消息/事件，而无需了解彼此，只需要知道正在发送的消息/事件的类型

![Architectural_6](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_6.png)

### Data Transfer Object

> 创建一个数据传输对象（DTO），其中包含远程调用所需的所有数据。修改远程方法签名，以接收DTO作为单个参数，并将单个DTO参数返回给客户端。如此可以避免多次调用远程服务器。



### Event Driven Architecture

![Architectural_5](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_5.png)

### Event Sourcing

> 确保在事件对象中捕获应用程序状态的每个更改，并且这些事件对象本身按照它们的应用顺序存储，其生命周期与应用程序状态本身相同。

常见示例如版本控制系统

关键在于保证对域对象的所有更改都是由事件对象驱动的。

事件的逆转和前进同样重要。如果事件的传播形式是通过差值，逆转则直截了当。如事件是向马丁的账户里添加10美元，逆事件则是向马丁的账户减去10美元。如果

### Hexagonal Architecture

> 六角形架构，围绕核心构建应用程序，主要目标是创建完全可测试的系统

传统的分层架构，将应用程序组织成各自具有其特定目的的层。数据库层负责数据事务，业务层负责业务逻辑，表示层处理用户输入。分层体系结构实现了关注分离原则，使应用程序更容易维护，对软件中的某个区域的更改不会影响到其他区域。

但是分层架构实现的应用程序，不能直观的看到应用的核心，是数据库，还是业务逻辑等。

![Architectural_7](/Users/zephyrjung/Documents/GithubSpace/ProgramInAction/doc/patterns/Architectural_7.png)

实际的端口数量不一定是六个，可以更少，也可以更多

### Layers

### Naked Objects

### Partial Response

> 部分响应而非返回完整对象，可以减少带宽使用，提高服务器响应速度

可以在result API中，要求客户端指定需要返回的字段：

```http
GET /dogs?fields=name,breed,age,owner(name,age)
```



### Serverless

### Service Layer

### Unit Of Work

