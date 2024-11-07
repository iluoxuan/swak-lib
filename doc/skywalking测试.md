## win11 本地测试
架构图：

https://skywalking.apache.org/zh/2020-04-19-skywalking-quick-start/0081Kckwly1gkl533oq0xj30ww0pomzt.jpg


## 启动oap服务
* oap接受上报的服务

```shell

docker pull apache/skywalking-oap-server:10.0.0
docker pull apache/skywalking-ui:10.0.0

```
## 启动ui

*  11800 :SkyWalking OAP 服务器默认使用这个端口来接收来自 Agent 的数据上报
*  12800:SkyWalking OAP 服务器的 web 管理界面默认运行在这个端口上，通过这个端口可以访问到 SkyWalking 的 UI 界面
```shell

docker run -d -p 11800:11800 -p 12800:12800 --name sw_oap apache/skywalking-oap-server:10.0.0

docker run -d -p 8080:8080 -e SW_OAP_ADDRESS=http://localhost:12800 apache/skywalking-ui:10.0.0


```

## agent使用

```shell

# idea中：

-javaagent:D:\ljq\work\config\agent\skywalking-agent-9.3\skywalking-agent.jar

```

