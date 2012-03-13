package cc.pq2.zombiesinmycity.activities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Mission;
import cc.pq2.zombiesinmycity.models.Place;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class RunMissionActivity extends MapActivity implements LocationListener {

	private Mission currentMission;
	private MapView mapview;
	private PlayerOverlay playerOverlay;
	private MissionOverlay missionOverlay;
	private Location lastLocation;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.runmission);
		currentMission = ((ZombiesInMyCityApplication) getApplicationContext()).getCurrentMission();
		mapview = (MapView) findViewById(R.id.runmission_mapview);
		mapview.setBuiltInZoomControls(true);
		mapview.getController().setZoom(20);
//		mapview.getOverlays().add(new Overlay());
		mapview.getController().animateTo(currentMission.getSegment(0).getStartPlace().toGeoPoint());
//		mapview.getController().animateTo(new GeoPoint((int) (currentMission.getSegment(0).getFinishPlace().getLatitude()*1e6), (int) (currentMission.getSegment(0).getFinishPlace().getLongitude()*1e6)));
//		mapview.getController().animateTo(new GeoPoint((int) (currentMission.getSegment(0).getStartPlace().getLatitude()*1e6), (int) (currentMission.getSegment(0).getStartPlace().getLongitude()*1e6)));
		
		playerOverlay = new PlayerOverlay(getResources().getDrawable(R.drawable.ic_launcher), currentMission.getSegment(0).getStartPlace().toGeoPoint(), "You");
		missionOverlay = new MissionOverlay(getResources().getDrawable(R.drawable.ic_launcher), currentMission.getSegment(0).getStartPlace().toGeoPoint(), currentMission.getSegment(0).getFinishPlace().toGeoPoint());
		//TODO Zombies
		
		mapview.getOverlays().add(playerOverlay);
		mapview.getOverlays().add(missionOverlay);
		
		//Register for location 
		 locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

		
		
		
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

	@Override
	public void onLocationChanged(Location location) {
		lastLocation = location;
		if(!findViewById(R.id.runmission_start).isEnabled()){
			findViewById(R.id.runmission_start).setEnabled(true);
		}else{
		
		//Update mission
		currentMission.getSegment(0).updateProgress(new Place(location, ""));
		
		if(currentMission.getSegment(0).isComplete()){
			Toast.makeText(getApplicationContext(), "Congratulations!", 3).show();
			locationManager.removeUpdates(this);
			//TODO Add to player stats
			//TODO Set up return mission ready to go
			finish();
		}
		
		//Update texts
		((TextView)findViewById(R.id.runmission_elapseddistance)).setText(currentMission.getSegment(0).getElapsedDistance() + " km");
		((TextView)findViewById(R.id.runmission_elapsedtime)).setText(currentMission.getSegment(0).getElapsedTime() + " ms");
		
		//Update overlays
		playerOverlay.updatePlayer(currentMission.getSegment(0).getLastLocationPlace().toGeoPoint());
		//TODO update mission overlay so it shows an arrow for out of view objective
		
		//Zoom to location
		mapview.getController().animateTo((new Place(location, "").toGeoPoint()));
		}		
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void startMission(View v){
		currentMission.getSegment(0).start(new Place(lastLocation, ""));
		findViewById(R.id.runmission_start).setVisibility(View.INVISIBLE);
	}


	protected class PlayerOverlay extends ItemizedOverlay<OverlayItem>{
		
		private OverlayItem player;

		public PlayerOverlay(Drawable defaultMarker, GeoPoint point, String title) {
			super(boundCenterBottom(defaultMarker));
			// TODO Auto-generated constructor stub
			player = new OverlayItem(point, title, "");			
			populate();
		}

		public void updatePlayer(GeoPoint point){
			player = new OverlayItem(point, player.getTitle(), "");
			populate();
		}
		
		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			return player;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 1;
		}
		
	}
	
	protected class MissionOverlay extends ItemizedOverlay<OverlayItem>{

		OverlayItem startPoint;
		OverlayItem finishPoint;
		
		public MissionOverlay(Drawable defaultMarker, GeoPoint start, GeoPoint finish) {
			super(boundCenterBottom(defaultMarker));
			// TODO Auto-generated constructor stub
			this.setStartPoint(start);
			this.setFinishPoint(finish);
			populate();
		}

		public void setStartPoint(GeoPoint point){
			startPoint = new OverlayItem(point, "Start", "");
		}
		
		public void setFinishPoint(GeoPoint point){
			finishPoint = new OverlayItem(point, "Finish", "");
			
		}
		
		@Override
		protected OverlayItem createItem(int i) {
			// TODO Auto-generated method stub
			if(i == 0){
				return startPoint;
			}else if (i==1){
				return finishPoint;
			}else{
				return null;
			}
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 2;
		}
		
	}

}

