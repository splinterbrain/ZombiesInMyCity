package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.ZombiesInMyCityApplication;
import cc.pq2.zombiesinmycity.models.Base;

public class ZombiesInMyCityActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        //Login goes here later
        

        
    }

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
        ZombiesInMyCityApplication app = (ZombiesInMyCityApplication) getApplicationContext();
        Base bases[] = app.getBases();
        if(bases.length < 1){
        	//Launch base browser
        	Intent intent = new Intent(this, IntroActivity.class);
        	startActivity(intent);
        }else{
        	Toast.makeText(app, "Good to have you still with us. Let's get you out on a mission.", 3).show();
        	Intent intent = new Intent(this, AllMyBaseActivity.class);
        	startActivity(intent);
        }
	}
    
    
}