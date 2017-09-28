package com.message.error;

import com.common.message.IMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import com.message.MsgBuffer.SMsgError;

public class SError extends IMessage{

	@Override
	public int getMsgid() {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public SMsgError getbody() {
		// TODO Auto-generated method stub
		try {
			return SMsgError.parseFrom(this.m_body);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setError(int error)
	{
		SMsgError body = SMsgError.newBuilder()
				.setCode(error).build();
		this.setBody(body.toByteString());
	}

}
