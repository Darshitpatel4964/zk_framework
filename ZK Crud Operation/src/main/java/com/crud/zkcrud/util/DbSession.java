package com.crud.zkcrud.util;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbSession {
	
	private DbSession() {    
	}

	public static SqlSession getSqlSessionFactory() {
		SqlSession session = null;
		try {
			Reader reader = Resources.getResourceAsReader("web/mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			session = sqlSessionFactory.openSession();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return session;
	}

}