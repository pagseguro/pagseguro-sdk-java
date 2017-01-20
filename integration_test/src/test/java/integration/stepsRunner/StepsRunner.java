package integration.stepsRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
        glue = "integration.Modulos", tags = "@signature")

public class StepsRunner {

}
