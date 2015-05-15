package com.NSCC.videoplayer;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

public class DBAdapter {
	
	
	
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_INFO = "info";
    public static final String KEY_Description = "description";
    public static final String KEY_RATING = "rating";
    public static final String KEY_numRating = "numratings";
	public static final String TAG = "DBAdapter";
	
	
	
	
	
	
	private static final String DATABASE_NAME = "MyDB";
	private static final String DATABASE_TABLE = "trailers";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_CREATE =
		"create table trailers(_id integer primary key autoincrement,"
		+ "name text not null,info text not null,description text not null,rating int not null,numratings int not null);";
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	
	public DBAdapter(Context ctx)

	{
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);	
	}
	


	
	
		
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context)
		{
			super(context,DATABASE_NAME,null,DATABASE_VERSION);
		}
		
		
		public void onCreate(SQLiteDatabase db)
		{
			try{
				db.execSQL(DATABASE_CREATE);
			}catch(SQLException e){
				e.printStackTrace();
			}		
		}//end method onCreate
		
		public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
		{
			Log.w(TAG,"Upgrade database from version " + oldVersion + " to "
			+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS trailers");
			onCreate(db);	
		}//end method onUpgrade
	}
	
	
	public void clear()
	{
		
		context.deleteDatabase(DATABASE_NAME);
		
	}//end method onUpgrade

	
	
	
	//open the database
	public DBAdapter open() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	
	
	
	
	//close the database
	public void close()
	{
		DBHelper.close();
	}
	
	
	/*
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_INFO = "info";
    public static final String KEY_Description = "description";
    public static final String KEY_RATING = "rating";
    public static final String KEY_numRating = "numratings";
	public static final String TAG = "DBAdapter";
	*/
	
	//insert a contact into the database
	public long insertTrailer(String trailername,String info,String desc, int rating, int numberofratings)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, trailername);
		initialValues.put(KEY_INFO, info);
		initialValues.put(KEY_Description, desc);
		initialValues.put(KEY_RATING, rating);
		initialValues.put(KEY_numRating, numberofratings);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	
	
	
	
	
	//delete a particular contact
	public boolean deleteTrailer(String iD)
	{
		return db.delete(DATABASE_TABLE,KEY_ROWID + "=" + "'"+iD+ "'",null) >0;
	}
	

	
	
	
	//retrieve all the contacts
	public Cursor getAllTrailer()
	{
		return db.query(DATABASE_TABLE,new String[]{KEY_ROWID,KEY_NAME,
				KEY_INFO,KEY_Description,KEY_RATING,KEY_numRating},null,null,null,null,null);
	}
	
	
	
	
	
	
	//retrieve a single trailer
	public Cursor getTrailer(String inputname) throws SQLException
	{
		
		Cursor mCursor=db.query(DATABASE_TABLE, // Table name
		          null, // String[] containing your column names
		          "name" +'=' +  "'"+ inputname +"'", // your where statement, you do not include the WHERE or the FROM DATABASE_TABLE parts of the query,
		          null,
		          null,
		          null,
		          null
		         );
		//Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_NAME,KEY_INFO,KEY_Description,KEY_RATING,KEY_numRating}, KEY_NAME +" = " + "fury",null,null,null,null,null);
		
		
		
		
		if(mCursor != null)
		{
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	
	
	
	
	
	//updates a contact
	public boolean updateTrailer(String trailername, String nRating)
	{
		ContentValues cval = new ContentValues();
		cval.put(KEY_RATING, nRating);
			
		
		return db.update(DATABASE_TABLE, cval, "name" +'=' +  "'"+ trailername +"'",null) >0;
	}
	
	
	
	public long insertsmallTrailer(String name, String desc)
	{
		
		String info ="Null Info";
		String rating ="1";
		String numberofratings = "1";
		
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_INFO, info);
		initialValues.put(KEY_Description, desc);
		initialValues.put(KEY_RATING, rating);
		initialValues.put(KEY_numRating, numberofratings);
		return db.insert(DATABASE_TABLE, null, initialValues);
	}

	

	
	
}//end class DBAdapter












