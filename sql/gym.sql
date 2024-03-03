CREATE DATABASE IF NOT EXISTS gym;
USE gym;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint NOT NULL COMMENT '管理员ID',
  `login` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录密码',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `detail` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '管理员信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '管理员', NULL, '2024-02-14 07:05:24', '2024-02-14 07:05:24', 0);

-- ----------------------------
-- Table structure for card
-- ----------------------------
DROP TABLE IF EXISTS `card`;
CREATE TABLE `card`  (
  `id` bigint NOT NULL COMMENT '卡ID',
  `member_id` bigint NULL DEFAULT NULL COMMENT '所属会员ID',
  `card_type` bigint NULL DEFAULT NULL COMMENT '卡类型ID',
  `valid_time` datetime NULL DEFAULT NULL COMMENT '有效期',
  `total` int NULL DEFAULT NULL COMMENT '总次数/金额（-1无效）',
  `remain` int NULL DEFAULT NULL COMMENT '剩余次数/金额',
  `status` tinyint NULL DEFAULT NULL COMMENT '状态（0正常、1过期、-1注销）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员卡信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of card
-- ----------------------------

-- ----------------------------
-- Table structure for card_type
-- ----------------------------
DROP TABLE IF EXISTS `card_type`;
CREATE TABLE `card_type`  (
  `id` bigint NOT NULL COMMENT '卡类型ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '卡类型名称',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `valid_time` int NULL DEFAULT NULL COMMENT '有效期（天）',
  `count` int NULL DEFAULT NULL COMMENT '次数（-1为无限）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '卡类型信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of card_type
-- ----------------------------
INSERT INTO `card_type` VALUES (1, '月卡', 300.00, 30, -1, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `card_type` VALUES (2, '季卡', 800.00, 90, -1, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `card_type` VALUES (3, '年卡', 2800.00, 365, -1, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `card_type` VALUES (4, '次卡', 100.00, 180, 10, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `card_type` VALUES (5, '体验卡', 1.00, 7, 1, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);

-- ----------------------------
-- Table structure for coach
-- ----------------------------
DROP TABLE IF EXISTS `coach`;
CREATE TABLE `coach`  (
  `id` bigint NOT NULL COMMENT '教练ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教练姓名',
  `contact` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教练联系方式',
  `skill` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '教练技能描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '教练信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of coach
-- ----------------------------
INSERT INTO `coach` VALUES (1, '张教练', '13800138000', '专业的瑜伽教练，有多年的教学经验', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `coach` VALUES (2, '李教练', '13900139000', '专业的力量训练教练，有丰富的教学经验', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `coach` VALUES (3, '王教练', '13700137000', '专业的有氧运动教练，有多年的教学经验', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `coach` VALUES (4, '赵教练', '13600136000', '专业的普拉提教练，有丰富的教学经验', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `coach` VALUES (5, '孙教练', '13500135000', '专业的跑步教练，有多年的教学经验', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);

-- ----------------------------
-- Table structure for consume
-- ----------------------------
DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume`  (
  `id` bigint NOT NULL COMMENT '消费记录ID',
  `member_id` bigint NULL DEFAULT NULL COMMENT '会员ID',
  `ctype` tinyint NULL DEFAULT NULL COMMENT '消费类型（0办卡、1课程）',
  `citem` bigint NULL DEFAULT NULL COMMENT '消费项目ID',
  `pay_type` tinyint NULL DEFAULT NULL COMMENT '支付方式（0现金、1刷卡、2支付宝、3微信）',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '消费金额',
  `status` tinyint NULL DEFAULT NULL COMMENT '消费状态（0已支付、1转入退款、2已完成）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '消费记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of consume
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint NOT NULL COMMENT '课程ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程名称',
  `coach_id` bigint NULL DEFAULT NULL COMMENT '教练ID',
  `duration` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '课程时长',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '课程价格',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '课程信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '初级瑜伽', 1, '1小时', 100.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (2, '中级瑜伽', 1, '1.5小时', 150.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (3, '高级瑜伽', 1, '2小时', 200.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (4, '初级力量训练', 2, '1小时', 100.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (5, '中级力量训练', 2, '1.5小时', 150.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (6, '初级有氧运动', 3, '1小时', 100.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (7, '中级有氧运动', 3, '1.5小时', 150.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (8, '高级有氧运动', 3, '2小时', 200.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (9, '初级普拉提', 4, '1小时', 100.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `course` VALUES (10, '初级跑步', 5, '1小时', 100.00, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);

-- ----------------------------
-- Table structure for exchange
-- ----------------------------
DROP TABLE IF EXISTS `exchange`;
CREATE TABLE `exchange`  (
  `id` bigint NOT NULL COMMENT '兑换记录ID',
  `member_id` bigint NULL DEFAULT NULL COMMENT '会员ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '兑换物品名称',
  `points` int NULL DEFAULT NULL COMMENT '兑换消耗积分',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '积分兑换记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of exchange
-- ----------------------------

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint NOT NULL COMMENT '会员ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '会员姓名',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `height` decimal(5, 2) NULL DEFAULT NULL COMMENT '身高',
  `weight` decimal(5, 2) NULL DEFAULT NULL COMMENT '体重',
  `body_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '体型',
  `contact` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系方式',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '地址',
  `points` int NULL DEFAULT NULL COMMENT '积分',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '会员信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` bigint NOT NULL COMMENT '项目ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
  `description` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '项目信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '瑜伽', '瑜伽是一种改善身心健康的运动方式', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `project` VALUES (2, '力量训练', '力量训练可以帮助提高肌肉力量和耐力', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `project` VALUES (3, '有氧运动', '有氧运动可以帮助提高心肺功能和身体耐力', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `project` VALUES (4, '普拉提', '普拉提是一种全身性的运动，可以帮助提高身体的柔韧性和力量', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `project` VALUES (5, '跑步', '跑步是一种简单有效的有氧运动，可以帮助提高心肺功能和身体耐力', '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);

-- ----------------------------
-- Table structure for refund
-- ----------------------------
DROP TABLE IF EXISTS `refund`;
CREATE TABLE `refund`  (
  `id` bigint NOT NULL COMMENT '退款记录ID',
  `member_id` bigint NULL DEFAULT NULL COMMENT '会员ID',
  `consume_id` bigint NULL DEFAULT NULL COMMENT '关联的消费记录ID',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '退款记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of refund
-- ----------------------------

-- ----------------------------
-- Table structure for reward
-- ----------------------------
DROP TABLE IF EXISTS `reward`;
CREATE TABLE `reward`  (
  `id` bigint NOT NULL COMMENT '奖励ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '兑换物品名称',
  `points` int NULL DEFAULT NULL COMMENT '所需积分',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT '是否已删除(0-未删除, 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '奖励信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reward
-- ----------------------------
INSERT INTO `reward` VALUES (1, '运动水壶', 100, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `reward` VALUES (2, '运动毛巾', 200, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `reward` VALUES (3, '运动帽', 300, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `reward` VALUES (4, '运动手环', 400, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);
INSERT INTO `reward` VALUES (5, '运动鞋', 500, '2024-03-03 23:13:50', '2024-03-03 23:13:50', 0);

SET FOREIGN_KEY_CHECKS = 1;
