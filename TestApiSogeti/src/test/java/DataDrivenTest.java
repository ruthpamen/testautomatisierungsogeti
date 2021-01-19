import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;


/*   DataDrivenTest
 *   @ author Ruth Pamen
 *   Datum 19.01.2021
 */

public class DataDrivenTest {



    public DataDrivenTest() throws ParseException {
    }

    @Test(dataProvider = "testdataprovider")

    void postCountryPostcode(String country, String postalecode, String placename) throws ParseException {
        Response resp = RestAssured.get("http://api.zippopotam.us/" + country + "/" + postalecode);

        //capture response body to perform validation
        String responseBody = resp.getBody().asString();

        Object obj = new JSONParser().parse(responseBody);
        JSONObject jo = (JSONObject) obj;


        //create Request object
        RequestSpecification httpRequest = RestAssured.given();

        //print response in console
        System.out.println("Response Body is" + responseBody);


        //status code validation
        int code = resp.getStatusCode();
        System.out.println("status code is" + code);
        Assert.assertEquals(code, 200);


        //verify the content type
        resp.getContentType();
        Assert.assertEquals(resp.getContentType(), "application/json");

        //Verify in Response "Place Name" for each input "Country" and "Postal Code"
        JSONArray places = (JSONArray) jo.get("places");
        Iterator itr = places.iterator();
        while (itr.hasNext()) {
            JSONObject place = (JSONObject) itr.next();

            String place_name = (String) place.get("place name");

            Assert.assertEquals(place_name, placename);



        }



    }

     //check Datas from the table
    @DataProvider(name="testdataprovider")
    Object[] [] getEmpData()
    {
        String testdata[][]={{"us","90210","Beverly Hills"},{"us","12345","Schenectady"},{"ca","B2R","Waverley"}};
        return(testdata);
    }



}
