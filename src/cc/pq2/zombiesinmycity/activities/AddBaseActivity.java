package cc.pq2.zombiesinmycity.activities;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Base;

public class AddBaseActivity extends Activity {

	private static String TAG = "ADD_BASE_ACTIVITY";
	private ProgressDialog loadingDialog;
	
	private double latitude;
	private double longitude;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addbase);

		findViewById(R.id.add_base_form_layout).setVisibility(View.INVISIBLE);
		
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener locationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				Log.v(TAG, String.format("Lat %f Long %f", latitude, longitude));
				try {
					URL url = new URL(
							String.format(
									"http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=15&size=150x150&scale=2&sensor=true",
									latitude, longitude));
					InputStream is = (InputStream) url.getContent();
					Drawable d = Drawable.createFromStream(is, "src");
//					LinearLayout layout = (LinearLayout) findViewById(R.id.add_base_root);
					// ImageView imgView = new ImageView(layout.getContext());
					// imgView.setMaxHeight(100);
					// imgView.setMaxWidth(100);
					// layout.addView(imgView);
					ImageView imgView = (ImageView) findViewById(R.id.add_base_form_location_image);
					imgView.setImageDrawable(d);
					
					//Get this working later
//					TextView text = (TextView) findViewById(R.id.add_base_form_title);
//					url = new URL(String.format("http://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&sensor=true",latitude, longitude));
//					String content = url.getContent();
//					JsonParser parser = new JsonParser();
//					JsonObject user = parser.parse(content).getAsJsonObject()
//							.get("results").getAsJsonObject().get("address_components").getAsJsonArray().get
					LinearLayout form = (LinearLayout) findViewById(R.id.add_base_form_layout);
					form.setVisibility(View.VISIBLE);
					form.requestLayout();
					form.getParent().requestLayout();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				loadingDialog.dismiss();
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				Log.v(TAG, "Provider disabled " + provider);

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

		};
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

		loadingDialog = ProgressDialog.show(AddBaseActivity.this, "", "Finding location...", true);

//		Location mockLocation = new Location(LocationManager.GPS_PROVIDER);
//		mockLocation.setLatitude(45.52782450);
//		mockLocation.setLongitude(-122.68527580);
//		locationListener.onLocationChanged(mockLocation);
		
		Button goButton = (Button) findViewById(R.id.add_base_form_submit);
		goButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				//Add base
				Base base = new Base();
				
				base.setName(((EditText)findViewById(R.id.add_base_form_name)).getText().toString());
				
				base.setLatitude(latitude);
				base.setLongitude(longitude);
				ZombiesInMyCityApplication app = (ZombiesInMyCityApplication) getApplicationContext();
				app.addBase(base);
				//Finish activity
				finish();
			}
			
		});
	}

}
