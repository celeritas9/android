package com.example.fragmenttutorial;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class ActivityB extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String s = extras.getString("author");
			TextView view = (TextView)findViewById(R.id.textViewAuthor);
			view.setText(s);
		}
	}
}
