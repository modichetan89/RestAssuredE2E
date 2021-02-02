package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	public AddPlace addPlacePayload() {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		Location loc = new Location();
		loc.setLat("-38.383494");
		loc.setLng("33.427362");
		ap.setLocation(loc);

		return ap;
	}
	
	public AddPlace addPlacePayloadDataDriven(String Add, String Lang, String Name) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(Add);
		ap.setLanguage(Lang);
		ap.setName(Name);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		Location loc = new Location();
		loc.setLat("-38.383494");
		loc.setLng("33.427362");
		ap.setLocation(loc);

		return ap;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
		
	}
}
