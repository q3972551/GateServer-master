package com.common.session;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import com.common.message.IMessage;
import com.message.MsgBuffer.Msg;
import com.tools.StringUtil;

public class IOSession {
	private volatile ChannelHandlerContext m_client = null; // 客户端 volatile避免不一致
	
	private static final AttributeKey<IOSession> KEY_PLAYER_SESSION = AttributeKey
			.valueOf("IOSession.class");
	
	protected String  m_address  = null;
	protected String  m_sessionid = null;
	
	public void clean()
	{
		m_client  = null;
		m_address = null;
	}
	
	public static IOSession getIOSession(ChannelHandlerContext session)
	{
		final IOSession playerObj = session.channel().attr(KEY_PLAYER_SESSION)
				.get();
		return playerObj;
	}

	public IOSession(ChannelHandlerContext ctx)
	{
		this.m_client = ctx;
		ctx.channel().attr(KEY_PLAYER_SESSION).set(this);
		this.m_address = ctx.channel().remoteAddress().toString();
		this.m_sessionid = StringUtil.getUUID();
		SessionManager.getInstance().addIOSession(this);
	}

	public String getAddress()
	{
		return this.m_address;
	}

	public ChannelFuture sendMsg(IMessage msg)
	{
		if (msg == null ||m_client.isRemoved())
		{
			return null;
		}
		
		return m_client.channel().writeAndFlush(msg.toMsg());
	}
	
	public ChannelFuture sendMsg(Msg msg)
	{
		if (msg == null ||m_client.isRemoved())
		{
			return null;
		}
		
		return m_client.channel().writeAndFlush(msg);
	}

	public void close()
	{
		try
		{
			this.m_client.channel().closeFuture();
		} catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	public void release()
	{

	}
	
	public String getSessionId()
	{
		return this.m_sessionid;
	}
}
