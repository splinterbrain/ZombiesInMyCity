package cc.pq2.zombiesinmycity.models;

import java.io.Serializable;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class Place implements Serializable {
	private static final long serialVersionUID = 6790106636076558136L;
	private double latitude;
	private double longitude;

	
	public Place(double latitude, double longitude, String name) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
	}
	
	public Place(Location location, String name){
		super();
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
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


	public GeoPoint toGeoPoint() {
		return new GeoPoint((int) (latitude*1e6), (int) (longitude*1e6));
	}
	
	//Ignore, etc go here later
}
