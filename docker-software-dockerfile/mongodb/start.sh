### 
# @Description: 请输入....
 # @Author: 麦子
 # @Date: 2019-09-17 01:14:47
 # @LastEditTime: 2019-09-17 02:37:59
 # @LastEditors: 麦子
 ###

#! /bin/bash 


#删除

docker stop rs1
docker stop rs2
docker stop rs3


# docker rm rs1  
docker rm rs2
docker rm rs3

#删除原始数据
cd /vscode/docker-software-dockerfile/mongodb/mongodb
rm -rf rs1
rm -rf rs2
rm -rf rs3


#启动
docker-compose up -d 

docker ps 


# 172.18.0.4
# 172.18.0.3 
# 172.18.0.2