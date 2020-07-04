#!/usr/bin/env bash

#
echo "install java maven 环境"

#安装java环境
cd /usr/local/java/ && ls && tar -xvf jdk-8u11-linux-x64.tar.gz && ls
export JAVA_HOME=/usr/local/java/jdk1.8.0_11
export CLASSPATH=$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
source /etc/profile
java -version
echo "java success"

#安装maven环境
cd /usr/local/ && mkdir maven
wget http://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
mv apache-maven-3.3.9-bin.tar.gz /usr/local/maven/ && ls
cd /usr/local/maven/ && tar -xzvf apache-maven-3.3.9-bin.tar.gz && ls
export MAVEN_HOME=/usr/local/maven/apache-maven-3.3.9
export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH
mvn -version
echo "maven success"



source /etc/profile  #针对用户
source /etc/bashrc   #针对系统