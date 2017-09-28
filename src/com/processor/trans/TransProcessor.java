package com.processor.trans;

import com.common.connect.GameServer;
import com.common.session.IOSession;
import com.logic.server.SvManager;
import com.message.MsgBuffer.Msg;
import com.processor.MsgProcessor;

public class TransProcessor extends MsgProcessor{

	@Override
	public void process(IOSession session, Msg msg) {
		// TODO Auto-generated method stub
		int msgid = msg.getMsghead().getMsgid();
		
		int module = (msgid / (int)Math.pow(16, 2) % 16);
		GameServer server = SvManager.getInstance().getServerByModule(module);
		server.sendMsg(msg);
	}

}
