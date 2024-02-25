#!/bin/sh
# 后端
cd gym-server
chmod +x mvnw
./mvnw clean package -DskipTests

# 前端
cd ../gym-admin
npm install
npm run build
