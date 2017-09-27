# GateServer-master
网关服务器，用于管理客户端连接，转发客户端的消息到各个服务器，同时检查消息的合法性和发送心跳包等。
此网关服务器会陆续更新，第一版写的会很糙
PS:
登陆服务器和逻辑服务器等启动时connect后，发送注册消息到网关上，这里的serverid不能重复。
message RegSVMsg{
   required int32 svid = 1;
   required int32 module = 2;     //模块id，负责的哪个业务模块。服务器在区分服务器与转发客户端消息时有用
}
