package com.processor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.common.config.GameConfig;
import com.message.MsgBuffer.MsgHead;
import com.processor.heartbeat.HeartBeatProcessor;
import com.processor.sv.SVProcessor;
import com.processor.sv2sv.Sv2SvProcessor;
import com.processor.trans.TransProcessor;
import com.tools.MD5Util;

public class MsgDistribute {
	
	public  static MsgDistribute instance = null;
	private Map<Integer,MsgProcessor> m_processor = new ConcurrentHashMap<Integer,MsgProcessor>();
	
	private MsgDistribute()
	{
		this.init();
	}
	
	public static MsgDistribute getInstance(){
		if (instance == null)
		{
			instance = new MsgDistribute();
		}
		return instance;
	}
	
	private void init()
	{
		m_processor.put(99999, new HeartBeatProcessor());
		m_processor.put(0, new TransProcessor());
		m_processor.put(5, new SVProcessor());
		m_processor.put(3, new Sv2SvProcessor());
	}
	
    public boolean isheadMD5Checkout(MsgHead head)
    {
    	int msgid =	head.getMsgid();
		long currentTime = head.getMsgdate();
		StringBuffer buffer = new StringBuffer();
		buffer.append(msgid).append(currentTime).append(GameConfig.BID);
		String md5 = MD5Util.Md5(buffer.toString());
		if (head.getMsgmd5().equals(md5))
		{
			return true;
		}
    	return false;
    }
    
	public MsgProcessor getProcessor(int msgid){
		MsgProcessor processor = null;


		if ((msgid / (int)Math.pow(16, 4)) == 0)
		{
			/*
			 * 来自client
			 */
			if (msgid == 0x00000)
			{
				processor = this.m_processor.get(99999);
			}
			else
			{
				processor = this.m_processor.get(0);
			}
		}
		else if ((msgid / (int)Math.pow(16, 4)) == 3)
		{
			processor = this.m_processor.get(3);
		}
		else if ((msgid / (int)Math.pow(16, 4)) == 5)
		{		
			/*
			 * 来自server
			 */
			processor = this.m_processor.get(5);
		}
	
		return processor;
	}
}
