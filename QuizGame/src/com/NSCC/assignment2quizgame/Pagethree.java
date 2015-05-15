package com.NSCC.assignment2quizgame;

import android.support.v7.app.ActionBarActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Pagethree extends ActionBarActivity {

	
	// For this we want to just create a simple page that lets us restart.
	public TextView tvStuffRec;
	Button btnGoto;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pagethree);
		// we just want to fill in the bundle so we can show the score
		tvStuffRec = (TextView)findViewById(R.id.txtTotalScore);
		btnGoto = (Button)findViewById(R.id.btnRestart);
		
		String myStuff="";
		Bundle extras=getIntent().getExtras();
		if(extras != null)//if bundle has content
		{
			myStuff = extras.getString("KEY");
			tvStuffRec.setText(myStuff);
			
		
		} // end if of bundle content
		
		
		
        btnGoto.setOnClickListener(new OnClickListener()
        {
        	
        	
        	
        	public void onClick(View arg0)
        	
        	{
        try {
        	
        	// send back to page two..
        	
    		//code using Intents go here
    		//startActivity(new Intent("ActivityTwo"));
        	Intent startIntent = new Intent();
        	startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	startIntent.setPackage(getApplicationContext().getPackageName());
        	getApplicationContext().startActivity(startIntent);
    		finish();
        		
        		
        		
        		
        	} catch (ActivityNotFoundException e) {
        		e.printStackTrace();
                 }
        
        	}
        });//end listener inner class
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pagethree, menu);
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
}
