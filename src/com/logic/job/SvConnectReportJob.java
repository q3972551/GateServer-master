/**
 * 将服务器连接状态保存在某地
 */
package com.logic.job;

import com.logic.server.SvManager;

public class SvConnectReportJob implements Runnable{

	private SvManager m_sv = null;
	
	public SvConnectReportJob(SvManager server)
	{
		m_sv = server;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
