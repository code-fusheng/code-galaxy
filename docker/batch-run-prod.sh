#!/bin/sh
docker run -it -m 512M -d -p 9999:9999 --rm gateway-server
docker run -it -m 256M -d -p 9000:9000 --rm auth-server
docker run -it -m 256M -d -p 10000:10000 --rm sys-server
docker run -it -m 256M -d -p 10100:10100 --rm user-server
docker run -it -m 256M -d -p 10200:10200 --rm article-server
docker run -it -m 256M -d -p 10300:10300 --rm exam-server
docker run -it -m 256M -d -p 10600:10600 --rm bill-server

