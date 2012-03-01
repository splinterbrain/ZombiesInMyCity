package cc.pq2.zombiesinmycity.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cc.pq2.zombiesinmycity.R;
import cc.pq2.zombiesinmycity.models.Base;

public class AllMyBaseActivity extends Activity {
	private ArrayList<Base> bases;
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewbase);
		if(bases == null){
			bases = new ArrayList<Base>();
			Intent intent = new Intent(this, AddBaseActivity.class);
			startActivity(intent);
		}
	}

}
