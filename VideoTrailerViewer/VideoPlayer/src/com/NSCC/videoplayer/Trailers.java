package com.NSCC.videoplayer;

import java.io.Serializable;

public class Trailers implements Serializable {
	
	
	
	public Trailers(){
		
		
		
	}
	
	
	
	 	private String ID;
	 	private String Name;
	 	private String Info;
	 	private String Description;
	 	private String Rating;
	 	private String Numratings;
	
	
	    
	    
	    public void setId(String input){
	    	
	    	ID=input;
	    	
	    }
	    
	   public void setName(String input){
		    	
		    	Name=input;
		    	
		    }
	   public void setInfo(String input){
	   	
	   		Info=input;
	   	
	   }
	   public void setDes(String input){
	   	
	   		Description=input;
	   	
	   }
	   public void setRating(String input){
	   	
	   	Rating=input;
	   	
	   }   
	   public void setNumRatings(String input){
	   
	   Numratings=input;
	   
   }
	    
	    
	   
	   
	   
		  public String getId(){
		    	
		    	return ID;
		    	
		    }
	      public String getName(){
	    	
	    	return Name;
			    	
			    }
		  public String getInfo(){
		  	
		  		return Info;
		  	
		  }
		  public String getDes(){
		  	
		  		return Description;
		  	
		  }
		  public String getRating(){
		  	
		  	return Rating;
		  	
		  }   
		  public String getNumRatings(){
		  
		  return Numratings;
		  
		}
		   
   
	
	
	
}
