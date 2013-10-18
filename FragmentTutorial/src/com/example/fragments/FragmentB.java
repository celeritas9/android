package com.example.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fragmenttutorial.R;

public class FragmentB extends Fragment {
	
	public final String cName = FragmentB.class.getName();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_b, container, false);
		return view;
	}
	
	@Override
	public void onPause()
	{
		Log.d(cName, "onPause called.");
		new Exception().printStackTrace();
		super.onPause();
	}
	
	@Override
	public void onStop()
	{
		Log.d(cName, "onStop called.");
		new Exception().printStackTrace();
		super.onStop();
	}
	
	@Override
	public void onDestroy()
	{
		Log.d(cName, "onDestroy called.");
		new Exception().printStackTrace();
		super.onDestroy();
	}
}
