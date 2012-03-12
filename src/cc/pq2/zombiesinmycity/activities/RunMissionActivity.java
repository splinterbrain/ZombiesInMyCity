package cc.pq2.zombiesinmycity.activities;

import android.os.Bundle;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Mission;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class RunMissionActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.runmission);
		Mission currentMission = ((ZombiesInMyCityApplication) getApplicationContext()).getCurrentMission();
		MapView mapview = (MapView) findViewById(R.id.runmission_mapview);
		mapview.setBuiltInZoomControls(true);
		mapview.getController().setZoom(20);
//		mapview.getOverlays().add(new Overlay());
		mapview.getController().animateTo(new GeoPoint((int) (currentMission.getSegment(0).getStartPlace().getLatitude()*1e6), (int) (currentMission.getSegment(0).getStartPlace().getLongitude()*1e6)));
//		mapview.getController().animateTo(new GeoPoint((int) (currentMission.getSegment(0).getFinishPlace().getLatitude()*1e6), (int) (currentMission.getSegment(0).getFinishPlace().getLongitude()*1e6)));
//		mapview.getController().animateTo(new GeoPoint((int) (currentMission.getSegment(0).getStartPlace().getLatitude()*1e6), (int) (currentMission.getSegment(0).getStartPlace().getLongitude()*1e6)));
		
		//Register for location 
		
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
