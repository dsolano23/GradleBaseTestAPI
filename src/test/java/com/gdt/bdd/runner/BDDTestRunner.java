package com.gdt.bdd.runner;



import com.gdt.enviroment.EnvironmentConstantsFiles;
import com.gdt.enviroment.EnvironmentConstantsNames;
import com.gdt.exception.NotFoundResourceException;
import com.gdt.utilsType.EnvPropertiesManagement;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.masterthought.cucumber.presentation.PresentationMode.RUN_WITH_JENKINS;

@RunWith(Cucumber.class)


@CucumberOptions(
				plugin = { 
						"pretty",
						"json:target/cucumber.json", 
						//"html:target/site/cucumber-pretty",
						},
		
				//Used only if you want run a specific feature by tag : @userManagement, @search or @filter


				tags = {"@CreateFQAsRestriction"},
                 //   tags = {"@GetUsersRestriction"},

				
				features = {"src/test/resources/features"},
		        glue = {"com.gdt.bdd.stepDefinition","com.gdt.enviroment"}
				)
public class BDDTestRunner {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BDDTestRunner.class);

	public static final String ENVIRONMENT_SYSTEM_VARIABLE = "environment";

		@BeforeClass
        public static void startEnvironment() throws Exception {

			if (System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE) == null) {
				logger.error(" ******************* THE ENVIRONMENT VARIABLE IS OBLIGATED TO SET ***************** ");
				//System.setProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE, EnvironmentConstantsFiles.LOCAL_ENVIRONMENT_FILE);
				System.setProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE, EnvironmentConstantsFiles.DEVELOP_ENVIRONMENT_FILE);
                //System.setProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE, EnvironmentConstantsFiles.PRE_PROD_ENVIRONMENT_FILE);
				//System.setProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE, EnvironmentConstantsFiles.PROD_ENVIRONMENT_FILE);
				logger.info(" Loading DEFAULT Environment Configuration File --------------------------- : " + System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE) + ".properties");
				EnvPropertiesManagement.loadEnvironmentProperties();
			} else if (System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE).equalsIgnoreCase(EnvironmentConstantsFiles.LOCAL_ENVIRONMENT_FILE) ||
					   System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE).equalsIgnoreCase(EnvironmentConstantsFiles.DEVELOP_ENVIRONMENT_FILE) ||
					   System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE).equalsIgnoreCase(EnvironmentConstantsFiles.QA_ENVIRONMENT_FILE)){
				logger.info(" Loading Environment Configuration File --------------------------- : " + System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE) + ".properties");
				EnvPropertiesManagement.loadEnvironmentProperties();
			}else{
				logger.error(" ******************* THE ENVIRONMENT VARIABLE IS OBLIGATED TO SET ***************** ");

				logger.error(" ******************* SYSTEM VARIABLE: " + EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE );
				logger.error(" ******************* POSSIBLE VALUES: " + EnvironmentConstantsFiles.LOCAL_ENVIRONMENT_FILE);
				logger.error(" ******************* POSSIBLE VALUES: " + EnvironmentConstantsFiles.DEVELOP_ENVIRONMENT_FILE );
				logger.error(" ******************* POSSIBLE VALUES: " + EnvironmentConstantsFiles.QA_ENVIRONMENT_FILE);
				throw new Exception("\r\n\r\n******************* THE ENVIRONMENT VARIABLE IS OBLIGATED TO SET *****************\r\n\r\n ");
			}
		}

		@AfterClass
	    public static void generateHTMLReport() throws InterruptedException, NotFoundResourceException, IOException {

	        logger.debug("Generating masterthought HTML reports .......");
			File reportOutputDirectory = new File("target");
			List<String> jsonFiles = new ArrayList<>();
			jsonFiles.add("target/cucumber.json");

			String buildNumber = "1";
			String projectName = "cucumberProject";
			PresentationMode runWithJenkins = RUN_WITH_JENKINS;

			String environment = System.getProperty(EnvironmentConstantsNames.ENVIRONMENT_SYSTEM_VARIABLE);
			String so = System.getProperty("os.name");
			Configuration configuration = new Configuration(reportOutputDirectory, projectName);
			// optional configuration

			configuration.addPresentationModes(runWithJenkins);
			configuration.setBuildNumber(buildNumber);
			// addidtional metadata presented on main page
			configuration.addClassifications("Platform", so);
			configuration.addClassifications("Environment",environment);

			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			Reportable result = reportBuilder.generateReports();
			// and here validate 'result' to decide what to do
			// if report has failed features, undefined steps etc
			logger.debug("Generated Masterthought HTML reports");
	    }
    }
