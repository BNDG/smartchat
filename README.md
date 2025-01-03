### 一个简单的 XMPP Android客户端

> 本项目是为了学习 XMPP 协议而写的一个简单的app，实现了一些基本的聊天功能，为了使用习惯，UI上参照了某著名聊天。

#### 推荐客户端

- [Conversations](https://codeberg.org/iNPUTmice/Conversations) 稳定性好，功能完善，更新及时。只是UI操作和国内的聊天软件不同，需要习惯。
- [Xabber](https://github.com/redsolution/xabber-android) 老牌客户端，支持web ios android，虽然好久没更新但最近说有新版本。

#### 简介

- **XMPP（Extensible Messaging and Presence Protocol，扩展消息与在线状态协议）** 是一种开源、基于XML的即时通讯协议，用于实现即时消息、在线状态以及多种通信功能。XMPP 最初由 **Jabber** 作为开源即时通讯协议开发，后来被广泛应用于各种即时通讯和社交媒体平台。
- 更多信息参见[《XMPP 新⼿使⽤⼿册》](https://xmppjx.codeberg.page/xssysc.html)，介绍的十分详细。
- 利用Smack库实现了XMPP协议的聊天功能[Smack 库地址](https://github.com/igniterealtime/Smack)

#### 实现的功能
> 登陆时如果服务器是国外的可能会用时一二十秒。会不稳定。
- 登陆、注册（注册得看服务器是否开放了注册，建议去对应的平台上注册）
- 单聊、群聊、群成员的管理等
- 添加好友、好友管理等
- 发送文字、图片、文件消息
- 图片编辑、录制视频、录制语音
- 搜索频道
- 个人名片修改昵称、头像
- 会话管理
- omemo(未完善)

#### 一些公共的 XMPP 服务器

- [conversations.im](http://conversations.im/)
- [yax.im](https://yaxim.org/yax.im/) ([Policy](https://yaxim.org/yax.im/tos/))
- [SUChat.org – Servidor XMPP](https://www.suchat.org/)
- 也可以自己搭建服务端，有很多开源服务端比如`tigase` `jabber`等

#### 一些截图

<img src="https://github.com/user-attachments/assets/9fd85208-b6cf-4ba8-aa22-3f40582d145f" alt="登录" title="登录" style="width: 20%; display: block; margin: auto;" />
<img src="https://github.com/user-attachments/assets/d57562a0-8bf3-4454-93a1-d5b9cc3dd8c1" alt="联系人" title="联系人" style="width: 20%; display: block; margin: auto;" />
<img src="https://github.com/user-attachments/assets/446ba690-8c28-42f3-8fd6-41e9535aaa20" alt="聊天界面" title="聊天界面" style="width: 20%; display: block; margin: auto;" />
<img src="https://github.com/user-attachments/assets/3deed7dc-56d9-4d3f-a6c5-73da21b6178d" alt="设置" title="设置" style="width: 20%; display: block; margin: auto;" />
<img src="https://github.com/user-attachments/assets/60770ca3-027d-499e-ab7b-f4ce4ee8a564" alt="会话" title="会话" style="width: 20%; display: block; margin: auto;" />
<img src="https://github.com/user-attachments/assets/e8f164a8-3e58-49c7-9db2-b8dff0997909" alt="发现频道" title="发现频道" style="width: 20%; display: block; margin: auto;" />





