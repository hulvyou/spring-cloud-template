# Eureka 服务注册与发现
> 服务发现在微服务体系中的重要性这里就不在解释了，Spring Cloud微服务体系支持多种服务发现产品集成；
* 服务发现主要产品
    >1. Eureka
    >2. Consul
    >3. zookeeper
    >4. etcd
> 针对这些服务发现产品有人进行了特性的对比，如下：
<table>
    <tr>
      <td>Feature</td>
      <td>euerka</td>
      <td>Consul</td>
      <td>zookeeper</td>
      <td>etcd</td>
    </tr>
    <tr>
      <td>服务健康检查</td>
       <td>可配支持</td>
      <td>服务状态，内存，硬盘等</td>
      <td>(弱)长连接，keepalive</td>
      <td>连接心跳</td>
    </tr>
    <tr>
      <td>多数据中心</td>
      <td>—</td>
      <td>支持</td>
      <td>—</td>
      <td>—</td>
    </tr>
    <tr>
      <td>kv存储服务</td>
      <td>—</td>
      <td>支持</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>一致性</td>
      <td>—</td>
      <td>raft</td>
      <td>paxos</td>
      <td>raft</td>
    </tr>
    <tr>
      <td>cap</td>
      <td>ap</td>
      <td>ca</td>
      <td>cp</td>
      <td>cp</td>
    </tr>
    <tr>
      <td>使用接口(多语言能力)</td>
      <td>http（sidecar）</td>
      <td>支持http和dns</td>
      <td>客户端</td>
      <td>http/grpc</td>
    </tr>
    <tr>
      <td>watch支持</td>
      <td>支持 long polling/大部分增量</td>
      <td>全量/支持long polling</td>
      <td>支持</td>
      <td>支持 long polling</td>
    </tr>
    <tr>
      <td>自身监控</td>
      <td>metrics</td>
      <td>metrics</td>
      <td>—</td>
      <td>metrics</td>
    </tr>
    <tr>
      <td>安全</td>
      <td>—</td>
      <td>acl /https</td>
      <td>acl</td>
      <td>https支持（弱）</td>
    </tr>
    <tr>
      <td>spring cloud集成</td>
      <td>已支持</td>
      <td>已支持</td>
      <td>已支持</td>
      <td>已支持</td>
    </tr>
</table>

## 为什么选择Eureka
> 1. 提供了完整的服务发现与注册功能，并且也经受住了Netflix自己的生产环境考验
> 2. 强大的社区开源支持，版本更新活跃，非常便于我们了解它的实现原理和排查问题
> 3. Spring Cloud子项目无缝对接Spring Boot，集群方便配置简单容易理解

## Eureka常见问题总结
> Eureka注册服务慢的问题
```properties
#默认30秒 在生产中，最好使用默认值，因为在服务器内部续约计算使用了该值
eureka.instance.leaseRenewalIntervalInSeconds=30
```
> Eureka的自我保护模式
```properties
#设为false（默认true），关闭自我保护
#保护模式主要用于一组客户端和Eureka Server之间存在网络分区场景下的保护。一旦进入保护模式，Eureka Server将会尝试保护其服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）
eureka.server.enable-self-preservation=false
```
> 如何解决Eureka Server不踢出已关停的节点的问题
   >> Eureka Server端
```properties
# 设为false（默认true），关闭自我保护
eureka.server.enable-self-preservation=false
# 清理间隔（单位毫秒，默认是60*1000）
eureka.server.eviction-interval-timer-in-ms=0
```
   >> Eureka Client端
```properties
# 开启健康检查（需要spring-boot-starter-actuator依赖）
# 只应该在application.yml中设置。如果设置在bootstrap.yml中将会导致一些不良的副作用，例如在Eureka中注册的应用名称是UNKNOWN等
eureka.client.healthcheck.enabled=true		
# 续约更新时间间隔（默认30秒）	
eureka.instance.lease-renewal-interval-in-seconds=10
# 续约到期时间（默认90秒）		
eureka.instance.lease-expiration-duration-in-seconds=30 	
```
***更改Eureka更新频率将打破服务器的自我保护功能，生产环境下不建议自定义这些配置***
> Docker 容器部署程序优雅关
```text
    我们的应用部署到服务器上程序会捕捉Terminal信号，收到该信号后会先向Eureka更新自己的状态再关闭。但实际上我们发现当容器关闭后，
Eureka上面的状态却未能及时更新。经测试发现程序并未接收到Terminal信号而是直接收到Kill信号。之所以这样是因为：容器通过执行docker stop命令结束任务，
默认是对容器内PID=1的进程发送Terminal信号10秒后发送kill信号，如果应用启动方式设置不合理，导致应用在容器内的PID不为1，则无法正常被关闭，程序将无法正常下线
解决方案将执行Java程序执行从shell模式改为exec模式
```


