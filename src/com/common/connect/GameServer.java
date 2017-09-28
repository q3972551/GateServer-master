package com.common.connect;


import net.sf.json.JSONObject;

import com.common.session.IOSession;
import com.logic.server.SvManager;
import com.message.MsgBuffer.Msg;
import com.message.MsgBuffer.MsgHead;

import io.netty.channel.ChannelFuture;

public class GameServer{
	
	private IOSession m_session = null;
	public GameServer(IOSession session) {
		m_session = session;
	}
	
	private int  m_module = 0;
	private int  m_id     = 0;
	
	public void clean()
	{
		m_session = null;
		m_module  = 0;
		m_id      = 0;
		SvManager.getInstance().unRegServer(this);
	}
	
	public void setGameServer(int id,int module)
	{
		m_module = module;
		m_id   = id;
	}
	
	public int getId()
	{
		return this.m_id;
	}
	
	public int getModule()
	{
		return this.m_module;
	}
	
	public ChannelFuture sendMsg(String session,Msg msg)
	{
		if (m_session == null)
		{
			return null;
		}
		MsgHead head = msg.getMsghead();
		head.toBuilder().setSessionid(session);
		return m_session.sendMsg(msg);
	}
	
	public ChannelFuture sendMsg(Msg msg)
	{
		if (m_session == null)
		{
			return null;
		}
		
		return m_session.sendMsg(msg);
	}
	
	public boolean isConnect()
	{
		return this.m_session.isConnect();
	}
	
	public String toReport()
	{
		JSONObject ob = new JSONObject();
		ob.put("ip", this.m_session.getAddress());
		ob.put("status", this.m_session.isConnect());
		ob.put("id", this.m_id);
		return ob.toString();
	}
}
