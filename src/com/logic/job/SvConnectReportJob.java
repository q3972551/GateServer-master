/**
 * 将服务器连接状态保存在某地
 */
package com.logic.job;

import com.logic.server.SvManager;

public class SvConnectReportJob implements Runnable{

	private SvManager m_manager = null;
	
	public SvConnectReportJob(SvManager manager)
	{
		m_manager = manager;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	

}
