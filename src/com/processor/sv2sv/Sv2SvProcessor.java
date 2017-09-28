package com.processor.sv2sv;

import org.apache.log4j.Logger;

import com.common.connect.GameServer;
import com.common.session.IOSession;
import com.common.message.HeadFactory;
import com.logic.server.SvManager;
import com.message.MsgBuffer.Msg;
import com.message.MsgBuffer.MsgHead;
import com.message.SVMsgBuffer.CMsgRegServer;
import com.message.SVMsgBuffer.SMsgRegServer;
import com.processor.MsgProcessor;

public class Sv2SvProcessor extends MsgProcessor{

	private Logger m_logger = Logger.getLogger(Sv2SvProcessor.class);
	@Override
	public void process(IOSession player, Msg msg) {
		// TODO Auto-generated method stub
		try{
			if (msg.getMsghead().getMsgid() == 0x30001)
			{
				CMsgRegServer cmsg = CMsgRegServer.parseFrom(msg.getMsgbody());
				this.registerSV(player,cmsg);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			m_logger.error("Sv2SvProcessor.class", e);
		}
	}
	private void registerSV(IOSession session,CMsgRegServer cMsg)
	{
		int svid = cMsg.getId();
		int module   = cMsg.getModule();
		GameServer server = new GameServer(session);
		server.setGameServer(svid, module);
		SvManager.getInstance().regServer(server);
		MsgHead head = HeadFactory.createMsgHead(0x60001, System.currentTimeMillis());
		SMsgRegServer sMsg = SMsgRegServer.newBuilder()
				.setError(0).build();
		Msg msg = Msg.newBuilder()
				.setMsghead(head)
				.setMsgbody(sMsg.toByteString()).build();
		server.sendMsg(msg);
	}
}
