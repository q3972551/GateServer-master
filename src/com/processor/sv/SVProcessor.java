package com.processor.sv;

import com.common.session.IOSession;
import com.common.session.SessionManager;
import com.message.MsgBuffer.Msg;
import com.message.MsgBuffer.MsgHead;
import com.processor.MsgProcessor;

public class SVProcessor extends MsgProcessor{
	
	@Override
	public void process(IOSession player, Msg msg) {
		// TODO Auto-generated method stub
		MsgHead head = msg.getMsghead();
		String sessionid = head.getSessionid();
		
		IOSession session = SessionManager.getInstance().getSession(sessionid);
		session.sendMsg(msg);
	}
}
