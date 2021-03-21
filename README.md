# 线上考试后端系统

#### 介绍
本系统通过教师用户创建班级编写试卷信息然后发布到班级。学生用户进入班级，在线作答，考试结果数据通过网络回收，系统自动进行判分，生成考试成绩和统计数据。教师用户也可以再次批阅试卷，确定最后得分。试卷支持单选、多选、判断、填空、简答五大题型，和是否允许题目复制、题目乱序功能

#### 软件架构
- 这是一款基于前后端分离的线上考试系统
- 前端采用vue框架，搭配vue-cli、axios、vuex、element-ui组件库。
- 后端采用springBoot框架、jwt、swagger、持久层mybatis，mysql数据库。

#### 源码地址
| 前端网址  |  后端网址 |
|---|---|
| https://gitee.com/tatata1124/online-examination-system-FE.git  | https://gitee.com/tatata1124/online-examination-system-server.git  |


#### 截图显示
登录页面
![登录页面](https://images.gitee.com/uploads/images/2021/0321/173124_7908d324_7451960.png "snipaste20210321_172951.png")

首页
![首页](https://images.gitee.com/uploads/images/2021/0321/173220_9cd27950_7451960.png "snipaste20210321_173208.png")

教师角色-我的班级
![教师角色-我的班级](https://images.gitee.com/uploads/images/2021/0321/173345_6108fa9a_7451960.png "snipaste20210321_173337.png")

学生角色-我的班级
![学生角色-我的班级](https://images.gitee.com/uploads/images/2021/0321/173532_e3e22c7f_7451960.png "snipaste20210321_173521.png")

教师角色-班级空间
![教师角色-班级空间](https://images.gitee.com/uploads/images/2021/0321/180543_de5e1faf_7451960.png "snipaste20210321_180524.png")

学生角色-班级空间
![学生角色-班级空间](https://images.gitee.com/uploads/images/2021/0321/180903_cb21ced6_7451960.png "snipaste20210321_180852.png")

教师角色-我的试卷
![教师角色-我的试卷](https://images.gitee.com/uploads/images/2021/0321/181730_e3863787_7451960.png "snipaste20210321_181720.png")

教师角色-新增试卷
![教师角色-新增试卷](https://images.gitee.com/uploads/images/2021/0321/183312_3fdbf5bc_7451960.png "QQ截图20210321183224.png")

学生角色-进行考试
![学生角色-进行考试](https://images.gitee.com/uploads/images/2021/0321/182159_58e99e65_7451960.png "M$IJLK`KQCVU0X6AF0%GJK5.png")

教师角色-批改试卷
![教师角色-批改试卷](https://images.gitee.com/uploads/images/2021/0321/182446_862907e5_7451960.png "QQ截图20210321182421.png")

学生角色-查看试卷
![学生角色-查看试卷](https://images.gitee.com/uploads/images/2021/0321/182601_9d5c4d0e_7451960.png "QQ截图20210321182542.png")


#### 使用说明

1.  xxxx
2.  xxxx
3.  xxxx

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


### 请求状态码与msg

+ 100: 请求失败
+ 200: 请求成功
+ 300: token验证失败,请先登录
+ 400: 权利不足,用户身份不正确 

+ 1001:该邮箱地址已被使用
+ 1002:该手机号码已被使用

+ 2001: 该班级不存在
+ 2002: 已加入该班级
+ 2003: 您不是该班级管理员
+ 2004: 您不是该班级的学生
+ 2005: 该班级不允许进入,请与班级的管理员联系

+ 3001: 您没有权利查看该试卷(身份不正确)
+ 3002: 您没有权利更改该试卷(身份不正确)
+ 3004: 您没有权利批改该试卷(身份不正确)
+ 3003: 您不是该班级的学生

