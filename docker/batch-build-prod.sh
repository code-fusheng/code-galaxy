#!/bin/sh
# 开始构建 Docker 微服务镜像
pwd
cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../common-center/core-common
mvn clean install -P prod -D skipTests

cd /Users/zhanghao/IdeaProjects/core/code-galaxy/docker/

cd ../gateway-server
mvn clean package docker:build  -P prod -D skipTests

cd ../auth-server
mvn clean package docker:build -P prod -D skipTests

cd ../server-center
cd sys-server/
mvn clean package docker:build -P prod -D skipTests

cd ..
cd user-server/
mvn clean package docker:build -P prod -D skipTests

cd ..
cd article-server/
mvn clean package docker:build -P prod -D skipTests

cd ..
cd exam-server/
mvn clean package docker:build -P prod -D skipTests

cd ..
cd bill-server/
mvn clean package docker:build -P prod -D skipTests

cd ..
cd test-server/
mvn clean package docker:build -P prod -D skipTests
