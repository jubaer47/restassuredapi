package testCase;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiTest {
	public String url = "http://localhost:3000/";
	private JSONObject data;
	public Response respons;
	public JsonPath path;
	public SoftAssert softassert;
	public RequestSpecification http;

	@BeforeMethod
	public void setUp() {
		RestAssured.baseURI = url;
		http = RestAssured.given();

	}

	@Test(priority = 1)
	public void post() {

		data = new JSONObject();
		data.put("first_name", "mohammad");
		data.put("last_name", "jubaer");
		data.put("email", "dummy@emila.com");

		http.body(data.toJSONString());
		http.header("Content-Type", "application/json");

		respons = http.request(Method.POST, "users");

		int responseCode = respons.getStatusCode();
		Assert.assertEquals(responseCode, 201);

	}

	@Test(priority = 2)
	public void GET() {
		respons = http.request(Method.GET, "users");
		String _From_Api = respons.getBody().asPrettyString();
		System.out.println("boy creat" + _From_Api);
		path = respons.jsonPath();
		softassert = new SoftAssert();
		String first_name = path.get("[5].email");
		Assert.assertEquals(first_name, "dummy@emila.com");
		int responsCode = respons.getStatusCode();
		softassert.assertEquals(responsCode, 200);
		
		ArrayList<String> data = new ArrayList<String>();
		
		
		
		Headers headers = respons.headers();
		
		
		Header a = headers.get("Content-Type");
		
	
		
		for(Header header : headers) {
			System.out.println(header);
		}
		
		
		
		
		
		
	}

	@Test(priority = 3)
	public void DELETE() {
		respons = http.request(Method.DELETE, "users/7");
		int responsCode = respons.getStatusCode();
		softassert = new SoftAssert();
		softassert.assertEquals(responsCode, 200);

	}
}
