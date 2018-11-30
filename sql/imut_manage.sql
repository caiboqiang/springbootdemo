/*
Navicat MySQL Data Transfer

Source Server         : 192.168.100.212
Source Server Version : 50173
Source Host           : 192.168.100.212:3306
Source Database       : imut_manage

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2018-11-30 11:04:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `class_info`
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info` (
  `classroom_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `classroom_name` varchar(100) NOT NULL COMMENT '教室名称',
  `classroom_position` varchar(100) NOT NULL COMMENT '教室位置',
  `capacity` int(50) NOT NULL COMMENT '容纳人数',
  `create_time` char(20) NOT NULL COMMENT '创建时间',
  `room_id` int(11) DEFAULT NULL COMMENT '房间id',
  `doctor_id` int(11) DEFAULT NULL COMMENT 'B超室医生标识Id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师标识Id',
  PRIMARY KEY (`classroom_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES ('20', 'sdfdsf', '433', '555', '2018-11-27 16:33:59', '0', '0', '0');

-- ----------------------------
-- Table structure for `condition_type`
-- ----------------------------
DROP TABLE IF EXISTS `condition_type`;
CREATE TABLE `condition_type` (
  `condition_id` int(11) NOT NULL AUTO_INCREMENT,
  `condition_name` varchar(50) NOT NULL,
  `create_time` char(20) NOT NULL,
  PRIMARY KEY (`condition_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of condition_type
-- ----------------------------
INSERT INTO `condition_type` VALUES ('1', '肝脏', '2018-9-10');
INSERT INTO `condition_type` VALUES ('2', '肾脏', '2018-09-10 18:17:35');
INSERT INTO `condition_type` VALUES ('6', '心脏', '2018-09-14 06:15:22');
INSERT INTO `condition_type` VALUES ('8', '晚期癌症', '2018-09-20 17:27:17');
INSERT INTO `condition_type` VALUES ('9', '早期癌症11', '2018-09-20 17:27:55');

-- ----------------------------
-- Table structure for `record_video`
-- ----------------------------
DROP TABLE IF EXISTS `record_video`;
CREATE TABLE `record_video` (
  `rv_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '录制视频id',
  `rv_name` varchar(100) NOT NULL COMMENT '录制视频名称',
  `doctor_id` int(11) NOT NULL COMMENT 'B超室医生标识id',
  `condition_id` int(11) DEFAULT NULL COMMENT '病情分类id',
  `teaching_date` char(20) NOT NULL COMMENT '教学日期',
  `rv_duration` varchar(20) DEFAULT NULL COMMENT '视频时长',
  `classroom_id` int(11) DEFAULT NULL COMMENT '教室id',
  `teacher_id` int(11) DEFAULT NULL COMMENT '老师标识Id',
  `rv_url` varchar(255) NOT NULL COMMENT '视频保存路径',
  PRIMARY KEY (`rv_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2343 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record_video
-- ----------------------------
INSERT INTO `record_video` VALUES ('606', '20180921091411shijiaoshi1', '24', '0', '2018-09-21', null, '71593', '10', '1.mp4');
INSERT INTO `record_video` VALUES ('607', '20180921091412shijiaoshi1', '24', '0', '2018-09-21', null, '71593', '10', '1.mp4');
INSERT INTO `record_video` VALUES ('2220', '20181126175730shijiaoshi1', '26', '0', '2018-11-26 17:56:31', null, '62481', null, '1.mp4');
INSERT INTO `record_video` VALUES ('2221', '20181126175736shijiaoshi1', '26', '0', '2018-11-26 17:56:36', null, '62481', null, '1.mp4');
INSERT INTO `record_video` VALUES ('2222', '20181127094241shijiaoshi1', '26', '0', '2018-11-27 09:41:38', null, '62481', null, '1.mp4');
INSERT INTO `record_video` VALUES ('2223', '20181127094253shijiaoshi1', '26', '0', '2018-11-27 09:41:50', null, '62481', null, '1.mp4');
INSERT INTO `record_video` VALUES ('2247', '20181127114330shijiaoshi1', '26', '0', '2018-11-27 11:42:27', null, '62481', null, 'http://10.100.99.22/record/b0f5555b-94bd-43b2-a7ef-796bc4da7ae4-----26-18501-----2018-11-27-11-42-09.webm');
INSERT INTO `record_video` VALUES ('2248', '20181127114339shijiaoshi1', '26', '0', '2018-11-27 11:42:36', null, '62481', null, 'http://10.100.99.22/record/7986f5c0-2b0b-4b16-870a-41e7c37866ec-----26-18501-----2018-11-27-11-42-19.webm');
INSERT INTO `record_video` VALUES ('2249', '20181127134436shijiaoshi1', '26', '0', '2018-11-27 13:43:33', null, '62481', null, 'http://10.100.99.22/record/2c3ed6b1-9e3e-41f9-b28d-685cba8529da-----26-16435-----2018-11-27-13-43-16.webm');
INSERT INTO `record_video` VALUES ('2250', '20181127134437shijiaoshi1', '26', '0', '2018-11-27 13:43:34', null, '62481', null, 'http://10.100.99.22/record/f28b7eee-e208-4738-a89b-b670d97a3231-----26-16435-----2018-11-27-13-43-17.webm');
INSERT INTO `record_video` VALUES ('2251', '20181127134625shijiaoshi1', '26', '0', '2018-11-27 13:45:22', null, '62481', null, 'http://10.100.99.22/record/b53c041b-efa7-4211-8878-11f38e8a7e40-----26-19327-----2018-11-27-13-45-05.webm');
INSERT INTO `record_video` VALUES ('2252', '20181127134708shijiaoshi1', '26', '0', '2018-11-27 13:46:05', null, '62481', null, 'http://10.100.99.22/record/04fcd2b6-974e-46b8-964d-37c71b5f6a2e-----26-19327-----2018-11-27-13-45-48.webm');
INSERT INTO `record_video` VALUES ('2253', '20181127134847shijiaoshi1', '26', '0', '2018-11-27 13:47:44', null, '62481', null, 'http://10.100.99.22/record/bd797afa-7f7a-4b6e-8daa-f1b30cfe05af-----26-13895-----2018-11-27-13-47-26.webm');
INSERT INTO `record_video` VALUES ('2254', '20181127134911shijiaoshi1', '26', '0', '2018-11-27 13:48:08', null, '62481', null, 'http://10.100.99.22/record/58ad1528-220e-42c7-9dd3-0593767e3a2c-----26-13895-----2018-11-27-13-47-51.webm');
INSERT INTO `record_video` VALUES ('2255', '20181127135015shijiaoshi1', '26', '0', '2018-11-27 13:49:12', null, '62481', null, 'http://10.100.99.22/record/36bce5c0-f7d5-4468-8e87-1496b6b668ac-----26-10668-----2018-11-27-13-48-54.webm');
INSERT INTO `record_video` VALUES ('2256', '20181127135020shijiaoshi1', '26', '0', '2018-11-27 13:49:17', null, '62481', null, 'http://10.100.99.22/record/74f2cb05-4b0a-4786-a048-afe63ced423a-----26-10668-----2018-11-27-13-49-00.webm');
INSERT INTO `record_video` VALUES ('2257', '20181127162945shijiaoshi1', '26', '0', '2018-11-27 16:30:23', null, '62481', null, 'http://10.100.99.22/record/d992c931-42f3-4989-b3c7-37a5a7e491cb-----26-18568-----2018-11-27-16-28-25.webm');
INSERT INTO `record_video` VALUES ('2258', '20181127163036shijiaoshi1', '26', '0', '2018-11-27 16:31:14', null, '62481', null, 'http://10.100.99.22/record/f063e334-72b3-4391-b308-2cbc8b17c520-----26-18568-----2018-11-27-16-29-15.webm');
INSERT INTO `record_video` VALUES ('2259', '20181128093926shijiaoshi1', '26', '0', '2018-11-28 09:38:05', null, '62481', null, 'http://10.100.99.22/record/841f4594-d837-49d0-bc11-2a327ff09ee5-----26-13276-----2018-11-28-09-38-04.webm');
INSERT INTO `record_video` VALUES ('2260', '20181128135620shijiaoshi1', '26', '0', '2018-11-28 13:54:59', null, '62481', null, 'http://10.100.99.22/record/d16e4900-d142-450f-ba3c-94e9826da3a9-----26-12997-----2018-11-28-13-54-57.webm');
INSERT INTO `record_video` VALUES ('2261', '20181128135651shijiaoshi1', '26', '0', '2018-11-28 13:55:30', null, '62481', null, 'http://10.100.99.22/record/94b3eec8-5929-47df-a892-ba3f3ce06453-----26-12997-----2018-11-28-13-55-29.webm');
INSERT INTO `record_video` VALUES ('2262', '20181128141421shijiaoshi1', '26', '0', '2018-11-28 14:13:00', null, '62481', null, 'http://10.100.99.22/record/662d95b5-bdfa-43d6-958f-f98ecfc8afcd-----26-14504-----2018-11-28-14-12-58.webm');
INSERT INTO `record_video` VALUES ('2263', '20181128141423shijiaoshi1', '26', '0', '2018-11-28 14:13:02', null, '62481', null, 'http://10.100.99.22/record/cf8c84a1-25bd-451f-b25e-73f88ab70cdf-----26-14504-----2018-11-28-14-13-00.webm');
INSERT INTO `record_video` VALUES ('2264', '20181128141701shijiaoshi1', '26', '0', '2018-11-28 14:15:40', null, '62481', null, 'http://10.100.99.22/record/efde1125-ef8f-44b1-91bc-65c64d0c7043-----26-17492-----2018-11-28-14-15-38.webm');
INSERT INTO `record_video` VALUES ('2265', '20181128141707shijiaoshi1', '26', '0', '2018-11-28 14:15:46', null, '62481', null, 'http://10.100.99.22/record/4c3eb437-c9ef-4c6f-a820-7c2bf896df30-----26-17492-----2018-11-28-14-15-44.webm');
INSERT INTO `record_video` VALUES ('2266', '20181128141959shijiaoshi1', '26', '0', '2018-11-28 14:18:38', null, '62481', null, 'http://10.100.99.22/record/b502906a-3370-4090-a196-089a22e53f9a-----26-14810-----2018-11-28-14-18-36.webm');
INSERT INTO `record_video` VALUES ('2267', '20181128142004shijiaoshi1', '26', '0', '2018-11-28 14:18:44', null, '62481', null, 'http://10.100.99.22/record/1d11757d-639e-407c-bbc9-befe54bce544-----26-14810-----2018-11-28-14-18-42.webm');
INSERT INTO `record_video` VALUES ('2268', '20181128144033shijiaoshi1', '26', '0', '2018-11-28 14:39:12', null, '62481', null, 'http://10.100.99.22/record/ca6d17da-9d86-4de7-8cd5-0f713e4c99c3-----26-13566-----2018-11-28-14-39-11.webm');
INSERT INTO `record_video` VALUES ('2269', '20181128144035shijiaoshi1', '26', '0', '2018-11-28 14:39:14', null, '62481', null, 'http://10.100.99.22/record/1fe1ebfa-f6c9-4a27-8e31-0bd8b3882620-----26-13566-----2018-11-28-14-39-12.webm');
INSERT INTO `record_video` VALUES ('2270', '20181128152432shijiaoshi1', '26', '0', '2018-11-28 15:23:11', null, '62481', null, 'http://10.100.99.22/record/8e21b85e-7bf3-40b1-a6fa-b8b69a742e67-----26-19054-----2018-11-28-15-23-09.webm');
INSERT INTO `record_video` VALUES ('2271', '20181128152453shijiaoshi1', '26', '0', '2018-11-28 15:23:32', null, '62481', null, 'http://10.100.99.22/record/68fd0680-3c7e-4aea-9473-82b17ffc0733-----26-19054-----2018-11-28-15-23-30.webm');
INSERT INTO `record_video` VALUES ('2272', '20181128152626shijiaoshi1', '26', '0', '2018-11-28 15:25:06', null, '62481', null, 'http://10.100.99.22/record/7c8a8226-4382-4380-a3c6-d807fe4f8a5f-----26-19487-----2018-11-28-15-25-04.webm');
INSERT INTO `record_video` VALUES ('2273', '20181128152632shijiaoshi1', '26', '0', '2018-11-28 15:25:11', null, '62481', null, 'http://10.100.99.22/record/8eef727c-43ca-4a91-b722-2fba515b858a-----26-19487-----2018-11-28-15-25-09.webm');
INSERT INTO `record_video` VALUES ('2274', '20181128152942shijiaoshi1', '26', '0', '2018-11-28 15:28:21', null, '62481', null, 'http://10.100.99.22/record/12fd648d-f0b6-4776-bd21-0dad3f6bf876-----26-12331-----2018-11-28-15-28-19.webm');
INSERT INTO `record_video` VALUES ('2275', '20181128153015shijiaoshi1', '26', '0', '2018-11-28 15:28:54', null, '62481', null, 'http://10.100.99.22/record/97e5bd38-ef28-4d07-8471-c896c0f9b354-----26-16198-----2018-11-28-15-28-52.webm');
INSERT INTO `record_video` VALUES ('2276', '20181128153033shijiaoshi1', '26', '0', '2018-11-28 15:29:12', null, '62481', null, 'http://10.100.99.22/record/c6ff5b03-9a30-431a-b0c2-acee7a0d2061-----26-16610-----2018-11-28-15-29-10.webm');
INSERT INTO `record_video` VALUES ('2277', '20181128153053shijiaoshi1', '26', '0', '2018-11-28 15:29:32', null, '62481', null, 'http://10.100.99.22/record/31ee4b1e-3be0-497c-aa9d-c143a66d7c61-----26-16610-----2018-11-28-15-29-30.webm');
INSERT INTO `record_video` VALUES ('2278', '20181128153057shijiaoshi1', '26', '0', '2018-11-28 15:29:37', null, '62481', null, 'http://10.100.99.22/record/0164eaea-9f53-4bf3-869a-14c93a296d9b-----26-12857-----2018-11-28-15-29-35.webm');
INSERT INTO `record_video` VALUES ('2279', '20181128153059shijiaoshi1', '26', '0', '2018-11-28 15:29:38', null, '62481', null, 'http://10.100.99.22/record/7819da67-f14c-43ce-ad1e-a499ff0ea1d2-----26-12857-----2018-11-28-15-29-36.webm');
INSERT INTO `record_video` VALUES ('2280', '20181128154555shijiaoshi1', '26', '0', '2018-11-28 15:44:34', null, '62481', null, 'http://10.100.99.22/record/b9d79223-4584-4811-8156-0cfcf6ee3a37-----26-17480-----2018-11-28-15-44-32.webm');
INSERT INTO `record_video` VALUES ('2281', '20181128154608shijiaoshi1', '26', '0', '2018-11-28 15:44:47', null, '62481', null, 'http://10.100.99.22/record/244b960c-7c18-4cef-86d8-8ff7cb0f3a17-----26-17480-----2018-11-28-15-44-45.webm');
INSERT INTO `record_video` VALUES ('2282', '20181128154829shijiaoshi1', '26', '0', '2018-11-28 15:47:09', null, '62481', null, 'http://10.100.99.22/record/e39c9a6f-141d-4e17-a482-b9a35234c81b-----26-17841-----2018-11-28-15-47-07.webm');
INSERT INTO `record_video` VALUES ('2283', '20181128154835shijiaoshi1', '26', '0', '2018-11-28 15:47:14', null, '62481', null, 'http://10.100.99.22/record/2be0a0d6-2c48-494f-9e46-1fe74ab3864a-----26-17841-----2018-11-28-15-47-12.webm');
INSERT INTO `record_video` VALUES ('2284', '20181128161701shijiaoshi1', '26', '0', '2018-11-28 16:15:40', null, '62481', null, 'http://10.100.99.22/record/3664ffe4-baac-4362-b6a1-b610396918eb-----26-18705-----2018-11-28-16-15-38.webm');
INSERT INTO `record_video` VALUES ('2285', '20181128161718shijiaoshi1', '26', '0', '2018-11-28 16:15:57', null, '62481', null, 'http://10.100.99.22/record/aa41866a-7de4-48de-aeae-b99eab09c949-----26-11150-----2018-11-28-16-15-55.webm');
INSERT INTO `record_video` VALUES ('2286', '20181128161727shijiaoshi1', '26', '0', '2018-11-28 16:16:06', null, '62481', null, 'http://10.100.99.22/record/04841ddc-ce5e-495a-a366-0c425c21b4f9-----26-11150-----2018-11-28-16-16-04.webm');
INSERT INTO `record_video` VALUES ('2287', '20181128161825shijiaoshi1', '26', '0', '2018-11-28 16:17:04', null, '62481', null, 'http://10.100.99.22/record/5d19cd3e-1a23-403b-aef1-a5ac939ab4b0-----26-13128-----2018-11-28-16-17-02.webm');
INSERT INTO `record_video` VALUES ('2288', '20181128161827shijiaoshi1', '26', '0', '2018-11-28 16:17:06', null, '62481', null, 'http://10.100.99.22/record/f1b243ed-9d17-4784-9cce-cf57c679965d-----26-13128-----2018-11-28-16-17-04.webm');
INSERT INTO `record_video` VALUES ('2289', '20181128161838shijiaoshi1', '26', '0', '2018-11-28 16:17:17', null, '62481', null, 'http://10.100.99.22/record/eb7b327e-cd28-4c72-91d1-7a37f5a80169-----26-19359-----2018-11-28-16-17-15.webm');
INSERT INTO `record_video` VALUES ('2290', '20181128161859shijiaoshi1', '26', '0', '2018-11-28 16:17:38', null, '62481', null, 'http://10.100.99.22/record/0aa4fc9e-1ed1-44ee-bdea-dbf035ce6ea3-----26-19359-----2018-11-28-16-17-36.webm');
INSERT INTO `record_video` VALUES ('2291', '20181128161954shijiaoshi1', '26', '0', '2018-11-28 16:18:33', null, '62481', null, 'http://10.100.99.22/record/3b0cd031-bec7-4b98-8130-c7a51528722c-----26-14096-----2018-11-28-16-18-31.webm');
INSERT INTO `record_video` VALUES ('2292', '20181128161955shijiaoshi1', '26', '0', '2018-11-28 16:18:35', null, '62481', null, 'http://10.100.99.22/record/8ec872d0-0e04-453f-aa9d-0a816dfa56d7-----26-14096-----2018-11-28-16-18-33.webm');
INSERT INTO `record_video` VALUES ('2293', '20181128162701shijiaoshi1', '26', '0', '2018-11-28 16:25:40', null, '62481', null, 'http://10.100.99.22/record/390e4f9e-de05-4252-a856-45fa7c19a104-----26-13091-----2018-11-28-16-25-38.webm');
INSERT INTO `record_video` VALUES ('2294', '20181128162744shijiaoshi1', '26', '0', '2018-11-28 16:26:23', null, '62481', null, 'http://10.100.99.22/record/dd033f7b-ca71-49a6-ae40-0b9f2949707d-----26-13091-----2018-11-28-16-26-21.webm');
INSERT INTO `record_video` VALUES ('2295', '20181128162839shijiaoshi1', '26', '0', '2018-11-28 16:27:18', null, '62481', null, 'http://10.100.99.22/record/5452de5d-fa26-4e06-9f72-a25724686977-----26-17785-----2018-11-28-16-27-16.webm');
INSERT INTO `record_video` VALUES ('2296', '20181128162845shijiaoshi1', '26', '0', '2018-11-28 16:27:24', null, '62481', null, 'http://10.100.99.22/record/d2306c27-022e-4d07-813e-f95fcc03722d-----26-14183-----2018-11-28-16-27-22.webm');
INSERT INTO `record_video` VALUES ('2297', '20181128162854shijiaoshi1', '26', '0', '2018-11-28 16:27:33', null, '62481', null, 'http://10.100.99.22/record/306caad8-31a6-4b3c-a7bb-73ad8853e712-----26-10073-----2018-11-28-16-27-31.webm');
INSERT INTO `record_video` VALUES ('2298', '20181128162900shijiaoshi1', '26', '0', '2018-11-28 16:27:39', null, '62481', null, 'http://10.100.99.22/record/e9e89475-a71d-4d49-9a9f-d04599e861bf-----26-10073-----2018-11-28-16-27-37.webm');
INSERT INTO `record_video` VALUES ('2299', '20181128163901shijiaoshi1', '26', '0', '2018-11-28 16:37:40', null, '62481', null, 'http://10.100.99.22/record/568b64c8-ba9f-43b1-941a-2decc683399f-----26-16791-----2018-11-28-16-37-38.webm');
INSERT INTO `record_video` VALUES ('2300', '20181128163902shijiaoshi1', '26', '0', '2018-11-28 16:37:41', null, '62481', null, 'http://10.100.99.22/record/5c9ce1e2-1279-42a3-a13e-a06a100ef95e-----26-16791-----2018-11-28-16-37-40.webm');
INSERT INTO `record_video` VALUES ('2301', '20181128164047shijiaoshi1', '26', '0', '2018-11-28 16:39:26', null, '62481', null, 'http://10.100.99.22/record/15640338-c197-4bc1-bb59-dfc38e1acb46-----26-14855-----2018-11-28-16-39-24.webm');
INSERT INTO `record_video` VALUES ('2302', '20181128164049shijiaoshi1', '26', '0', '2018-11-28 16:39:28', null, '62481', null, 'http://10.100.99.22/record/b014338f-a28e-4b2a-8e65-1b5d182d6875-----26-14855-----2018-11-28-16-39-26.webm');
INSERT INTO `record_video` VALUES ('2303', '20181128165135shijiaoshi1', '26', '0', '2018-11-28 16:50:14', null, '62481', null, 'http://10.100.99.22/record/f474495e-dd94-4c30-bf32-9b98f178717c-----26-15832-----2018-11-28-16-50-12.webm');
INSERT INTO `record_video` VALUES ('2304', '20181128165137shijiaoshi1', '26', '0', '2018-11-28 16:50:16', null, '62481', null, 'http://10.100.99.22/record/3fb4575f-2775-442e-bea5-d68bf14810cb-----26-15832-----2018-11-28-16-50-14.webm');
INSERT INTO `record_video` VALUES ('2305', '20181128165926shijiaoshi1', '26', '0', '2018-11-28 16:58:05', null, '62481', null, 'http://10.100.99.22/record/26d16a6b-9035-4763-9f18-ad7b9c51bc85-----26-15457-----2018-11-28-16-58-03.webm');
INSERT INTO `record_video` VALUES ('2306', '20181128165932shijiaoshi1', '26', '0', '2018-11-28 16:58:11', null, '62481', null, 'http://10.100.99.22/record/a73894a7-4567-4b5c-ba27-207de9a4ffe3-----26-15457-----2018-11-28-16-58-09.webm');
INSERT INTO `record_video` VALUES ('2307', '20181128171442shijiaoshi1', '26', '0', '2018-11-28 17:13:21', null, '62481', null, 'http://10.100.99.22/record/f8ced355-745f-42f3-9cb8-614220571c3e-----26-14523-----2018-11-28-17-13-19.webm');
INSERT INTO `record_video` VALUES ('2308', '20181128171447shijiaoshi1', '26', '0', '2018-11-28 17:13:26', null, '62481', null, 'http://10.100.99.22/record/4875b4f4-16d4-4751-85ee-d514c454c3b6-----26-14523-----2018-11-28-17-13-24.webm');
INSERT INTO `record_video` VALUES ('2309', '20181128172218shijiaoshi1', '26', '0', '2018-11-28 17:20:57', null, '62481', null, 'http://10.100.99.22/record/4d53ece9-a4ac-44c0-be16-68a766e092c5-----26-17486-----2018-11-28-17-20-55.webm');
INSERT INTO `record_video` VALUES ('2310', '20181128172220shijiaoshi1', '26', '0', '2018-11-28 17:20:59', null, '62481', null, 'http://10.100.99.22/record/420843d5-3677-4426-8a1c-148a250ea7b1-----26-17486-----2018-11-28-17-20-57.webm');
INSERT INTO `record_video` VALUES ('2311', '20181128172553shijiaoshi1', '26', '0', '2018-11-28 17:24:32', null, '62481', null, 'http://10.100.99.22/record/814ba61f-5ff9-4687-9284-eee98a112a44-----26-10621-----2018-11-28-17-24-30.webm');
INSERT INTO `record_video` VALUES ('2312', '20181128172603shijiaoshi1', '26', '0', '2018-11-28 17:24:42', null, '62481', null, 'http://10.100.99.22/record/414369f4-9b76-46f0-9c3e-4280782b6ed8-----26-10621-----2018-11-28-17-24-40.webm');
INSERT INTO `record_video` VALUES ('2313', '20181128173306shijiaoshi1', '26', '0', '2018-11-28 17:31:45', null, '62481', null, 'http://10.100.99.22/record/9dbc4bdd-b014-4947-b8e7-4633836e93bb-----26-18468-----2018-11-28-17-31-43.webm');
INSERT INTO `record_video` VALUES ('2314', '20181128173306shijiaoshi1', '26', '0', '2018-11-28 17:31:45', null, '62481', null, 'http://10.100.99.22/record/241206ed-e78c-43dc-87d4-b517df073342-----26-18468-----2018-11-28-17-31-43.webm');
INSERT INTO `record_video` VALUES ('2315', '20181128174547shijiaoshi1', '26', '0', '2018-11-28 17:44:26', null, '62481', null, 'http://10.100.99.22/record/6c57e47e-4ebe-465b-bcbc-a8c04066dcc7-----26-11225-----2018-11-28-17-44-24.webm');
INSERT INTO `record_video` VALUES ('2316', '20181128174650shijiaoshi1', '26', '0', '2018-11-28 17:45:29', null, '62481', null, 'http://10.100.99.22/record/8bd7bf32-813b-40aa-8de3-9a601f2441f2-----26-11225-----2018-11-28-17-45-27.webm');
INSERT INTO `record_video` VALUES ('2317', '20181129111959shijiaoshi1', '26', '0', '2018-11-29 11:18:36', null, '62481', null, 'http://10.100.99.22/record/487a5062-2774-44f4-805b-f20e873bd246-----26-13012-----2018-11-29-11-18-34.webm');
INSERT INTO `record_video` VALUES ('2318', '20181129112000shijiaoshi1', '26', '0', '2018-11-29 11:18:37', null, '62481', null, 'http://10.100.99.22/record/f573adc0-a52b-4776-b91e-f542a9e608a3-----26-13012-----2018-11-29-11-18-35.webm');
INSERT INTO `record_video` VALUES ('2319', '20181129141827shijiaoshi1', '26', '0', '2018-11-29 14:17:04', null, '62481', null, 'http://10.100.99.22/record/68e3a08a-7c43-45c5-a9fe-1aef88c25303-----26-13069-----2018-11-29-14-17-02.webm');
INSERT INTO `record_video` VALUES ('2320', '20181129141829shijiaoshi1', '26', '0', '2018-11-29 14:17:06', null, '62481', null, 'http://10.100.99.22/record/a18180e8-a048-4db4-8358-739b6c315312-----26-13069-----2018-11-29-14-17-04.webm');
INSERT INTO `record_video` VALUES ('2321', '20181129141945shijiaoshi1', '26', '0', '2018-11-29 14:18:22', null, '62481', null, 'http://10.100.99.22/record/5138f8d7-2258-4aae-96fb-eec2c6f74b16-----26-11284-----2018-11-29-14-18-20.webm');
INSERT INTO `record_video` VALUES ('2322', '20181129141947shijiaoshi1', '26', '0', '2018-11-29 14:18:24', null, '62481', null, 'http://10.100.99.22/record/3f896591-ac02-487e-b170-400047d65ead-----26-11284-----2018-11-29-14-18-22.webm');
INSERT INTO `record_video` VALUES ('2323', '20181129143426shijiaoshi1', '26', '0', '2018-11-29 14:33:03', null, '62481', null, 'http://10.100.99.22/record/f43f5cc5-1d35-4cf0-a991-c9fdfcfa942b-----26-16555-----2018-11-29-14-33-01.webm');
INSERT INTO `record_video` VALUES ('2324', '20181129143429shijiaoshi1', '26', '0', '2018-11-29 14:33:06', null, '62481', null, 'http://10.100.99.22/record/8741d08b-9480-4a8f-9cec-7bd0db3ce4a0-----26-16555-----2018-11-29-14-33-03.webm');
INSERT INTO `record_video` VALUES ('2325', '20181129143931shijiaoshi1', '26', '0', '2018-11-29 14:38:08', null, '62481', null, 'http://10.100.99.22/record/03c4bdc2-1ac3-401f-83b1-e9b3344bfef2-----26-14126-----2018-11-29-14-38-06.webm');
INSERT INTO `record_video` VALUES ('2326', '20181129143933shijiaoshi1', '26', '0', '2018-11-29 14:38:10', null, '62481', null, 'http://10.100.99.22/record/eb19e7ee-e820-4357-acce-9994c4044ad6-----26-14126-----2018-11-29-14-38-08.webm');
INSERT INTO `record_video` VALUES ('2327', '20181129152303shijiaoshi1', '26', '0', '2018-11-29 15:21:40', null, '62481', null, 'http://10.100.99.22/record/56091ecb-4990-4ffd-b8ca-5fec1375f8c5-----26-11485-----2018-11-29-15-21-38.webm');
INSERT INTO `record_video` VALUES ('2328', '20181129152305shijiaoshi1', '26', '0', '2018-11-29 15:21:42', null, '62481', null, 'http://10.100.99.22/record/f3ebec9e-ccb9-41ee-be93-8e192919625b-----26-11485-----2018-11-29-15-21-40.webm');
INSERT INTO `record_video` VALUES ('2329', '20181129152448shijiaoshi1', '26', '0', '2018-11-29 15:23:25', null, '62481', null, 'http://10.100.99.22/record/780d0e5c-041c-45e5-ba49-3bc3e638dedd-----26-18703-----2018-11-29-15-23-23.webm');
INSERT INTO `record_video` VALUES ('2330', '20181129152450shijiaoshi1', '26', '0', '2018-11-29 15:23:27', null, '62481', null, 'http://10.100.99.22/record/5971fc1e-7e11-44be-9e18-ee04ebf73443-----26-18703-----2018-11-29-15-23-25.webm');
INSERT INTO `record_video` VALUES ('2331', '20181129152459shijiaoshi1', '26', '0', '2018-11-29 15:23:36', null, '62481', null, 'http://10.100.99.22/record/e982aaa1-84e2-41e8-b002-c8bcc9e96b1b-----26-18402-----2018-11-29-15-23-34.webm');
INSERT INTO `record_video` VALUES ('2332', '20181129152501shijiaoshi1', '26', '0', '2018-11-29 15:23:38', null, '62481', null, 'http://10.100.99.22/record/58bc5a30-57e9-4b70-845d-5985b58cb9f0-----26-18402-----2018-11-29-15-23-36.webm');
INSERT INTO `record_video` VALUES ('2333', '20181129152801shijiaoshi1', '26', '0', '2018-11-29 15:26:38', null, '62481', null, 'http://10.100.99.22/record/30ad5579-3a7f-408f-9a92-30e45b08443a-----26-19588-----2018-11-29-15-26-36.webm');
INSERT INTO `record_video` VALUES ('2334', '20181129152802shijiaoshi1', '26', '0', '2018-11-29 15:26:39', null, '62481', null, 'http://10.100.99.22/record/723b5ef2-c745-4c35-8082-eb7bbdd9138d-----26-19588-----2018-11-29-15-26-37.webm');
INSERT INTO `record_video` VALUES ('2335', '20181129161127shijiaoshi1', '26', '0', '2018-11-29 16:10:04', null, '62481', null, 'http://10.100.99.22/record/80f90006-9611-427a-90f1-97b26095ccdb-----26-18512-----2018-11-29-16-10-02.webm');
INSERT INTO `record_video` VALUES ('2336', '20181129161129shijiaoshi1', '26', '0', '2018-11-29 16:10:06', null, '62481', null, 'http://10.100.99.22/record/be7af6ff-bd45-408b-b344-6ddba26089c0-----26-18512-----2018-11-29-16-10-04.webm');
INSERT INTO `record_video` VALUES ('2337', '20181129162439shijiaoshi1', '26', '0', '2018-11-29 16:23:15', null, '62481', null, 'http://10.100.99.22/record/2bee1301-36fa-4edd-87a3-a992b621a74d-----26-14705-----2018-11-29-16-23-13.webm');
INSERT INTO `record_video` VALUES ('2338', '20181129162440shijiaoshi1', '26', '0', '2018-11-29 16:23:17', null, '62481', null, 'http://10.100.99.22/record/b64f5e3f-f0c2-422a-a81b-2b192a45ac80-----26-14705-----2018-11-29-16-23-15.webm');
INSERT INTO `record_video` VALUES ('2339', '20181129162446shijiaoshi1', '26', '0', '2018-11-29 16:23:23', null, '62481', null, 'http://10.100.99.22/record/6e6b40e3-a48c-433a-a5a8-62f70f5a679c-----26-16544-----2018-11-29-16-23-20.webm');
INSERT INTO `record_video` VALUES ('2340', '20181129162447shijiaoshi1', '26', '0', '2018-11-29 16:23:24', null, '62481', null, 'http://10.100.99.22/record/d6efc721-7cab-4d3b-855e-29b79a55271c-----26-16544-----2018-11-29-16-23-22.webm');
INSERT INTO `record_video` VALUES ('2341', '20181129162505shijiaoshi1', '26', '0', '2018-11-29 16:23:42', null, '62481', null, 'http://10.100.99.22/record/4c54294d-21e9-4b11-8cb8-46b7a55f5382-----26-17324-----2018-11-29-16-23-40.webm');
INSERT INTO `record_video` VALUES ('2342', '20181129162507shijiaoshi1', '26', '0', '2018-11-29 16:23:44', null, '62481', null, 'http://10.100.99.22/record/96f9254f-e5a5-47db-ba87-08afe9903834-----26-17324-----2018-11-29-16-23-41.webm');

-- ----------------------------
-- Table structure for `user_group`
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `user_group_id` int(11) NOT NULL,
  `user_group_name` varchar(100) NOT NULL,
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '管理员');
INSERT INTO `user_group` VALUES ('2', '老师');
INSERT INTO `user_group` VALUES ('3', 'B超室医生');

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_account` varchar(100) NOT NULL,
  `user_phone` varchar(50) NOT NULL,
  `user_group_id` int(11) NOT NULL,
  `isfreeze` int(11) NOT NULL,
  `create_time` char(20) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'admin', '13260532925', '1', '0', '2018-9-6', '123456');
INSERT INTO `user_info` VALUES ('2', '张三11', '15852365412', '3', '0', '2018-9-6', '123456');
INSERT INTO `user_info` VALUES ('3', '李四1', '15854785698', '3', '0', '2018-9-6', '123456');
INSERT INTO `user_info` VALUES ('4', '11111', '15896258888', '3', '0', '2018-09-06 14:28:02', '1111111');
INSERT INTO `user_info` VALUES ('7', '张医生', '18986270431', '3', '0', '2018-09-10 03:38:59', '123456');
INSERT INTO `user_info` VALUES ('8', '张老师', '18986270432', '2', '0', '2018-09-10 03:39:41', '123456');
INSERT INTO `user_info` VALUES ('10', 'qwer', '18040540028', '2', '0', '2018-09-11 05:52:15', '123456');
INSERT INTO `user_info` VALUES ('12', '123', '15865456325', '3', '0', '2018-09-18 15:26:08', '111111');
INSERT INTO `user_info` VALUES ('13', '12', '15236225632', '3', '0', '2018-09-18 16:01:16', '111111');
INSERT INTO `user_info` VALUES ('14', '1 23', '15963258745', '3', '0', '2018-09-18 16:05:14', '123 123');
INSERT INTO `user_info` VALUES ('15', '1231111111', '15236363636', '3', '0', '2018-09-18 16:07:01', '111111');
INSERT INTO `user_info` VALUES ('16', '1111111111', '15963258899', '3', '0', '2018-09-18 16:49:16', '111111111111');
INSERT INTO `user_info` VALUES ('21', '123', '15852585258', '3', '0', '2018-09-19 11:01:20', '111111');
INSERT INTO `user_info` VALUES ('26', 'fdgfd', '18040540033', '3', '0', '2018-09-21 09:29:57', '123456');
INSERT INTO `user_info` VALUES ('28', 'sddddd', '18040540066', '3', '0', '2018-09-26 15:14:18', '123456');
INSERT INTO `user_info` VALUES ('29', '研发测试', '18078945612', '2', '0', '2018-11-26 14:10:05', '123456');












/*=========================================================================================================*/
CREATE TABLE `module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', 'add');
INSERT INTO `module` VALUES ('2', 'delete');
INSERT INTO `module` VALUES ('3', 'query');
INSERT INTO `module` VALUES ('4', 'update');

-- ----------------------------
-- Table structure for module_role
-- ----------------------------
DROP TABLE IF EXISTS `module_role`;
CREATE TABLE `module_role` (
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  KEY `rid` (`rid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `module` (`mid`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_role
-- ----------------------------
INSERT INTO `module_role` VALUES ('1', '1');
INSERT INTO `module_role` VALUES ('1', '2');
INSERT INTO `module_role` VALUES ('1', '3');
INSERT INTO `module_role` VALUES ('1', '4');
INSERT INTO `module_role` VALUES ('2', '1');
INSERT INTO `module_role` VALUES ('2', '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'customer');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'hlhdidi', '123');
INSERT INTO `user` VALUES ('2', 'xyycici', '1992');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  KEY `u_fk` (`uid`),
  KEY `r_fk` (`rid`),
  CONSTRAINT `r_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`),
  CONSTRAINT `u_fk` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

