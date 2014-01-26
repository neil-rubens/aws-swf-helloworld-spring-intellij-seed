For more details see http://activeintelligence.org/blog/archive/intellij-aws-flow-framework-swf/

Change domain in AppConfig (you might have clashes with existing "Samples" domain's version of activities; so just make a new domain)

Put needed values in aws.properties

Run: HelloWorldServiceTest

Sometime an exception is thrown with regards to connection pool (I think it is due to terminating the service; without waiting sufficiently long).
`java.lang.IllegalStateException: Connection pool shut down`


# TroubleShooting

Problem: com.amazonaws.AmazonServiceException: Status Code: 400, AWS Service: AmazonSimpleWorkflow, AWS Request ID: 6ee6b605-8661-11e3-88a2-a5ccaae822cf, AWS Error Code: UnrecognizedClientException, AWS Error Message: The security token included in the request is invalid.
Solution: fill in aws.properties

# References:

For more details see http://activeintelligence.org/blog/archive/intellij-aws-flow-framework-swf/

Based in part on [https://www.newvem.com/aws-simple-workflow-service-swf-part-2-sample-use-case/]
