package com.amazonaws.services.simpleworkflow.flow.examples.helloworld;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Neil Rubens
 *
 */

@Configuration
public class AppConfig {

    public String getActivityTasklistName() {
        return "HelloWorldActivity";
    }

    public String getDomainName() {
        return "Samples";
    }

    public String getWorkflowTasklistName() {
        return "HelloWorldWorkflow";
    }

    @Bean
    public AWSCredentialsProvider getCredentials() throws Exception {
//        return new InstanceProfileCredentialsProvider();
//        return new EnvironmentVariableCredentialsProvider();
        return new ClasspathPropertiesFileCredentialsProvider("aws.properties");


    }

    @Bean
    public AmazonSimpleWorkflow getSimpleWorkflowClient(
            AWSCredentialsProvider awsCredentialsProvider) {
        return new AmazonSimpleWorkflowClient(awsCredentialsProvider);
    }

    @Bean
    public HelloWorldService getHelloWorldService() {
        return new HelloWorldService();
    }


    @Bean
    public HelloWorldWorkflowClientExternal getWorkflowClientExternal(
            AmazonSimpleWorkflow workflowClient) {
        return new HelloWorldWorkflowClientExternalFactoryImpl(
                workflowClient, getDomainName()).getClient();
    }

    @Bean
    public WorkflowWorker getWorkflowWorker(AmazonSimpleWorkflow swfClient) throws Exception {
        WorkflowWorker workflowWorker = new WorkflowWorker(swfClient, getDomainName(),
                getWorkflowTasklistName());

        workflowWorker.addWorkflowImplementationType(HelloWorldWorkflowImpl.class);

        return workflowWorker;
    }

    @Bean
    public HelloWorldActivitiesImpl getActivitiesImpl() {
        return new HelloWorldActivitiesImpl();
    }


    @Bean
    public ActivityWorker getActivityWorker(AmazonSimpleWorkflow swfClient, HelloWorldActivitiesImpl activitiesImpl) throws Exception {
        ActivityWorker activityWorker = new ActivityWorker(swfClient, getDomainName(),
                getActivityTasklistName());

        activityWorker.addActivitiesImplementation(activitiesImpl);

        return activityWorker;
    }

}
