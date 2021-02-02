package resources;
//enum is special class in java which have collection of constants or methods
public enum APIResources {
	AddPlaceAPI("/maps/api/place/add/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	UpdatePlaceAPI("/maps/api/place/get/json");
	
	private String resource;
	APIResources(String resource){
		this.resource=resource;
	}
	
	public String getResource() {
		return resource;
	}
}
