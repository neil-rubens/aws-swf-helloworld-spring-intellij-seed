package com.amazonaws.services.simpleworkflow.flow.examples.helloworld;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;

import javax.inject.Inject;

/**
 * @author Neil Rubens
 */
public class HelloWorldService {

    @Inject
    HelloWorldWorkflowClientExternal workflowClientExternal;

    @Inject
    AmazonSimpleWorkflow workflowService;

    public void requestHelloWorld(String name) {
        workflowClientExternal.helloWorld(name);
    }


}
