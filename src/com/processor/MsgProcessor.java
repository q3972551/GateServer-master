package com.processor;

import com.common.session.IOSession;
import com.message.MsgBuffer.Msg;

public abstract class MsgProcessor {
	public abstract  void process(IOSession session,Msg msg);
}
