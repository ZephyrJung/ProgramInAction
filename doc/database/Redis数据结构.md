### Redis支持的数据结构：

- Binary-safe string

  ​

- List

  字符串元素的集合，顺序由插入顺序决定。是基本的链表

- Set

  不重复的，无序的字符串元素集合

- Sorted set

  类似于Set，但每个字符串元素关联了一个浮点数值，称为score。元素总是按照score顺序取出。所以可以检索一个区域内的元素（比如前10个，后10个）

- Hash

  关联值和域，值和域都是字符串。

- Bit array（bitmap）

  使用特殊指令，来讲字符数值按照位数组来处理。

- HyperLogLog

  这是为了估计集合的基数而使用的概率数据结构。

### Redis Key

可以使用任意二进制序列作为key，从字符串到一个JPEG文件。空字符串也是一个合法的key。key允许的最大值是512MB。

### Redis String

可以使用SET和GET指令来设置和检索一个字符串值。当KEY存在时，SET会替换掉任何已经存在的值。

```shell
> set mykey somevalue
OK
> get mykey
"somevalue"
```

值可以存放每种类型的字符串，如一个jpeg图像。值得大小不能超过512MB

SET可以带一些有趣的参数，如下可以设置当KEY已经存在的时候SET失败，或者相反，当KEY存在的时候，SET才能成功：

```shell
> set mykey newval nx
(nil)
> set mykey newval xx
OK
```

虽然Redis的基本数值类型是字符串，但依然可以进行一些有趣的操作，如：

```shell
> set counter 100
OK
> incr counter
(integer) 101
> incr counter
(integer) 102
> incrby counter 50
(integer) 152
```

类似指令如DECR，DECRBY

INCR是原子操作。

GETSET指令向KEY设置了一个新值，并返回KEY原先的值。

通过MSET和MGET可以设置或检索多个key的值：

```shell
> mset a 10 b 20 c 30
OK
> mget a b c
1) "10"
2) "20"
3) "30"
```

EXITS指令返回1或0信号来指示指定的KEY是否存在。

DEL命令删除一个KEY及关联的值，不论值是什么：

```shell
> set mykey hello
OK
> exists mykey
(integer) 1
> del mykey
(integer) 1
> exists mykey
(integer) 0
```

DEL本身也会返回1或0来表示是否成功删除。

TYPE指令返回指定KEY存储的值得类型：

```shell
> set mykey x
OK
> type mykey
string
> del mykey
(integer) 1
> type mykey
none
```

#### Redis到期

可以为一个KEY设置超时，令其在指定期限内生存，超时则自动销毁。

设置时间精度可以是秒，也可以是毫秒。

```shell
> set key some-value
OK
> expire key 5
(integer) 1
>get key (immediately)
"some-value"
> get key (after some time)
(nil)
```

第二次读取超过了5秒时间，故而获取不到了。

可以用PERSIST指令来去除EXPIRE

另一种设置到期的指令是：

```shell
> set key 100 ex 10
OK
> ttl key
(integer) 9
```

TTL指令用于查看KEY剩余的留存时间。

如果想要设置毫秒级到期，可以查询PEXPIRE和PTTL指令，以及SET的参数列表。

### Redis List

Redis list的实现是一个链式表。

LPUSH命令向List的左侧（头部）添加元素，RPUSH向右侧（尾部）添加元素。LRANGE命令从list中提取一定范围的元素

```shell
> rpush mylist A
(integer) 1
> rpush mylist B
(integer) 2
>lpush mylist first
(integer) 3
>lrange mylist 0 -1
1) "first"
2) "A"
3) "B"
```

LRANGE需要两个索引，代表要返回区间的第一个和最后一个元素。两个索引都可以是负数，代表从尾部数起。-1代表最后一个元素。

LPUSH和RPUSH都是可变参数命令：

```shell
> rpush mylist 1 2 3 4 5 "foo bar"
(integer) 9
> lrange mylist 0 -1
1) "first"
2) "A"
3) "B"
4) "1"
5) "2"
6) "3"
7) "4"
8) "5"
9) "foo bar"
```

可以通过POP命令来删除元素，类似于PUSH，两边都可以：

```shell
> rpush mylist a b c
(integer) 3
> rpop mylist
"c"
> rpop mylist
"b"
> rpop mylist
"a"
```

当List为空时，POP返回nil

#### list一般使用场景

- 在社交网络中保存用户最后一次更新的帖子
- 进程间通信，使用生产者消费者模型。

#### 覆盖表

Redis允许我们使用表作为一个覆盖集合，通过LTRIM命令，只记住最后N个项，忽略最老的。

```shell
> rpush mylist 1 2 3 4 5
(integer) 5
> ltrim mylist 0 2
OK
> lrange mylist 0 -1
1) "1"
2) "2"
3) "3"
```

LTRIM指令告诉Redis将list元素限制在0~2，其他的均会被忽略。

#### 阻塞list操作

BRPOP和BLPOP命令可以在lit为空时阻塞操作，直到一个新的值添加到list中，或者到了用户指定的超时时间。

```shell
> brpop tasks 5
1) "tasks"
2) "do_something"
```

还有指令：RPOPLPUSH，BRPOPLPUSH，作用不明。

### 自动创建和删除Key

略

### Redis Hash

```shell
> hmset user:1000 username antirez birthyear 1977 verified 1
OK
> hget user:1000 username
"antirez"
> hget user:1000 birthyear
"1997"
> hgetall user:1000
1) "username"
2) "antirez"
3) "birthyear"
4) "1977"
5) "verified"
6) "1"
```

