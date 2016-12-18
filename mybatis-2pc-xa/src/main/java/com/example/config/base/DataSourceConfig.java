package com.example.config.base;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.XADataSourceWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DataSourceConfig {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired
	private XADataSourceWrapper wrapper;
	
	public DataSource createDataSource(DbProperties dbp) throws Exception {
		JdbcDataSource xaDataSource = new JdbcDataSource();
		xaDataSource.setUrl(dbp.getUrl());
		xaDataSource.setUser(dbp.getUser());
		xaDataSource.setPassword(dbp.getPassword());
		return this.wrapper.wrapDataSource(xaDataSource);
	}

	public SqlSessionTemplate createSqlSessionTemplate(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setConfigLocation(context.getResource(context.getEnvironment().getProperty("mybatis.config-location")));
		return new SqlSessionTemplate(bean.getObject());
	}

	public DataSourceInitializer createDataSourceInitializer(DataSource dataSource, Resource resource) {
			DataSourceInitializer dsi = new DataSourceInitializer();
			dsi.setDatabasePopulator(new ResourceDatabasePopulator(resource));
			dsi.setDataSource(dataSource);
			return dsi;
	}
 
}