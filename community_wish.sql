/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : communitity_wish

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 07/02/2023 18:04:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面地址',
  `link` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '链接地址',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '类型   0 文本内容 1链接',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除状况 0未删除 1已删除',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'banner表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_banner
-- ----------------------------
INSERT INTO `t_banner` VALUES (1, '1111', '/upload/xy/xy1666766445198epkjs.jpg', 'https://www.baidu.com', '1', '1', '2022-11-03 10:50:49');
INSERT INTO `t_banner` VALUES (14, '<h1>\n	<img src=\"http://www.ddooo.com/uppic/151228/20151228155359769.jpg\" /><br />\n</h1>', '/upload/fj/fj1667625964473pmsdh.png', '', '0', '1', '2022-11-07 14:09:55');
INSERT INTO `t_banner` VALUES (16, '<h2 style=\"box-sizing:inherit;margin:0px;padding:0px;color:#6D6F74;font-family:&quot;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"box-sizing:inherit;margin:0px;padding:0px;\">文本测试内容</span>\n</h2>\n<h3 style=\"box-sizing:inherit;margin:0px;padding:0px;color:#6D6F74;font-family:&quot;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"box-sizing:inherit;margin:0px;padding:0px;\">这是一个文本测试内容</span>\n</h3>\n<p style=\"box-sizing:inherit;margin-top:0.5vw;margin-bottom:0.5vw;padding:0px;color:#6D6F74;font-family:&quot;font-size:13px;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"box-sizing:inherit;margin:0px;padding:0px;\">这是一</span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\">是一个文本测试内容</span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\">这是一个<span style=\"box-sizing:inherit;margin:0px;padding:0px;color:#E56600;\">文本测试内容</span></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;color:#E56600;\">这是一个文本测试内容</span>\n</p>\n<p style=\"box-sizing:inherit;margin-top:0.5vw;margin-bottom:0.5vw;padding:0px;color:#6D6F74;font-family:&quot;font-size:13px;white-space:normal;background-color:#FFFFFF;\">\n	<span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><span style=\"box-sizing:inherit;margin:0px;padding:0px;color:#E56600;\">这是一个文本</span>测<em>试内容</em><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><em>这是一个文本测试内容</em></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><em>这是一个文本测试内容</em></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><em>这是一个文本测试内容</em></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><em>这是一个文本测试内容</em></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><em>这是一个文</em><u>本测试内容</u></span><span style=\"box-sizing:inherit;margin:0px;padding:0px;\"><u>这</u></span></span>\n</p>', '/upload/fj/fj1666772563616rypsd.png', '', '0', '1', '2022-11-03 11:14:46');
INSERT INTO `t_banner` VALUES (17, '', '/upload/fj/fj1666767474320pnaxk.png', 'www.carmenkajsa.top', '1', '1', '2022-11-03 10:50:47');
INSERT INTO `t_banner` VALUES (19, '', '/upload/fj/fj1666772552194czfmb.png', '1234', '1', '1', '2022-10-26 16:22:54');
INSERT INTO `t_banner` VALUES (20, '<h1>\n	这是一个文本测试内容12312313123\n</h1>\n<h2>\n	<span style=\"white-space:normal;\">这是一个文本测试内容11231231231</span>\n</h2>\n<h3>\n	<span style=\"white-space:normal;\">这是一个文本测试内容</span> \n</h3>\n<p>\n	<span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个<span style=\"background-color:#006600;\">文本测试内容</span></span><span style=\"white-space:normal;background-color:#006600;\">这是一个文本测试内容</span><span style=\"white-space:normal;background-color:#006600;\">这是一个文本测试内容</span><span style=\"white-space:normal;background-color:#006600;\">这是一个文本测试内容</span><span style=\"white-space:normal;\"><span style=\"background-color:#006600;\">这是</span>一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这<span style=\"color:#666666;\">是一个文本测试内容</span></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span><span style=\"color:#666666;\"></span> \n</p>\n<p>\n	<span style=\"white-space:normal;\"><span style=\"color:#666666;\">这是一个文本测试</span><strong><span style=\"color:#666666;\">内容</span></strong><span style=\"white-space:normal;\"><strong><span style=\"color:#666666;\">这</span>是一个文本测试内容</strong></span><span style=\"white-space:normal;\"><strong>这是一个文本测试内容</strong></span><span style=\"white-space:normal;\"><strong>这是一个文本测试内容</strong></span><span style=\"white-space:normal;\"><strong>这是一个文本</strong>测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个<span style=\"color:#E56600;\">文本测试内容</span></span><span style=\"white-space:normal;color:#E56600;\">这是一个文本测试内容</span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span><span style=\"color:#E56600;\"></span></span> \n</p>\n<p>\n	<span style=\"white-space:normal;\"><span style=\"color:#E56600;\">这是一个文本</span>测<em>试内容</em><span style=\"white-space:normal;\"><em>这是一个文本测试内容</em></span><span style=\"white-space:normal;\"><em>这是一个文本测试内容</em></span><span style=\"white-space:normal;\"><em>这是一个文本测试内容</em></span><span style=\"white-space:normal;\"><em>这是一个文本测试内容</em></span><span style=\"white-space:normal;\"><em>这是一个文</em><u>本测试内容</u></span><span style=\"white-space:normal;\"><u>这是一个文本测试内容</u></span><span style=\"white-space:normal;\"><u>这是一个文本测试内容</u></span><span style=\"white-space:normal;\"><u>这是一个</u>文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试内容</span><span style=\"white-space:normal;\">这是一个文本测试</span></span> \n</p>\n<p>\n	<span style=\"white-space:normal;\">这是一个文本测试内容</span> \n</p>', '/upload/fj/fj1667215030671fmnyn.png', '', '0', '1', '2022-11-07 14:10:01');
INSERT INTO `t_banner` VALUES (21, '<h1>\n	<span style=\"font-weight:normal;font-size:32px;font-family:Arial;\">本类型测</span> \n</h1>\n<p>\n	<span style=\"font-weight:normal;font-size:32px;font-family:Arial;\">太阳花111</span>\n</p>\n<p>\n	<span style=\"font-weight:normal;font-size:14px;font-family:Arial;\">试</span><span style=\"font-weight:normal;font-size:14px;font-family:Arial;\">文本类型测试</span><span style=\"font-weight:normal;font-size:14px;font-family:Arial;\">文<strong><u><em>本类型测试</em></u></strong></span><span style=\"font-weight:normal;font-size:14px;font-family:Arial;\"><strong><u><em>文本类型测试</em></u></strong></span><span style=\"font-weight:normal;font-size:14px;\"><span style=\"font-family:Arial;\"><strong><u><em>文本</em></u></strong></span><strong><u><em>类型测试</em></u></strong></span><span style=\"font-weight:normal;font-size:14px;\"><strong><u><em>文本类型测试</em></u></strong></span><span style=\"font-weight:normal;font-size:14px;\"><strong><u><em>文本类型测试</em></u></strong></span><span style=\"font-weight:normal;font-size:14px;\"><strong><u><em>文</em></u></strong>本类型测试</span><span style=\"font-weight:normal;font-size:14px;\">文本类型测试</span><span style=\"font-weight:normal;font-size:14px;\">文本类型测试</span><span style=\"font-weight:normal;font-size:14px;\">文本类<span style=\"background-color:#337FE5;\">型测试</span></span><span style=\"font-weight:normal;font-size:14px;background-color:#337FE5;\">文本类型测试</span><span style=\"font-size:12px;font-weight:normal;\"><span style=\"font-size:14px;background-color:#337FE5;\">文本类型</span><span style=\"font-size:14px;background-color:#337FE5;\">测试</span></span><span style=\"font-weight:normal;font-size:14px;background-color:#337FE5;\">文本类型测试</span> \n</p>\n<p>\n	<span style=\"white-space:normal;\"><span style=\"white-space:normal;font-size:14px;background-color:#337FE5;\">文本类型<span style=\"font-family:Arial;\">测试</span></span><span style=\"white-space:normal;font-size:14px;\"><span style=\"background-color:#337FE5;font-family:Arial;\">文<span style=\"font-family:Microsoft YaHei;\">本类型测</span></span><span style=\"font-family:&quot;\">试</span></span><span style=\"white-space:normal;font-size:14px;font-family:&quot;\">文本类型测试</span><span style=\"white-space:normal;font-size:14px;font-family:&quot;\">文本类型测试</span><span style=\"white-space:normal;font-size:14px;font-family:&quot;\">文本类型测试</span></span> \n</p>\n<p>\n	<span style=\"white-space:normal;\"><span style=\"white-space:normal;font-size:14px;font-family:&quot;\">文本类型测试</span><span style=\"white-space:normal;font-size:14px;font-family:&quot;\">文本类型测试</span><span style=\"white-space:normal;\"><span style=\"font-size:14px;font-family:Arial;\">文本类型测试</span> </span></span> \n</p>\n<p style=\"white-space:normal;\">\n	<span style=\"font-size:14px;\"><span style=\"font-family:Arial;\">型测试文本类型</span>测试文本类型测试文本类型测试文本类型测试文本类型测试文本类型测试<span style=\"color:#9933E5;\">文本类型测试<span style=\"font-family:Arial Black;\">ASDOahdiuca投入与</span></span></span> \n</p>\n<p style=\"white-space:normal;\">\n	<span style=\"font-size:14px;\"><span style=\"color:#9933E5;\">文本类型测试文本类型测试文本类</span>型测试文本类型测试文</span> \n</p>\n<span style=\"font-size:14px;\"></span> \n<p>\n	<br />\n</p>\n<p>\n	<span style=\"white-space:normal;\"><span style=\"white-space:normal;font-size:14px;\"><u>文本类型测试</u></span><span style=\"white-space:normal;font-size:14px;\"><u><span style=\"color:#B8D100;\">文本</span><span style=\"color:#B8D100;\">类</span></u></span></span><span style=\"font-size:14px;\"><em><span style=\"color:#B8D100;\">文</span><strong><span style=\"color:#B8D100;\">本类型测试</span></strong></em></span><span style=\"font-size:14px;color:#B8D100;\"><em><strong>文本类型测试</strong></em></span><span style=\"font-size:14px;color:#B8D100;\"><em><strong>文本类型测试</strong></em></span><span style=\"font-size:14px;color:#B8D100;\"><em><strong>文本类型测试</strong></em></span><span style=\"font-size:14px;color:#B8D100;\"><em><strong>文本类型测试</strong></em></span><span style=\"font-size:14px;color:#B8D100;\"><em><strong>文本类型测试</strong></em></span><span style=\"font-size:14px;\"><em><strong><span style=\"color:#B8D100;\">文</span><span style=\"background-color:#EE33EE;color:#B8D100;\">本类型</span><span style=\"color:#B8D100;\">测试</span></strong></em></span><span style=\"font-size:14px;\"><em><strong><span style=\"color:#B8D100;\">文本类型</span><span style=\"background-color:#FFFFFF;color:#B8D100;\">测试</span></strong></em></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;color:#B8D100;\"><u><strong>文本类型测试</strong></u></span><span style=\"font-size:14px;background-color:#FFFFFF;\"><u><strong><span style=\"color:#B8D100;\">文本类型测</span>试</strong></u></span><span style=\"font-size:14px;\"><u><strong><span style=\"background-color:#FFFFFF;\">文本<span style=\"background-color:#E53333;\">类</span></span></strong><span style=\"background-color:#E53333;\">型测试</span></u></span><span style=\"font-size:14px;background-color:#E53333;\"><u>文本类型测试</u></span><span style=\"font-size:14px;background-color:#E53333;\"><u>文本类型测试</u></span><span style=\"font-size:14px;background-color:#E53333;\"><u>文本类型测试</u></span><span style=\"font-size:14px;background-color:#E53333;\"><u>文本类型测试</u></span><span style=\"font-size:14px;background-color:#E53333;\"><u>文本类型测试</u></span><span style=\"font-size:14px;background-color:#FFFFFF;\"><u><span style=\"background-color:#E53333;\">文本类</span>型测试</u></span><span style=\"font-size:14px;\"><u><span style=\"background-color:#FFFFFF;\">文本类型测</span>试</u></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span><span style=\"font-size:14px;\"><em></em></span> \n</p>', '/upload/fj/fj1667216164942ymxfj.png', '', '0', '1', '2022-11-07 14:09:57');
INSERT INTO `t_banner` VALUES (22, '123', '/upload/fj/fj1666949308214zycrw.png', '123', '1', '1', '2022-11-03 11:14:43');
INSERT INTO `t_banner` VALUES (23, '123', '/upload/fj/fj1666951286619wmhee.png', '', '0', '1', '2022-10-28 18:01:49');
INSERT INTO `t_banner` VALUES (24, '', '/upload/fj/fj1667028646750txrwh.png', '123123', '1', '1', '2022-11-01 15:07:01');
INSERT INTO `t_banner` VALUES (25, '12', '/upload/fj/fj1667215519140jaxha.png', '', '0', '1', '2022-10-31 19:36:11');
INSERT INTO `t_banner` VALUES (26, '在平平淡淡的学习、工作、生活中，大家都有写作文的经历，对作文很是熟悉吧，作文一定要做到主题集中，围绕同一主题作深入阐述，切忌东拉西扯，主题涣散甚至无主题。写起作文来就毫无头绪？下面是小编精心整理的高二语文作文，供大家参考借鉴，希望可以帮助到有需要的朋友。<br />\n<div>\n	<br />\n</div>\n<img src=\"https://hxjtg.fzjiading.com/wxy-api/upload/file1667286232712hshfa.png\" alt=\"\" />', '/upload/fj/fj1667286240467ztjcw.png', '', '0', '1', '2022-11-07 14:09:51');
INSERT INTO `t_banner` VALUES (27, '123123', '/upload/fj/fj1667289798196hciiw.png', '123123', '1', '1', '2022-11-03 11:14:50');
INSERT INTO `t_banner` VALUES (28, NULL, '/upload/fj/fj1667290242906ffzbb.png', '12313', '1', '1', '2022-11-03 10:51:02');
INSERT INTO `t_banner` VALUES (29, NULL, '/upload/fj/fj1667290327331isseb.png', '123123123', '1', '1', '2022-11-03 10:51:07');
INSERT INTO `t_banner` VALUES (30, NULL, '/upload/fj/fj1667290351825aabbs.png', '安安生生大大方方', '1', '1', '2022-11-03 10:51:11');
INSERT INTO `t_banner` VALUES (31, NULL, '/upload/fj/fj1667292996340ndcta.png', 'ZSCDSAD', '1', '1', '2022-11-03 10:50:58');
INSERT INTO `t_banner` VALUES (32, '<p>\n	ZSDASD\n</p>\n<p>\n	12312313\n</p>\n<p>\n	12313123\n</p>', '/upload/fj/fj1667293030356erbmx.png', NULL, '0', '1', '2022-11-03 10:50:53');
INSERT INTO `t_banner` VALUES (33, NULL, '/upload/fj/fj1667293436842fjpad.png', '123', '1', '1', '2022-11-03 11:14:41');
INSERT INTO `t_banner` VALUES (34, NULL, '/upload/fj/fj1667293475727zdniy.png', 'zhongyu1asdasdada1231asdad', '1', '1', '2022-11-02 13:27:11');
INSERT INTO `t_banner` VALUES (35, 'www<img src=\"https://hxjtg.fzjiading.com/wxy-api/upload/fj/fj1667366769437ikpbk.png\" width=\"300\" height=\"300\" title=\"111\" align=\"right\" alt=\"111\" />', '/upload/fj/fj1667366769437ikpbk.png', '', '0', '1', '2022-12-06 15:25:08');
INSERT INTO `t_banner` VALUES (36, '<p>\n	励志文章感谢身边的懒人励志文章感谢身边的懒人我们要学会感谢别人的懒惰因为正是他们的懒惰才使我们拥有了更多做事的机会为我们搭起了展示才华的舞台与通向...\n</p>\n<p>\n	<img src=\"https://hxjtg.fzjiading.com/wxy-api/upload/file1667444689199bhhtf.png\" alt=\"\" /> \n</p>', '/upload/fj/fj1667738827225wmeei.png', NULL, '0', '1', '2022-11-07 14:10:08');
INSERT INTO `t_banner` VALUES (37, NULL, '/upload/fj/fj1667443989647makrw.jpg', 'www.baidu.com', '1', '1', '2022-11-07 14:08:35');
INSERT INTO `t_banner` VALUES (38, '<p>\n	苍霞新城社区党委运用五事工作法提高基层治理成效、解决群众“急难愁盼”问题的重要平台，该程序面向辖区居民征集需求，机关、企事业单位、在职党员主动认领，是践行新思想、落实为民服务的重要载体。\n</p>\n<p>\n	<img src=\"https://hxjtg.fzjiading.com/wxy-api/upload/file1667801349943mcxtb.png\" alt=\"\" /> \n</p>', '/upload/fj/fj1670311487542ifmth.png', NULL, '0', '0', '2022-11-07 14:09:25');

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code`  (
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES ('00000352');

-- ----------------------------
-- Table structure for t_jf
-- ----------------------------
DROP TABLE IF EXISTS `t_jf`;
CREATE TABLE `t_jf`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `userid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `change` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '积分加减 0加 1减',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '积分变化原因',
  `score` int(11) NULL DEFAULT NULL COMMENT '积分数量',
  `jf_audit_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '积分审核状态 0：待审核 1：审核通过 2：审核不通过',
  `jf_audit_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '审核备注',
  `jf_auditid` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `operatorid` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `changenum` int(11) NULL DEFAULT NULL COMMENT '变化积分数量',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_fjb_del`(`del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '积分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作内容',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人id',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作人ip',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `pid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单表ID',
  `menuname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单名称',
  `link` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单路径',
  `parent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单父类ID',
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '图标',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除 1删除 0保留',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('fbxy', '发布心愿', '/xygl/fbxy', 'xygl', 'el-icon-s-promotion', 0, '0', '2022-10-25 14:42:14');
INSERT INTO `t_menu` VALUES ('fbxysh', '发布心愿审核', '/xygl/fbxysh', 'xygl', 'el-icon-position', 0, '0', '2022-10-25 14:44:43');
INSERT INTO `t_menu` VALUES ('goods', '商品', '/goods', 'x1', 'el-icon-user-solid', 0, '0', '2023-02-07 16:55:30');
INSERT INTO `t_menu` VALUES ('jfgl', '积分管理', '/jfgl', 'x1', 'el-icon-user-solid', 0, '0', '2023-02-05 21:17:38');
INSERT INTO `t_menu` VALUES ('jflist', '积分列表', '/jfgl/jflist', 'jfgl', 'el-icon-user-solid', 0, '0', '2023-02-05 21:18:04');
INSERT INTO `t_menu` VALUES ('jsgl', '角色管理', '/xtgl/jsgl', 'xtgl', 'el-icon-s-check', 0, '0', '2022-10-25 11:43:40');
INSERT INTO `t_menu` VALUES ('lbtgl', 'banner管理', '/xtgl/lbtgl', 'xtgl', 'el-icon-picture-outline', 0, '0', '2022-10-25 11:45:44');
INSERT INTO `t_menu` VALUES ('spgl', '商品列表', '/goods/sqgl', 'goods', 'el-icon-user-solid', 0, '0', '2023-02-07 16:56:04');
INSERT INTO `t_menu` VALUES ('sxxy', '实现心愿', '/xygl/sxxy', 'xygl', 'el-icon-s-opportunity', 0, '1', '2022-10-25 14:48:19');
INSERT INTO `t_menu` VALUES ('sxxysh', '实现心愿审核', '/xygl/sxxysh', 'xygl', 'el-icon-s-opportunity', 0, '0', '2022-10-25 14:46:58');
INSERT INTO `t_menu` VALUES ('wylb', '完愿列表', '/xygl/wylb', 'xygl', 'el-icon-s-order', 0, '0', '2022-11-05 23:07:37');
INSERT INTO `t_menu` VALUES ('xgmm', '密码修改', '/xtgl/xgmm', 'xtgl', 'el-icon-lock', 0, '0', '2022-08-25 09:40:13');
INSERT INTO `t_menu` VALUES ('xtgl', '系统设置', '/xtgl', 'x1', 'el-icon-setting', 3, '0', '2022-10-24 11:30:26');
INSERT INTO `t_menu` VALUES ('xygl', '心愿管理', '/xygl', 'x1', 'el-icon-star-off', 1, '0', '2022-10-25 14:41:20');
INSERT INTO `t_menu` VALUES ('xylist', '心愿列表', '/xygl/xylist', 'xygl', 'el-icon-s-order', 0, '0', '2022-10-25 14:47:41');
INSERT INTO `t_menu` VALUES ('yhgl', '系统用户管理', '/xtgl/yhgl', 'xtgl', 'el-icon-s-custom', 0, '0', '2022-08-25 09:40:13');
INSERT INTO `t_menu` VALUES ('yhglm', '用户管理', '/yhglm', 'x1', 'el-icon-user', 2, '0', '2022-10-25 11:44:49');
INSERT INTO `t_menu` VALUES ('yhlb', '客户列表', '/yhglm/yhlb', 'yhglm', 'el-icon-user-solid', 0, '0', '2022-10-25 11:46:38');
INSERT INTO `t_menu` VALUES ('zyzgl', '志愿者管理', '/zyzgl', 'x1', 'el-icon-s-custom', 0, '0', '2023-02-05 21:18:58');
INSERT INTO `t_menu` VALUES ('zyzlist', '志愿者列表', '/zyzgl/zyzlist', 'zyzgl', 'el-icon-user-solid', 0, '0', '2023-02-05 21:19:33');
INSERT INTO `t_menu` VALUES ('zyzsh', '志愿者审核', '/zyzgl/zyzsh', 'zyzgl', 'el-icon-user-solid', 0, '0', '2023-02-05 21:20:12');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色表ID',
  `rolename` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '角色备注',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除状况  0：未删除  1：已删除',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, '超级管理员', '管理全部内容', '2022-10-24 17:21:05', '0');
INSERT INTO `t_role` VALUES (2, '心愿管理员', '只能编辑心愿列表', '2022-10-24 17:22:17', '0');
INSERT INTO `t_role` VALUES (7, '测试角色', '测试角色12345', '2022-10-25 17:10:52', '1');
INSERT INTO `t_role` VALUES (8, '测试管理员', '测试1111', '2022-10-26 17:05:16', '1');
INSERT INTO `t_role` VALUES (9, '测试123', '1', '2022-10-28 18:00:39', '1');
INSERT INTO `t_role` VALUES (10, '系统管理员', 'x123', '2022-10-29 15:28:36', '1');
INSERT INTO `t_role` VALUES (11, '请问', '111', '2022-10-31 19:25:07', '1');

-- ----------------------------
-- Table structure for t_rolemenu
-- ----------------------------
DROP TABLE IF EXISTS `t_rolemenu`;
CREATE TABLE `t_rolemenu`  (
  `roleid` int(11) NOT NULL COMMENT '角色ID',
  `menudid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_rolemenu
