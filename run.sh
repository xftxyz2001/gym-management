#!/bin/sh
# 0 清理
docker rm -f $(docker ps -aq)

docker rmi -f chenluoxing/gym-server:0.0.1
docker rmi -f chenluoxing/gym-admin:0.0.1

# 1 构建
## 后端
cd gym-server
chmod +x mvnw
./mvnw clean package -DskipTests

## 前端
cd ../gym-admin
npm install
npm run build


## 构建docker镜像
cd ..
docker-compose build


# 3 启动
docker-compose up -d

echo "服务已启动，需放行端口：3001(前端)、8080(后端)"
