package cc.pq2.zombiesinmycity.controllers;

import cc.pq2.zombiesinmycity.models.Place;

public class PlacesApi {
	private static final String apiKey = "AIzaSyB-1FHcKWZQMNVBwHn8E1OR1wwriObNEb0";
//	https://maps.googleapis.com/maps/api/place/search/json?key=AIzaSyB-1FHcKWZQMNVBwHn8E1OR1wwriObNEb0&location=45.52782450,-122.68527580&radius=10000&sensor=false&keyword=groceries
	public static Place searchForPlace(double latitude, double longitude, String keyword){
		String url = String.format("https://maps.googleapis.com/maps/api/place/search/json?key=%s&location=%f,%f&radius=10000&sensor=false&keyword=%s", PlacesApi.apiKey, latitude, longitude, keyword);
		Place place = new Place();
		return place;
	}
}