-- ----------------------------
INSERT INTO `t_rolemenu` VALUES (1, 'xygl');
INSERT INTO `t_rolemenu` VALUES (1, 'fbxy');
INSERT INTO `t_rolemenu` VALUES (1, 'fbxysh');
INSERT INTO `t_rolemenu` VALUES (1, 'sxxy');
INSERT INTO `t_rolemenu` VALUES (1, 'sxxysh');
INSERT INTO `t_rolemenu` VALUES (1, 'xylist');
INSERT INTO `t_rolemenu` VALUES (1, 'yhglm');
INSERT INTO `t_rolemenu` VALUES (1, 'yhlb');
INSERT INTO `t_rolemenu` VALUES (1, 'xtgl');
INSERT INTO `t_rolemenu` VALUES (1, 'jsgl');
INSERT INTO `t_rolemenu` VALUES (1, 'lbtgl');
INSERT INTO `t_rolemenu` VALUES (1, 'xgmm');
INSERT INTO `t_rolemenu` VALUES (1, 'yhgl');
INSERT INTO `t_rolemenu` VALUES (2, 'xygl');
INSERT INTO `t_rolemenu` VALUES (2, 'fbxy');
INSERT INTO `t_rolemenu` VALUES (2, 'fbxysh');
INSERT INTO `t_rolemenu` VALUES (2, 'sxxysh');
INSERT INTO `t_rolemenu` VALUES (2, 'xylist');
INSERT INTO `t_rolemenu` VALUES (2, 'wylb');
INSERT INTO `t_rolemenu` VALUES (1, 'wylb');
INSERT INTO `t_rolemenu` VALUES (1, 'zyzlist');
INSERT INTO `t_rolemenu` VALUES (1, 'zyzsh');
INSERT INTO `t_rolemenu` VALUES (1, 'jflist');
INSERT INTO `t_rolemenu` VALUES (1, 'zyzgl');
INSERT INTO `t_rolemenu` VALUES (1, 'jfgl');
INSERT INTO `t_rolemenu` VALUES (1, 'goods');
INSERT INTO `t_rolemenu` VALUES (1, 'spgl');

