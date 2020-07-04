#!/usr/bin/env bash

build() {
    cd /Users/maizi/.ssh
    rm -rf id_rsa
    ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
    cp -fp ~/.ssh/id_rsa.pub ~/工具/dockerBuild
    echo "mac秘钥 success"

    #开始构建
    cd /Users/maizi/工具/dockerBuild
    docker build -t mycentos7 .

}

start() {
    #运行
    docker run -itd --name mycentos7 -p 22:22 --privileged=true mycentos7 /usr/sbin/init
}

stop() {
    docker stop mycentos7
    docker rm mycentos7
}

remove() {
    docker stop mycentos7
    docker rm mycentos7
    docker rmi mycentos7
}

check() {
    docker exec -it mycentos7 bash
}

#使用说明，用来提示输入参数
usage() {
    echo "Usage: sh 执行脚本.sh [start|stop|restart]"
    exit 1
}

case "$1" in
"build")
    build
    start
    ;;
"start")
    start
    ;;
"stop")
    stop
    ;;
"check")
    check
    ;;
"restart")
    echo "restart......"
    stop
    start
    echo "restart success"
    ;;
*)
    check
    ;;
esac
