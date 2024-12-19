package workshopcucumberadvance.Test.StepsDef.Runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import static io.cucumber.junit.platform.engine.Constants.PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("workshopcucumberadvance")
@SelectClasspathResource("workshopcucumberadvance")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
//this parameter will add the allure report as an additional
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
// this will disable parallel execution by default it is enabled
@ConfigurationParameter(key = PARALLEL_EXECUTION_ENABLED_PROPERTY_NAME, value = "false")
// this will skip the @SkipThisTag by default or it will execute the tags that you specify
//"@RunThisTag and not @RunThisTag"
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@ExampleLogin")
public class RunCucumberTest {
}
