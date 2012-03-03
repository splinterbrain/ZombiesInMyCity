package cc.pq2.zombiesinmycity.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cc.pq2.zombiesinmycity.R;

public class IntroActivity extends Activity {

	private final String panels[] = { "Hello? Can you hear me? This is Survivor Base Zero.",
			"Good to see you're still alive and human.", "There aren't too many of us left, but luckily for us the satellites and cell towers are still running... for now.", "Make sure you have your GPS turned on, we can use it to guide you and warn you when zombies get close."};
	private int currentPanel = 0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);


		findViewById(R.id.intro_panels).post(new Runnable(){
			@Override
			public void run() {
				HorizontalScrollView panelsScroller = (HorizontalScrollView) findViewById(R.id.intro_panels_scrollview);
				LinearLayout panelsLayout = (LinearLayout) findViewById(R.id.intro_panels);

				for (String panel : panels) {
					TextView text = new TextView(panelsLayout.getContext());
					text.setText(panel);
					text.setTextColor(Color.WHITE);
					int width = panelsScroller.getWidth();
					int height = panelsScroller.getHeight();
					panelsLayout.addView(text, width, height);
					
				}
			}
			
		});
		


		Button skipButton = (Button) findViewById(R.id.intro_button_skip);

		skipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				ProgressDialog.show(arg0.getContext(), "", "Zeroing in on your location...", true);
				
				Intent intent = new Intent(IntroActivity.this, AddBaseActivity.class);				
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				finish();
			}
		});

		Button nextButton = (Button) findViewById(R.id.intro_button_next);
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				currentPanel++;
				if (currentPanel < panels.length) {
					HorizontalScrollView panelsScroller = (HorizontalScrollView) findViewById(R.id.intro_panels_scrollview);
//					LinearLayout panelsLayout = (LinearLayout) findViewById(R.id.intro_panels);
					panelsScroller.scrollTo(panelsScroller.getWidth() * currentPanel, 0);
				} else {
//					ProgressDialog.show(v.getContext(), "", "Zeroing in on your location...", true);

					Intent intent = new Intent(IntroActivity.this, AddBaseActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					finish();
				}
			}

		});

	}
}
