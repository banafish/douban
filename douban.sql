/*
Navicat MySQL Data Transfer

Source Server         : mea
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : douban

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-05-15 21:31:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(7) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(30) NOT NULL,
  `avatar` varchar(50) NOT NULL DEFAULT '/file/user_normal.jpg',
  `sign` varchar(30) NOT NULL DEFAULT '添加签名文档',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------
INSERT INTO `t_account` VALUES ('126', 'mea', 'e10adc3949ba59abbe56e057f20f883e', '1959291663@qq.com', '/file/15566857298091959291663.jpg', '喜欢唱，跳，rap，和打篮球');
INSERT INTO `t_account` VALUES ('129', 'xxm', 'f379eaf3c831b04de153469d1bec345e', 'bananice1959@qq.com', '/file/1556702561148bananice1959.jpg', '与分可用分');
INSERT INTO `t_account` VALUES ('130', 'bana', 'e10adc3949ba59abbe56e057f20f883e', 'bananice@mail2.gdut.edu.cn', '/file/1556702614801bananice.jpg', 'jhfjksdf');
INSERT INTO `t_account` VALUES ('131', 'naya', 'e10adc3949ba59abbe56e057f20f883e', '123456@qq.com', '/file/1556799119614123456.jpg', 'lalalalala.....');
INSERT INTO `t_account` VALUES ('133', '用户1', 'e10adc3949ba59abbe56e057f20f883e', '123@qq.com', '/file/user_normal.jpg', '添加签名文档');
INSERT INTO `t_account` VALUES ('134', '用户2', 'e10adc3949ba59abbe56e057f20f883e', '110@qq.com', '/file/user_normal.jpg', '添加签名文档');
INSERT INTO `t_account` VALUES ('135', '用户3', 'e10adc3949ba59abbe56e057f20f883e', '888@qq.com', '/file/user_normal.jpg', '添加签名文档');
INSERT INTO `t_account` VALUES ('136', '用户4', 'e10adc3949ba59abbe56e057f20f883e', '666@qq.com', '/file/user_normal.jpg', '添加签名文档');
INSERT INTO `t_account` VALUES ('137', '暴躁老哥', 'e10adc3949ba59abbe56e057f20f883e', '111@qq.com', '/file/1557900586598111.jpg', '添加签名文档');
INSERT INTO `t_account` VALUES ('138', '我我我我我我我', 'dfbc2185f68f31e9217cec217bb97b20', '1@qq.com', '/file/user_normal.jpg', '添加签名文档');

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author_email` varchar(30) NOT NULL,
  `title` varchar(30) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `content` text NOT NULL,
  `picture_urls` varchar(225) DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hot` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `f_a_a` (`author_email`),
  CONSTRAINT `f_a_a` FOREIGN KEY (`author_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('7', '1959291663@qq.com', '全职高手第二季pv截图，看后面的群演有惊喜', '音乐、社会、', '全职高手第二季pv截图，看后面的群演有惊喜', '/file/15567033425141959291663.jpg,/file/15567033425381959291663.jpg,', '2019-05-04 21:18:43', '0');
INSERT INTO `t_article` VALUES ('9', '1959291663@qq.com', '复联”演员报酬曝光：小罗伯特唐尼5亿、斯嘉丽约翰逊1.3亿', '言论、社会、', '<p>外媒Hollywood Reporter在一篇报道中曝光了几位漫威演员的电影报酬。</p><p>其中饰演钢铁侠的小罗伯特唐尼在《复联3》中至少拿走了7500万美元，约合人民币5.05亿。同其他演员不同的是，唐尼和漫威方面的财务商定更加特殊，多个消息源向THR的记者爆料了唐尼的报酬，在2017年的《蜘蛛侠：英雄归来》中，虽然他只进行了3天的拍摄，但每天仍收到了500万美元。<br></p><p>至于其他演员，例如寡姐斯嘉丽约翰逊在2000万美元左右，合人民币约1.3亿元，《黑寡妇》独立电影目前还在筹备中。“美国队长”克里斯·埃文斯和“雷神”克里斯·海姆斯沃斯则都在之前进行了重新谈判，范围在1500万到2000万美元之间。<br></p><p>至于演员能得到红利奖励，据一位了解交易过程的经纪人说，漫威将红利门槛从票房5亿美元调高至了7亿美元，而对《复仇者联盟》系列来说，这个门槛更是涨到了15亿美元，不过对此漫威和演员方面都拒绝进行评论。<br></p><p>之后一些漫威演员将出演迪士尼流媒体上的新剧，例如“绯红女巫”伊丽莎白·奥尔森、“猎鹰”安东尼·麦凯、“冬兵”塞巴斯蒂安·斯坦和“鹰眼”杰瑞米·雷纳，他们谈的合约和电影都是分开的，新剧集数在6~8集左右，这些合约并不会影响到后续的漫威电影。<br></p>', '/file/15567037343691959291663.jpg,', '2019-05-01 21:19:04', '1');
INSERT INTO `t_article` VALUES ('10', 'bananice1959@qq.com', 'emmm 这里 不会被没看过re0的人误会么 虽然我知道是为', '读书、音乐、', 'emmm 这里 不会被没看过re0的人误会么<br><div><font color=\"#333333\"><span style=\"background-color: rgb(255, 255, 255);\">虽然我知道是为了突出搞笑效果啊 但是这样不会让没看re0的人觉得斯巴鲁是个走运的家伙么 明明他都死了那么多次 却被认为是走运 这不是对他努力的最大的侮辱么？</span></font><br></div>', '/file/1556705439059bananice1959.jpg,/file/1556705439077bananice1959.jpg,/file/1556705439087bananice1959.jpg,/file/1556705439097bananice1959.jpg,', '2019-05-01 21:14:02', '0');
INSERT INTO `t_article` VALUES ('11', 'bananice@mail2.gdut.edu.cn', '在世界尽头咏唱恋曲的少女YU-NO第五话好搞笑啊！', '情感、', '男猪这是剧透了吧 哈哈', '/file/1556705565490bananice.jpg,/file/1556705565504bananice.jpg,', '2019-05-01 18:12:00', '0');
INSERT INTO `t_article` VALUES ('12', 'bananice1959@qq.com', '回顾火影话说团藏背锅类角色里最惨的一位了？', '读书、音乐、', '中期佩恩之战，神罗炸木叶不出手感觉这人物就有点歪了<div>本来好像是个为了村子背负黑暗的角色结果变成了一个一生都在攒复活币的lowB被柱子干死后后期AB索性直接成为万恶之源<br></div><div>是个人洗白就要拉出来鞭尸背锅<br></div><div>长门事件团藏有锅但也只是助力而已，毕竟轮回眼是最重要的一环斑也安排了带土当监护人。<br></div><div><div>兜为什么会变成boss?团藏和蛇叔逼得。这就是直接牵连了</div><div>然后bug问题最多的鼬屠族都变这货的锅了</div></div><div>最惨的是博人传也被拉出来背个莫名其妙的锅<br></div><div>这悲惨程度比时臣惨多了啊<br></div>', '/file/1556705959975bananice1959.jpg,', '2019-05-05 21:23:10', '1');
INSERT INTO `t_article` VALUES ('13', '123456@qq.com', '日本网友因不满B社给《辐射4》做了个萌化MOD', '思想、读书、', 'NexusMods hiyokomod&nbsp;日本一名网友为《辐射4》做了个萌化MOD「Animerace Nanakochan」，让你可以将玩家或者是NPC的模型都替换成动漫美少女，通过这一MOD你可以调整一些预置的参数，然后让游戏里的角色变成你心仪的样子，并且还会根据情况作出不同的表情。<div>MOD使用说明：<br></div><div>作者还解释了他创作这一MOD的理由：当他最初听见霞的声音时，就认定她是一位女神级别的美少女，由于实在是太喜欢了，甚至都忘记了这是一个Bethesda的游戏。然后当他在真正见到霞时，他的梦想幻灭了，大受打击的他只剩下两个选择：让她变成理想中那样或是告别这个游戏。</div><div><div>演示视频欣赏：</div></div><div>他选择了前者，然后在他去努力学习了此前从未接触过的建模和角色MOD的知识之后，现在终于创作出了这一MOD，发布两天以来，已有两千多人下载了它，你也可以访问Nexusmod的页面来获取。<br></div>', '/file/1556799411088123456.jpg,/file/1556799411100123456.jpg,/file/1556799411110123456.jpg,', '2019-05-02 20:16:00', '0');
INSERT INTO `t_article` VALUES ('14', '123456@qq.com', '古立特闹钟 下厨的茜', '言论、美食、', '古立特闹钟 下厨的茜&nbsp;', '/file/1556799509291123456.jpg,', '2019-05-06 20:41:56', '1');
INSERT INTO `t_article` VALUES ('15', '123456@qq.com', '真·中美合拍，成了', '读书、音乐、社会、', '【中美合拍，《变形金刚：哪吒》将于今年下半年在央视首播[tv_思考]】根据外媒报道，中央电视台将与美国孩之宝公司合拍一部名为《变形金刚：哪吒》的动画作品。本片将于今年第三季度在央视黄金档首播，虽然目前只会在中国播出，但未来很有可能面向全球发行。', '/file/1556799625399123456.jpg,/file/1556799625413123456.jpg,', '2019-05-02 20:20:00', '0');
INSERT INTO `t_article` VALUES ('20', '1959291663@qq.com', '测试页码刷新2', '思想、读书、音乐、', 'dhfbasdvbsdlv', '', '2019-05-02 22:18:00', '0');
INSERT INTO `t_article` VALUES ('25', '1959291663@qq.com', '测试页码刷新7', '言论、美食、思想、读书、音乐、社会、', 'dhfbasdvbsdlv', '', '2019-05-02 22:19:00', '0');
INSERT INTO `t_article` VALUES ('27', '1959291663@qq.com', '测试页码刷新9', '言论、读书、音乐、社会、', 'dhfbasdvbsdlv', '', '2019-05-02 22:19:00', '0');
INSERT INTO `t_article` VALUES ('36', '1959291663@qq.com', '测试页码刷新18', '思想、音乐、社会、', 'dhfbasdvbsdlv', '/file/15568068621591959291663.jpg,', '2019-05-02 22:21:00', '0');
INSERT INTO `t_article` VALUES ('37', '1959291663@qq.com', '测试页码刷新19', '思想、音乐、', 'dhfbasdvbsdlv', '/file/15568068681481959291663.jpg,', '2019-05-02 22:21:00', '0');
INSERT INTO `t_article` VALUES ('42', '1959291663@qq.com', '测试修改文章', '言论、情感、', '<u><strike><i><b>热嘎嘎撒发不发的吧</b></i></strike></u>', '', '2019-05-08 18:52:00', '0');
INSERT INTO `t_article` VALUES ('44', '1959291663@qq.com', '拦截违规字符', '音乐、', '<strike>懒得自己实现</strike>', '/file/15576646216931959291663.jpg,', '2019-05-15 13:36:47', '0');
INSERT INTO `t_article` VALUES ('45', '123@qq.com', '凑数', '读书、音乐、', '<i>测试测试</i>', '/file/1557762524265123.jpg,', '2019-05-13 23:48:00', '0');
INSERT INTO `t_article` VALUES ('47', '888@qq.com', '凑数', '言论、', '<ul><li>测试测试测试<br></li></ul>', '/file/1557762777960888.jpg,', '2019-05-13 23:52:00', '0');
INSERT INTO `t_article` VALUES ('48', '888@qq.com', '凑数2', '读书、', '测试测试测试<div>cs&nbsp;</div><div>删除测试</div>', '/file/1557762850008888.jpg,', '2019-05-13 23:54:00', '0');
INSERT INTO `t_article` VALUES ('49', '111@qq.com', '凑数3', '情感、', '测试<div>测试</div><div>测试</div>', '/file/1557763094762111.jpg,', '2019-05-13 23:58:00', '0');
INSERT INTO `t_article` VALUES ('50', '111@qq.com', '凑数4', '情感、读书、', '测试<div>测试</div><div>测试</div><div>测试</div>', '/file/1557763115444111.jpg,/file/1557763115456111.jpg,', '2019-05-13 23:58:00', '0');
INSERT INTO `t_article` VALUES ('51', '111@qq.com', '凑数5', '社会、', '<p>测试</p><p>测试</p><p>测试</p>', '', '2019-05-14 00:00:00', '0');
INSERT INTO `t_article` VALUES ('52', '111@qq.com', '凑数6', '音乐、社会、', '<p>测试</p><p>测试</p><p>测试</p>', '', '2019-05-14 00:00:00', '0');
INSERT INTO `t_article` VALUES ('54', '111@qq.com', '凑数8', '读书、音乐、社会、', '<p>测试</p><p>测试</p><p>测试</p>', '/file/1557763289431111.jpg,', '2019-05-15 21:05:39', '0');
INSERT INTO `t_article` VALUES ('55', '110@qq.com', '凑数10', '', '凑数<div>测试</div>', '/file/1557763531263110.jpg,', '2019-05-14 00:05:00', '0');
INSERT INTO `t_article` VALUES ('56', '110@qq.com', '凑数11', '音乐、', '凑数<div>测试</div>', '/file/1557763538931110.jpg,', '2019-05-14 00:05:00', '0');
INSERT INTO `t_article` VALUES ('57', '110@qq.com', '凑数12', '读书、音乐、', '凑数<div>测试</div>', '/file/1557763544879110.jpg,', '2019-05-14 00:05:00', '0');
INSERT INTO `t_article` VALUES ('58', 'bananice@mail2.gdut.edu.cn', '//', '', '///', '', '2019-05-15 00:41:00', '0');
INSERT INTO `t_article` VALUES ('59', 'bananice@mail2.gdut.edu.cn', '//', '', '?????', '', '2019-05-15 00:41:00', '0');
INSERT INTO `t_article` VALUES ('60', 'bananice1959@qq.com', '11', '', '1111', '', '2019-05-15 00:52:00', '0');
INSERT INTO `t_article` VALUES ('61', 'bananice1959@qq.com', '11', '', '2222', '', '2019-05-15 00:52:00', '0');
INSERT INTO `t_article` VALUES ('62', 'bananice1959@qq.com', 'nmsl', '', 'snml', '', '2019-05-15 00:59:00', '0');
INSERT INTO `t_article` VALUES ('63', 'bananice1959@qq.com', 'nmsl', '', 'nmslnmsl', '', '2019-05-15 01:03:00', '0');
INSERT INTO `t_article` VALUES ('64', 'bananice1959@qq.com', 'xcxc', '', '2134', '', '2019-05-15 01:06:00', '0');
INSERT INTO `t_article` VALUES ('65', 'bananice1959@qq.com', '34rt34t', '读书、', 'gfqwg', '/file/1557853621247bananice1959.jpg,', '2019-05-15 01:07:00', '0');

-- ----------------------------
-- Table structure for t_article_info
-- ----------------------------
DROP TABLE IF EXISTS `t_article_info`;
CREATE TABLE `t_article_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(30) NOT NULL,
  `article_id` int(11) NOT NULL,
  `good` int(1) DEFAULT NULL,
  `collect` int(1) DEFAULT NULL,
  `forword` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_eamil` (`user_email`,`article_id`) USING BTREE,
  KEY `f_a_i` (`article_id`),
  CONSTRAINT `f_a_i` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`) ON DELETE CASCADE,
  CONSTRAINT `f_u_i` FOREIGN KEY (`user_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article_info
-- ----------------------------
INSERT INTO `t_article_info` VALUES ('1', '1959291663@qq.com', '7', '1', null, null);
INSERT INTO `t_article_info` VALUES ('2', '1959291663@qq.com', '10', '1', '1', null);
INSERT INTO `t_article_info` VALUES ('3', '1959291663@qq.com', '11', '1', null, null);
INSERT INTO `t_article_info` VALUES ('4', '1959291663@qq.com', '12', null, '1', '1');
INSERT INTO `t_article_info` VALUES ('5', '123456@qq.com', '7', '1', '1', null);
INSERT INTO `t_article_info` VALUES ('6', '123456@qq.com', '9', '1', '1', null);
INSERT INTO `t_article_info` VALUES ('7', '1959291663@qq.com', '9', null, null, null);
INSERT INTO `t_article_info` VALUES ('44', 'bananice1959@qq.com', '9', '1', '1', '1');
INSERT INTO `t_article_info` VALUES ('51', '1959291663@qq.com', '15', '1', null, '1');
INSERT INTO `t_article_info` VALUES ('54', '1959291663@qq.com', '14', '1', '1', '1');
INSERT INTO `t_article_info` VALUES ('57', '1959291663@qq.com', '13', null, null, '1');
INSERT INTO `t_article_info` VALUES ('60', 'bananice1959@qq.com', '14', '1', null, '1');
INSERT INTO `t_article_info` VALUES ('62', '123456@qq.com', '14', '1', null, null);
INSERT INTO `t_article_info` VALUES ('63', '110@qq.com', '14', '1', null, null);

-- ----------------------------
-- Table structure for t_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_email` varchar(30) NOT NULL,
  `to_email` varchar(30) NOT NULL,
  `msg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f_chat1` (`from_email`),
  KEY `f_chat2` (`to_email`),
  CONSTRAINT `f_chat1` FOREIGN KEY (`from_email`) REFERENCES `t_friend` (`host_email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_chat2` FOREIGN KEY (`to_email`) REFERENCES `t_friend` (`guest_email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_chat
-- ----------------------------

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NOT NULL,
  `user_email` varchar(30) NOT NULL,
  `content` varchar(255) NOT NULL,
  `time` datetime DEFAULT NULL,
  `good_count` int(5) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `f_c_a_a` (`article_id`),
  KEY `f_c_u_u` (`user_email`),
  CONSTRAINT `f_c_a_a` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_c_u_u` FOREIGN KEY (`user_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('1', '14', '1959291663@qq.com', 'buy,buy,buy', '2019-05-10 15:53:52', '15');
INSERT INTO `t_comment` VALUES ('2', '14', '1959291663@qq.com', '测试1', '2019-05-10 15:54:17', '10');
INSERT INTO `t_comment` VALUES ('3', '14', '1959291663@qq.com', '测试2', '2019-05-10 15:06:47', '5');
INSERT INTO `t_comment` VALUES ('4', '14', '1959291663@qq.com', '测试3', '2019-05-10 15:06:47', '5');
INSERT INTO `t_comment` VALUES ('5', '14', '1959291663@qq.com', '测试4', '2019-05-10 15:17:20', '6');
INSERT INTO `t_comment` VALUES ('6', '14', '1959291663@qq.com', '测试5', '2019-05-10 15:17:23', '7');
INSERT INTO `t_comment` VALUES ('7', '14', '1959291663@qq.com', '测试6', '2019-05-10 15:17:27', '7');
INSERT INTO `t_comment` VALUES ('8', '14', 'bananice1959@qq.com', '为什么你妈的', '2019-05-11 00:09:00', '5');
INSERT INTO `t_comment` VALUES ('9', '14', 'bananice1959@qq.com', '为什么', '2019-05-11 00:10:00', '0');
INSERT INTO `t_comment` VALUES ('10', '14', 'bananice1959@qq.com', 'mygod', '2019-05-11 00:10:00', '0');
INSERT INTO `t_comment` VALUES ('11', '14', 'bananice@mail2.gdut.edu.cn', 'haihaihaiqilai', '2019-05-11 00:35:00', '1');
INSERT INTO `t_comment` VALUES ('12', '14', 'bananice@mail2.gdut.edu.cn', '爆肝', '2019-05-11 00:35:00', '0');
INSERT INTO `t_comment` VALUES ('13', '14', 'bananice1959@qq.com', '我的天', '2019-05-11 14:58:00', '0');
INSERT INTO `t_comment` VALUES ('14', '12', '123@qq.com', '啦啦啦啦', '2019-05-11 15:03:00', '0');
INSERT INTO `t_comment` VALUES ('15', '12', '123@qq.com', '我死了', '2019-05-11 15:03:00', '0');
INSERT INTO `t_comment` VALUES ('17', '7', '111@qq.com', '坎坎坷坷', '2019-05-11 15:28:00', '1');
INSERT INTO `t_comment` VALUES ('19', '9', '1959291663@qq.com', '？？？', '2019-05-13 15:05:00', '0');
INSERT INTO `t_comment` VALUES ('20', '51', '1959291663@qq.com', '评论', '2019-05-14 17:24:00', '1');

-- ----------------------------
-- Table structure for t_douyou
-- ----------------------------
DROP TABLE IF EXISTS `t_douyou`;
CREATE TABLE `t_douyou` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_email` varchar(30) NOT NULL,
  `guest_email` varchar(30) NOT NULL DEFAULT '',
  `msg` varchar(225) NOT NULL DEFAULT '',
  `statue` int(1) DEFAULT '1' COMMENT '本来打算用状态来显示已读未读功能，结果不够时间实现',
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `f_send` (`host_email`),
  KEY `f_to` (`guest_email`),
  CONSTRAINT `f_send` FOREIGN KEY (`host_email`) REFERENCES `t_account` (`email`),
  CONSTRAINT `f_to` FOREIGN KEY (`guest_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_douyou
-- ----------------------------
INSERT INTO `t_douyou` VALUES ('3', '1959291663@qq.com', '123456@qq.com', '闹钟我买爆', '1', '2019-05-09 01:26:00');
INSERT INTO `t_douyou` VALUES ('4', '1959291663@qq.com', '123456@qq.com', '好友', '1', '2019-05-08 13:05:25');
INSERT INTO `t_douyou` VALUES ('5', '1959291663@qq.com', '123456@qq.com', '茜是我老婆，不接受反驳', '1', '2019-05-09 01:37:00');
INSERT INTO `t_douyou` VALUES ('6', '1959291663@qq.com', '123456@qq.com', '我永远喜欢新条茜.jpg', '1', '2019-05-09 13:14:00');
INSERT INTO `t_douyou` VALUES ('7', '123456@qq.com', '1959291663@qq.com', 'nmsl', '1', '2019-05-09 13:23:00');
INSERT INTO `t_douyou` VALUES ('8', '123456@qq.com', '1959291663@qq.com', '是我的', '1', '2019-05-09 13:23:00');
INSERT INTO `t_douyou` VALUES ('9', '123456@qq.com', '1959291663@qq.com', 'tmd,cnmlgb', '1', '2019-05-09 13:23:00');
INSERT INTO `t_douyou` VALUES ('10', '1959291663@qq.com', '123456@qq.com', '<img src=\"/file/1556799874993123456.jpg\">', '1', '2019-05-09 13:26:00');
INSERT INTO `t_douyou` VALUES ('16', '123456@qq.com', '1959291663@qq.com', '1111111111', '1', '2019-05-09 13:29:00');
INSERT INTO `t_douyou` VALUES ('18', '1959291663@qq.com', '123@qq.com', 'lalalala', '1', '2019-05-13 17:05:00');
INSERT INTO `t_douyou` VALUES ('19', '1959291663@qq.com', '123@qq.com', 'wsm为什么', '1', '2019-05-13 21:16:00');
INSERT INTO `t_douyou` VALUES ('20', '1959291663@qq.com', '123@qq.com', '测试', '1', '2019-05-14 17:25:00');
INSERT INTO `t_douyou` VALUES ('22', 'bananice1959@qq.com', '123@qq.com', 'fwgqw', '1', '2019-05-15 01:11:00');
INSERT INTO `t_douyou` VALUES ('23', '1959291663@qq.com', '123456@qq.com', '测试', '1', '2019-05-15 13:39:00');
INSERT INTO `t_douyou` VALUES ('24', '123456@qq.com', '1959291663@qq.com', 'nmsl', '1', '2019-05-09 13:23:00');
INSERT INTO `t_douyou` VALUES ('25', '123456@qq.com', '1959291663@qq.com', 'nmsl', '1', '2019-05-09 13:23:00');
INSERT INTO `t_douyou` VALUES ('26', '1959291663@qq.com', '111@qq.com', '111111', '1', '2019-05-15 21:05:00');

-- ----------------------------
-- Table structure for t_follow
-- ----------------------------
DROP TABLE IF EXISTS `t_follow`;
CREATE TABLE `t_follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(30) NOT NULL,
  `follow_email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_e_f` (`user_email`,`follow_email`) USING BTREE,
  KEY `f_f_e` (`follow_email`),
  CONSTRAINT `f_f_e` FOREIGN KEY (`follow_email`) REFERENCES `t_account` (`email`),
  CONSTRAINT `f_u_e` FOREIGN KEY (`user_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_follow
-- ----------------------------
INSERT INTO `t_follow` VALUES ('35', '123456@qq.com', '123@qq.com');
INSERT INTO `t_follow` VALUES ('16', '123456@qq.com', '1959291663@qq.com');
INSERT INTO `t_follow` VALUES ('10', '1959291663@qq.com', '111@qq.com');
INSERT INTO `t_follow` VALUES ('27', '1959291663@qq.com', '123456@qq.com');
INSERT INTO `t_follow` VALUES ('22', '1959291663@qq.com', '123@qq.com');
INSERT INTO `t_follow` VALUES ('26', '1959291663@qq.com', '1959291663@qq.com');
INSERT INTO `t_follow` VALUES ('12', '1959291663@qq.com', '888@qq.com');
INSERT INTO `t_follow` VALUES ('5', '1959291663@qq.com', 'bananice1959@qq.com');
INSERT INTO `t_follow` VALUES ('14', '1959291663@qq.com', 'bananice@mail2.gdut.edu.cn');
INSERT INTO `t_follow` VALUES ('33', 'bananice1959@qq.com', '111@qq.com');
INSERT INTO `t_follow` VALUES ('9', 'bananice1959@qq.com', '1959291663@qq.com');

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_email` varchar(30) NOT NULL,
  `guest_email` varchar(30) NOT NULL DEFAULT '',
  `msg` varchar(10) NOT NULL DEFAULT '好友',
  `time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `statue` int(1) DEFAULT '1',
  `host_black` int(1) DEFAULT '0',
  `guest_black` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_friend` (`host_email`,`guest_email`) USING HASH,
  KEY `f_friend` (`guest_email`),
  KEY `host_email` (`host_email`),
  CONSTRAINT `f_friend` FOREIGN KEY (`guest_email`) REFERENCES `t_account` (`email`),
  CONSTRAINT `f_user` FOREIGN KEY (`host_email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES ('3', '1959291663@qq.com', '123456@qq.com', '同事', '2019-05-15 13:47:37', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('5', '1959291663@qq.com', 'bananice1959@qq.com', '家人', '2019-05-15 13:47:36', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('7', 'bananice@mail2.gdut.edu.cn', '1959291663@qq.com', '同学', '2019-05-08 22:41:18', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('13', '1959291663@qq.com', 'bananice@mail2.gdut.edu.cn', '同事', '2019-05-08 22:41:18', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('15', 'bananice1959@qq.com', '1959291663@qq.com', '好友1', '2019-05-15 13:47:36', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('18', '666@qq.com', '1959291663@qq.com', '好友4', '2019-05-15 15:29:32', '0', '0', '1');
INSERT INTO `t_friend` VALUES ('24', '1959291663@qq.com', '666@qq.com', '同事', '2019-05-15 15:29:32', '0', '1', '0');
INSERT INTO `t_friend` VALUES ('37', '123456@qq.com', '1959291663@qq.com', '好友', '2019-05-15 13:47:37', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('111', '110@qq.com', 'bananice1959@qq.com', '好友', '2019-05-09 16:44:29', '1', '0', '0');
INSERT INTO `t_friend` VALUES ('112', '888@qq.com', 'bananice1959@qq.com', '好友', '2019-05-08 16:44:47', '1', '0', '0');
INSERT INTO `t_friend` VALUES ('113', '666@qq.com', 'bananice1959@qq.com', '好友', '2019-05-09 16:44:55', '1', '0', '0');
INSERT INTO `t_friend` VALUES ('118', '1959291663@qq.com', '110@qq.com', '家人', '2019-05-14 19:45:00', '1', '0', '0');
INSERT INTO `t_friend` VALUES ('119', 'bananice1959@qq.com', '111@qq.com', '朋友', '2019-05-15 00:13:00', '1', '0', '0');
INSERT INTO `t_friend` VALUES ('133', '123456@qq.com', '111@qq.com', '好友1', '2019-05-15 14:11:17', '0', '0', '0');
INSERT INTO `t_friend` VALUES ('135', '111@qq.com', '123456@qq.com', '同学', '2019-05-15 14:11:17', '0', '0', '0');

-- ----------------------------
-- Table structure for t_reply
-- ----------------------------
DROP TABLE IF EXISTS `t_reply`;
CREATE TABLE `t_reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `reply_email` varchar(30) NOT NULL,
  `replyer_email` varchar(30) NOT NULL,
  `content` varchar(50) NOT NULL,
  `good_count` int(10) DEFAULT '0',
  `time` datetime DEFAULT NULL,
  `reply_name` varchar(10) DEFAULT NULL,
  `replyer_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `f_r_r_u1` (`reply_email`),
  KEY `f_r_r_u2` (`replyer_email`),
  KEY `f_r_r_c` (`comment_id`),
  CONSTRAINT `f_r_r_c` FOREIGN KEY (`comment_id`) REFERENCES `t_comment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_r_r_u1` FOREIGN KEY (`reply_email`) REFERENCES `t_account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_r_r_u2` FOREIGN KEY (`replyer_email`) REFERENCES `t_account` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_reply
-- ----------------------------
INSERT INTO `t_reply` VALUES ('9', '1', '1959291663@qq.com', 'bananice1959@qq.com', 'nmsllll', '0', '2019-05-11 13:25:00', 'mea', 'xxm');
INSERT INTO `t_reply` VALUES ('10', '1', '1959291663@qq.com', 'bananice1959@qq.com', '测试回复1', '1', '2019-05-11 13:40:00', 'mea', 'xxm');
INSERT INTO `t_reply` VALUES ('11', '1', '1959291663@qq.com', 'bananice1959@qq.com', '测试回复2', '1', '2019-05-11 13:40:00', 'mea', 'xxm');
INSERT INTO `t_reply` VALUES ('12', '1', '1959291663@qq.com', 'bananice@mail2.gdut.edu.cn', 'cs', '15', '2019-05-11 13:56:00', 'mea', 'bana');
INSERT INTO `t_reply` VALUES ('13', '1', 'bananice1959@qq.com', 'bananice@mail2.gdut.edu.cn', '测试4', '0', '2019-05-11 13:56:00', 'xxm', 'bana');
INSERT INTO `t_reply` VALUES ('14', '1', 'bananice1959@qq.com', 'bananice@mail2.gdut.edu.cn', '测试5', '0', '2019-05-11 13:58:00', 'xxm', 'bana');
INSERT INTO `t_reply` VALUES ('15', '1', '1959291663@qq.com', 'bananice@mail2.gdut.edu.cn', '测试6', '23', '2019-05-11 14:05:00', 'mea', 'bana');
INSERT INTO `t_reply` VALUES ('17', '2', '1959291663@qq.com', 'bananice1959@qq.com', 'bugcccc', '0', '2019-05-11 14:56:00', 'mea', 'xxm');
INSERT INTO `t_reply` VALUES ('18', '11', 'bananice@mail2.gdut.edu.cn', 'bananice1959@qq.com', '怎么解决', '0', '2019-05-11 14:57:00', 'bana', 'xxm');
INSERT INTO `t_reply` VALUES ('19', '2', 'bananice1959@qq.com', '123456@qq.com', '为什么为什么', '0', '2019-05-11 14:59:00', 'xxm', 'naya');
INSERT INTO `t_reply` VALUES ('20', '2', 'bananice1959@qq.com', '123@qq.com', '插不进去', '0', '2019-05-11 15:01:00', 'xxm', '用户1');
INSERT INTO `t_reply` VALUES ('22', '2', '123456@qq.com', '111@qq.com', '我也很绝望啊啊啊啊', '0', '2019-05-11 15:20:00', 'naya', '暴躁老哥');
INSERT INTO `t_reply` VALUES ('23', '2', 'bananice1959@qq.com', '111@qq.com', '啊啊啊', '0', '2019-05-11 15:21:00', 'xxm', '暴躁老哥');
INSERT INTO `t_reply` VALUES ('24', '2', '1959291663@qq.com', '111@qq.com', '啊啊啊啊', '0', '2019-05-11 15:21:00', 'mea', '暴躁老哥');
INSERT INTO `t_reply` VALUES ('25', '7', '1959291663@qq.com', '1959291663@qq.com', '&#33258;&#24049;&#35780;&#35770;&#33258;&#24049;', '0', '2019-05-13 00:11:00', 'mea', 'mea');
INSERT INTO `t_reply` VALUES ('26', '7', '1959291663@qq.com', '1959291663@qq.com', '&#33258;&#24049;&#22238;&#22797;&#33258;&#24049;', '0', '2019-05-13 00:12:00', 'mea', 'mea');
INSERT INTO `t_reply` VALUES ('27', '1', 'bananice@mail2.gdut.edu.cn', '1959291663@qq.com', '&#36882;&#24402;&#27979;&#35797;', '11', '2019-05-13 00:21:00', 'bana', 'mea');
INSERT INTO `t_reply` VALUES ('28', '1', 'bananice@mail2.gdut.edu.cn', '1959291663@qq.com', '&#36882;&#24402;&#27979;&#35797;2', '0', '2019-05-13 00:22:00', 'bana', 'mea');
INSERT INTO `t_reply` VALUES ('29', '6', '1959291663@qq.com', '1959291663@qq.com', '&#21487;&#20197;&#32842;', '0', '2019-05-13 13:14:00', 'mea', 'mea');
INSERT INTO `t_reply` VALUES ('30', '6', '1959291663@qq.com', '1959291663@qq.com', '&#36825;&#26679;&#23601;&#34892;&#20102;', '0', '2019-05-13 13:15:00', 'mea', 'mea');
INSERT INTO `t_reply` VALUES ('31', '15', '123@qq.com', '1959291663@qq.com', '妈的', '0', '2019-05-13 13:46:00', '用户1', 'mea');
INSERT INTO `t_reply` VALUES ('32', '14', '123@qq.com', '1959291663@qq.com', '行不行啊', '0', '2019-05-13 13:47:00', '用户1', 'mea');
INSERT INTO `t_reply` VALUES ('33', '17', '111@qq.com', '123456@qq.com', '啊啊啊', '15', '2019-05-13 15:04:00', '暴躁老哥', 'naya');
INSERT INTO `t_reply` VALUES ('34', '17', '111@qq.com', '123456@qq.com', '啊啊啊', '0', '2019-05-13 15:04:00', '暴躁老哥', 'naya');
INSERT INTO `t_reply` VALUES ('36', '14', '123@qq.com', '1959291663@qq.com', '可以了吗', '0', '2019-05-13 19:19:00', '用户1', 'mea');
INSERT INTO `t_reply` VALUES ('37', '14', '123@qq.com', '1959291663@qq.com', '&lt;img src=&quot;xss攻击&quot;&gt;', '0', '2019-05-13 19:20:00', '用户1', 'mea');
INSERT INTO `t_reply` VALUES ('38', '5', '1959291663@qq.com', '110@qq.com', '回复', '0', '2019-05-14 00:08:00', 'mea', '用户2');
INSERT INTO `t_reply` VALUES ('39', '5', '1959291663@qq.com', '110@qq.com', '&lt;img src=&quot;xss&quot;&gt;', '0', '2019-05-14 00:09:00', 'mea', '用户2');
INSERT INTO `t_reply` VALUES ('40', '20', '1959291663@qq.com', '1959291663@qq.com', '行了', '0', '2019-05-14 17:24:00', 'mea', 'mea');

-- ----------------------------
-- Table structure for t_report
-- ----------------------------
DROP TABLE IF EXISTS `t_report`;
CREATE TABLE `t_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_email` varchar(30) NOT NULL,
  `report_name` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  `statue` int(11) DEFAULT '0' COMMENT '0表示没被封号，1代表被封号',
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_report` (`report_email`),
  KEY `f_report2` (`report_name`),
  CONSTRAINT `f_report1` FOREIGN KEY (`report_email`) REFERENCES `t_account` (`email`),
  CONSTRAINT `f_report2` FOREIGN KEY (`report_name`) REFERENCES `t_account` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_report
-- ----------------------------
INSERT INTO `t_report` VALUES ('2', '888@qq.com', '用户3', '2019-05-11 21:58:00', '1', '2019-05-10 12:12:00');
INSERT INTO `t_report` VALUES ('3', '666@qq.com', '用户4', '2019-05-15 13:49:00', '1', '2019-05-15 13:54:00');
INSERT INTO `t_report` VALUES ('9', 'bananice1959@qq.com', 'xxm', '2019-05-15 13:49:00', '1', '2019-05-16 13:13:00');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_r_a` (`email`),
  CONSTRAINT `f_r_a` FOREIGN KEY (`email`) REFERENCES `t_account` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '1959291663@qq.com', 'admin');
INSERT INTO `t_role` VALUES ('2', 'bananice1959@qq.com', 'admin');
