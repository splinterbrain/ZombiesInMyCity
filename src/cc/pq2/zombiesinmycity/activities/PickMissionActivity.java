package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.controllers.PlacesApi;
import cc.pq2.zombiesinmycity.models.Base;
import cc.pq2.zombiesinmycity.models.Place;

public class PickMissionActivity extends Activity {
	private static final String TAG = "PICKMISSIONACTIVITY";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Base base = ((ZombiesInMyCityApplication) getApplicationContext()).getBase(getIntent().getExtras().getInt("baseIndex"));
		Place[] foodPlaces = PlacesApi.searchForPlaces(base.getLatitude(), base.getLongitude(), "groceries");
		for(Place place : foodPlaces){
			Log.v(TAG, place.getName());
		}
	}
	
}
