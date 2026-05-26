package petclinic

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class PetClinicSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html")

  val scn = scenario("PetClinic Load Test")
    .exec(http("Home").get("/"))
    .pause(1)
    .exec(http("Owners").get("/owners"))
    .pause(1)
    .exec(http("Vets").get("/vets"))

  setUp(
    scn.inject(atOnceUsers(20))
  ).protocols(httpProtocol)
}
