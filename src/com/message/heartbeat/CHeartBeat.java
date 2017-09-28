package com.message.heartbeat;

import com.common.message.IMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.message.MsgBuffer.CMsgHeartBeat;

public class CHeartBeat extends IMessage{

	@Override
	public int getMsgid() {
		// TODO Auto-generated method stub
		return 0x00000;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CMsgHeartBeat getbody() {
		// TODO Auto-generated method stub
		try {
			return CMsgHeartBeat.parseFrom(this.m_body);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
