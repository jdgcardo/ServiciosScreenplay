package co.servicios.screenplay.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions
        (
                features ="src\\test\\resources\\features\\call_soap_service_with_screenplay.feature",
                glue = "co.servicios.screenplay",
                snippets = SnippetType.CAMELCASE
        )

public class CallSoapServiceWithScreenplayRunner {

}