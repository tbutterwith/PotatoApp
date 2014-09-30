package com.potato.potatoapp.activities;

import java.util.List;

import com.potato.potatoapp.ListAdapter;
import com.potato.potatoapp.R;
import com.potato.potatoapp.R.drawable;
import com.potato.potatoapp.R.id;
import com.potato.potatoapp.R.layout;
import com.potato.potatoapp.R.menu;
import com.potato.potatoapp.beans.Symptom;
import com.potato.potatoapp.database.DiseaseDatabaseController;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SymptomChildActivity extends ListActivity {

	String symptom_names[];
	String disease_names[];
	String disease_descriptions[];
	DiseaseDatabaseController db;
	List<Symptom> symptoms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_symptom);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		Intent parent = getIntent();
		int parentID = parent.getIntExtra("parent", 0);
		setSymptoms(parentID);
		Integer imageId = R.drawable.apterousaphid;
		db = new DiseaseDatabaseController(this);
		ListAdapter adapters = new ListAdapter(SymptomChildActivity.this,
				symptom_names, imageId);
		ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(adapters);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				click(position);
			}

		});

	}

	public void click(int position) {
		int pos = position;
		// intent.putExtra("position", pos);
		int symParent = symptoms.get(pos).getId();
		symptoms = db.getSymptomFromParent(symParent);
		if (symptoms.size() > 0) {
			Intent intent = new Intent(SymptomChildActivity.this,
					SymptomChildActivity.class);
			intent.putExtra("parent", symParent);

		}
	}


	public void setSymptoms(int parent) {
		symptoms = db.getSymptomFromParent(parent);
		symptom_names = new String[symptoms.size()];
		for (int i = 0; i < symptoms.size(); i++) {
			symptom_names[i] = symptoms.get(i).getDescription();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.symptom, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		finish();
		return true;
	}

}
