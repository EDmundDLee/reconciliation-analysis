/*
 Navicat Premium Data Transfer

 Source Server         : 10.66.226.74开发库
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : 10.66.226.74:3306
 Source Schema         : rongxin_farm

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 20/03/2023 09:53:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business_dict
-- ----------------------------
DROP TABLE IF EXISTS `business_dict`;
CREATE TABLE `business_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典级别',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项编码',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `parent_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点id',
  `is_enabled` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可用',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务字典表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of business_dict
-- ----------------------------
INSERT INTO `business_dict` VALUES (118, NULL, '1', 'business', '商务类别', '商务信息', 1, '0', 'Y', NULL, '2023-03-14 08:52:23', NULL, '2023-03-14 15:02:15');
INSERT INTO `business_dict` VALUES (120, NULL, '1', 'comtype', '商品类别', '商品信息类别', 3, '0', 'Y', NULL, '2023-03-14 09:07:37', NULL, '2023-03-14 13:47:53');
INSERT INTO `business_dict` VALUES (121, NULL, '2', 'comtype_food', '食品类别', '食品类别', 1, '120', 'Y', NULL, '2023-03-14 09:07:48', NULL, '2023-03-14 14:08:59');
INSERT INTO `business_dict` VALUES (123, NULL, '1', 'zztype', '种子种类', '种子类别', 2, '0', 'Y', NULL, '2023-03-14 09:21:54', NULL, '2023-03-14 13:47:10');
INSERT INTO `business_dict` VALUES (124, NULL, '3', 'comtype_food_rou', '肉类', '食品肉类', 1, '121', 'Y', NULL, '2023-03-14 13:45:45', NULL, '2023-03-14 14:07:03');
INSERT INTO `business_dict` VALUES (125, NULL, '2', 'comtype_drink', '饮品', '饮品', 2, '120', 'Y', NULL, '2023-03-14 13:50:56', NULL, '2023-03-14 14:07:13');
INSERT INTO `business_dict` VALUES (126, NULL, '3', 'comtype_food_fruit', '果蔬', '水果蔬菜', 2, '121', 'Y', NULL, '2023-03-14 13:51:55', NULL, '2023-03-14 14:07:06');
INSERT INTO `business_dict` VALUES (127, NULL, '3', 'comtype_food_sea', '海鲜', '海鲜', 3, '121', 'Y', NULL, '2023-03-14 14:08:35', NULL, '2023-03-14 14:08:32');
INSERT INTO `business_dict` VALUES (129, NULL, '3', 'comtype_food_fish', '鱼', '鱼', 1, '121', 'Y', NULL, '2023-03-14 14:39:27', NULL, '2023-03-14 14:39:24');

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (3, 'rx_demo', '', NULL, NULL, 'RxDemo', 'crud', 'com.rongxin.system', 'system', 'demo', NULL, 'rx', '0', '/', NULL, 'admin', '2022-08-10 10:15:46', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (4, 'file_upload_share', '文件上传明细', NULL, NULL, 'FileUploadShare', 'crud', 'com.rongxin.system', 'system', 'share', '文件上传明细', 'rx', '0', '/', NULL, 'admin', '2022-08-25 08:54:20', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (7, 'sys_notice_user', '用户通知公告关联表', NULL, NULL, 'SysNoticeUser', 'crud', 'com.rongxin.system', 'system', 'noticeuser', '用户通知公告关联', 'rx', '0', '/', '{\"parentMenuId\":2001}', 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55', NULL);
INSERT INTO `gen_table` VALUES (8, 'rx_student', '学生', NULL, NULL, 'RxStudent', 'crud', 'com.rongxin.demo', 'allcontent', 'student', 'allcontent', 'rx', '0', '/', '{}', 'admin', '2022-09-06 10:16:59', '', '2022-09-06 11:28:44', NULL);
INSERT INTO `gen_table` VALUES (9, 'act_re_model', '工作流流程维护', NULL, NULL, 'ActReModel', 'crud', 'com.rongxin.demo', 'example', 'model', '工作流基础示例', 'rx', '0', '/', '{\"parentMenuId\":\"2001\"}', 'admin', '2022-09-14 08:37:38', '', '2022-09-14 08:54:02', NULL);
INSERT INTO `gen_table` VALUES (10, 'tb_leave', '请假信息主表', NULL, NULL, 'TbLeave', 'crud', 'com.rongxin.demo', 'example', 'leave', '请假示例', 'rx', '0', '/', '{}', 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44', NULL);
INSERT INTO `gen_table` VALUES (11, 'biz_article', '文章内容', NULL, NULL, 'BizArticle', 'crud', 'com.rongxin.cms', 'cms', 'article', '文章内容', 'rx', '0', '/', '{\"parentMenuId\":\"2058\"}', 'admin', '2022-10-09 09:45:18', '', '2022-10-09 10:56:59', NULL);
INSERT INTO `gen_table` VALUES (12, 'biz_column', '栏目类别', '', '', 'BizColumn', 'tree', 'com.rongxin.cms', 'cms', 'cmscol', '栏目类别', 'rx', '0', '/', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"parent_id\"}', 'admin', '2022-10-09 09:45:18', '', '2022-10-10 13:52:15', NULL);
INSERT INTO `gen_table` VALUES (13, 'biz_link', '', NULL, NULL, 'BizLink', 'crud', 'com.rongxin.system', 'system', 'link', NULL, 'rx', '0', '/', NULL, 'admin', '2022-10-09 09:45:19', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (14, 'biz_picture', '', NULL, NULL, 'BizPicture', 'crud', 'com.rongxin.system', 'system', 'picture', NULL, 'rx', '0', '/', NULL, 'admin', '2022-10-09 09:45:19', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (15, 'biz_copyright', 'cms版权信息表', '', '', 'BizCopyright', 'crud', 'com.rongxin.cms', 'cms', 'copyright', 'cms版权信息', 'yggyk', '0', '/', '{\"parentMenuId\":\"2058\"}', 'admin', '2022-10-12 15:22:56', '', '2022-11-02 15:40:09', NULL);
INSERT INTO `gen_table` VALUES (16, 'biz_article_rule', '规则内容关系表', NULL, NULL, 'BizArticleRule', 'crud', 'com.rongxin.cms', 'cms', 'rule', '规则内容关系', 'rx', '0', '/', '{\"parentMenuId\":2058}', 'admin', '2022-11-01 09:00:31', '', '2022-11-01 09:02:25', NULL);
INSERT INTO `gen_table` VALUES (17, 'biz_attribute', 'cms内容拓展属性表', NULL, NULL, 'BizAttribute', 'crud', 'com.rongxin.cms', 'cms', 'attribute', '内容拓展属性', 'rx', '0', '/', '{\"parentMenuId\":2058}', 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28', NULL);
INSERT INTO `gen_table` VALUES (18, 'biz_attribute_value', '内容拓展属性值表', NULL, NULL, 'BizAttributeValue', 'crud', 'com.rongxin.cms', 'cms', 'attvalue', '内容拓展属性值', 'rx', '0', '/', '{\"parentMenuId\":2058}', 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:04:25', NULL);
INSERT INTO `gen_table` VALUES (19, 'biz_rule', '规则表', NULL, NULL, 'BizRule', 'crud', 'com.rongxin.cms', 'cms', 'rule', '内容拓展属性规则表', 'rx', '0', '/', '{\"parentMenuId\":\"2058\"}', 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:45:15', NULL);
INSERT INTO `gen_table` VALUES (20, 'sys_user', '用户信息表', NULL, NULL, 'SysUser', 'crud', 'com.rongxin.system', 'system', 'user', '用户信息', 'rx', '0', '/', NULL, 'admin', '2022-11-02 15:02:20', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (21, 'sys_test', '用户通知公告关联表', NULL, NULL, 'SysTest', 'crud', 'com.rongxin.system', 'system', 'test', '用户通知公告关联', 'rx', '0', '/', NULL, 'admin', '2022-11-02 15:36:45', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (22, 'sys_user_post', '用户与岗位关联表', NULL, NULL, 'SysUserPost', 'crud', 'com.rongxin.system', 'system', 'post', '用户与岗位关联', 'rx', '0', '/', NULL, 'admin', '2022-11-07 09:37:49', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (23, 'biz_column_rule', '栏目规则关系表', NULL, NULL, 'BizColumnRule', 'crud', 'com.rongxin.cms', 'cms', 'column_rule', '栏目规则属性', 'rx', '0', '/', '{}', 'admin', '2022-11-08 16:10:05', '', '2022-11-08 16:12:38', NULL);
INSERT INTO `gen_table` VALUES (24, 't_member', '人员管理', NULL, NULL, 'TMember', 'crud', 'com.rongxin.member', 'member', 'member', '人员管理', 'rx', '0', '/', '{\"parentMenuId\":2096}', 'admin', '2022-12-05 16:43:35', '', '2022-12-06 10:24:24', NULL);
INSERT INTO `gen_table` VALUES (25, 't_company', '公司管理', NULL, NULL, 'TCompany', 'crud', 'com.rongxin.company', 'company', 'company', '公司管理', 'rx', '0', '/', '{\"parentMenuId\":\"2096\"}', 'admin', '2022-12-08 09:59:42', '', '2022-12-08 15:29:57', NULL);
INSERT INTO `gen_table` VALUES (26, 'crm_family_member', '家庭成员表', NULL, NULL, 'CrmFamilyMember', 'crud', 'com.rongxin.crm', 'crm', 'familymember', '家庭成员', 'rx', '0', '/', '{}', 'admin', '2023-01-07 11:35:02', '', '2023-01-07 11:38:53', NULL);
INSERT INTO `gen_table` VALUES (27, 'crm_farmer_info', '农户信息表', 'crm_loan_info', 'farmer_id', 'CrmFarmerInfo', 'sub', 'com.rongxin.crm', 'crm', 'farmerinfo', '农户信息', 'rx', '0', '/', '{}', 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30', NULL);
INSERT INTO `gen_table` VALUES (28, 'crm_loan_info', '贷款信息表', NULL, NULL, 'CrmLoanInfo', 'crud', 'com.rongxin.system', 'system', 'info', '贷款信息', 'rx', '0', '/', NULL, 'admin', '2023-01-07 11:35:03', '', NULL, NULL);
INSERT INTO `gen_table` VALUES (29, 'sv_concern', '关注信息表', NULL, NULL, 'SvConcern', 'crud', 'com.rongxin.service.video.api', 'system', 'concern', '关注', 'rx', '0', '/', '{\"parentMenuId\":2058}', 'admin', '2023-02-21 15:38:29', '', '2023-02-21 15:39:43', NULL);
INSERT INTO `gen_table` VALUES (30, 'business_dict', '业务字典表', '', '', 'BusinessDict', 'tree', 'com.rongxin.web.controller.system', 'business', 'dict', '业务字典', 'rx', '0', '/', '{\"treeCode\":\"code\",\"treeName\":\"value\",\"treeParentCode\":\"parent_id\",\"parentMenuId\":1}', 'admin', '2023-03-14 08:24:39', '', '2023-03-14 08:28:25', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 275 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', '主键', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-07-28 15:33:58', NULL, '2022-08-05 10:46:48');
INSERT INTO `gen_table_column` VALUES (2, '1', 'demo_name', '姓名', 'varchar(32)', 'String', 'demoName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-07-28 15:33:58', NULL, '2022-08-05 10:46:48');
INSERT INTO `gen_table_column` VALUES (3, '1', 'demo_sex', '性别', 'int(11)', 'Long', 'demoSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2022-07-28 15:33:58', NULL, '2022-08-05 10:46:48');
INSERT INTO `gen_table_column` VALUES (4, '1', 'demo_birth', '出生日期', 'date', 'Date', 'demoBirth', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2022-07-28 15:33:58', NULL, '2022-08-05 10:46:48');
INSERT INTO `gen_table_column` VALUES (5, '2', 'id', '主键', 'varchar(32)', 'String', 'id', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:57');
INSERT INTO `gen_table_column` VALUES (6, '2', 'fileName', '文件名称', 'varchar(255)', 'String', 'filename', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:57');
INSERT INTO `gen_table_column` VALUES (7, '2', 'fileSize', '文件大小', 'varchar(30)', 'String', 'filesize', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:57');
INSERT INTO `gen_table_column` VALUES (8, '2', 'fileUrl', '文件路径', 'varchar(255)', 'String', 'fileurl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:57');
INSERT INTO `gen_table_column` VALUES (9, '2', 'groupId', '文件组id', 'varchar(32)', 'String', 'groupid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (10, '2', 'createUser', '上传人', 'varchar(255)', 'String', 'createuser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (11, '2', 'createDate', '上传日期', 'datetime', 'Date', 'createdate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (12, '2', 'deptCode', '部门编码', 'varchar(255)', 'String', 'deptcode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (13, '2', 'updateDate', '修改日期', 'datetime', 'Date', 'updatedate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (14, '2', 'updateUser', '修改人', 'varchar(255)', 'String', 'updateuser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (15, '2', 'isdel', '是否删除(1 删除,0 未删除)', 'varchar(1)', 'String', 'isdel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (16, '2', 'standby1', '备用1', 'varchar(255)', 'String', 'standby1', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (17, '2', 'standby2', '备用2', 'varchar(255)', 'String', 'standby2', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (18, '2', 'standby3', '备用3', 'varchar(255)', 'String', 'standby3', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (19, '2', 'standby4', '备用4', 'varchar(255)', 'String', 'standby4', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (20, '2', 'standby5', '备用5', 'varchar(255)', 'String', 'standby5', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2022-08-08 10:44:24', '', '2022-08-08 10:48:58');
INSERT INTO `gen_table_column` VALUES (21, '3', 'id', '主键', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-08-10 10:15:46', NULL, '2022-11-02 16:10:23');
INSERT INTO `gen_table_column` VALUES (22, '3', 'demo_name', '姓名', 'varchar(32)', 'String', 'demoName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-08-10 10:15:46', NULL, '2022-11-02 16:10:23');
INSERT INTO `gen_table_column` VALUES (23, '3', 'demo_sex', '性别', 'int(11)', 'Long', 'demoSex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2022-08-10 10:15:46', NULL, '2022-11-02 16:10:23');
INSERT INTO `gen_table_column` VALUES (24, '3', 'demo_birth', '出生日期', 'date', 'Date', 'demoBirth', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2022-08-10 10:15:46', NULL, '2022-11-02 16:10:23');
INSERT INTO `gen_table_column` VALUES (25, '4', 'id', '主键', 'varchar(32)', 'String', 'id', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-08-25 08:54:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (26, '4', 'fileName', '文件名称', 'varchar(255)', 'String', 'filename', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (27, '4', 'fileSize', '文件大小', 'varchar(30)', 'String', 'filesize', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (28, '4', 'fileUrl', '文件路径', 'varchar(255)', 'String', 'fileurl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (29, '4', 'groupId', '文件组id', 'varchar(32)', 'String', 'groupid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (30, '4', 'createUser', '上传人', 'varchar(255)', 'String', 'createuser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (31, '4', 'createDate', '上传日期', 'datetime', 'Date', 'createdate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (32, '4', 'deptCode', '部门编码', 'varchar(255)', 'String', 'deptcode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (33, '4', 'updateDate', '修改日期', 'datetime', 'Date', 'updatedate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (34, '4', 'updateUser', '修改人', 'varchar(255)', 'String', 'updateuser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (35, '4', 'isdel', '是否删除(1 删除,0 未删除)', 'varchar(1)', 'String', 'isdel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (36, '4', 'standby1', '备用1', 'varchar(255)', 'String', 'standby1', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (37, '4', 'standby2', '备用2', 'varchar(255)', 'String', 'standby2', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (38, '4', 'standby3', '备用3', 'varchar(255)', 'String', 'standby3', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (39, '4', 'standby4', '备用4', 'varchar(255)', 'String', 'standby4', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (40, '4', 'standby5', '备用5', 'varchar(255)', 'String', 'standby5', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (41, '4', 'fileType', '文件类型', 'varchar(255)', 'String', 'filetype', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 17, 'admin', '2022-08-25 08:54:21', '', NULL);
INSERT INTO `gen_table_column` VALUES (58, '7', 'notice_id', '标识ID', 'varchar(32)', 'String', 'noticeId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 1, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (59, '7', 'status', '已读状态（0未读 1已读）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 2, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (60, '7', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (61, '7', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (62, '7', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (63, '7', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2022-08-29 09:18:56', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (64, '7', 'id', '主键', 'varchar(32)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2022-08-29 09:18:57', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (65, '7', 'user_id', '用户主键', 'varchar(32)', 'String', 'userId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-08-29 09:18:57', '', '2022-08-29 09:19:55');
INSERT INTO `gen_table_column` VALUES (66, '8', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-06 10:16:59', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (67, '8', 'name', '姓名', 'varchar(255)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-09-06 10:16:59', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (68, '8', 'sex', '性别', 'varchar(32)', 'String', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2022-09-06 10:17:00', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (69, '8', 'birthdate', '生日', 'datetime', 'Date', 'birthdate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2022-09-06 10:17:00', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (70, '8', 'intro', '介绍', 'varchar(255)', 'String', 'intro', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-09-06 10:17:00', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (71, '8', 'address', '地址', 'varchar(255)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-06 10:17:00', '', '2022-09-06 11:28:44');
INSERT INTO `gen_table_column` VALUES (72, '9', 'ID_', '主键', 'varchar(64)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-14 08:37:38', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (73, '9', 'REV_', NULL, 'int(11)', 'Long', 'rev', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (74, '9', 'NAME_', '名称', 'varchar(255)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (75, '9', 'KEY_', '标识', 'varchar(255)', 'String', 'key', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (76, '9', 'CATEGORY_', NULL, 'varchar(255)', 'String', 'category', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (77, '9', 'CREATE_TIME_', '创建时间', 'timestamp(3)', 'Date', 'createTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (78, '9', 'LAST_UPDATE_TIME_', '更新时间', 'timestamp(3)', 'Date', 'lastUpdateTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (79, '9', 'VERSION_', '版本', 'int(11)', 'Long', 'version', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (80, '9', 'META_INFO_', NULL, 'varchar(4000)', 'String', 'metaInfo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (81, '9', 'DEPLOYMENT_ID_', NULL, 'varchar(64)', 'String', 'deploymentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (82, '9', 'EDITOR_SOURCE_VALUE_ID_', NULL, 'varchar(64)', 'String', 'editorSourceValueId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (83, '9', 'EDITOR_SOURCE_EXTRA_VALUE_ID_', NULL, 'varchar(64)', 'String', 'editorSourceExtraValueId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (84, '9', 'TENANT_ID_', NULL, 'varchar(255)', 'String', 'tenantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-09-14 08:37:39', '', '2022-09-14 08:54:03');
INSERT INTO `gen_table_column` VALUES (85, '10', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (86, '10', 'inst_id', '工作流实例ID', 'varchar(64)', 'String', 'instId', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (87, '10', 'comment', '请假事由', 'varchar(255)', 'String', 'comment', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (88, '10', 'status', '状态 ', 'char(1)', 'String', 'status', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'radio', '', 4, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (89, '10', 'del_flag', '删除标志', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (90, '10', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (91, '10', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (92, '10', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (93, '10', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2022-09-15 16:22:11', '', '2022-09-15 16:25:44');
INSERT INTO `gen_table_column` VALUES (94, '11', 'id', '主键id', 'bigint(20)', 'Long', 'id', '1', '1', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (95, '11', 'column_id', '栏目id', 'bigint(20)', 'Long', 'columnId', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (96, '11', 'title', '标题', 'varchar(60)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (97, '11', 'content', '内容', 'longtext', 'String', 'content', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'editor', '', 4, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (98, '11', 'create_date', '时间', 'datetime', 'Date', 'createDate', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (99, '11', 'create_name', '创建人', 'varchar(40)', 'String', 'createName', '0', '0', NULL, NULL, NULL, NULL, NULL, 'LIKE', 'input', '', 6, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (100, '11', 'create_id', '创建id', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (101, '11', 'title_img_id', '标题图片id', 'bigint(20)', 'Long', 'titleImgId', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (104, '11', 'title_img_url', '文章标题图片', 'varchar(250)', 'String', 'titleImgUrl', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'imageUpload', '', 11, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (105, '12', 'id', '标识', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-10 13:52:15');
INSERT INTO `gen_table_column` VALUES (106, '12', 'parent_id', '父级id', 'bigint(20)', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-10 13:52:15');
INSERT INTO `gen_table_column` VALUES (107, '12', 'name', '栏目名称', 'varchar(40)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-10-09 09:45:18', NULL, '2022-10-10 13:52:15');
INSERT INTO `gen_table_column` VALUES (108, '12', 'create_date', '创建时间', 'datetime', 'Date', 'createDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:16');
INSERT INTO `gen_table_column` VALUES (109, '12', 'create_name', '创建人名称', 'varchar(40)', 'String', 'createName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:16');
INSERT INTO `gen_table_column` VALUES (110, '12', 'create_id', '创建人id', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:16');
INSERT INTO `gen_table_column` VALUES (112, '13', 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (113, '13', 'title', '链接标题', 'varchar(100)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (114, '13', 'img_url', '图片链接', 'varchar(250)', 'String', 'imgUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (115, '13', 'link_url', '导航链接', 'varchar(300)', 'String', 'linkUrl', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (116, '13', 'create_name', '创建人名称', 'varchar(100)', 'String', 'createName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (117, '13', 'create_date', '创建时间', 'datetime', 'Date', 'createDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2022-10-09 09:45:19', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (119, '14', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-10-09 09:45:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (120, '14', 'article_id', '文章id', 'bigint(20)', 'Long', 'articleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-10-09 09:45:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (121, '14', 'url', '图片存储路径', 'varchar(300)', 'String', 'url', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-10-09 09:45:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (122, '14', 'describe', '图片描述', 'varchar(100)', 'String', 'describe', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-10-09 09:45:19', '', NULL);
INSERT INTO `gen_table_column` VALUES (123, '11', 'art_type', '1:草稿 2:发布', 'int(4)', 'Integer', 'artType', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'select', 'cms_status', 9, '', '2022-10-09 10:22:43', '', '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (124, '11', 'art_delete', '逻辑删除1:删除0:为删除', 'int(4)', 'Integer', 'artDelete', '0', '0', NULL, NULL, NULL, NULL, NULL, 'EQ', 'input', '', 10, '', '2022-10-09 10:22:43', '', '2022-10-09 10:56:59');
INSERT INTO `gen_table_column` VALUES (125, '12', 'col_delete', '逻辑删除:1删除 0:未删除', 'int(10)', 'Integer', 'colDelete', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2022-10-09 13:23:51', NULL, '2022-10-10 13:52:16');
INSERT INTO `gen_table_column` VALUES (126, '13', 'isDel', '逻辑删除:0未删除 1:已删除', 'int(11)', 'Long', 'isdel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2022-10-10 13:37:10', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (127, '13', 'create_id', '创建人id', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, '', '2022-10-10 13:37:10', NULL, '2022-10-10 13:52:30');
INSERT INTO `gen_table_column` VALUES (128, '15', 'id', '', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (129, '15', 'copyright', '版权', 'varchar(100)', 'String', 'copyright', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (130, '15', 'wx', '联系微信号', 'varchar(250)', 'String', 'wx', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (131, '15', 'address', '地址', 'varchar(300)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (132, '15', 'create_name', '创建人名称', 'varchar(100)', 'String', 'createName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (133, '15', 'create_date', '创建时间', 'datetime', 'Date', 'createDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (134, '15', 'isDel', '逻辑删除:0未删除 1:已删除', 'int(11)', 'Long', 'isdel', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'sys_show_hide', 7, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (135, '15', 'create_id', '创建人id', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (136, '15', 'zip_code', '邮编', 'varchar(250)', 'String', 'zipCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (137, '15', 'copyright_name', '版权所有', 'varchar(100)', 'String', 'copyrightName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 10, 'admin', '2022-10-12 15:22:57', NULL, '2022-11-02 15:40:24');
INSERT INTO `gen_table_column` VALUES (138, '16', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:02:25');
INSERT INTO `gen_table_column` VALUES (139, '16', 'article_id', '所属内容', 'bigint(20)', 'Long', 'articleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:02:25');
INSERT INTO `gen_table_column` VALUES (140, '16', 'rule_id', '所属规则', 'bigint(20)', 'Long', 'ruleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:02:25');
INSERT INTO `gen_table_column` VALUES (141, '17', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28');
INSERT INTO `gen_table_column` VALUES (142, '17', 'rule_id', '所属规则', 'bigint(20)', 'Long', 'ruleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28');
INSERT INTO `gen_table_column` VALUES (143, '17', 'attribute_name', '属性名称', 'varchar(64)', 'String', 'attributeName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28');
INSERT INTO `gen_table_column` VALUES (144, '17', 'attribute_status', '属性状态', 'varchar(32)', 'String', 'attributeStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 4, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28');
INSERT INTO `gen_table_column` VALUES (145, '17', 'attribute_order', '排序', 'varchar(32)', 'String', 'attributeOrder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:03:28');
INSERT INTO `gen_table_column` VALUES (146, '18', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-01 09:00:32', '', '2022-11-01 09:04:25');
INSERT INTO `gen_table_column` VALUES (147, '18', 'rule_id', '所属规则', 'bigint(20)', 'Long', 'ruleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:26');
INSERT INTO `gen_table_column` VALUES (148, '18', 'article_id', '所属内容', 'bigint(20)', 'Long', 'articleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:26');
INSERT INTO `gen_table_column` VALUES (149, '18', 'attr_value', '值', 'varchar(255)', 'String', 'attrValue', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:27');
INSERT INTO `gen_table_column` VALUES (150, '18', 'attr_status', '状态', 'varchar(32)', 'String', 'attrStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:28');
INSERT INTO `gen_table_column` VALUES (151, '18', 'attr_order', '排序', 'varchar(32)', 'String', 'attrOrder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:28');
INSERT INTO `gen_table_column` VALUES (152, '18', 'attr_id', '所属属性', 'bigint(20)', 'Long', 'attrId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:04:28');
INSERT INTO `gen_table_column` VALUES (153, '19', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:45:15');
INSERT INTO `gen_table_column` VALUES (154, '19', 'rule_name', '规则名称', 'varchar(32)', 'String', 'ruleName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:45:15');
INSERT INTO `gen_table_column` VALUES (155, '19', 'rule_status', '状态', 'varchar(32)', 'String', 'ruleStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', 'sys_normal_disable', 3, 'admin', '2022-11-01 09:00:33', '', '2022-11-01 09:45:15');
INSERT INTO `gen_table_column` VALUES (156, '20', 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (157, '20', 'dept_id', '部门ID', 'bigint(20)', 'Long', 'deptId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (158, '20', 'user_name', '用户账号', 'varchar(30)', 'String', 'userName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (159, '20', 'nick_name', '用户昵称', 'varchar(30)', 'String', 'nickName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (160, '20', 'user_type', '用户类型（00系统用户）', 'varchar(2)', 'String', 'userType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (161, '20', 'email', '用户邮箱', 'varchar(50)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (162, '20', 'phonenumber', '手机号码', 'varchar(11)', 'String', 'phonenumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (163, '20', 'sex', '用户性别（0男 1女 2未知）', 'char(1)', 'String', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 8, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (164, '20', 'avatar', '头像地址', 'varchar(1024)', 'String', 'avatar', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (165, '20', 'password', '密码', 'varchar(100)', 'String', 'password', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (166, '20', 'status', '帐号状态（0正常 1停用）', 'char(1)', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (167, '20', 'del_flag', '删除标志（0代表存在 2代表删除）', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (168, '20', 'login_ip', '最后登录IP', 'varchar(128)', 'String', 'loginIp', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (169, '20', 'login_date', '最后登录时间', 'datetime', 'Date', 'loginDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 14, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (170, '20', 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (171, '20', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (172, '20', 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (173, '20', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (174, '20', 'remark', '备注', 'varchar(500)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', '', 19, 'admin', '2022-11-02 15:02:20', '', NULL);
INSERT INTO `gen_table_column` VALUES (175, '21', 'id', '主键', 'varchar(32)', 'String', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-02 15:36:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (176, '21', 'user_id', '用户主键', 'varchar(32)', 'String', 'userId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-02 15:36:45', '', NULL);
INSERT INTO `gen_table_column` VALUES (177, '22', 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-07 09:37:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (178, '22', 'post_id', '岗位ID', 'bigint(20)', 'Long', 'postId', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 2, 'admin', '2022-11-07 09:37:49', '', NULL);
INSERT INTO `gen_table_column` VALUES (179, '23', 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-11-08 16:10:05', '', '2022-11-08 16:12:38');
INSERT INTO `gen_table_column` VALUES (180, '23', 'column_id', '所属栏目', 'bigint(20)', 'Long', 'columnId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-11-08 16:10:05', '', '2022-11-08 16:12:38');
INSERT INTO `gen_table_column` VALUES (181, '23', 'rule_id', '所属规则id', 'bigint(20)', 'Long', 'ruleId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-11-08 16:10:05', '', '2022-11-08 16:12:38');
INSERT INTO `gen_table_column` VALUES (182, '24', 'id', 'id', 'int(2)', 'Integer', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-12-05 16:43:35', NULL, '2022-12-06 10:24:49');
INSERT INTO `gen_table_column` VALUES (183, '24', 'name', '姓名', 'varchar(64)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2022-12-05 16:43:35', NULL, '2022-12-06 10:24:49');
INSERT INTO `gen_table_column` VALUES (184, '24', 'mobile', '手机号', 'varchar(11)', 'String', 'mobile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-12-05 16:43:35', NULL, '2022-12-06 10:24:49');
INSERT INTO `gen_table_column` VALUES (185, '24', 'address', '地址', 'varchar(300)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-12-05 16:43:35', NULL, '2022-12-06 10:24:49');
INSERT INTO `gen_table_column` VALUES (186, '24', 'school', '毕业学校', 'varchar(256)', 'String', 'school', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-12-05 16:43:35', NULL, '2022-12-06 10:24:49');
INSERT INTO `gen_table_column` VALUES (187, '25', 'id', 'id', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', NULL, 1, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (188, '25', 'member_id', '人员id', 'int(20)', 'Long', 'memberId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'sys_user_sex', 2, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (190, '25', 'company_name', '公司名称', 'varchar(200)', 'String', 'companyName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (191, '25', 'credit_code', '社会统一信用代码', 'varchar(100)', 'String', 'creditCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (195, '25', 'agent', '委托代理人', 'varchar(64)', 'String', 'agent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (196, '25', 'agent_mobile', '委托代理人联系电话', 'varchar(64)', 'String', 'agentMobile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (197, '25', 'legal_representative', '法定代表人', 'varchar(64)', 'String', 'legalRepresentative', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (198, '25', 'company_address', '公司地址', 'varchar(200)', 'String', 'companyAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (201, '25', 'create_id', '创建人', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (202, '25', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', NULL, 10, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (203, '25', 'modify_id', '修改人', 'bigint(20)', 'Long', 'modifyId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (204, '25', 'modify_time', '修改时间', 'timestamp', 'Date', 'modifyTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (205, '25', 'agent_email', '委托代理人邮箱', 'varchar(64)', 'String', 'agentEmail', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (206, '25', 'bank', '开户行', 'varchar(64)', 'String', 'bank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (207, '25', 'bank_no', '银行账号', 'varchar(19)', 'String', 'bankNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-12-08 09:59:42', NULL, '2022-12-26 13:44:55');
INSERT INTO `gen_table_column` VALUES (208, '26', 'id', 'id', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (209, '26', 'family_code', '家庭编码', 'varchar(16)', 'String', 'familyCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (210, '26', 'name', '家庭成员姓名', 'varchar(64)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (211, '26', 'id_card', '家庭成员身份证号', 'varchar(18)', 'String', 'idCard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (212, '26', 'mobile', '家庭成员手机号', 'varchar(11)', 'String', 'mobile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (213, '26', 'relationship', '与户主关系', 'varchar(64)', 'String', 'relationship', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:38:53');
INSERT INTO `gen_table_column` VALUES (214, '27', 'id', 'id', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (215, '27', 'name', '农户姓名', 'varchar(64)', 'String', 'name', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (216, '27', 'id_card', '身份证号', 'varchar(18)', 'String', 'idCard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (217, '27', 'mobile', '联系电话', 'varchar(64)', 'String', 'mobile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (218, '27', 'province', '省', 'varchar(64)', 'String', 'province', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (219, '27', 'city', '市', 'varchar(64)', 'String', 'city', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (220, '27', 'county', '县', 'varchar(64)', 'String', 'county', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (221, '27', 'country', '乡', 'varchar(64)', 'String', 'country', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (222, '27', 'village', '村', 'varchar(64)', 'String', 'village', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (223, '27', 'address', '地址信息', 'varchar(500)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 10, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (224, '27', 'black_white_list', '黑白灰名单', 'varchar(64)', 'String', 'blackWhiteList', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (225, '27', 'new_old_users', '新老用户', 'varchar(64)', 'String', 'newOldUsers', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (226, '27', 'black_white_reason', '黑白名单原因', 'varchar(200)', 'String', 'blackWhiteReason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (227, '27', 'rate_status', '评级状态-字典（RATING_STATUS）', 'int(4)', 'Integer', 'rateStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 14, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (228, '27', 'users_rate', '用户评级', 'varchar(64)', 'String', 'usersRate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:30');
INSERT INTO `gen_table_column` VALUES (229, '27', 'users_tag', '用户标签-字典（USERS_TAG）', 'int(4)', 'Integer', 'usersTag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (230, '27', 'home_code', '家庭编码', 'varchar(16)', 'String', 'homeCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (231, '27', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (232, '27', 'create_id', '创建人', 'varchar(64)', 'String', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (233, '27', 'modify_time', '修改时间', 'datetime', 'Date', 'modifyTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 20, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (234, '27', 'modify_id', '修改人', 'varchar(64)', 'String', 'modifyId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-01-07 11:35:03', '', '2023-01-07 11:37:31');
INSERT INTO `gen_table_column` VALUES (235, '28', 'id', 'id', 'int(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (236, '28', 'farmer_id', '农户id', 'int(20)', 'Long', 'farmerId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (237, '28', 'id_cqrd', '身份证号', 'varchar(18)', 'String', 'idCqrd', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (238, '28', 'province', '省', 'varchar(2)', 'String', 'province', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (239, '28', 'city', '市', 'varchar(4)', 'String', 'city', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (240, '28', 'county', '县', 'varchar(6)', 'String', 'county', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (241, '28', 'country', '乡', 'varchar(9)', 'String', 'country', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (242, '28', 'village', '村', 'varchar(12)', 'String', 'village', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (243, '28', 'address', '地址信息', 'varchar(300)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (244, '28', 'loan_products', '贷款产品', 'varchar(200)', 'String', 'loanProducts', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (245, '28', 'loan_bank', '贷款银行', 'varchar(200)', 'String', 'loanBank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (246, '28', 'loan_amount', '贷款金额（元）', 'decimal(10,0)', 'Long', 'loanAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (247, '28', 'approve_amount', '放款金额（元）', 'decimal(10,0)', 'Long', 'approveAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (248, '28', 'insured_products', '参保产品', 'varchar(200)', 'String', 'insuredProducts', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (249, '28', 'loan_status', '贷款状态', 'varchar(64)', 'String', 'loanStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 15, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (250, '28', 'residual_maturity', '剩余期限（天）', 'int(4)', 'Integer', 'residualMaturity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (251, '28', 'loan_term', '贷款期限（月）', 'int(4)', 'Integer', 'loanTerm', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (252, '28', 'credit_time', '申请日期', 'datetime', 'Date', 'creditTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 18, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (253, '28', 'credit_end_time', '到期日期', 'datetime', 'Date', 'creditEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 19, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (254, '28', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 20, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (255, '28', 'create_id', '创建人', 'varchar(64)', 'String', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (256, '28', 'modify_time', '修改时间', 'datetime', 'Date', 'modifyTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 22, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (257, '28', 'modify_id', '修改人', 'varchar(64)', 'String', 'modifyId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2023-01-07 11:35:03', '', NULL);
INSERT INTO `gen_table_column` VALUES (258, '29', 'id', NULL, 'int(11)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-02-21 15:38:29', '', '2023-02-21 15:39:43');
INSERT INTO `gen_table_column` VALUES (259, '29', 'user_id', '用户ID', 'varchar(32)', 'String', 'userId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-02-21 15:38:30', '', '2023-02-21 15:39:43');
INSERT INTO `gen_table_column` VALUES (260, '29', 'merchant_id', '商户ID', 'varchar(32)', 'String', 'merchantId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-02-21 15:38:30', '', '2023-02-21 15:39:43');
INSERT INTO `gen_table_column` VALUES (261, '29', 'start_time', NULL, 'datetime', 'Date', 'startTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-02-21 15:38:30', '', '2023-02-21 15:39:43');
INSERT INTO `gen_table_column` VALUES (262, '30', 'id', NULL, 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-03-14 08:24:39', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (263, '30', 'type', '字典类型', 'varchar(32)', 'String', 'type', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (264, '30', 'level', '字典级别', 'varchar(32)', 'String', 'level', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 3, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (265, '30', 'code', '字典项编码', 'varchar(100)', 'String', 'code', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 4, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (266, '30', 'value', '字典项值', 'varchar(100)', 'String', 'value', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (267, '30', 'description', '描述', 'varchar(200)', 'String', 'description', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (268, '30', 'sort', '排序', 'int(11)', 'Long', 'sort', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 7, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (269, '30', 'parent_id', '父节点ID', 'varchar(20)', 'String', 'parentId', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 8, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:25');
INSERT INTO `gen_table_column` VALUES (270, '30', 'is_enabled', '是否可用', 'tinyint(4)', 'Integer', 'isEnabled', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', 'sys_yes_no', 9, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:26');
INSERT INTO `gen_table_column` VALUES (271, '30', 'create_by', '创建人', 'bigint(20)', 'Long', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-03-14 08:24:40', '', '2023-03-14 08:28:26');
INSERT INTO `gen_table_column` VALUES (272, '30', 'create_time', '创建时间', 'timestamp', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-03-14 08:24:41', '', '2023-03-14 08:28:26');
INSERT INTO `gen_table_column` VALUES (273, '30', 'update_by', '修改人', 'bigint(20)', 'Long', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-03-14 08:24:41', '', '2023-03-14 08:28:26');
INSERT INTO `gen_table_column` VALUES (274, '30', 'update_time', '修改时间', 'timestamp', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2023-03-14 08:24:41', '', '2023-03-14 08:28:26');

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '参数配置表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-07-28 10:49:51', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-07-28 10:49:52', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-07-28 10:49:52', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', 'true', 'Y', 'admin', '2022-07-28 10:49:52', 'admin', '2022-08-11 13:53:00', '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'true', 'Y', 'admin', '2022-07-28 10:49:52', 'admin', '2022-08-23 11:05:40', '是否开启注册用户功能（true开启，false关闭）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 110 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '融信科技', 0, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '长春总公司123', 1, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '彭帅', '2022-11-08 14:44:34');
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '吉林分公司', 2, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '融信', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-07-28 10:49:34', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典数据表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:50', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-07-28 10:49:51', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (29, 0, '草稿', '1', 'cms_status', NULL, 'primary', 'N', '0', 'admin', '2022-10-09 10:54:54', 'admin', '2022-10-09 10:55:28', NULL);
INSERT INTO `sys_dict_data` VALUES (30, 1, '发布', '2', 'cms_status', NULL, 'success', 'N', '0', 'admin', '2022-10-09 10:55:16', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典类型表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-07-28 10:49:49', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '发布状态', 'cms_status', '0', 'admin', '2022-10-09 10:53:53', '', NULL, '发布状态列表');

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 933 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (932, 'admin', '127.0.0.1', '内网IP', 'Chrome 11', 'Windows 10', '0', '登录成功', '2023-03-20 09:50:55');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2125 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2022-07-28 10:49:37', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2022-07-28 10:49:37', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2022-07-28 10:49:37', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2022-07-28 10:49:37', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2022-07-28 10:49:37', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2022-07-28 10:49:37', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2022-07-28 10:49:37', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2022-07-28 10:49:37', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2022-07-28 10:49:38', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2022-07-28 10:49:38', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2022-07-28 10:49:38', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2022-07-28 10:49:38', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2022-07-28 10:49:38', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2022-07-28 10:49:38', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2022-07-28 10:49:38', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2022-07-28 10:49:38', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2022-07-28 10:49:38', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2022-07-28 10:49:38', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2022-07-28 10:49:38', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2022-07-28 10:49:38', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2022-07-28 10:49:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2022-07-28 10:49:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2022-07-28 10:49:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2022-07-28 10:49:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2022-07-28 10:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2022-07-28 10:49:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 7, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 115, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 115, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 115, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 115, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 115, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2022-07-28 10:49:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2118, '业务字典', 1, 1, 'businessdict', 'business/dict/index', NULL, 1, 0, 'C', '0', '0', 'business:dict:list', 'dict', 'admin', '2023-03-14 08:33:01', 'admin', '2023-03-17 15:13:11', '业务字典菜单');
INSERT INTO `sys_menu` VALUES (2119, '业务字典查询', 2118, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'business:dict:query', '#', 'admin', '2023-03-14 08:33:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2120, '业务字典新增', 2118, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'business:dict:add', '#', 'admin', '2023-03-14 08:33:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2121, '业务字典修改', 2118, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'business:dict:edit', '#', 'admin', '2023-03-14 08:33:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2122, '业务字典删除', 2118, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'business:dict:remove', '#', 'admin', '2023-03-14 08:33:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2123, '业务字典导出', 2118, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'business:dict:export', '#', 'admin', '2023-03-14 08:33:02', '', NULL, '');

-- ----------------------------
-- Table structure for sys_nation
-- ----------------------------
DROP TABLE IF EXISTS `sys_nation`;
CREATE TABLE `sys_nation`  (
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_nation
-- ----------------------------
INSERT INTO `sys_nation` VALUES ('1', '汉族');
INSERT INTO `sys_nation` VALUES ('2', '回族');
INSERT INTO `sys_nation` VALUES ('3', '蒙古族');
INSERT INTO `sys_nation` VALUES ('4', '藏族');
INSERT INTO `sys_nation` VALUES ('5', '维吾尔族');
INSERT INTO `sys_nation` VALUES ('6', '苗族');
INSERT INTO `sys_nation` VALUES ('7', '彝族');
INSERT INTO `sys_nation` VALUES ('8', '壮族');
INSERT INTO `sys_nation` VALUES ('9', '布依族');
INSERT INTO `sys_nation` VALUES ('10', '朝鲜族');
INSERT INTO `sys_nation` VALUES ('11', '满族');
INSERT INTO `sys_nation` VALUES ('12', '侗族');
INSERT INTO `sys_nation` VALUES ('13', '瑶族');
INSERT INTO `sys_nation` VALUES ('14', '白族');
INSERT INTO `sys_nation` VALUES ('15', '土家族');
INSERT INTO `sys_nation` VALUES ('16', '哈尼族');
INSERT INTO `sys_nation` VALUES ('17', '哈萨克族');
INSERT INTO `sys_nation` VALUES ('18', '傣族');
INSERT INTO `sys_nation` VALUES ('19', '黎族');
INSERT INTO `sys_nation` VALUES ('20', '僳僳族');
INSERT INTO `sys_nation` VALUES ('21', '佤族');
INSERT INTO `sys_nation` VALUES ('22', '畲族');
INSERT INTO `sys_nation` VALUES ('23', '高山族');
INSERT INTO `sys_nation` VALUES ('24', '拉祜族');
INSERT INTO `sys_nation` VALUES ('25', '水族');
INSERT INTO `sys_nation` VALUES ('26', '东乡族');
INSERT INTO `sys_nation` VALUES ('27', '纳西族');
INSERT INTO `sys_nation` VALUES ('28', '景颇族');
INSERT INTO `sys_nation` VALUES ('29', '柯尔克孜族');
INSERT INTO `sys_nation` VALUES ('30', '土族');
INSERT INTO `sys_nation` VALUES ('31', '达斡尔族');
INSERT INTO `sys_nation` VALUES ('32', '仫佬族');
INSERT INTO `sys_nation` VALUES ('33', '羌族');
INSERT INTO `sys_nation` VALUES ('34', '布朗族');
INSERT INTO `sys_nation` VALUES ('35', '撒拉族');
INSERT INTO `sys_nation` VALUES ('36', '毛南族');
INSERT INTO `sys_nation` VALUES ('37', '仡佬族');
INSERT INTO `sys_nation` VALUES ('38', '锡伯族');
INSERT INTO `sys_nation` VALUES ('39', '阿昌族');
INSERT INTO `sys_nation` VALUES ('40', '普米族');
INSERT INTO `sys_nation` VALUES ('41', '塔吉克族');
INSERT INTO `sys_nation` VALUES ('42', '怒族');
INSERT INTO `sys_nation` VALUES ('43', '乌孜别克族');
INSERT INTO `sys_nation` VALUES ('44', '俄罗斯族');
INSERT INTO `sys_nation` VALUES ('45', '鄂温克族');
INSERT INTO `sys_nation` VALUES ('46', '德昂族');
INSERT INTO `sys_nation` VALUES ('47', '保安族');
INSERT INTO `sys_nation` VALUES ('48', '裕固族');
INSERT INTO `sys_nation` VALUES ('49', '京族');
INSERT INTO `sys_nation` VALUES ('50', '塔塔尔族');
INSERT INTO `sys_nation` VALUES ('51', '独龙族');
INSERT INTO `sys_nation` VALUES ('52', '鄂伦春族');
INSERT INTO `sys_nation` VALUES ('53', '赫哲族');
INSERT INTO `sys_nation` VALUES ('54', '门巴族');
INSERT INTO `sys_nation` VALUES ('55', '珞巴族');
INSERT INTO `sys_nation` VALUES ('56', '基诺族');
INSERT INTO `sys_nation` VALUES ('1', '汉');
INSERT INTO `sys_nation` VALUES ('3', '蒙古');
INSERT INTO `sys_nation` VALUES ('10', '朝鲜');
INSERT INTO `sys_nation` VALUES ('11', '满');
INSERT INTO `sys_nation` VALUES ('38', '锡伯');
INSERT INTO `sys_nation` VALUES ('19', '黎');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(9) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '通知公告表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 融信新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2022-07-28 10:49:56', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 融信系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2022-07-28 10:49:56', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (29, '测试', '1', 0x3C703E3C696D67207372633D2268747470733A2F2F6A6E77712D6C616E642D7075622D746573742E6F73732D636E2D6265696A696E672E616C6979756E63732E636F6D2F6A6C6E637771772F706B67732F6465762F2F75706C6F61645750532E706E673F457870697265733D3139373739353933333026616D703B4F53534163636573734B657949643D556A794B444C314A6F32345A3654436D26616D703B5369676E61747572653D324848467970744F756939707747476C7753347164435557554A45253344223EE6B58BE8AF953C2F703E, '0', 'admin', '2022-08-29 16:16:38', 'admin', '2022-09-08 09:10:54', NULL);
INSERT INTO `sys_notice` VALUES (30, 'test', '1', 0x3C703E746573743C2F703E, '0', 'admin', '2022-09-28 16:25:56', '', NULL, NULL);
INSERT INTO `sys_notice` VALUES (31, '通知你', '1', 0x3C703EE9809AE79FA5E4BDA03C2F703E, '0', 'admin', '2022-09-28 16:26:36', '', NULL, NULL);
INSERT INTO `sys_notice` VALUES (32, '新同志', '1', 0x3C703EE696B0E5908CE5BF973C2F703E, '0', 'admin', '2022-09-28 16:27:13', '', NULL, NULL);
INSERT INTO `sys_notice` VALUES (33, 'testes', '1', 0x3C703E7465746574657465743C2F703E, '0', 'admin', '2022-09-28 16:57:23', '', NULL, NULL);
INSERT INTO `sys_notice` VALUES (34, '2022-12.06 版本更新', '1', 0x3C703EE78988E69CACE69BB4E696B03C2F703E, '0', 'admin', '2022-12-06 10:33:34', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_notice_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice_user`;
CREATE TABLE `sys_notice_user`  (
  `notice_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标识ID',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '已读状态（0未读 1已读）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户通知公告关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_notice_user
-- ----------------------------
INSERT INTO `sys_notice_user` VALUES ('30', '1', '1', '2022-09-28 16:24:05', '', NULL, '19bbb61437b441b8b333d72314bf4adb', '1');
INSERT INTO `sys_notice_user` VALUES ('31', '0', '1', '2022-09-28 16:24:45', '', NULL, '1ed69eb498054de3876828d1ece42e13', '2');
INSERT INTO `sys_notice_user` VALUES ('30', '0', '1', '2022-09-28 16:24:05', '', NULL, '416c483911a4429096f34ff3be7cb2a3', '7');
INSERT INTO `sys_notice_user` VALUES ('30', '0', '1', '2022-09-28 16:24:05', '', NULL, '41a9619c54f041a9960fbd90a5193122', '3');
INSERT INTO `sys_notice_user` VALUES ('34', '0', '1', '2022-12-06 10:32:48', '', NULL, '41ed99bd9d3148e2baaaf2ff69c549b8', '7');
INSERT INTO `sys_notice_user` VALUES ('32', '0', '1', '2022-09-28 16:25:22', '', NULL, '4d3380e7f9244651b12ed441bfde73e2', '3');
INSERT INTO `sys_notice_user` VALUES ('33', '0', '1', '2022-09-28 16:55:32', '', NULL, '4e2bbbbe27404666aef701e19b87ef76', '2');
INSERT INTO `sys_notice_user` VALUES ('33', '0', '1', '2022-09-28 16:55:32', '', NULL, '513f838094d842a585786a8fd7a6c277', '3');
INSERT INTO `sys_notice_user` VALUES ('31', '0', '1', '2022-09-28 16:24:45', '', NULL, '5ac26b2c5d444614901fa2f26879b19f', '3');
INSERT INTO `sys_notice_user` VALUES ('32', '1', '1', '2022-09-28 16:25:22', '', NULL, '6289066a519a42e4bebc6c3dae4333a7', '1');
INSERT INTO `sys_notice_user` VALUES ('34', '0', '1', '2022-12-06 10:32:48', '', NULL, '699e5ea04bd941239b6ab7e6fbab7f69', '3');
INSERT INTO `sys_notice_user` VALUES ('33', '0', '1', '2022-09-28 16:55:32', '', NULL, '79a43e5c56fa4237861c746ddf47d7a1', '7');
INSERT INTO `sys_notice_user` VALUES ('34', '0', '1', '2022-12-06 10:32:48', '', NULL, '864245ab601b498d992a5f275d7397a2', '8');
INSERT INTO `sys_notice_user` VALUES ('34', '0', '1', '2022-12-06 10:32:48', '', NULL, '91fb9bd12e3c42d9b0eeb91249d6942e', '2');
INSERT INTO `sys_notice_user` VALUES ('32', '0', '1', '2022-09-28 16:25:22', '', NULL, '953c9befea2f4d2caad74f5bc38dbe74', '2');
INSERT INTO `sys_notice_user` VALUES ('34', '1', '1', '2022-12-06 10:32:48', '', NULL, 'a36d20bfdbbd4f2daab787d820f91cc6', '1');
INSERT INTO `sys_notice_user` VALUES ('33', '1', '1', '2022-09-28 16:55:32', '', NULL, 'a52351dd864549598058e165d9d3ba73', '1');
INSERT INTO `sys_notice_user` VALUES ('29', '1', '1', '2022-08-29 16:14:51', '', NULL, 'a75b3dabad3e4f658f2b9937bccc1f72', '1');
INSERT INTO `sys_notice_user` VALUES ('31', '1', '1', '2022-09-28 16:24:45', '', NULL, 'adddc50880bd4f45a69ec8529c8085fd', '1');
INSERT INTO `sys_notice_user` VALUES ('32', '0', '1', '2022-09-28 16:25:22', '', NULL, 'b31632455f9840cb98517c95c8dfdac4', '7');
INSERT INTO `sys_notice_user` VALUES ('29', '1', '1', '2022-08-29 16:14:51', '', NULL, 'ba0ae09b86ba47b485df8de63685b6b2', '1');
INSERT INTO `sys_notice_user` VALUES ('31', '0', '1', '2022-09-28 16:24:45', '', NULL, 'ccd1af03077d426ea72b4c5efc5008b1', '7');
INSERT INTO `sys_notice_user` VALUES ('29', '1', '1', '2022-08-29 16:14:51', '', NULL, 'f7d5983a399943f2b8ff7a3c6e56ac9f', '1');
INSERT INTO `sys_notice_user` VALUES ('29', '1', '1', '2022-08-29 16:14:52', '', NULL, 'fb8200882d63435aba184b7bf4075a2f', '1');
INSERT INTO `sys_notice_user` VALUES ('30', '0', '1', '2022-09-28 16:24:05', '', NULL, 'fd83dad687e2464aa8a3ccaf14f0b245', '2');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1202 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志记录' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '岗位信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2022-07-28 10:49:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2022-07-28 10:49:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2022-07-28 10:49:36', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2022-07-28 10:49:36', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2022-07-28 10:49:36', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2022-07-28 10:49:37', '彭帅', '2022-11-03 09:36:52', '普通角色');
INSERT INTO `sys_role` VALUES (3, 'test', 'test', 3, '1', 1, 1, '0', '0', 'admin', '2022-11-03 09:11:44', 'admin', '2022-11-09 10:41:27', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 102);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 109);
INSERT INTO `sys_role_menu` VALUES (3, 110);
INSERT INTO `sys_role_menu` VALUES (3, 111);
INSERT INTO `sys_role_menu` VALUES (3, 112);
INSERT INTO `sys_role_menu` VALUES (3, 113);
INSERT INTO `sys_role_menu` VALUES (3, 114);
INSERT INTO `sys_role_menu` VALUES (3, 115);
INSERT INTO `sys_role_menu` VALUES (3, 116);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 501);
INSERT INTO `sys_role_menu` VALUES (3, 1001);
INSERT INTO `sys_role_menu` VALUES (3, 1002);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);
INSERT INTO `sys_role_menu` VALUES (3, 1011);
INSERT INTO `sys_role_menu` VALUES (3, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1013);
INSERT INTO `sys_role_menu` VALUES (3, 1014);
INSERT INTO `sys_role_menu` VALUES (3, 1015);
INSERT INTO `sys_role_menu` VALUES (3, 1016);
INSERT INTO `sys_role_menu` VALUES (3, 1017);
INSERT INTO `sys_role_menu` VALUES (3, 1018);
INSERT INTO `sys_role_menu` VALUES (3, 1019);
INSERT INTO `sys_role_menu` VALUES (3, 1020);
INSERT INTO `sys_role_menu` VALUES (3, 1021);
INSERT INTO `sys_role_menu` VALUES (3, 1022);
INSERT INTO `sys_role_menu` VALUES (3, 1023);
INSERT INTO `sys_role_menu` VALUES (3, 1024);
INSERT INTO `sys_role_menu` VALUES (3, 1025);
INSERT INTO `sys_role_menu` VALUES (3, 1026);
INSERT INTO `sys_role_menu` VALUES (3, 1027);
INSERT INTO `sys_role_menu` VALUES (3, 1028);
INSERT INTO `sys_role_menu` VALUES (3, 1029);
INSERT INTO `sys_role_menu` VALUES (3, 1030);
INSERT INTO `sys_role_menu` VALUES (3, 1031);
INSERT INTO `sys_role_menu` VALUES (3, 1032);
INSERT INTO `sys_role_menu` VALUES (3, 1033);
INSERT INTO `sys_role_menu` VALUES (3, 1034);
INSERT INTO `sys_role_menu` VALUES (3, 1035);
INSERT INTO `sys_role_menu` VALUES (3, 1036);
INSERT INTO `sys_role_menu` VALUES (3, 1037);
INSERT INTO `sys_role_menu` VALUES (3, 1038);
INSERT INTO `sys_role_menu` VALUES (3, 1039);
INSERT INTO `sys_role_menu` VALUES (3, 1040);
INSERT INTO `sys_role_menu` VALUES (3, 1041);
INSERT INTO `sys_role_menu` VALUES (3, 1042);
INSERT INTO `sys_role_menu` VALUES (3, 1043);
INSERT INTO `sys_role_menu` VALUES (3, 1044);
INSERT INTO `sys_role_menu` VALUES (3, 1045);
INSERT INTO `sys_role_menu` VALUES (3, 1046);
INSERT INTO `sys_role_menu` VALUES (3, 1047);
INSERT INTO `sys_role_menu` VALUES (3, 1048);
INSERT INTO `sys_role_menu` VALUES (3, 1049);
INSERT INTO `sys_role_menu` VALUES (3, 1050);
INSERT INTO `sys_role_menu` VALUES (3, 1051);
INSERT INTO `sys_role_menu` VALUES (3, 1052);
INSERT INTO `sys_role_menu` VALUES (3, 1053);
INSERT INTO `sys_role_menu` VALUES (3, 1054);
INSERT INTO `sys_role_menu` VALUES (3, 1055);
INSERT INTO `sys_role_menu` VALUES (3, 1056);
INSERT INTO `sys_role_menu` VALUES (3, 1057);
INSERT INTO `sys_role_menu` VALUES (3, 1058);
INSERT INTO `sys_role_menu` VALUES (3, 1059);
INSERT INTO `sys_role_menu` VALUES (3, 1060);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '融信', '00', 'ry@163.com', '15888888888', '1', 'https://jnwq-land-pub-test.oss-cn-beijing.aliyuncs.com/jlncwqw/pkgs/dev/avatar2022/09/08/blob_20220908085659A002.jpeg?Expires=1977958620&OSSAccessKeyId=UjyKDL1Jo24Z6TCm&Signature=RE5%2F89a2z3kh9amze8i3SpweiZ8%3D', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-03-20 09:50:57', 'admin', '2022-07-28 10:49:35', '', '2023-03-20 09:50:54', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '融信', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$z1Jgw4AqH8iGAXE3PnL1h.Zfjw7gHkFzuj.FCJruKEKiQFz.asO/G', '0', '0', '124.235.209.122', '2022-11-02 16:03:37', 'admin', '2022-07-28 10:49:35', 'admin', '2022-11-09 10:41:16', '测试员');
INSERT INTO `sys_user` VALUES (3, 103, 'zs', 'zs', '00', '', '15888888887', '0', '', '$2a$10$aj3S77nNXAxlipvlBHh6Oe.BhGETSNrULrpazQNNaTuAN/MUPn8di', '0', '0', '127.0.0.1', '2022-08-30 14:41:35', '', '2022-08-23 11:06:19', 'admin', '2022-08-30 14:43:22', NULL);
INSERT INTO `sys_user` VALUES (7, NULL, '13604434551', '13604434551', '00', '', '', '0', '', '$2a$10$/atYoQbRbgAmODCaRrXvceqZz02k7ksdCu2Q5S0rcoNbp705zGxnu', '0', '0', '127.0.0.1', '2022-11-11 16:06:40', '', '2022-08-23 16:24:41', 'admin', '2022-11-11 16:08:13', NULL);
INSERT INTO `sys_user` VALUES (8, 100, '彭帅', '彭帅', '00', '', '15944054312', '0', '', '$2a$10$MzBI8szDfxX21vw7K/BUkO4B8imoyYQbxe5z6njDxOU1SzYAvVHn2', '0', '0', '127.0.0.1', '2022-11-08 14:43:23', 'admin', '2022-11-03 09:13:14', '', '2022-11-08 14:44:11', NULL);
INSERT INTO `sys_user` VALUES (9, 103, '13604433551', '13604433551', '00', '', '13604433551', '0', '', '$2a$10$4a1.W2fJLb3DALfSJiIeduztjlI4cuOgvdZzRr3JcbMJveEg3/Z/a', '0', '0', '', NULL, 'admin', '2023-02-16 17:54:59', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (10, 104, '13604433552', '13604433552', '00', '', '13604433552', '0', '', '$2a$10$RfeLtK9fbS7eMY38DYQhEeV.BNKe3RhZ7CL9QykR/qRFkYgwy2jIm', '0', '0', '', NULL, 'admin', '2023-02-16 17:55:57', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (11, 105, '13604433553', '13604433553', '00', '', '13604433553', '0', '', '$2a$10$3XtWaKkcPnpdIomE3HN9WesZIz6eXIFB/Az91XlWayUT1TrLUI4pG', '0', '0', '', NULL, 'admin', '2023-02-16 17:56:13', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (12, 104, '13604433554', '13604433554', '00', '', '13604433554', '0', '', '$2a$10$PuIS96VZ4FF7hX7vQCVEjOSNvuGhhRKoPG1IUpJT7gLPHVvGBsN4m', '0', '0', '', NULL, 'admin', '2023-02-16 17:56:24', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (13, 104, '13604433555', '13604433555', '00', '', '13604433555', '0', '', '$2a$10$rxkcmputdRwxt83WlfdavOpWx58awsH1LuPE0U9wg5Agy0pbJjOGC', '0', '0', '', NULL, 'admin', '2023-02-16 17:56:35', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (14, 104, '13604433556', '13604433556', '00', '', '13604433556', '0', '', '$2a$10$y/0935qsj/joNta.PzxjZuZ6ayN3ReiDURPa/hkp8NP23a1jeKQ8e', '0', '0', '', NULL, 'admin', '2023-02-16 17:56:48', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (15, 103, '13604433557', '13604433557', '00', '', '13604433557', '0', '', '$2a$10$mD.w6NdcI5BjUnGOrQY1iOUnrxze36ArDwqkj7YnosBGJm3u3ndWS', '0', '0', '', NULL, 'admin', '2023-02-16 17:57:03', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (16, 104, '13604433558', '13604433558', '00', '', '13604433558', '0', '', '$2a$10$ahHCa935Owad8m54RePeYOLl1xZS0USfG1FkQQZtJ7KBqO.HAvCUe', '0', '0', '', NULL, 'admin', '2023-02-16 17:57:18', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (17, 103, '13604433559', '13604433559', '00', '', '13604433559', '0', '', '$2a$10$9P6g2MHEaOByLpRDKZa/DOH3UfHWrepeaXcUEVP1Cadz8Xxq/gddq', '0', '0', '', NULL, 'admin', '2023-02-16 17:57:35', '', NULL, NULL);
INSERT INTO `sys_user` VALUES (18, 104, '13604433560', '13604433560', '00', '', '13604433560', '0', '', '$2a$10$ouzN/nKMX4ZFN80sUULIO.IKUTMoypMi4wlGS23GecbT3qwlgGMcG', '0', '0', '', NULL, 'admin', '2023-02-16 17:57:47', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (7, 2);
INSERT INTO `sys_user_role` VALUES (8, 2);
INSERT INTO `sys_user_role` VALUES (8, 3);


ALTER TABLE sys_user ADD auth_id VARCHAR(100) comment '认证ID';

SET FOREIGN_KEY_CHECKS = 1;
