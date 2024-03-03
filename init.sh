#!/bin/sh

# mysql
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.2.0
docker cp mysql:/etc/mysql ~/mysql8.2.0
docker rm -f mysql

# ---
mkdir -p ~/appconfig/gym-server
