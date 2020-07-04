#!/bin/bash

echo "开始执行容器启动脚本"
source /etc/profile
cat /etc/profile

#防止容器启动后退出
tail -f /dev/null
