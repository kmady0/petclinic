package petclinic;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class PetClinicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080");

    ScenarioBuilder scn = scenario("PetClinic Load Test")
        .exec(http("Home").get("/"))
        .pause(1)
        .exec(http("Vets").get("/vets").header("Accept", "application/json"))
        .pause(1);

    { setUp(scn.injectOpen(atOnceUsers(20))).protocols(httpProtocol); }
}