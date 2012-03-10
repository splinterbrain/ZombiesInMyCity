package cc.pq2.zombiesinmycity.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
import cc.pq2.zombiesinmycity.models.Place;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PlacesApi {
	private static final String TAG = "PLACESAPI";
	private static final String apiKey = "AIzaSyB-1FHcKWZQMNVBwHn8E1OR1wwriObNEb0";
	
//	https://maps.googleapis.com/maps/api/place/search/json?key=AIzaSyB-1FHcKWZQMNVBwHn8E1OR1wwriObNEb0&location=45.52782450,-122.68527580&radius=10000&sensor=false&keyword=groceries
	public static Place[] searchForPlaces(Place place, String keyword){
		try {
			ArrayList<Place> places = new ArrayList<Place>();
			String url = String.format("https://maps.googleapis.com/maps/api/place/search/json?key=%s&location=%f,%f&radius=10000&sensor=false&keyword=%s", PlacesApi.apiKey, place.getLatitude(), place.getLongitude(), keyword);
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			ResponseHandler<String> respHandler = new BasicResponseHandler();
			String respBody = httpClient.execute(httpGet, respHandler);

			JsonParser parser = new JsonParser();
			JsonArray results = parser.parse(respBody).getAsJsonObject().get("results").getAsJsonArray();
			Iterator<JsonElement> iter = results.iterator();
			while(iter.hasNext()){
				JsonObject result = iter.next().getAsJsonObject();
				JsonObject location = result.get("geometry").getAsJsonObject().get("location").getAsJsonObject();
				places.add(new Place(location.get("lat").getAsDouble(), location.get("lng").getAsDouble(), result.get("name").getAsString()));
			}
//			Place place = new Place(longitude, longitude, respBody);
//			places.trimToSize();
			Place[] ret = new Place[places.size()]; 
			places.toArray(ret);
			return ret;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static double getDistance(Place startPlace, Place finishPlace){
		//Thanks wikipedia
		// r*ds where r = 6372.8km
		// ds in radians
		// ds = atan(x/y)
		// x = sqrt((cos(lat_f)*sin(dLon))^2 + (cos(lat_s)*sin(lat_f) - sin(lat_s)*cos(lat_f)*cos(dLon))^2 )
		// y = sin(lat_s)*sin(lat_f) + cos(lat_s)*cos(lat_f)*cos(dLon)
		Log.v(TAG, startPlace.getLatitude() + ", " + startPlace.getLongitude());
		Log.v(TAG, finishPlace.getLatitude() + ", " + finishPlace.getLongitude());
		double lat_s = Math.toRadians((double)startPlace.getLatitude());
		double lat_f = Math.toRadians((double)finishPlace.getLatitude());
		double dLon = Math.toRadians((double)(finishPlace.getLongitude() - startPlace.getLongitude()));
		double x = Math.sqrt(Math.pow(Math.cos(lat_f)*Math.sin(dLon),2) + Math.pow(Math.cos(lat_s)*Math.sin(lat_f) - Math.sin(lat_s)*Math.cos(lat_f)*Math.cos(dLon),2));
		double y = Math.sin(lat_s)*Math.sin(lat_f) + Math.cos(lat_s)*Math.cos(lat_f)*Math.cos(dLon);
		double ds = Math.atan2(x,y);

		return 6372.8*ds;
	}
}
