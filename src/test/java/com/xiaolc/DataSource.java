package com.xiaolc;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: lc
 * @Date: 2019/6/19 16:01
 */
public class DataSource {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    //操作流程实例
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //生成表
    @Test
    public void createTable() {
        configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");//&nullCatalogMeansCurrent=true  从5.x到6.x要加上这句要不然会遍历表  会报错
        configuration.setJdbcUrl("jdbc:mysql://lc.xiaolc.cn:3306/activiti6?useSSL=false&nullCatalogMeansCurrent=true");
        configuration.setJdbcUsername("admin");
        configuration.setJdbcPassword("");
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine);
    }

    //生成表
    @Test
    public void createTable_2() throws Exception {
        ProcessEngine processEngine = configuration.createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
        System.out.println(processEngine);
    }

    //部署流程实例
    @Test
    public void deploy() {
        Deployment deploy = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("diagrans/wsy.bpmn")
                .deploy();
        System.out.println(deploy);
    }

    /**
     * 启动流程实例
     */
    @Test
    public void startProcess() {
        String key = "wsy";
        ProcessInstance processInstance = processEngine.getRuntimeService()//正在执行的流程实例和执行对象相关的service
                .startProcessInstanceByKey(key);//使用流程定义key启动,key对应这bpmn中的id的属性值，用key启动的是流程的最新版本
        System.out.println("流程实例id：" + processInstance.getId());
        System.out.println("流程定义id：" + processInstance.getProcessDefinitionId());

    }

    @Test
    public void findMyTask() {
        String assignee = "张三";
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        tasks.stream().forEach(task -> {
            System.out.println("任务id：" + task.getId());
            System.out.println("任务名称：" + task.getName());
            System.out.println("任务创建时间：" + task.getCreateTime());
            System.out.println("流程实例ID：" + task.getProcessInstanceId());
            System.out.println("流程定义ID：" + task.getProcessDefinitionId());
            System.out.println("执行对象ID：" + task.getExecutionId());
        });
    }

    //完成任务
    @Test
    public void completeTask() {
        String taskId = "32502";
        processEngine.getTaskService()
                .complete(taskId);
        System.out.println("任务完成");
    }


    /**
     * 查看流程图
     */

    @Test
    public void vieImage() throws Exception {
        String deploymentId = "5001";
        List<String> names = processEngine.getRepositoryService()
                .getDeploymentResourceNames(deploymentId);

        String imageName = null;
        for (String name : names) {
            System.out.println("name：" + name);
            if (name.indexOf(".png") >= 0) {
                imageName = name;
            }
        }
        System.out.println("imageName：" + imageName);
        if (imageName != null) {
            File f = new File("e/" + imageName);
            InputStream in = processEngine.getRepositoryService().getResourceAsStream(deploymentId, imageName);
            FileUtils.copyInputStreamToFile(in, f);
        }

    }
}
