package com.common.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
	private static SessionManager s_instance = null;
	
	public Map<String,IOSession> m_sessionMap = new ConcurrentHashMap<String,IOSession>();
	private SessionManager()
	{
		
	}
	
	public static SessionManager getInstance()
	{
		if (s_instance == null)
		{
			s_instance = new SessionManager();
		}
		
		return s_instance;
	}
	
	public void addIOSession(IOSession session)
	{
		this.m_sessionMap.put(session.getSessionId(), session);
	}
	
	public void removeSession(IOSession session)
	{
		this.m_sessionMap.remove(session.getSessionId());
	}
	
	public int getCurSessionCount()
	{
		return this.m_sessionMap.size();
	}
	
	public IOSession getSession(String id)
	{
		return this.m_sessionMap.get(id);
	}
}
