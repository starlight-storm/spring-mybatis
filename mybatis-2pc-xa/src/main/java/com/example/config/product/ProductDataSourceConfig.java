package com.example.config.product;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;

import com.example.config.base.DataSourceConfig;
import com.example.config.base.DbProperties;
 
@Configuration
@MapperScan(basePackages = "com.example.service.repository.product", sqlSessionTemplateRef = "productSqlSessionTemplate")
public class ProductDataSourceConfig extends DataSourceConfig {

	@Bean(name = "productDbProperties")
	@ConfigurationProperties(prefix = "mybatis.product.datasource")
	public DbProperties dbProperties() {
		return new DbProperties();
	}
	
	// @Primaryと@BeanのnameがCustomerのConfigと違うことに注意
	@Bean(name = "productDataSource")
	public DataSource dataSource(@Qualifier("productDbProperties") DbProperties dbProperties) throws Exception {
		return createDataSource(dbProperties);
	}
 
	@Bean(name = "productSqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("productDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionTemplate(dataSource);
	}
 
	@Bean(name = "productDataSourceInitializer")
	public DataSourceInitializer dataSourceInitializer(@Qualifier("productDataSource") DataSource dataSource, @Value("classpath:product.sql") Resource resource) {
		return createDataSourceInitializer(dataSource, resource);
	}
}