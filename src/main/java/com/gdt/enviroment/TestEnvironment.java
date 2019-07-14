package com.gdt.enviroment;

import com.gdt.baseClient.client.BaseHttpClient;
import com.gdt.exception.NotFoundResourceException;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class TestEnvironment {

    /**
     * The Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TestEnvironment.class);

    private Map<String, Object> stepDataMap= new HashMap<>();

    public TestEnvironment() {
		super();
		// not delete
	}

	public static BaseHttpClient getApiClient() {
        return apiClient;
    }

    private static BaseHttpClient apiClient;

    /**
     * Singleton instance strategy
     */

    private static Scenario testScenario;

    public static Scenario getScenario() {
        return testScenario;
    }

    public static BaseHttpClient setApiClient(BaseHttpClient client){
        return apiClient;
    }
    public static String getScenarioName() {
        String scenarioName = "Not Name Scenario";
        if ( testScenario != null && testScenario.getName() != null) {
            scenarioName = testScenario.getName().toUpperCase();
        }
        return scenarioName;
    }

    @Before
    public void setUp(Scenario scenario) throws MalformedURLException, NotFoundResourceException {
        logger.info("*************************************");
        logger.info(String.format(" Started Test %s ", scenario.getName()));

        testScenario = scenario;

    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void tearDown(Scenario scenario) throws NotFoundResourceException {

        if (scenario.isFailed()) {
            logger.info("scenario failed *************************************");
        }
    }

    public void put(String key, Object data) {
		stepDataMap.put(key, data);
	}
	
	public synchronized Object get(String key) {
		return stepDataMap.get(key);
	}

}
