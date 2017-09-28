package com.processor.heartbeat;

import com.common.session.IOSession;
import com.message.MsgBuffer.Msg;
import com.message.heartbeat.SHeartBeat;
import com.processor.MsgProcessor;

public class HeartBeatProcessor extends MsgProcessor{
	
	@Override
	public void process(IOSession player,Msg msg) {
		// TODO Auto-generated method stub
		SHeartBeat sMsg = new SHeartBeat();
		sMsg.setBody(System.currentTimeMillis());
		player.sendMsg(sMsg);
	}

}
