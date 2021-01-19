import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.get;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.simple.JSONArray;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/*   API Test
 *   @ author Ruth Pamen
 *   Datum 19.01.2021
 */

public class ApiTest {
    //Specify base URI
    Response resp = RestAssured.get("http://api.zippopotam.us/de/bw/stuttgart");
    String responseBody = resp.getBody().asString();

    Object obj = new JSONParser().parse(responseBody);

    // typecasting obj to JSONObject
    JSONObject jo = (JSONObject) obj;

    public ApiTest() throws ParseException {
    }

    @Test
    void getStatusCode() {

        //create Request object
        RequestSpecification httpRequest = RestAssured.given();

        //print response in console
        System.out.println("Response Body is" + responseBody);


        //status code validation
        int code = resp.getStatusCode();
        System.out.println("status code is" + code);
        Assert.assertEquals(code, 200);

    }

    @Test
    void testContenType() {

        //verify the content type
        resp.getContentType();
        Assert.assertEquals(resp.getContentType(), "application/json");
    }
    @Test
    void testValidationTime() {
        //verify that the response time is below 1s
        Assert.assertTrue(resp.getTimeIn(TimeUnit.SECONDS) <= 1, "yes response time is below 1s");
    }





        //verify that that "country" is Germany and "state" is "Baden-WÃ¼ttenberg"
        @Test
        void testCountryAndState()  {

            String country = (String) jo.get("country");
            Assert.assertEquals(country, "Germany");

            String state = (String) jo.get("state");
            Assert.assertEquals(state, "Baden-Württemberg");


        }

        //verify in Response - For Post Code "70597" the place name has "Stuttgart Degerloch"

        @Test
        void testPostCodeAndPlaceName()  {

        JSONArray places = (JSONArray) jo.get("places");
        Iterator itr = places.iterator();
        while (itr.hasNext()) {
            JSONObject place = (JSONObject) itr.next();

            String postcode = (String)place.get("post code");

            String placename = (String)place.get("place name");

            if(postcode.equals("70597")){
                Assert.assertEquals(placename, "Stuttgart Degerloch");

            }

        }







    }


    }



