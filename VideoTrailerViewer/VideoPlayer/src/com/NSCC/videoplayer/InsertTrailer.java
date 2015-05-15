package com.NSCC.videoplayer;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class InsertTrailer extends ActionBarActivity {
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_trailer);
		
		
	
		final Spinner spinner1 = (Spinner) findViewById(R.id.spinTrailers);
		Button btnSubmit;
	
		List<String> list = new ArrayList<String>();
		list.add("guardiansofthegalaxy");
		list.add("thehungergamesmockingjay");
		
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner1.setAdapter(dataAdapter);
			
			btnSubmit = (Button) findViewById(R.id.btnsubmit);
			final EditText textarea = (EditText)findViewById(R.id.etInputdesc);
			
			
			
			
			btnSubmit.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View arg0)
	        	{
	        		String selectedTrailer = String.valueOf(spinner1.getSelectedItem());
	        			String inputDesc = textarea.getText().toString();
	        		
	        			
	        			if(inputDesc.length()>1){
	        			
	        			InsertTrailer(selectedTrailer, inputDesc);
	        			
	        			 final Intent i = new Intent("TrailerList");//create intent object
	        			 startActivityForResult(i,1);
	        			}
	        			else {
	        				Toast.makeText(getApplicationContext(), "Enter Data into Text Box.",
	        						   Toast.LENGTH_LONG).show();
	        			}
	        			
	        	        	}
	        });//end listener inner class
	}

	
	
	
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_trailer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void InsertTrailer(String name, String desc) {
		DBAdapter db = new DBAdapter(this);
		db.open();
		
		db.insertsmallTrailer(name,desc);
		
		
	}
	

}
