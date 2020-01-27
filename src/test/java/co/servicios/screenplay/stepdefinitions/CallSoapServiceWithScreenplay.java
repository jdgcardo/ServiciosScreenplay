package co.servicios.screenplay.stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CallSoapServiceWithScreenplay {

    @Before
    public void configureBaseUrl(){
        RestAssured.baseURI = "http://webapp.tcscourier.com/codapi/Service1.asmx?op=GetAllCountries";
        setTheStage(new OnlineCast());
    }

    @Given("^that jorge calls a SOAP API with Screenplay$")
    public void thatJorgeCallsASOAPAPIWithScreenplay() {
        theActorCalled("JUAN").whoCan(CallAnApi.at(RestAssured.baseURI));

    }

    @When("^jorge obtains the SOAP response$")
    public void jorgeObtainsTheSOAPResponse() {
        theActorInTheSpotlight().attemptsTo(
                Get.resource(RestAssured.baseURI).with(
                         request -> request
                                .header("Content-Type", "application/xml")
                                .header("Accept-Encoding", "gzip,deflate")
                                .body("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                                        "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                                        "  <soap:Body>\n" +
                                        "    <GetAllCountries xmlns=\"http://202.61.51.93:6265/\" />\n" +
                                        "  </soap:Body>\n" +
                                        "</soap:Envelope>")
                )
        );
    }

    @Then("^jorge verifies the quality OF SOAP response$")
    public void jorgeVerifiesTheQualityOFSOAPResponse() {
        seeThatResponse( "User details should be correct",
                response -> response.statusCode(200));
    }
}
