package com.NSCC.assignment2quizgame;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;


public class Pagetwo extends ActionBarActivity {
	
	// all kinds of vars setup.
	public TextView tvStuffRec;
	public TextView txtTerms;
	public Button btnquit;
	public Button Button1;
	public Button Button2;
	public Button Button3;
	public Button Button4;
	public InputStream is;
	public Builder build;
	public TextView textScore;
	// our score keeper
	int score;
	// total steps counter
	int totalsteps;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pagetwo);
		// initalize and set objects
		score=0;
		totalsteps=0;
		textScore = (TextView)findViewById(R.id.txtfinalScore);
		tvStuffRec = (TextView)findViewById(R.id.txtNamedisplay);
		txtTerms=(TextView)findViewById(R.id.txtTerms);
		btnquit= (Button)findViewById(R.id.btnQuit);
		Button1 = (Button)findViewById(R.id.btnQuiz1);
		Button2 = (Button)findViewById(R.id.btnQuiz2);
		Button3 = (Button)findViewById(R.id.btnQuiz3);	
		Button4 = (Button)findViewById(R.id.btnQuiz4);
		is = this.getResources().openRawResource(R.raw.quizgame);
		build= new Builder(is);
		
		
		//We need to display name, so I passed it through from the other text field
		// and it will populate here in a label near the top.
		String myStuff="";
		Bundle extras=getIntent().getExtras();
		if(extras != null)//if bundle has content
		{
			myStuff = extras.getString("KEY");
			tvStuffRec.setText(myStuff);
			
		
		} // end if of bundle content
		
		
		
		
		
			// We will load the objects and arrays here. and also increase the steps
		totalsteps++;
		loadQuiz();
	
		
	
		Button1.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	
        	{    
        		//check for end step
        		if(totalsteps == 10){
        			endStep();
        		}
        		
        		else {
        		
        			// checking btn 1
        		if(build.check(Button1.getText().toString(),txtTerms.getText().toString())){
        			
                    Toast.makeText(getBaseContext(),
                            "CORRECT!", 
                            Toast.LENGTH_SHORT).show();
                    score++;
                    		loadQuiz();
        			
        		}
        		
        		else {
        			// checking wrong btn1
        		    Toast.makeText(getBaseContext(),
                            "WRONG!", 
                            Toast.LENGTH_SHORT).show();
        		    		score--;
        		    		loadQuiz();
        		}
        		totalsteps++;
        		}}
        });//end listener inner class
        
		// checking btn2
		Button2.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	
        	{    
        		
        		if(totalsteps == 10){
        			endStep();
        		}
        		
        		else {
        		
        			if(build.check(Button2.getText().toString(),txtTerms.getText().toString())){
        			
                    Toast.makeText(getBaseContext(),
                            "CORRECT!", 
                            Toast.LENGTH_SHORT).show();
                    		score++;
                    		loadQuiz();
        			
        		}
        		
        			else {
        			// checking btn2 wrong
	        		    Toast.makeText(getBaseContext(),
	                            "WRONG!", 
	                            Toast.LENGTH_SHORT).show();
		        		    score--;
		        		    loadQuiz();
        		}	
        		}
        		
        		totalsteps++;
        	}
        	
        	
        });//end listener inner class
        
		
		// checking btn 3
		Button3.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	
        	{    
        		
        		if(totalsteps == 10){
        			endStep();
        		}
        		
        		else {
        		// checking btn 3 wrong
        			if(build.check(Button3.getText().toString(),txtTerms.getText().toString())){
	        			
	                    Toast.makeText(getBaseContext(),
	                            "CORRECT!", 
	                            Toast.LENGTH_SHORT).show();
	                    		score++;
	                    		loadQuiz();
	        			
	        		}
	        		
	        		else {
	        			
	        		    Toast.makeText(getBaseContext(),
	                            "WRONG!", 
	                            Toast.LENGTH_SHORT).show();
			        		    score--;
			        		    loadQuiz();
	        		}
        		}
        		totalsteps++;
        	}
        });//end listener inner class
		
        // checking btn 4
		Button4.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	
        	{    
        		
        		if(totalsteps == 10){
        			endStep();
        		}
        		
        		else {
        		
        		if(build.check(Button4.getText().toString(),txtTerms.getText().toString())){
        			
                    Toast.makeText(getBaseContext(),
                            "CORRECT!", 
                            Toast.LENGTH_SHORT).show();
                    		score++;
                    		loadQuiz();
        			
        		}
        		
        		else {
        			// checking btn wrong 4
        		    Toast.makeText(getBaseContext(),
                            "WRONG!", 
                            Toast.LENGTH_SHORT).show();
		        		    score--;
		        		    loadQuiz();
        		}
        		}
        		totalsteps++;
        	}
        });//end listener inner class
		
		
		// Quit button
		btnquit.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	
        	{    

        		endStep();
        		
        		}
        		
        	        });//end listener inner class
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pagetwo, menu);
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


	
	// this loads all of our quiz using our builder object
	public void loadQuiz()
	
	{
		String correctTerm=null;
		
		// getting the term
		correctTerm= build.getterm();
		txtTerms.setText(correctTerm);
		textScore.setText(Integer.toString(score));		
		ArrayList<Object> temp = build.getDef(correctTerm);
		// put the shuffled values into the buttons
		Button1.setText(temp.get(0).toString());
		Button2.setText(temp.get(1).toString());
		Button3.setText(temp.get(2).toString());	
		Button4.setText(temp.get(3).toString());
		
		
	}
	
	// this is the end step we will go here if we quit or reach the end
	public void endStep(){
		
		Intent i = new Intent("Pagethree");//create intent object
		Bundle extras = new Bundle();//create bundle object
		String strTemp = textScore.getText().toString();
		extras.putString("KEY", strTemp);//fill bundle
		i.putExtras(extras);
		
		startActivityForResult(i,1);
		finish();
		
		
	}

}


