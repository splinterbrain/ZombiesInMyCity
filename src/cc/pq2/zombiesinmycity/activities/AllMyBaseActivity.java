package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Base;
import cc.pq2.zombiesinmycity.models.Mission;

public class AllMyBaseActivity extends Activity {
	private static final String TAG = "ALLMYBASEACTIVITY";
	private Base bases[];
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.allmybase);

		bases = ((ZombiesInMyCityApplication) getApplicationContext()).getBases();
		for(Base base : bases){
			Log.v(TAG, base.getName());
		}
		if(bases.length == 1){
			//Show base and mission select
			TextView baseName = (TextView) findViewById(R.id.amb_base_name);
			baseName.setText(bases[0].getName());
		}else{
			//Show list of bases
		}
		
		Button newMissionBtn = (Button)findViewById(R.id.amb_newmission_button);
		newMissionBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AllMyBaseActivity.this, PickMissionActivity.class);
				intent.putExtra("baseIndex", 0);
				startActivity(intent);
			}
			
		});
		
		
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		Mission currentMission = ((ZombiesInMyCityApplication) getApplicationContext()).getCurrentMission();
		if(currentMission != null){
			Intent intent = new Intent(AllMyBaseActivity.this, RunMissionActivity.class);
			startActivity(intent);
		}
	}

}