HMSET指令设置了hash的多个域，HGET检索一个单独的域。HMGET与HGET类似，但能够返回一个数组：

```shell
> hmget user:1000 username birthyear no-such-field
1) "antirez"
2) "1977"
3) (nil)
```

可以对一个域进行操作，如HINCRBY指令：

```shell
> hincrby user:1000 birthyear 10
(integer) 1987
> hincrby user:1000 birthyear 10
(integer) 1997
```

### Redis Set

SADD命令向Set中添加元素。还可以进行一些其他操作如交、并或多个集合之间的差等。

```shell
> sadd myset 1 2 3
(integer) 3
> smembers myset
1. 3
2. 1
3. 2
```

检查元素是否存在：

```shell
> sismember myset 3
(integer) 1
> sismember myset 30
(integer) 0
```

假设对新闻文章添加标签，文章ID为1000的有标签1,2,5和77，Set可以保存如下news项关联这些ID：

```shell
> sadd news:1000:tags 1 2 5 77
(integer) 4
```

也可能想要反向关系：保存带有指定标签的文章ID：

```shell
> sadd tag:1:news 1000
(integer) 1
> sadd tag:2:news 1000
(integer) 1
> sadd tag:5:news 1000
(integer) 1
> sadd tag:77:news 1000
(integer) 1
```

列出给定对象的所有标签：

```shell
> smembers news:1000:tags
1. 5
2. 1
3. 77
4. 2
```

SINTER命令可以求得多个Set的交集：

```shell
> sinter tag:1:news tag:2:news tag:10:news tag:27:news
...results...
```

SPOP命令用来提取一个元素，它将删除一个随机元素，并将其返回到客户端。

SUNIONSTORE命令用来求得集合的并集，并保存到另一个集合中。

SCARD命令用于获得集合中元素的个数（集合的基数）

SRANDMEMBER命令用于获取一个随机元素而不移除它。

### Redis Sorted Set

有序集合的元素都关联了一个浮点数值，他们再取出时时有序的。排序的规则是：

- 如果元素A和B的score不同，则当A.score > B.score时，A > B。
- 如果A和B的socre相同，则按A和B字符串的字典顺序排序。鉴于集合中不会包含重复元素，故A和B字符串是不会相等的。

```shell
> zadd hackers 1940 "Alan Kay"
(integer) 1
> zadd hackers 1957 "Sophie Wilson"
(integer) 1
> zadd hackers 1953 "Richard Stallman"
(integer) 1
> zadd hackers 1949 "Anita Borg"
(integer) 1
> zadd hackers 1965 "Yukihiro Matsumoto"
(integer) 1
> zadd hackers 1914 "Hedy Lamarr"
(integer) 1
> zadd hackers 1916 "Claude Shannon"
(integer) 1
> zadd hackers 1969 "Linus Torvalds"
(integer) 1
> zadd hackers 1912 "Alan Turing"
(integer) 1
```

ZADD指令与SADD指令类似，但在元素之前多了一个参数作为score。

查看元素（按score序从小到大）：

```shell
> zrange hackers 0 -1
1) "Alan Turing"
2) "Hedy Lamarr"
3) "Claude Shannon"
4) "Alan Kay"
5) "Anita Borg"
6) "Richard Stallman"
7) "Sophie Wilson"
8) "Yukihiro Matsumoto"
9) "Linus Torvalds"
```

反向排序查看：

```shell
> zrevrange hackers 0 -1
1) "Linus Torvalds"
2) "Yukihiro Matsumoto"
3) "Sophie Wilson"
4) "Richard Stallman"
5) "Anita Borg"
6) "Alan Kay"
7) "Claude Shannon"
8) "Hedy Lamarr"
9) "Alan Turing"
```

可以令结果返回socre：

```shell
> zrange hackers 0 -1 withscores
1) "Alan Turing"
2) "1912"
3) "Hedy Lamarr"
4) "1914"
5) "Claude Shannon"
6) "1916"
7) "Alan Kay"
8) "1940"
9) "Anita Borg"
10) "1949"
11) "Richard Stallman"
12) "1953"
13) "Sophie Wilson"
14) "1957"
15) "Yukihiro Matsumoto"
16) "1965"
17) "Linus Torvalds"
18) "1969"
```

查看1950年以前出生的：

```shell
> zrangebyscore hackers -inf 1950
1) "Alan Turing"
2) "Hedy Lamarr"
3) "Claude Shannon"
4) "Alan Kay"
5) "Anita Borg"
```

删除1940年到1960年出生的：

```shell
> zremrangebyscore hackers 1940 1960
(integer) 4
```

查看元素位置：

```shell
> zrank hackers "Anita Borg"
(integer) 4
```

查看反序位置可使用ZREVRANK指令。

操作词典排序指令：ZRANGEBYLEX，ZREVRANGEBYLEX，ZREMRANGEBYLEX，ZLEXCOUNT。

有序集合可以在任何时候被更新，只要使用ZADD命令。

### Bitmap

Bitmap不是一个真实的数据类型，而是定义在String类型上的一组位操作。

通过SETBIT和GETBIT命令进行添加和检索：

```shell
> setbit key 10 1
(integer) 1
> getbit key 10
(integer) 1
> getbit key 11
(integer) 0
```

### HyperLogLogs

HyperLogLog是一种概率数据结构，用于计数独特的事物（技术上，这被称为估计集合的基数）。

to be continued