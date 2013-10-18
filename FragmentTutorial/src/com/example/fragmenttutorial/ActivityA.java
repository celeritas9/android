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
		setContentView(R.layout.activity_a);
		
		addFragment();
	}

	private void addFragment() {
		FragmentManager man = getSupportFragmentManager();
		FragmentTransaction trans = man.beginTransaction();
		trans.add(R.layout.activity_a, new FragmentA());
		trans.commit();
	}
}
