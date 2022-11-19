#!/bin/bash
rsync -P "-e ssh -p 22221" -avz --progress . root@42.192.222.62:/root/Document/k8s/app/code-galaxy/
