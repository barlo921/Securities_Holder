package com.barlo.investment_portfolio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;

@Slf4j
public class TestResults implements BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    private static final String START_TIME = "start time";
    private static final StringBuilder RESULTS = new StringBuilder();

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        getStore(context).put(START_TIME, System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method testMethod = context.getRequiredTestMethod();
        boolean testResults = context.getExecutionException().isPresent();
        long startTime = getStore(context).remove(START_TIME, long.class);
        long duration = System.currentTimeMillis() - startTime;

        String result = String.format("\n%-37s %7d %10s", testMethod.getName(), duration, !testResults ? "SUCCESS" : "FAILED");
        RESULTS.append(result);

        log.info(result + " ms\n");
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        log.info("\n---------------------------------------------------------" +
                "\nTest                              Duration, ms    Result" +
                "\n---------------------------------------------------------" +
                RESULTS +
                "\n---------------------------------------------------------");
    }

    private ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
}