package com.server;

import com.common.session.IOSession;
import com.message.MsgBuffer.Msg;
import com.message.MsgBuffer.MsgHead;
import com.message.error.SError;
import com.processor.MsgDistribute;
import com.processor.MsgProcessor;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class BufferHandler extends SimpleChannelInboundHandler<Msg>{

	@Override
	public void channelActive(ChannelHandlerContext ctx)
	{
		new IOSession(ctx);
		System.out.println("Create Session!");
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Msg msg)
			throws Exception {
		// TODO Auto-generated method stub
		
		IOSession player = IOSession.getIOSession(ctx);
		MsgHead head = msg.getMsghead();
		
		MsgDistribute distribute = MsgDistribute.getInstance();
		if (distribute.isheadMD5Checkout(head))
		{
			SError sMsg = new SError();
			sMsg.setError(20001);
			player.sendMsg(sMsg);
		}
		else
		{
			int msgid = head.getMsgid();
			MsgProcessor process = distribute.getProcessor(msgid);
			if (process == null)
			{
				return ;
			}
			process.process(player,msg);
		}
	}

}
