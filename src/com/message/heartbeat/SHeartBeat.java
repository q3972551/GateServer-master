package com.message.heartbeat;

import com.common.message.IMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.message.MsgBuffer.SMsgHeartBeat;

public class SHeartBeat extends IMessage{

	@Override
	public int getMsgid() {
		// TODO Auto-generated method stub
		return 0x50000;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SMsgHeartBeat getbody() {
		// TODO Auto-generated method stub
		try {
			return SMsgHeartBeat.parseFrom(this.m_body);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public void setBody(long time)
	{
		SMsgHeartBeat body1 = SMsgHeartBeat.newBuilder()
				.setTime(time).build();
		this.setBody(body1.toByteString());
	}
}
