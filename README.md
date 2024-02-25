# 健身房管理系统
支持会员注册及档案管理、办卡退卡、积分兑换奖励、会员促销、会员结算收款、统计分析出会员的消费情况、消费频次等，为会员提供更加合理、科学的会员推荐方案等。

## 运行

### 步骤
```bash
# 打包（jdk21、nodejs20
sh build.sh
# 构建（docker-compose1.29
docker-compose build
# 运行
docker-compose up -d
```

### 外部化配置
`~/appconfig/gym-server/application.yml`


## 开发

### 环境
- JDK 21.0.1
- Node.js 20.10.0
- MySQL 8.2.0

### hosts
```
127.0.0.1 mysql
```
