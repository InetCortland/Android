package com.NSCC.assignment2quizgame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.view.View.OnClickListener;
import android.content.ActivityNotFoundException;
import android.content.Intent;


public class Pageone extends ActionBarActivity {
	
	
	// creating buttons
	Button btnGoto;
	EditText nameText;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageone);
        // link objects to real ui values
        btnGoto = (Button)findViewById(R.id.btnSubmit);
        nameText = (EditText)findViewById(R.id.txtNamebox);
        
        
        btnGoto.setOnClickListener(new OnClickListener()
        {
        	
        	
        	
        	public void onClick(View arg0)
        	
        	{
        try {
        	// make sure there is a value in the box, otherwise it will toast an error
        	if(nameText.getText().toString().trim().length() == 0)
        	{
        		
                Toast.makeText(getBaseContext(),
                        "Name Cannot be empty", 
                        Toast.LENGTH_SHORT).show();
        		

        	}
        	
        	
        	else {
        		
        		//code using Intents go here
        		//startActivity(new Intent("ActivityTwo"));
        		Intent i = new Intent("Pagetwo");//create intent object
        		Bundle extras = new Bundle();//create bundle object
        		extras.putString("KEY", nameText.getText().toString());//fill bundle
        		i.putExtras(extras);
        		// this will send off a intent
        		startActivityForResult(i,1);
        		finish();
        		
        	}
        		
        		
        		
        		
        	} catch (ActivityNotFoundException e) {
        		e.printStackTrace();
                 }
        
        	}
        });//end listener inner class
        

		
		
        
        
    } // end on create


    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pageone, menu);
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
