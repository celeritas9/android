package com.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.data.Book;
import com.example.fragmenttutorial.ActivityB;

public class FragmentA extends ListFragment implements OnItemClickListener {
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),	
				android.R.layout.simple_list_item_1, Book.BOOK_NAMES);
		this.setListAdapter(adapter);
		getListView().setOnItemClickListener(this);
		new Exception().printStackTrace();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		//Start a new Activity in order to display author name
		String author = Book.AUTHOR_NAMES[position];
		Intent intent = new Intent(getActivity().getApplicationContext(), ActivityB.class);
		intent.putExtra("author", author);
		startActivity(intent);
	}

}
