# RpcServer
Rpc框架服务端实现原理
服务端通过netty接收到客户端传过来数据
获得接口名，根据接口名查找到实现该接口的实现类
根据实现类通过java的反射创建对象
通过反射执行传递过来的方法
通过netty将执行结果返回给客户端
