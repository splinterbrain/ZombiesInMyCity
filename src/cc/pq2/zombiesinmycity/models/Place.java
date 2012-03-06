package cc.pq2.zombiesinmycity.models;

public class Place {
	private double latitude;
	private double longitude;

	
	public Place(double latitude, double longitude, String name) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	
	//Ignore, etc go here later
}
