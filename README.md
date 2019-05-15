# douban
模仿网页版豆瓣，实现部分功能

### 开发环境
- eclipse ee
- tomcat7.0
- mysql5.7
- jdk1.8

### 使用说明
- 由于图片上传之后是保存在项目文件下的images文件夹中，使用要找到eclipse的Servers项目的server.xml，在<host>后加上<Context path="/file"  docBase="d:/javacode/Douban/WebContent/images" debug="0" reloadable="true"/>
- 图片用io输出时用的是绝对路径，最好在d盘新建javacode文件夹，把项目放进去
- 用到的包放在WebContent的WEB-INF下的lib中，所有包都要导入
- 数据库连接通过DataSource取得连接，可以在META-INF的context.xml更改相应配置
- 登录页是index.jsp


### 功能构思
- 页面用后端渲染
- 忘记密码通过发送链接到用户邮箱，用户点击链接重置密码
- 管理员通过在数据库存放的角色信息来实现，每次用户登录后都会获取角色，不过角色只有admin这一种，角色要去数据库输入
- 自动登录，管理员权限和检查用户是否登录通过过滤器实现
- 对敏感输入在过滤器中用工具类进行转义
- 多级评论通过评论和回复分开两张表来实现，一张表存评论一张表存回复，通过评论id取得下面所有的回复
- 实时聊天用ajax轮询实现，取出聊天信息就删除，不保存聊天记录，只能一对一聊天

### 数据库设计
- 数据库有11张表，t_account放用户的个人信息，t_article放文章基本信息，t_article_info放文章点赞收藏转发信息，t_chat展示存放聊天信息，t_comment放评论信息，t_reply放回复信息，t_douyou放豆邮，t_follow放关注信息，t_friend放好友信息，t_report放举报信息，t_role放角色信息
- 需要用到外键的地方基本都用上了，删除时基本都设成了主表删除，从表也删除，有些表用到了联合唯一索引保证两列的组合不重复