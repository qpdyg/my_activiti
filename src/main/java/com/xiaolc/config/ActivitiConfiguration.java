package com.xiaolc.config;

import org.activiti.engine.*;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;


/**
 * 注入activiti的service
 *
 * @Author: lc
 * @Date: 2019/6/21 15:07
 */
@Configuration
public class ActivitiConfiguration extends AbstractProcessEngineAutoConfiguration {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration() {
        SpringProcessEngineConfiguration spec = new SpringProcessEngineConfiguration();
        spec.setDataSource(dataSource);
        spec.setTransactionManager(platformTransactionManager);
        spec.setDatabaseSchemaUpdate("true");
        Resource[] resources = null;
        // 启动自动部署流程
        try {
            resources = new PathMatchingResourcePatternResolver().getResources("classpath*:diagrans/*.bpmn");
        } catch (IOException e) {
            e.printStackTrace();
        }
        spec.setDeploymentResources(resources);
        return spec;
    }

    @Bean
    public ProcessEngineFactoryBean processEngine() {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration());
        return processEngineFactoryBean;
    }


    @Bean
    public RepositoryService repositoryService() throws Exception {
        return processEngine().getObject().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() throws Exception {
        return processEngine().getObject().getRuntimeService();
    }

    @Bean
    public TaskService taskService() throws Exception {
        return processEngine().getObject().getTaskService();
    }

    @Bean
    public HistoryService historyService() throws Exception {
        return processEngine().getObject().getHistoryService();
    }
    /**
     * 初始化配置
     *
     * @return
     */
    @Bean
    public SpringProcessEngineConfiguration processEngineConfiguration() {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        // 执行工作流对应的数据源
        configuration.setDataSource(dataSource);
        // 是否自动创建流程引擎表
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        // 流程历史等级
        configuration.setHistoryLevel(HistoryLevel.FULL);
        // 流程图字体
        configuration.setActivityFontName("宋体");
        configuration.setAnnotationFontName("宋体");
        configuration.setLabelFontName("宋体");
        configuration.setTransactionManager(transactionManager);
        return configuration;

    }
}
