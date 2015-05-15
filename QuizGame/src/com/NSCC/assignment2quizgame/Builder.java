package com.NSCC.assignment2quizgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Random;

// builder class lets us do all kinds of raw file to text processes as well as lots of the software logic.
public class Builder {

	public boolean check;
	public String strTerm;
	public String strDef;
	public ArrayList<Object> TEST =new ArrayList<Object>();
	public Map<String,String> HashMap = new HashMap<String,String>();
	public ArrayList<Object> allTermsList =new ArrayList<Object>();
	public ArrayList<Object> allDefs =new ArrayList<Object>();
	public ArrayList<Object> populateButtons =new ArrayList<Object>();
	 
	// allTermsList.add(VALUE);   ////     populateButtons.add(VALUE);
	// allTermsList.remove(3); ect
	//  HashMap.put("KEYVALUE", "LARGEVALUE");

	
	
	// Builder Object Constructor
	public Builder(InputStream is){
		
		// We will just fill the hashmap value from the file.
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String str = null;
		String FullFile = null;
			
		try{
			while ((str = br.readLine()) != null) {
				
				// Was getting "null" before my real data.... Needed to remove it
				if (FullFile == null){
					
					FullFile=str;
					
				}
				else {
					// else add str onto fullfile
				FullFile = FullFile + str;
				}
	         }
			
			int i=0;
			// There is where we remove @ signs and seperate the strings out into a string.
			// array and I feel this is absolutely amazing.
			String[] ar=FullFile.split("@");
			
			// We might as well setup both of our array lists as well
		while(i < (ar.length -1)){
			
			HashMap.put(ar[i], ar[i+1]);
			allDefs.add(ar[i+1]);
			allTermsList.add(ar[i]);
			
			// we need to do this twice to avoid double keys or double values in the map or the array lists.
			i++;
			i++;
			
			// Double checked and the values do load properly into the hash map.
		}
			
		
		}
		
		catch (IOException e){
			e.printStackTrace();
		}

		
		
	}
	
	// Get a term from array list and return it as well as removie one of the terms
	public String getterm (){
		int	randomNum;
		
		int max = allTermsList.size();
		Random r = new Random();
		
		
		// make sure random num doesnt crash program.
		if (max-1 <=0){
			randomNum=0;
			
		}
		else {
		
		 randomNum = r.nextInt(max-1);
		}
		
		strTerm = allTermsList.get(randomNum).toString();
		allTermsList.remove(randomNum);
		
		return strTerm;
		
	}
	
	
	// check based on info sent in. We want to return true if the values match
	public Boolean check (String buttoncheck , String labelCheck) {
		
		//Calling to hashmap for a quick check of the key.
		String checkforMatch = HashMap.get(labelCheck);
		
		if(checkforMatch == buttoncheck){
			
			populateButtons.clear();
			check=true;
		}
		else {
			populateButtons.clear();
			check=false;
		}
		
		
		
		
		
		return check;
		
		
		
		
	}
	
	// Get the few parts for the button output, we're pushing a arraylist outside as I feel its one
	// of the most effective ways to push that out without forcing several calls from our 
	//class using get methods which might be  worse
	public ArrayList<Object> getDef(String correctTerm){
		
		String correctDef = HashMap.get(correctTerm);
		Random r = new Random();
		int max = allDefs.size();
		int counter = 1;
		// we want to set the correct def into it to ensure that we have a match.
		// we also want to populate the table with default values as null generates an error
		populateButtons.add(correctDef);
		populateButtons.add("default");
		populateButtons.add("default");
		populateButtons.add("default");
		
		
		// While the list size is less then 4 AKA index 0,1,2,3
		while(counter <=3){
			int randomNum = r.nextInt(max);
			
			//check to make sure we don't add the def TWICE OR MORE
			
			if(populateButtons.get(0).toString() == allDefs.get(randomNum).toString()){}
			
			else if(populateButtons.get(1).toString() == allDefs.get(randomNum).toString()){}
			
			else if(populateButtons.get(2).toString() == allDefs.get(randomNum).toString()){}
			
			else if(populateButtons.get(3).toString() == allDefs.get(randomNum).toString()){}
			
			// Could prob use switches but w/e..
			// if we now know there are no copys inside of it, we can then add it to the list
			// that we will return.
			else {
				
				populateButtons.add(counter,allDefs.get(randomNum).toString());	
				//we add the value in, and it merely pushes the other value down
				// We end up having 1 extra slot in our array list so this is why we remove it
				populateButtons.remove(populateButtons.size()-1);
				counter++;
				
			}
			
			}
				
				
		// Before we return the list, I want to shuffle them.
		// seeding random for better random
		// I still end up getting repeats, not sure why...
		long seed = System.nanoTime();
		Collections.shuffle(populateButtons, new Random(seed));
		
		
		
		
		// we now return the collection so the front end can populate the fields.
		
		return populateButtons;
		}
}
