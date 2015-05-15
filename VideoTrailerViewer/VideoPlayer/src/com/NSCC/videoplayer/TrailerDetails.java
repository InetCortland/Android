package com.NSCC.videoplayer;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

public class TrailerDetails extends ActionBarActivity {

	
	String passed = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trailer_details);
	
		
	
		
		Bundle extras= getIntent().getExtras();
		
		
		if(extras != null)//if bundle has content
		{
			passed = extras.getString("KEY");
		}
		
		final Trailers mytrailers = fetchTrailer(passed);
		
		
		
		TextView tvTitle= (TextView) findViewById(R.id.tvMovietitle);
		TextView tvRating= (TextView) findViewById(R.id.tvcurrentRating);
		EditText textarea = (EditText)findViewById(R.id.etdec);
		Button btnGoto = (Button)findViewById(R.id.btnPlay);
		Button btnDelete = (Button)findViewById(R.id.btnDelete);
		 RatingBar rtb = (RatingBar)findViewById(R.id.rbRating);
        
		 final String tailername = mytrailers.getName();
		  tvTitle.setText(mytrailers.getName());
	      textarea.setText(mytrailers.getDes());
	      tvRating.setText(mytrailers.getRating());
	      	  
		  rtb.setOnRatingBarChangeListener(new OnRatingBarChangeListener()
	        {
			  
				@Override
				public void onRatingChanged(RatingBar ratingBar, float rating,
						boolean fromUser) {
					
					
					
					Trailers trailer = fetchTrailer(tailername);
					float frating = Float.parseFloat(trailer.getRating());
					frating=frating+rating;
					
					UpdateRating(tailername,frating);	
					
					
					
					TextView tvRating= (TextView) findViewById(R.id.tvcurrentRating);
					
					tvRating.setText(trailer.getRating());
					
					
							
							
				}



	        });//end listener inner class
	     
		
	

	      
		  final Intent i = new Intent("VideoPage");//create intent object
		  extras.putString("KEY", passed);//fill bundle
  			i.putExtras(extras);
  			
  			
  			
		  final Intent ii = new Intent("TrailerList");//create intent object
    		//Bundle extras = new Bundle();//create bundle object
    		//extras.putString("KEY","passed");//fill bundle
    		
		  btnGoto.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View arg0)
	        	{
	        		//code using Intents go here
	        		//startActivity(new Intent("ActivityTwo"));
	        		
	        		startActivityForResult(i,1);
	        		
	        	}
	        });//end listener inner class
		
			btnDelete.setOnClickListener(new OnClickListener()
	        {
	        	public void onClick(View arg0)
	        	{
	        		
	        		Trailers mytrailers = fetchTrailer(passed);
	        		Delete(mytrailers.getId());
	        		
	        		
	        		startActivityForResult(ii,1);
	        		finish();
	        	}
	        });//end listener inner class
	        
	} // end on create

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trailer_details, menu);
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
	
	public Trailers fetchTrailer(String name){
		
		DBAdapter db = new DBAdapter(this);
		db.open();
		
		
		Cursor c = db.getTrailer(name);
		
		
		
		Trailers mytrailers = new Trailers();
			
		mytrailers.setId(c.getString(0));
		mytrailers.setName(c.getString(1));
		mytrailers.setInfo(c.getString(2));
		mytrailers.setDes(c.getString(3));
		mytrailers.setRating(c.getString(4));
		mytrailers.setNumRatings(c.getString(5));
			
		db.close();
		return mytrailers;
		
		
	}
	private void UpdateRating(String name, float rating) {
		DBAdapter db = new DBAdapter(this);
		db.open();
		
			String nRating=Float.toString(rating);
			boolean A = db.updateTrailer(name,nRating);
		
		
	}
	
	
	private void Delete(String ID) {
		
		
		
		DBAdapter db = new DBAdapter(this);
		db.open();
	
		db.deleteTrailer(ID);
		
		
	}
	
	
	
}
