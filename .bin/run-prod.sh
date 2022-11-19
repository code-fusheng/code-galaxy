#!/bin/bash
docker login -u admin -p Harbor12345 42.192.222.62:9191

docker tag gateway-server:latest 42.192.222.62:9191/code-galaxy/gateway-server:latest
docker push 42.192.222.62:9191/code-galaxy/gateway-server:latest
kubectl apply -f yaml.d/gateway-server-prod.yaml

docker tag auth-server:latest 42.192.222.62:9191/code-galaxy/auth-server:latest
docker push 42.192.222.62:9191/code-galaxy/auth-server:latest
kubectl apply -f yaml.d/auth-server-prod.yaml

docker tag user-server:latest 42.192.222.62:9191/code-galaxy/user-server:latest
docker push 42.192.222.62:9191/code-galaxy/user-server:latest
kubectl apply -f yaml.d/user-server-prod.yaml

docker tag sys-server:latest 42.192.222.62:9191/code-galaxy/sys-server:latest
docker push 42.192.222.62:9191/code-galaxy/sys-server:latest
kubectl apply -f yaml.d/sys-server-prod.yaml

docker tag test-server:latest 42.192.222.62:9191/code-galaxy/test-server:latest
docker push 42.192.222.62:9191/code-galaxy/test-server:latest
kubectl apply -f yaml.d/test-server-prod.yaml