-- ----------------------------
-- Table structure for t_sp
-- ----------------------------
DROP TABLE IF EXISTS `t_sp`;
CREATE TABLE `t_sp`  (
  `pid` int(11) NOT NULL COMMENT '商品表id',
  `spname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `spprice` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品价格',
  `spxq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品介绍',
  `kc` int(11) NULL DEFAULT NULL COMMENT '库存',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '新增时间',
  `upd_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` int(11) NULL DEFAULT NULL COMMENT '是否删除 0 否 1是',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片地址',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信昵称',
  `portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '联系方式',
  `account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '账号',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `openid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'openid',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户类型 1：小程序用户 2：系统用户',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '添加时间',
  `jyzk` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '禁用状况 0启用 1禁用',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除状况 0未删除 1已删除',
  `yzm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '验证码',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_user_phone`(`phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1894 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '区委老干部局', '管理员', '', '11111', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', '2', '2023-01-29 11:36:07', '0', '0', NULL);
INSERT INTO `t_user` VALUES (1212, '张三', 'Zhang张三', '', '13022224444', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '', '2', '2022-12-07 14:51:53', '0', '0', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `userid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户id',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`roleid`, `userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, '1', '2022-10-31 19:22:49');
INSERT INTO `t_user_role` VALUES (1, '1212', '2022-12-09 15:39:08');

-- ----------------------------
-- Table structure for t_wish
-- ----------------------------
DROP TABLE IF EXISTS `t_wish`;
CREATE TABLE `t_wish`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `wishusername` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '许愿人名称',
  `wishuserid` int(11) NULL DEFAULT NULL COMMENT '许愿人id',
  `wish_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '许愿内容',
  `adder` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '许愿地点',
  `money` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '许愿金额',
  `wish_time` datetime(0) NULL DEFAULT NULL COMMENT '许愿时间',
  `wish_audit_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '心愿审核状态 0：待审核 1：审核通过 2：审核不通过',
  `wish_audit_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '心愿审核备注',
  `wish_auditid` int(11) NULL DEFAULT NULL COMMENT '心愿审核人id',
  `wish_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '心愿状态 0：待认领 1：已认领 2：已完成',
  `operatorid` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `price` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否大金额（0：否 1是）',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_fjb_del`(`del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 265 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '心愿表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wish
-- ----------------------------
INSERT INTO `t_wish` VALUES (212, 'xx', 10, 'xx', '福建省福州市鼓楼区', NULL, '2022-11-13 15:10:44', '1', '', NULL, '0', NULL, '2022-11-13 15:10:44', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (213, '苍霞老人', 1, '根据台江区新一轮社区综合提升行动工作考评表及“精品示范社区”打造标准要求，需拍摄一部能够全方位展现苍霞新城改造成果和基层治理成效的宣传片。', '福州市', NULL, '2022-11-13 15:23:59', '1', '', 1, '0', 1, '2022-11-13 15:23:59', NULL, '1', 1, '0');
INSERT INTO `t_wish` VALUES (214, '苍霞老人', 1, '嘉盛苑小区内设有社区老人活动馆，现有会员200余人。活动馆使用多年，内部线路老化并多为私拉电线，存在安全隐患。计划将老人活动馆线路重新规划安装布线，为民办实事、给老年人提供良好、安全的活动场所。', '福州市', NULL, '2022-11-13 15:24:50', '1', '', 1, '2', 1, '2022-11-13 15:24:50', NULL, '1', 1, '0');
INSERT INTO `t_wish` VALUES (215, '苍霞新城社区', 10, '苍霞新城社区', '福建省福州市鼓楼区', NULL, '2022-11-13 15:38:47', '1', '', NULL, '0', NULL, '2022-11-13 15:38:47', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (216, '苍霞新城社区', 10, '苍霞新城社区', '福建省福州市鼓楼区', NULL, '2022-11-13 15:39:26', '1', '', NULL, '0', NULL, '2022-11-13 15:39:26', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (217, '苍霞居民', 10, '苍霞居民', '福建省福州市鼓楼区', NULL, '2022-11-13 15:40:47', '1', '', NULL, '2', NULL, '2022-11-13 15:40:47', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (218, '苍霞新城社区', 1, '因疫情防控工作需要，急需充电宝10个。', '', NULL, '2022-11-13 18:47:41', '1', '', 1, '2', 1, '2022-11-13 18:47:41', NULL, '0', 8, '0');
INSERT INTO `t_wish` VALUES (219, '苍霞新城社区', 1, '因疫情防控工作需要，急需伸缩隔离栏150个,1米线1000条。', '福州市', NULL, '2022-11-13 18:54:08', '1', '', 1, '2', 1, '2022-11-13 18:54:08', '2022-11-13 18:54:52', '1', 2, '0');
INSERT INTO `t_wish` VALUES (220, '苍霞新城社区', 1, '因疫情防控需要，需喇叭5个，雨衣50件', '福州市', NULL, '2022-11-13 18:55:58', '1', '', 1, '2', 1, '2022-11-13 18:55:58', NULL, '0', 1, '0');
INSERT INTO `t_wish` VALUES (221, '苍霞新城社区', 1, '因疫情防控工作需要，现需志愿者马甲30件，喇叭5个', '福州市', NULL, '2022-11-13 18:57:07', '1', '', 1, '2', 1, '2022-11-13 18:57:07', NULL, '0', 1, '0');
INSERT INTO `t_wish` VALUES (222, '赵', 10, '赵', '福建省福州市鼓楼区', NULL, '2022-11-13 19:31:01', '1', '', NULL, '2', NULL, '2022-11-13 19:31:01', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (223, '赵大姐', 1, '儿童书籍', '福州市', NULL, '2022-11-13 19:32:59', '1', '', 1, '0', 1, '2022-11-13 19:32:59', NULL, '1', 2, '0');
INSERT INTO `t_wish` VALUES (224, '苍霞新城嘉盛苑老人', 1, '嘉盛苑小区内设有社区老人活动馆，现有会员200余人。活动馆使用多年，内部线路老化并多为私拉电线，存在安全隐患。计划将老人活动馆线路重新规划安装布线，为民办实事、给老年人提供良好、安全的活动场所。', '苍霞新城社区嘉盛苑', NULL, '2022-11-13 20:44:59', '1', '', 1, '1', 1, '2022-11-13 20:44:59', NULL, '1', 2, '0');
INSERT INTO `t_wish` VALUES (225, '苍霞新城社区', 1, '因打造苍霞新城精品社区，现需制作苍霞新城宣传视频。', '苍霞街道苍霞新城社区', NULL, '2022-11-13 20:46:21', '1', '', 1, '2', 1, '2022-11-13 20:46:21', '2022-11-13 20:46:51', '1', 1, '0');
INSERT INTO `t_wish` VALUES (226, '苍霞新城社区', 1, '因疫情防控工作需要，急需伸缩隔离栏150个,1米线500条', '苍霞新城社区', NULL, '2022-11-13 20:57:44', '1', '', NULL, '2', 1, '2022-11-13 20:57:44', '2023-02-01 10:21:03', '0', 1, '0');
INSERT INTO `t_wish` VALUES (227, '苍霞新城社区', 1, '“苍霞人家”生活馆作为福州市党性教育现场教学市级示范点，接待来访团队和个人任务量巨大，目前缺少专职讲解员和运维人员。', '苍霞人家生活馆', NULL, '2022-11-13 21:14:46', '1', '', 1, '2', 1, '2022-11-13 21:14:46', NULL, '1', 1, '0');
INSERT INTO `t_wish` VALUES (228, '苍霞新城社区', 1, '根据台江区新一轮社区综合提升行动工作考评表及“精品示范社区”打造标准要求，社区需建有幼儿园（含微型幼儿园）。目前苍霞新城社区辖区范围内无符合条件的幼儿园。', '苍霞新城社区', NULL, '2022-11-13 21:15:35', '1', '', 1, '0', 1, '2022-11-13 21:15:35', NULL, '1', 3, '0');
INSERT INTO `t_wish` VALUES (229, '苍霞新城社区', 1, '根据台江区新一轮社区综合提升行动工作考评表及“精品示范社区”打造标准要求，需拍摄一部能够全方位展现苍霞新城改造成果和基层治理成效的宣传片。', '苍霞新城社区', NULL, '2022-11-13 21:20:51', '1', '', 1, '2', 1, '2022-11-13 21:20:51', NULL, '0', 1, '0');
INSERT INTO `t_wish` VALUES (230, '苍霞新城社区', 1, '因苍霞新城正进行全方位改造提升，嘉盛苑、嘉华苑、嘉兴苑、嘉惠苑四个小区宣传栏需重新选点并制作安装。', '苍霞新城社区', NULL, '2022-11-13 21:49:07', '1', '', 1, '1', 1, '2022-11-13 21:49:07', NULL, '0', 4, '0');
INSERT INTO `t_wish` VALUES (231, '是的', 10, '是的', '福建省福州市鼓楼区', NULL, '2022-11-19 15:51:46', '1', '', NULL, '2', NULL, '2022-11-19 15:51:46', NULL, '1', NULL, '0');
INSERT INTO `t_wish` VALUES (232, '苍霞新城社区', 1213, '“苍霞人家”生活馆作为福州市党性教育现场教学点市级示范点，接待来访团队和个人任务量巨大，目前缺少专职讲解员和运维人员。', '苍霞人家生活馆', NULL, '2022-11-21 17:58:10', '1', '', 1, '1', 1, '2022-11-21 17:58:10', NULL, '0', 1, '0');
INSERT INTO `t_wish` VALUES (233, '苍霞新城社区', 1, '因疫情防控工作需要，现需5名志愿者协助维护核酸点位秩序。', '苍霞新城社区', NULL, '2022-12-05 08:52:03', '1', '', 1, '2', 1, '2022-12-05 08:52:03', NULL, '0', 5, '0');
INSERT INTO `t_wish` VALUES (234, '苍霞新城社区', 1, '因疫情防控工作需要，需3名核酸采样信息录入员，协助全民核酸采样工作。', '苍霞新城社区', NULL, '2022-12-05 08:54:08', '1', '', NULL, '2', 1, '2022-12-05 08:54:08', '2023-02-01 01:06:48', '0', 6, '0');
INSERT INTO `t_wish` VALUES (235, '张三', 1212, '平安健康', '福州', NULL, '2022-12-08 17:07:13', '1', '', 1212, '2', 1212, '2022-12-08 17:07:13', NULL, '1', 3, '1');
INSERT INTO `t_wish` VALUES (236, '鱼谜', 1352, '平安喜乐哈哈哈', '福州 ', '', '2022-12-08 17:14:45', '0', '', NULL, '0', NULL, '2022-12-08 17:14:08', NULL, '1', 1, '0');
INSERT INTO `t_wish` VALUES (237, '张三', 1212, '测试', '福州', NULL, '2023-01-12 11:38:09', '1', '', 1212, '2', 1212, '2023-01-12 11:38:09', NULL, '1', 1, '0');
INSERT INTO `t_wish` VALUES (238, '苍霞新城社区', 1, '为增强居民对社区的归属感和凝聚力，共同打造和谐、融洽的氛围，社区需多方共建，开展多元化活动。', '嘉和苑小区', NULL, '2023-01-29 10:45:35', '1', '', 1, '0', 1, '2023-01-29 10:45:35', NULL, '1', 2, '0');
INSERT INTO `t_wish` VALUES (239, '张女士', 1, '因家庭困难，孩子上网课所需电脑无法购买，希望能够得到帮助。', '苍霞新城社区嘉和苑', '', '2023-01-29 11:10:37', '1', '', 1, '0', NULL, '2023-01-29 11:10:37', NULL, '1', 2, '0');
INSERT INTO `t_wish` VALUES (240, '谢女士', 1857, '为增强居民对社区的归属感和凝聚力，共同打造和谐、融洽的氛围，社区需多方共建，开展多元化活动。', '苍霞新城嘉和苑小区', NULL, '2023-01-29 11:33:15', '1', '', 1, '1', 1, '2023-01-29 11:33:15', NULL, '1', 2, '0');

-- ----------------------------
-- Table structure for t_wish_claimant
-- ----------------------------
DROP TABLE IF EXISTS `t_wish_claimant`;
CREATE TABLE `t_wish_claimant`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `wishid` int(11) NULL DEFAULT NULL COMMENT '心愿表id',
  `claimant` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '认领人姓名',
  `claimantid` int(11) NULL DEFAULT NULL COMMENT '认领人id',
  `realize_time` datetime(0) NULL DEFAULT NULL COMMENT '实现时间',
  `expressage` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '快递单号',
  `claimant_audit_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '认领审核状态 0：待审核 1：审核通过 2：审核不通过',
  `claimant_audit_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '认领审核备注',
  `claimant_auditid` int(11) NULL DEFAULT NULL COMMENT '认领审核人id',
  `operatorid` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_fjb_del`(`del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '心愿实现表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wish_claimant
-- ----------------------------
INSERT INTO `t_wish_claimant` VALUES (175, 214, '张三', 10, '2022-11-13 15:25:47', '', '1', '', NULL, NULL, '2022-11-13 15:25:47', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (176, 217, '是', 10, '2022-11-13 17:36:16', '', '1', '', NULL, NULL, '2022-11-13 17:36:16', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (177, 218, '盈科律所', 10, '2022-11-13 18:52:34', '', '1', '', NULL, NULL, '2022-11-13 18:52:34', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (178, 219, '邮储银行', 10, '2022-11-13 19:02:23', '', '1', '', NULL, NULL, '2022-11-13 19:02:23', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (179, 220, '福州市华优汇创投资有限公司', 10, '2022-11-13 19:03:31', '', '1', '', NULL, NULL, '2022-11-13 19:03:31', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (180, 221, '福建银讯科技有限公司党支部', 10, '2022-11-13 19:04:36', '', '1', '', NULL, NULL, '2022-11-13 19:04:36', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (181, 222, '遇见', 10, '2022-11-13 19:31:28', '', '1', '', NULL, NULL, '2022-11-13 19:31:28', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (182, 225, '台江区国投', 10, '2022-11-13 20:48:12', '', '1', '', NULL, NULL, '2022-11-13 20:48:12', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (183, 226, '邮储银行', 10, '2022-11-13 00:00:00', '', '1', '', NULL, NULL, '2022-11-13 20:59:10', '2023-02-01 10:21:03', '0');
INSERT INTO `t_wish_claimant` VALUES (184, 229, '区国投', 10, '2022-11-13 21:22:38', '', '1', '', NULL, NULL, '2022-11-13 21:22:38', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (185, 231, '没有吗？这个题吧。', 10, '2022-11-19 15:52:14', '', '1', '', NULL, NULL, '2022-11-19 15:52:14', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (186, 227, '张三。', 10, '2022-11-19 15:54:59', '', '1', '', NULL, NULL, '2022-11-19 15:54:59', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (187, 233, '市仲裁委', 10, '2022-12-05 08:53:53', '', '1', '', NULL, NULL, '2022-12-05 08:53:53', NULL, '0');
INSERT INTO `t_wish_claimant` VALUES (188, 234, '市地铁集团', 10, '2022-12-05 00:00:00', '123', '1', '', NULL, NULL, '2022-12-05 08:55:09', '2023-02-01 01:06:48', '0');

-- ----------------------------
-- Table structure for t_wish_file
-- ----------------------------
DROP TABLE IF EXISTS `t_wish_file`;
CREATE TABLE `t_wish_file`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `wishid` int(11) NULL DEFAULT NULL COMMENT '心愿实现表id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '附件名称',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '附件地址',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件类型',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_fjb_del`(`del`) USING BTREE,
  INDEX `index_fjb_wishid`(`wishid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 215 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '心愿附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wish_file
-- ----------------------------
INSERT INTO `t_wish_file` VALUES (142, 189, '', '/upload/xy/xy1670490482195cfxzd.jpg', '2022-12-08 17:08:02', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (143, 190, '', '/upload/xy/xy1673494754889brcfs.jpg', '2023-01-12 11:39:14', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (144, 191, '', '/upload/xy/xy1674963285228itazk.jpg', '2023-01-29 11:34:45', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (145, 192, '', '/upload/xy/xy1674979655644sshec.png', '2023-01-29 16:07:35', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (172, 253, 'index-on.png', '/upload/xy/xy1675134043512nwrzc.png', '2023-01-31 11:01:10', NULL, '1', NULL);
INSERT INTO `t_wish_file` VALUES (173, 253, 'my-on.png', '/upload/xy/xy1675134048917xwxar.png', '2023-01-31 11:01:10', NULL, '1', NULL);
INSERT INTO `t_wish_file` VALUES (174, 253, 'index-on.png', '/upload/xy/xy1675134043512nwrzc.png', '2023-01-31 11:02:22', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (175, 253, 'my-on.png', '/upload/xy/xy1675134048917xwxar.png', '2023-01-31 11:02:22', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (176, 254, 'index.png', '/upload/xy/xy1675134262263mtwdf.png', '2023-01-31 11:04:28', NULL, '1', NULL);
INSERT INTO `t_wish_file` VALUES (177, 254, 'my-on.png', '/upload/xy/xy1675134265366dxfxw.png', '2023-01-31 11:04:28', NULL, '1', NULL);
INSERT INTO `t_wish_file` VALUES (178, 254, 'index.png', '/upload/xy/xy1675134262263mtwdf.png', '2023-01-31 11:05:33', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (179, 254, 'my-on.png', '/upload/xy/xy1675134265366dxfxw.png', '2023-01-31 11:05:33', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (180, 207, '', '/upload/xy/xy1675152864052hxfyz.jpg', '2023-01-31 16:14:24', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (181, 208, '', '/upload/xy/xy1675155902255whfje.jpg', '2023-01-31 17:05:02', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (182, 209, '', '/upload/xy/xy1675156184071rzrcp.jpg', '2023-01-31 17:09:44', NULL, '1', NULL);
INSERT INTO `t_wish_file` VALUES (183, 209, '', '/upload/xy/xy1675156184099wyhkt.jpg', '2023-01-31 17:09:44', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (184, 257, '1223.png', '/upload/xy/xy1675156366869cxhab.png', '2023-01-31 17:12:57', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (185, 257, '压岁钱的来历 - 副本.jpg', '/upload/xy/xy1675156375495yzpmj.jpg', '2023-01-31 17:12:57', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (186, 210, '', '/upload/xy/xy1675156640831rbnxy.jpg', '2023-01-31 17:17:20', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (187, 210, '', '/upload/xy/xy1675156640888ahxcf.jpg', '2023-01-31 17:17:20', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (188, 259, '1223.png', '/upload/xy/xy1675156650830bdxkk.png', '2023-01-31 17:20:12', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (189, 259, '1223.png', '/upload/xy/xy1675157090055ijyfr.png', '2023-01-31 17:24:57', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (190, 259, '微信图片_20230130200637.jpg', '/upload/xy/xy1675157095297imync.jpg', '2023-01-31 17:24:57', NULL, '0', NULL);
INSERT INTO `t_wish_file` VALUES (191, 214, 'm1.png', '/upload/xy/xy1675160279547mbftw.png', '2023-01-31 18:18:02', NULL, '0', NULL);

-- ----------------------------
-- Table structure for t_zyz
-- ----------------------------
DROP TABLE IF EXISTS `t_zyz`;
CREATE TABLE `t_zyz`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '表ID',
  `zyzid` int(11) NULL DEFAULT NULL COMMENT '志愿者id',
  `zyzname` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '志愿者姓名',
  `sq_time` datetime(0) NULL DEFAULT NULL COMMENT '申请时间',
  `reason` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '申请原因',
  `zyz_audit_state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '志愿者审核状态 0：待审核 1：审核通过 2：审核不通过',
  `zyz_audit_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '审核备注',
  `zyz_auditid` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `operatorid` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `add_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `del` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  PRIMARY KEY (`pid`) USING BTREE,
  INDEX `index_fjb_del`(`del`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 222 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '志愿者申请表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
