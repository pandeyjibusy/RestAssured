package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddLocation;
import pojo.location;

public class TestdataBuild {
	public AddLocation addPlacePayload(String name, String language, String address) {
		AddLocation p = new AddLocation();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		p.setName(name);
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		
		location l = new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l);
		return p;
	}
	
	public String deletePlacePayload(String place_id) {
		
		return "{\"place_id\":\""+place_id+"\"}";
	}
}
