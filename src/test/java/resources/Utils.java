package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification reqSpec;
	public RequestSpecification requestSpecification() throws FileNotFoundException {
		PrintStream log = new PrintStream(new FileOutputStream("Logs/logging.txt"));
		reqSpec = new RequestSpecBuilder()
				.setContentType(ContentType.JSON)
				.setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
	}
	
	public static String getGlobalPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("D:\\EclipseWorkspace\\RestAssured\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);	
	}

}
