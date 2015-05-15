package com.NSCC.videoplayer;

//Trailers[] trailers = new Trailers[10];
/*
for (int i = 0; i < trailers.length; i++) {
	trailers[i] = new Trailers();
	}*/


/*
	Toast.makeText(this,trailers[0].getId(),Toast.LENGTH_LONG).show();
	Toast.makeText(this,trailers[1].getId(),Toast.LENGTH_LONG).show();
	Toast.makeText(this,trailers[2].getId(),Toast.LENGTH_LONG).show();
	/*
	Toast.makeText(this,trailers[0].getDes(),Toast.LENGTH_LONG).show();
	Toast.makeText(this,trailers[0].getInfo(),Toast.LENGTH_LONG).show();
	*/
//final String name=trailers[0].getName();

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

@SuppressWarnings({ "unused", "deprecation" })
@SuppressLint("SdCardPath")
public class TrailerList extends ActionBarActivity {
	
	

	

	
	
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trailer_list);
		// loading information into the database
		try{
			String destPath = "/data/data/" + getPackageName() +"/database/MyDB";
			File f = new File(destPath);
			if(!f.exists()){
				CopyDB(getBaseContext().getAssets().open("mydb"),
						new FileOutputStream(destPath));
			}
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		
		DBAdapter db = new DBAdapter(this);
		// We clear the Database First
		
		// Now we load the data using loadDatabase Function
		//****************************************************************************************
		//****************************************************************************************
		//****************************************************************************************
		//db.clear();
		//loadDatabase();
		//****************************************************************************************
		//****************************************************************************************
		//****************************************************************************************
		
		
		// this loads all of our database into trailer objects
		final Trailers[] trailers = loadTrailers();
		Button btnInsert = (Button)findViewById(R.id.btnInsert);
		
		
		
		
		
		GridView gridView = (GridView) findViewById(R.id.gridView1);
		gridView.setAdapter(new ImageAdapter(this));
		
		
		
		
		gridView.setOnItemClickListener(new OnItemClickListener()

		{
			public void onItemClick(AdapterView<?> parent,
			View v, int position, long id)
			{
				
				Intent i = new Intent("TrailerDetails");
        		Bundle extras = new Bundle();//create bundle object
        		extras.putString("KEY", trailers[position].getName());//fill bundle
        		i.putExtras(extras);
        		
        		startActivityForResult(i,1);
        		finish();
       		
			}
		});
		 
		
		
		
		final Intent ii = new Intent("Insert");//create intent object
		btnInsert.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View arg0)
        	{
        		//code using Intents go here
        		//startActivity(new Intent("ActivityTwo"));
        		
        		startActivityForResult(ii,1);
        		finish();
        	}
        });//end listener inner class
		
		
		
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trailer_list, menu);
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
		
	
	public void loadDatabase(){
		
		try{
			String destPath = "/data/data/" + getPackageName() +"/database/MyDB";
			File f = new File(destPath);
			if(!f.exists()){ 
				
				CopyDB(getBaseContext().getAssets().open("mydb")
						,new FileOutputStream(destPath));
			}
			
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
			
		DBAdapter db = new DBAdapter(this);
				
		//add a contact- CREATE
		db.open();
		long id = db.insertTrailer("fury", "Fury - Sherman tank and a five-man crew, undertakes a deadly mission behind enemy lines.", "In April 1945, the Allies are making their final push in the European theater. A battle-hardened Army sergeant named Don 'Wardaddy' Collier (Brad Pitt), leading a Sherman tank and a five-man crew, undertakes a deadly mission behind enemy lines. Hopelessly outnumbered, outgunned and saddled with an inexperienced soldier (Logan Lerman) in their midst, Wardaddy and his men face overwhelming odds as they move to strike at the heart of Nazi Germany.", 1,  1);
		id = db.insertTrailer("bighero6", "Hiro's closest companion is Baymax, a robot whose sole purpose is to take care of people.", "Robotics prodigy Hiro (Ryan Potter) lives in the city of San Fransokyo. Besides his older brother, Tadashi, Hiro's closest companion is Baymax (Scott Adsit), a robot whose sole purpose is to take care of people. When a devastating turn of events throws Hiro into the middle of a dangerous plot, he transforms Baymax and his other friends, Go Go Tamago (Jamie Chung), Wasabi (Damon Wayans Jr.), Honey Lemon (Genesis Rodriguez) and Fred (T.J. Miller) into a band of high-tech heroes.", 1,  1);
		id = db.insertTrailer("birdman", "An ambitious Broadway production that he hopes will breathe new life into his stagnant career.", "Former cinema superhero Riggan Thomson (Michael Keaton) is mounting an ambitious Broadway production that he hopes will breathe new life into his stagnant career. It's risky, but he hopes that his creative gamble will prove that he's a real artist and not just a washed-up movie star. As opening night approaches, a castmate is injured, forcing Riggan to hire an actor (Edward Norton) who is guaranteed to shake things up. Meanwhile, Riggan must deal with his girlfriend, daughter and ex-wife.", 1,  1);
			
		db.close();
		
		}
	
	public Trailers[] loadTrailers()
	{
		
		DBAdapter db = new DBAdapter(this);
		db.open();
		Cursor c = db.getAllTrailer();
		Trailers[] mytrailers = new Trailers[c.getCount()];
		
		
		
		for (int i = 0; i < mytrailers.length; i++) {
			mytrailers[i] = new Trailers();
			}
		
		int i = 0;
		
		while (c.moveToNext()) {
			
		mytrailers[i].setId(c.getString(0));
		mytrailers[i].setName(c.getString(1));
		mytrailers[i].setInfo(c.getString(2));
		mytrailers[i].setDes(c.getString(3));
		mytrailers[i].setRating(c.getString(4));
		mytrailers[i].setNumRatings(c.getString(5));
	
			
		i++;
		}
		
		db.close();
		return mytrailers;
	}
	
	public void CopyDB(InputStream inputStream,OutputStream outputStream)

			throws IOException{
				//copy 1k bytes at a time
				byte[] buffer = new byte[1024];
				int length;
				while((length = inputStream.read(buffer)) > 0)
				{
					outputStream.write(buffer,0,length);
				}	
				inputStream.close();
				outputStream.close();
				
			}//end method CopyDB
	
	public class ImageAdapter extends BaseAdapter
	{
		

		Trailers[] trailersz = loadTrailers();
		
		Integer[] imageIDs= new Integer[trailersz.length];
		
		
		/*
		Integer[] imageIDs={
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury,
				R.drawable.fury
			}; */
		
		private Context context;
		
		public ImageAdapter(Context c)
		{
			context = c;
		}
	
		//returns the number of images
		public int getCount()
		{
			return imageIDs.length;
		}
		
		//returns the item
		public Object getItem(int position)
		{
			return position;
		}
		
		//returns the item id
		public long getItemId(int position)
		{
			return position;
		}
		
		
		//returns an ImageView view
		public View getView(int position,View convertView, ViewGroup parent)
		{
			Trailers[] trailers = loadTrailers();
			
			
			
			for(int i = 0; i<trailers.length; i++)
		    {
		    
				int id = getResources().getIdentifier(trailers[i].getName(), "drawable", getPackageName());  
				//getResources().getIdentifier("us","drawable","com.app");
		        //int resID = getResources().getIdentifier("fury", "drawable", getPackageName());
		        imageIDs[i]= id;
		    } 
			
			
			
			
			ImageView imageView;
			if(convertView == null)
			{
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(185,185));
				imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
				imageView.setPadding(5,5,5,5);
			}else{
				imageView = (ImageView) convertView;
			}
			
			imageView.setImageResource(imageIDs[position]);
			return imageView;
			
		}//end method getView
		
	}
	
	
}
