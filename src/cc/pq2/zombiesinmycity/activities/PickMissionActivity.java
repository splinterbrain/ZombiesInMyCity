package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.controllers.PlacesApi;
import cc.pq2.zombiesinmycity.models.Base;
import cc.pq2.zombiesinmycity.models.Place;

public class PickMissionActivity extends Activity implements OnClickListener {
	private static final String TAG = "PICKMISSIONACTIVITY";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pickmission);
		Base base = ((ZombiesInMyCityApplication) getApplicationContext()).getBase(getIntent().getExtras().getInt("baseIndex"));
		Place[] foodPlaces = PlacesApi.searchForPlaces(base.getPlace(), "groceries");
		LinearLayout missionList = (LinearLayout) findViewById(R.id.pickmission_missionlist);
		for(Place place : foodPlaces){
			Log.v(TAG, place.getName());
			double dist = PlacesApi.getDistance(base.getPlace(), place);
			Button missionButton = new Button(missionList.getContext());			
			missionButton.setText(place.getName() + "(" + dist + " km)");
			missionButton.setTag(place);
			missionButton.setOnClickListener(this);
			missionList.addView(missionButton);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Place place = (Place)v.getTag();
		Toast.makeText(getApplicationContext(), "Starting mission to " + place.getName(), 3).show();
	}
	
}
