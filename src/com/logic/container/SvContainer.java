package com.logic.container;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.common.connect.GameServer;

public class SvContainer {
	private int m_module = 0;
	private Map<Integer,GameServer> m_map = new ConcurrentHashMap<Integer,GameServer>(); 
	
	public SvContainer(int module)
	{
		m_module = module;
	}
	
	public void addSv(GameServer server)
	{
		this.m_map.put(server.getId(), server);
	}
	
	public void rmSv(GameServer server)
	{
		this.m_map.remove(server.getId());
	}
	
	public GameServer getAvailableServer()
	{
		GameServer server = null;
		Iterator<Entry<Integer, GameServer>> iter = m_map.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<Integer, GameServer> entry = iter.next();
			GameServer sv = entry.getValue();
			if (sv.isConnect())
			{
				server = sv;
				break;
			}
		}
		
		return server;
	}
	
	public GameServer getServer(int serverid)
	{
		return this.m_map.get(serverid);
	}
	
//	public String toReport()
//	{
//		JSONObject
//	}
}
