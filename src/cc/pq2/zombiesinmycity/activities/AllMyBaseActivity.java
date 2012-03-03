package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Base;

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
			
		}else{
			//Show list of bases
		}
	}

}
