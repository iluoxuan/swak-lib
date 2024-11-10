# arthas排查问题

* 一般的公司都没有把arthas打到服务的镜像里面
* 可以考虑让公司把arthas打到镜像中

```shell

curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar


```

### 获取springboob中的请求header
```shell

watch org.springframework.web.servlet.DispatcherServlet doService '#httpRequest=params[0],#allHeaders={},#forEnumeration = :[#this.hasMoreElements()? (#headerName=#this.nextElement(),#allHeaders.add(#headerName+"="+#httpRequest.getHeader(#headerName)),#forEnumeration(#this)):null],#forEnumeration(#httpRequest.getHeaderNames()),#allHeaders'  -n 5  -x 3

```

## ognl 表达式使用

