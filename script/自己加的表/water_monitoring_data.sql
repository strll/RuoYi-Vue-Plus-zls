/*
 Navicat Premium Data Transfer

 Source Server         : 本地mysql
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : 192.168.36.128:3307
 Source Schema         : ruoyi

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 27/03/2025 19:37:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for water_monitoring_data
-- ----------------------------
DROP TABLE IF EXISTS `water_monitoring_data`;
CREATE TABLE `water_monitoring_data`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sensor_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '传感器编号',
  `flow_rate` double NULL DEFAULT NULL COMMENT '水流量（L/min）',
  `pressure` double NULL DEFAULT NULL COMMENT '水压（MPa）',
  `quality_index` double NULL DEFAULT NULL COMMENT '水质指数（0-100）',
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录时间',
  `tenant_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '000000' COMMENT '租户编号',
  `create_dept` bigint NULL DEFAULT NULL COMMENT '创建部门',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1905173131694825474 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '监测数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of water_monitoring_data
-- ----------------------------
INSERT INTO `water_monitoring_data` VALUES (1905173131694825473, '1905171502245470210', 100, 20, 59, '2025-03-27 16:20:44', '000000', 103, 1, '2025-03-27 16:20:46', 1, '2025-03-27 16:20:46');

SET FOREIGN_KEY_CHECKS = 1;
