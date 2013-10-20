package com.example.fragmenttutorial;

import com.example.fragments.FragmentA;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityA extends FragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Inflate this layout. If I try to inflate the layout activity_a, it will throw an IllegalArgumentException as 
		//it fails to find the id for layout layout_activity_a in the addFragment() below.
		setContentView(R.layout.layout_activity_a);
		
		addFragment();
	}

	private void addFragment() {
		FragmentManager man = getSupportFragmentManager();
		FragmentTransaction trans = man.beginTransaction();
		
		//Add the fragment given by FragmentA into the layout by layout_activity_a.
		trans.add(R.id.layout_activity_a, new FragmentA());
		//Save the changes.
		trans.commit();
	}
}
