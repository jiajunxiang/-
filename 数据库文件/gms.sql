/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : localhost:3306
 Source Schema         : gms

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 10/05/2023 09:45:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for outbound_record
-- ----------------------------
DROP TABLE IF EXISTS `outbound_record`;
CREATE TABLE `outbound_record`  (
  `outbound_record_id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_name` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_category` int(0) NULL DEFAULT NULL,
  `outbound_quantity` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`outbound_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outbound_record
-- ----------------------------
INSERT INTO `outbound_record` VALUES (1, '2023-01-28', '英语金卡', 150, 8);
INSERT INTO `outbound_record` VALUES (2, '2023-01-28', '德语黑卡', 150, 4);
INSERT INTO `outbound_record` VALUES (3, '2023-01-28', '德语黑卡', 100, 2);
INSERT INTO `outbound_record` VALUES (4, '2023-01-28', '德语黑卡', 147, 1);
INSERT INTO `outbound_record` VALUES (5, '2023-02-03', '法语200张', 50, 40);
INSERT INTO `outbound_record` VALUES (6, '2023-02-07', '德语200张', 50, 40);
INSERT INTO `outbound_record` VALUES (7, '2023-02-08', '意大利VMAX', 120, 25);
INSERT INTO `outbound_record` VALUES (8, '2023-02-10', '英语金卡', 100, 1);
INSERT INTO `outbound_record` VALUES (9, '2023-02-10', '英语黑卡', 100, 1);
INSERT INTO `outbound_record` VALUES (10, '2023-02-10', '英语银卡', 100, 1);
INSERT INTO `outbound_record` VALUES (11, '2023-02-13', '英语金卡', 150, 9);
INSERT INTO `outbound_record` VALUES (12, '2023-02-20', '英语75+25', 120, 16);
INSERT INTO `outbound_record` VALUES (13, '2023-02-24', '英语金卡', 150, 20);
INSERT INTO `outbound_record` VALUES (14, '2023-02-24', '英语银卡', 150, 20);
INSERT INTO `outbound_record` VALUES (15, '2023-03-13', '彩虹银卡', 150, 2);
INSERT INTO `outbound_record` VALUES (16, '2023-03-13', '英语金卡', 150, 8);
INSERT INTO `outbound_record` VALUES (17, '2023-03-13', '英语银卡', 150, 12);
INSERT INTO `outbound_record` VALUES (18, '2023-03-13', '英语黑卡', 150, 5);
INSERT INTO `outbound_record` VALUES (19, '2023-03-13', '德语金卡', 150, 10);
INSERT INTO `outbound_record` VALUES (20, '2023-03-13', '德语黑卡', 150, 13);
INSERT INTO `outbound_record` VALUES (21, '2023-03-13', '法语金卡', 150, 4);
INSERT INTO `outbound_record` VALUES (22, '2023-03-13', '法语金卡', 148, 1);
INSERT INTO `outbound_record` VALUES (23, '2023-03-28', '英语200张', 52, 14);
INSERT INTO `outbound_record` VALUES (24, '2023-03-28', '英语200张', 50, 13);
INSERT INTO `outbound_record` VALUES (25, '2023-03-29', '英语金卡', 150, 10);
INSERT INTO `outbound_record` VALUES (26, '2023-03-29', '法语GX', 120, 10);
INSERT INTO `outbound_record` VALUES (27, '2023-04-03', '英语金卡', 150, 15);
INSERT INTO `outbound_record` VALUES (28, '2023-04-06', '德语200张', 50, 37);
INSERT INTO `outbound_record` VALUES (29, '2023-04-24', '德语金卡', 150, 13);
INSERT INTO `outbound_record` VALUES (30, '2023-05-06', '英语75+25', 120, 3);

-- ----------------------------
-- Table structure for outbound_record_copy1
-- ----------------------------
DROP TABLE IF EXISTS `outbound_record_copy1`;
CREATE TABLE `outbound_record_copy1`  (
  `outbound_record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '出库记录id',
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建时间',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名称',
  `goods_category` int(0) NULL DEFAULT NULL COMMENT '货物类别',
  `outbound_quantity` int(0) NULL DEFAULT NULL COMMENT '出库数量',
  PRIMARY KEY (`outbound_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outbound_record_copy1
-- ----------------------------
INSERT INTO `outbound_record_copy1` VALUES (1, '2023.01.28', '英语黑卡', 15, 30);
INSERT INTO `outbound_record_copy1` VALUES (2, '2023.01.29', '英语200张', 3, 1);

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '种类',
  `number` int(0) NULL DEFAULT NULL COMMENT '数量'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES ('法语金卡', 0);
INSERT INTO `stock` VALUES ('英语黑卡', 130);
INSERT INTO `stock` VALUES ('英语银卡', 820);
INSERT INTO `stock` VALUES ('德语黑卡', 50);
INSERT INTO `stock` VALUES ('德语金卡', 196);
INSERT INTO `stock` VALUES ('英语金卡', 2020);
INSERT INTO `stock` VALUES ('黑盒', 735);
INSERT INTO `stock` VALUES ('德语VMAX', 512);
INSERT INTO `stock` VALUES ('法语GX', 0);
INSERT INTO `stock` VALUES ('法语VMAX', 0);
INSERT INTO `stock` VALUES ('法语200张', 49);
INSERT INTO `stock` VALUES ('意大利VMAX', 1392);
INSERT INTO `stock` VALUES ('德语GX', 120);
INSERT INTO `stock` VALUES ('德语200张', 21);
INSERT INTO `stock` VALUES ('英语VMAX', 870);
INSERT INTO `stock` VALUES ('英语75+25', 1080);
INSERT INTO `stock` VALUES ('英语200张', 26);
INSERT INTO `stock` VALUES ('彩虹银卡', 0);

-- ----------------------------
-- Table structure for stock_copy1
-- ----------------------------
DROP TABLE IF EXISTS `stock_copy1`;
CREATE TABLE `stock_copy1`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `number` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock_copy1
-- ----------------------------
INSERT INTO `stock_copy1` VALUES ('法语金卡', 749);
INSERT INTO `stock_copy1` VALUES ('英语黑卡', 900);
INSERT INTO `stock_copy1` VALUES ('英语银卡', 2384);
INSERT INTO `stock_copy1` VALUES ('德语黑卡', 2000);
INSERT INTO `stock_copy1` VALUES ('德语金卡', 1546);
INSERT INTO `stock_copy1` VALUES ('英语金卡', 836);
INSERT INTO `stock_copy1` VALUES ('黑盒', 664);
INSERT INTO `stock_copy1` VALUES ('德语VMAX', 752);
INSERT INTO `stock_copy1` VALUES ('法语GX', 2393);
INSERT INTO `stock_copy1` VALUES ('法语VMAX', 1087);
INSERT INTO `stock_copy1` VALUES ('法语200张', 49);
INSERT INTO `stock_copy1` VALUES ('意大利VMAX', 1394);
INSERT INTO `stock_copy1` VALUES ('德语GX', 120);
INSERT INTO `stock_copy1` VALUES ('德语200张', 1871);
INSERT INTO `stock_copy1` VALUES ('英语VMAX', 350);
INSERT INTO `stock_copy1` VALUES ('英语75+25', 2723);
INSERT INTO `stock_copy1` VALUES ('英语200张', 691);
INSERT INTO `stock_copy1` VALUES ('彩虹', 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for warehousing_record
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_record`;
CREATE TABLE `warehousing_record`  (
  `warehousing_record_id` int(0) NOT NULL AUTO_INCREMENT,
  `create_time` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_name` varchar(254) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goods_category` int(0) NULL DEFAULT NULL,
  `receipt_quantity` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`warehousing_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehousing_record
-- ----------------------------
INSERT INTO `warehousing_record` VALUES (1, '2023-02-02', '法语200张', 50, 40);
INSERT INTO `warehousing_record` VALUES (2, '2023-02-02', '法语VMAX', 120, 8);
INSERT INTO `warehousing_record` VALUES (3, '2023-02-07', '法语GX', 120, 17);
INSERT INTO `warehousing_record` VALUES (4, '2023-02-07', '意大利VMAX', 120, 42);
INSERT INTO `warehousing_record` VALUES (5, '2023-02-10', '英语金卡', 100, 10);
INSERT INTO `warehousing_record` VALUES (6, '2023-02-10', '德语黑卡', 100, 20);
INSERT INTO `warehousing_record` VALUES (7, '2023-02-11', '英语金卡', 200, 2);
INSERT INTO `warehousing_record` VALUES (8, '2023-02-11', '英语银卡', 200, 1);
INSERT INTO `warehousing_record` VALUES (9, '2023-02-13', '英语金卡', 150, 9);
INSERT INTO `warehousing_record` VALUES (10, '2023-02-24', '英语金卡', 150, 20);
INSERT INTO `warehousing_record` VALUES (11, '2023-02-24', '英语银卡', 150, 20);
INSERT INTO `warehousing_record` VALUES (12, '2023-03-11', '英语金卡', 150, 1);
INSERT INTO `warehousing_record` VALUES (13, '2023-03-27', '英语金卡', 150, 5);
INSERT INTO `warehousing_record` VALUES (14, '2023-03-27', '英语银卡', 150, 5);
INSERT INTO `warehousing_record` VALUES (15, '2023-03-29', '英语金卡', 150, 16);
INSERT INTO `warehousing_record` VALUES (16, '2023-04-01', '英语金卡', 150, 18);
INSERT INTO `warehousing_record` VALUES (17, '2023-04-06', '法语VMAX', 120, 20);
INSERT INTO `warehousing_record` VALUES (18, '2023-04-24', '德语金卡', 150, 14);
INSERT INTO `warehousing_record` VALUES (19, '2023-05-06', '英语75+25', 120, 3);

-- ----------------------------
-- Table structure for warehousing_record_copy1
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_record_copy1`;
CREATE TABLE `warehousing_record_copy1`  (
  `warehousing_record_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '入库记录id',
  `create_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建时间',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名称',
  `goods_category` int(0) NULL DEFAULT NULL COMMENT '货物类别',
  `receipt_quantity` int(0) NULL DEFAULT NULL COMMENT '入库数量',
  PRIMARY KEY (`warehousing_record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehousing_record_copy1
-- ----------------------------
INSERT INTO `warehousing_record_copy1` VALUES (59, '2023-02-21', '英金', 400, 2);
INSERT INTO `warehousing_record_copy1` VALUES (60, '2023.02.21', '德黑', 150, 3);
INSERT INTO `warehousing_record_copy1` VALUES (61, '2023.02.01', '意蓝', 100, 4);

-- ----------------------------
-- Triggers structure for table outbound_record
-- ----------------------------
DROP TRIGGER IF EXISTS `trigerOutbound`;
delimiter ;;
CREATE TRIGGER `trigerOutbound` AFTER INSERT ON `outbound_record` FOR EACH ROW UPDATE stock set number=number-(NEW.goods_category*NEW.outbound_quantity) where
stock.name=NEW.goods_name
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table outbound_record_copy1
-- ----------------------------
DROP TRIGGER IF EXISTS `tri1`;
delimiter ;;
CREATE TRIGGER `tri1` AFTER INSERT ON `outbound_record_copy1` FOR EACH ROW UPDATE stock_copy1 set number=number-(NEW.goods_category*NEW.outbound_quantity) where
stock_copy1.name=NEW.goods_name
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table warehousing_record
-- ----------------------------
DROP TRIGGER IF EXISTS `trigerWarehousing`;
delimiter ;;
CREATE TRIGGER `trigerWarehousing` AFTER INSERT ON `warehousing_record` FOR EACH ROW UPDATE stock set number=number+(NEW.goods_category*NEW.receipt_quantity) where
stock.name=NEW.goods_name
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table warehousing_record_copy1
-- ----------------------------
DROP TRIGGER IF EXISTS `tig1`;
delimiter ;;
CREATE TRIGGER `tig1` AFTER INSERT ON `warehousing_record_copy1` FOR EACH ROW UPDATE stock_copy1 set number=number+(NEW.goods_category*NEW.receipt_quantity) where
stock_copy1.name=NEW.goods_name
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
