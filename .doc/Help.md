### 1、本地 Nacos 服务启动
```shell script
# 切换至 nacos 执行文件目录
$ cd /Users/zhanghao/Downloads/local/nacos/bin
# 执行启动脚本命令 启动 Nacos 服务
# standaloe 单机模式运行 非集群
$ sh startup.sh -m standalone
# 查看输出日志
$ tail -f /Users/zhanghao/Downloads/local/nacos/logs/start.out
# 关闭服务
$ sh shutdown.sh
```

### 2、在 yml 中使用 pom 中的变量
```xml
<!-- 添加 filtering true 配置 -->
<resource>
    <directory>src/main/resources</directory>
    <filtering>true</filtering>
</resource>
```
**通过@@使用**
```yml
spring:
  profiles:
    active: @profiles.active@
```

### 3、非对称加密指令
```shell script
keytool -genkeypair -alias oauth2 -keyalg RSA -keypass oauth2 -keystore oauth2.jks -storepass oauht2
CN=zhanghao, OU=fusheng, O=fusheng, L=changsha, ST=湖南, C=f
```

### 二、项目部署
```shell
docker login -u admin -p Harbor12345 42.192.222.62:9090

kubectl create ns prod

docker tag test-server:latest 42.192.222.62:9090/code-galaxy/test-server:latest
docker push 42.192.222.62:9090/code-galaxy/test-server:latest
kubectl create -f test-server-prod.yml
# kubectl get pod -n prod -o wide
# kubectl delete -f test-server-prod.yml
kubectl logs -f test-server-prod-cdf744576-h5g2n -n prod

docker tag gateway-server:latest 42.192.222.62:9090/code-galaxy/gateway-server:latest
docker push 42.192.222.62:9090/code-galaxy/gateway-server:latest
kubectl create -f gateway-server-prod.yml
kubectl logs -f user-server-prod-6bc6c956b5-dkhjn -n prod

docker tag auth-server:latest 42.192.222.62:9090/code-galaxy/auth-server:latest
docker push 42.192.222.62:9090/code-galaxy/auth-server:latest
kubectl create -f auth-server-prod.yml

docker tag user-server:latest 42.192.222.62:9090/code-galaxy/user-server:latest
docker push 42.192.222.62:9090/code-galaxy/user-server:latest
kubectl create -f user-server-prod.yml

docker tag sys-server:latest 42.192.222.62:9090/code-galaxy/sys-server:latest
docker push 42.192.222.62:9090/code-galaxy/sys-server:latest
kubectl create -f sys-server-prod.yml
```


mvn clean package docker:build  -DpushImage -Dmaven.test.skip=true -Pprod

打印GC日志  -XX:+PrintGCDetails




