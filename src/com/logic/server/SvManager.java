package com.logic.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.common.connect.GameServer;
import com.logic.container.SvContainer;
import com.sv.ServerModule;

public class SvManager{
	
	private static SvManager s_instance     = null;
	private Map<Integer,GameServer> m_serverMap = new ConcurrentHashMap<Integer,GameServer>();
	private Map<Integer,SvContainer> m_svModuleMap = new HashMap<Integer,SvContainer>();
	
	private SvManager()
	{
		this.init();
	}
	
	public static SvManager getInstance()
	{
		if (s_instance == null)
		{
			s_instance = new SvManager();
		}
		
		return s_instance;
	}
	
	
	private void init()
	{
		m_svModuleMap.put(ServerModule.Login, new SvContainer(ServerModule.Login));
		m_svModuleMap.put(ServerModule.Logic, new SvContainer(ServerModule.Logic));
	}
	
	public void regServer(GameServer server)
	{
		this.m_serverMap.put(server.getId(), server);
		SvContainer container = this.m_svModuleMap.get(server.getModule());
		container.addSv(server);
	}
	
	public GameServer getServerById(int id)
	{
		return this.m_serverMap.get(id);
	}
	
	public void unRegServer(GameServer server)
	{
		this.m_serverMap.remove(server.getId());
		SvContainer container = this.m_svModuleMap.get(server.getModule());
		container.rmSv(server);
	}
	
	public GameServer getServerByModule(int module)
	{
		SvContainer container = this.m_svModuleMap.get(module);
		return container.getAvailableServer();
	}
}
