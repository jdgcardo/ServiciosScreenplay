package co.servicios.screenplay.stepdefinitions;

import co.servicios.screenplay.models.User;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

public class CallRestServiceWithScreenplay {

    @Before
    public void configureBaseUrl(){
        RestAssured.baseURI = "https://reqres.in/api";
        setTheStage(new OnlineCast());
    }


    @Given("^that jorge calls a REST API with Screenplay$")
    public void thatJorgeCallsARESTAPIWithScreenplay() {
        theActorCalled("jorge").whoCan(CallAnApi.at(RestAssured.baseURI));

    }


    @When("^jorge obtains the response$")
    public void jorgeObtainsTheResponse() {
        theActorInTheSpotlight().attemptsTo(Get.resource("/users/1"));
        User user = SerenityRest.lastResponse()
                .jsonPath()
                .getObject("data", User.class);

    }

    @Then("^jorge verifies the quality response$")
    public void jorgeVerifiesTheQualityResponse() {
        theActorInTheSpotlight().should(
                seeThatResponse( "User details should be correct",
                        response -> response.statusCode(200)
                                .body("data.first_name", equalTo("George"))
                                .body("data.last_name", equalTo("Bluth"))
                )
        );

    }

    @When("^jorge obtains the POST response$")
    public void jorgeObtainsThePOSTResponse() {
        theActorInTheSpotlight().attemptsTo(
                Post.to("/users")
                        .with(request -> request.header("Content-Type", "application/json")
                .body("{\"firstName\": \"Sarah-Jane\",\"lastName\": \"Smith\"}")
        ));
    }

    @Then("^jorge verifies the POST quality response$")
    public void jorgeVerifiesThePOSTQualityResponse() {
        theActorInTheSpotlight().should(
                seeThatResponse("The user should have been successfully added",
                        response -> response.statusCode(201)
                ));
    }

    @When("^jorge obtains the PUT response$")
    public void jorgeObtainsThePUTResponse() {
        theActorInTheSpotlight().attemptsTo(
                Put.to("/users")
                        .with(request -> request.header("Content-Type", "application/json")
                                .body("{\"firstName\": \"jack\",\"lastName\": \"smith\"}")
                        )
        );
    }

    @Then("^jorge verifies the PUT quality response$")
    public void jorgeVerifiesThePUTQualityResponse() {
        theActorInTheSpotlight().should(
                seeThatResponse(response -> response.statusCode(200)
                        .body("updatedAt", not(isEmptyString())))

        );

    }

    @When("^jorge obtains the DELETE response$")
    public void jorgeObtainsTheDELETEResponse() {
        theActorInTheSpotlight().attemptsTo(
                Delete.from("/users/1")
        );
    }

    @Then("^jorge verifies the DELETE quality response$")
    public void jorgeVerifiesTheDELETEQualityResponse() {
        theActorInTheSpotlight().should(
                seeThatResponse(response -> response.statusCode(204))
        );
    }
}
