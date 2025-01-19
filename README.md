### 1、简介：Minecraft自研战斗消息反馈插件
---

### 2、命令

无

---

### 3、占位符
```
%battlenotice_message_combat% - 近战聊天框提示开启状态（开启/关闭）
%battlenotice_message_combat% - 近战标题提示开启状态（开启/关闭）
%battlenotice_message_combat% - 聊天框提示开启状态（开启/关闭）
%battlenotice_message_combat% - 近战聊天框提示开启状态（开启/关闭）

```
---

### 4、特性介绍
- 支持PlaceholderAPI占位符
- 聊天事件监听优先级可自定义，默认为NORMAL
- 使用Mysql数据库

---

### 5、config.yml
```yml
#GuildPlugin by X_32xm
#for only Dxzzz.net
#QQ:2644489337

ChatSettings:
  #聊天事件触发优先级
  #可选参数: MONITOR/HIGHEST/HIGH/NORMAL/LOW/LOWEST 从LOW到MONITOR，监听到的消息越来越晚！
  Priority: NORMAL

  #是否判断event.isCancelled()
  DetectCancelOrNot: true

  #聊天前缀(支持PlaceholderAPI)
  TalkPrefix: "%rank_rank%§7%tag_all% §8> §f "

  #只有本项为true，过期权限检查才会生效。注意：后端服务器请保证有且仅有一个服务器将此项设置为true
  AsLobby: false

#数据库设置(仅支持MySQL)
DataBase:
  MySQL:
    Host: localhost
    Username: root
    Password:
    Port: 3306


```
