package com.logic.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.common.connect.GameServer;

public class SvManager{
	
	private static SvManager s_instance     = null;
	private Map<Integer,GameServer> m_serverMap = new ConcurrentHashMap<Integer,GameServer>();
	private Map<Integer,GameServer> m_svModuleMap = new ConcurrentHashMap<Integer,GameServer>();
	
	public static SvManager getInstance()
	{
		if (s_instance == null)
		{
			s_instance = new SvManager();
		}
		
		return s_instance;
	}
	
	public void regServer(GameServer server)
	{
		this.m_serverMap.put(server.getId(), server);
		this.m_svModuleMap.put(server.getModule(), server);
	}
	
	public GameServer getServerById(int id)
	{
		return this.m_serverMap.get(id);
	}
	
	public void unRegServer(GameServer server)
	{
		this.m_serverMap.remove(server.getId());
		this.m_svModuleMap.remove(server.getModule());
	}
	
	public GameServer getServerByModule(int module)
	{
		return this.m_serverMap.get(module);
	}
}
