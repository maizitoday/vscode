FROM elasticsearch:6.8.3
EXPOSE 9300
EXPOSE 9200

# 编译
# docker build -f /vscode/docker-software-dockerfile/ElasticSearch/Dockerfile .

# 创建网段
# docker network create esk

# macOS with Docker for Mac
#$ screen ~/Library/Containers/com.docker.docker/Data/vms/0/tty
# sysctl -w vm.max_map_count=262144

# 启动
# docker run -itd --name elasticsearch --net esk -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:6.8.3

# 注意这个 --name 名字不能修改， 因为kibana默认的配置文件 kibana.yml 就是这个 elasticsearch.hosts: [ "http://elasticsearch:9200" ]

# docker
#9300端口是使用tcp客户端连接使用的端口；
#9200端口是通过http协议连接es使用的端口；

#单节点发现编辑  discovery.type
#我们认识到某些用户需要将传输绑定到外部接口以测试其对传输客户端的使用。对于这种情况，我们提供发现类型single-node（通过设置discovery.type为来 配置single-node）；在这种情况下，节点将选举自己为主节点，并且不会与任何其他节点一起加入群集。
