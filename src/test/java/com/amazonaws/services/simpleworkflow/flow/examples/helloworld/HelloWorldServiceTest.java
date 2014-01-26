package com.amazonaws.services.simpleworkflow.flow.examples.helloworld;

import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import com.amazonaws.services.simpleworkflow.flow.junit.spring.FlowSpringJUnit4ClassRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * @author Neil Rubens
 */

@RunWith(FlowSpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=AppConfig.class)
public class HelloWorldServiceTest {

    @Inject
    HelloWorldService helloWorldService;

    @Inject
    ActivityWorker activityWorker;

    @Inject
    WorkflowWorker workflowWorker;


    @Before
    public void before() {
        this.workflowWorker.start();
        this.activityWorker.start();
    }


    @After
    public void after() throws Exception {
        this.workflowWorker.shutdownAndAwaitTermination(10, TimeUnit.SECONDS);
        this.activityWorker.shutdownAndAwaitTermination(10, TimeUnit.SECONDS);
    }



    @Test
    public void basicTest() throws Exception {
        helloWorldService.requestHelloWorld("Buddy");
        Thread.sleep(30000L); // Give it a little bit of time to complete
    }

}
