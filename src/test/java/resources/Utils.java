package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	public static RequestSpecification requestSpecification() throws IOException {
		if(reqSpec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("Logs/logging.txt"));
		reqSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri(getGlobalPropertyValue("baseURL"))
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
		}
		return reqSpec;
	}
	
	public static ResponseSpecification responseSpecification() throws FileNotFoundException {
		if(resSpec==null) {
			resSpec = new ResponseSpecBuilder()
					.expectContentType(ContentType.JSON)
					.expectStatusCode(200)
					.build();
			return resSpec;
			}
			return resSpec;
	}
	
	public static String getGlobalPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\EclipseWorkspace\\RestAssured\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);	
	}
	
	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
	}

}
