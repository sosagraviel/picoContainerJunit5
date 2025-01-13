package workshopcucumberadvance.Test.StepsDef.Runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("workshopcucumberadvance.Test.StepsDef")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/workshopcucumberadvance")
//this parameter will add the allure report as an additional
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, "
				+ "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm, "
				+ "html:target/cucumber-reports/report.html, "
				+ "json:target/cucumber-reports/cucumber.json, "
				+ "com.aventstack.chaintest.plugins.ChainTestCucumberListener:target/chaintest/chaintest-report.json"
)
// this will disable parallel execution by default it is enabled
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "false")

// this will skip the @SkipThisTag by default or it will execute the tags that you specify
//"@RunThisTag and not @RunThisTag"
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ExampleLogin")
public class RunCucumberTest {
}
