package com.example.config.orderer;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import com.example.config.base.DataSourceConfig;
import com.example.config.base.DbProperties;
 
@Configuration
@MapperScan(basePackages = "com.example.service.repository.orderer", sqlSessionTemplateRef = "ordererSqlSessionTemplate")
public class OrdererDataSourceConfig extends DataSourceConfig {
	
	@Bean(name = "ordererDbProperties")
	@ConfigurationProperties(prefix = "mybatis.orderer.datasource")
	public DbProperties dbProperties() {
		return new DbProperties();
	}
 
	@Bean(name = {"dataSource", "ordererDataSource"})
	@Primary
	public DataSource dataSource(@Qualifier("ordererDbProperties") DbProperties dbProperties) throws Exception {
		return createDataSource(dbProperties);
	}
 
	@Bean(name = "ordererSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ordererDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionTemplate(dataSource);
	}
 
	@Bean(name = "ordererDataSourceInitializer")
	public DataSourceInitializer dataSourceInitializer(@Qualifier("ordererDataSource") DataSource dataSource, @Value("classpath:orderer.sql") Resource resource) { 
		return createDataSourceInitializer(dataSource, resource);
	}
}