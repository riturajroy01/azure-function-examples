package org.example.functions;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class HttpTriggerFunctionPost {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExamplePost")
    public HttpResponseMessage HttpExamplePost(
            @HttpTrigger(
                name = "req",
                methods = HttpMethod.POST,
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");

        // Parse query parameter
       final String student = request.getBody().get();


        if (student == null) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass student info in the request body").build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK).body(student).build();
        }

    }
}
