package com;

import com.coder.ProtoBufDecoder;
import com.coder.ProtoBufEncoder;
import com.config.ServerConfig;
import com.server.BufferHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class GateServer
{
	public static final int id = 0x00001;
	private static GateServer s_instance = null;

	public static GateServer getInstance()
	{

		if (s_instance == null)
		{
			s_instance = new GateServer();
		}

		return s_instance;
	}

	public static void main(String args[])
	{

		GateServer server = GateServer.getInstance();
		server.startUp();
		server.bind(ServerConfig.IP(), ServerConfig.PORT());

	}

	/**
	 * 网络层启动方法
	 */
	public void bind(String ip, int port)
	{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
					.option(ChannelOption.TCP_NODELAY, true)
					.childHandler(new ChannelInitializer<SocketChannel>()
					{

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception
						{
							ch.pipeline().addLast(new ProtoBufDecoder());
							ch.pipeline().addLast(new ProtoBufEncoder());
							ch.pipeline().addLast(new BufferHandler());
						}
					});
			ChannelFuture f = b.bind(ip, port).sync();
			System.out.println(this.getClass().getName() + "  start!");
			f.channel().closeFuture();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
	
	private void startUp()
	{
		
	}
}
