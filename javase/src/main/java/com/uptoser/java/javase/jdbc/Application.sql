/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.200_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : 192.168.3.200:3306
 Source Schema         : employees

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 02/01/2025 10:31:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `account` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `balance` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 'A', 2400);
INSERT INTO `account` VALUES (2, 'B', 400);


-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `note` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role_no` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'role_name_1', 'note_1', 1);
INSERT INTO `t_role` VALUES (2, 'role_name_2', 'note_2', 2);
INSERT INTO `t_role` VALUES (3, 'role_name_3', 'note_3', 3);
INSERT INTO `t_role` VALUES (5, 'role_name_5', 'note_5', 5);



-- ----------------------------
-- Procedure structure for count_role
-- ----------------------------
DROP PROCEDURE IF EXISTS `count_role`;
delimiter ;;
CREATE PROCEDURE `count_role`(IN p_role_name VARCHAR(12),OUT count_total INT,OUT exec_date DATE)
BEGIN

SELECT COUNT(*) INTO count_total FROM t_role WHERE role_name LIKE CONCAT('%',p_role_name,'%');
SELECT CURDATE() INTO exec_date;

END
;;
delimiter ;


SET FOREIGN_KEY_CHECKS = 1;


