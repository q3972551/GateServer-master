package com.common.message;

import com.common.config.GameConfig;
import com.message.MsgBuffer.MsgHead;
import com.tools.MD5Util;


public class HeadFactory {
	
	public static MsgHead createMsgHead(int msgid,long curTime){
		StringBuffer buffer = new StringBuffer();
		buffer.append(msgid).append(curTime).append(GameConfig.BID);
		String md5 = MD5Util.Md5(buffer.toString());
    	MsgHead head = MsgHead.newBuilder()
    			.setMsgdate(curTime)
    			.setMsgid(msgid)
    			.setMsgmd5(md5).build();
    	return head;
	}
}
