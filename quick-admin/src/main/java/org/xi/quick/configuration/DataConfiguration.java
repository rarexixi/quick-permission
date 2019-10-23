package org.xi.quick.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"org.xi.quick.sys.mapper", "org.xi.quick.biz.mapper"}, sqlSessionTemplateRef = "sysSqlSessionTemplate")
public class DataConfiguration {

    @Bean(name = "sysData")
    @ConfigurationProperties(prefix = "spring.datasource.sys")
    @Primary
    public DataSource sysData() {
        return new DruidDataSource();
    }

    @Bean(name = "sysSqlSessionFactory")
    @Primary
    public SqlSessionFactory sysSqlSessionFactory(@Qualifier("sysData") DataSource sysData) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(sysData);

        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] sysResources = resourcePatternResolver.getResources("classpath:mapper/*/*.xml");
        sqlSessionFactoryBean.setMapperLocations(sysResources);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "sysTransactionManager")
    @Primary
    public DataSourceTransactionManager sysTransactionManager(@Qualifier("sysData") DataSource sysData) {
        return new DataSourceTransactionManager(sysData);
    }

    @Bean(name = "sysSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sysSqlSessionTemplate(@Qualifier("sysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}