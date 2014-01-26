package com.amazonaws.services.simpleworkflow.flow.examples.helloworld;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

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
    public AWSCredentials getCredentials() throws Exception {
        //return new PropertiesCredentials(getClass().getResourceAsStream("aws.properties")); // may need to be in the "/aws.properties"
        //return new PropertiesCredentials(getClass().getResourceAsStream(System.getenv("AWS_PROPERTIES")));
        return new PropertiesCredentials( new File("aws.properties"));
    }

    @Bean
    public AmazonSimpleWorkflow getSimpleWorkflowClient(
            AWSCredentials awsCredentials) {
        return new AmazonSimpleWorkflowClient(awsCredentials);
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
