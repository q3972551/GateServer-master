package com.common.message;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessage;
import com.message.MsgBuffer.Msg;
import com.message.MsgBuffer.MsgHead;

public abstract class IMessage {
	
	public MsgHead    m_head = null;
	public ByteString m_body   = null;
	
	public abstract int getMsgid();
	
	public MsgHead getHead(){
		return this.m_head;
	}
	public abstract <T extends  GeneratedMessage> T getbody();
	
	protected void setBody(ByteString body)
	{
		this.m_head = HeadFactory.createMsgHead(this.getMsgid(), System.currentTimeMillis());
		this.m_body = body;		
	}
	
	public Msg toMsg()
	{
		Msg msg = Msg.newBuilder()
				.setMsghead(m_head)
				.setMsgbody(m_body).build();
		return msg;
	}
	
}
