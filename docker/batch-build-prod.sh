#!/bin/sh
# 开始构建 Docker 微服务镜像

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../common-center/core-common
mvn clean package -P prod

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../gateway-server
mvn clean package -P prod

