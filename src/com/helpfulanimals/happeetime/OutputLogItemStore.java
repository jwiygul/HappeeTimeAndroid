package com.helpfulanimals.happeetime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

public class OutputLogItemStore {
	private static final String TAG = "OutputLogItemStore";
	private static final String FILENAME="OutputLogItems.json";
	private static OutputLogItemStore mLogItemStore;
	private static Context mAppContext;
	private static OutputLogItemSerializer mSerializer;
	private static ArrayList<OutputLogItemContainer> bigContainer;
	private static OutputLogItemContainer logItemContainer;
	
	private OutputLogItemStore(Context appContext){
		mAppContext = appContext;
		mSerializer = new OutputLogItemSerializer(mAppContext,FILENAME);
		Date date = new Date();
		OutputLogItemContainer otherLIC = new OutputLogItemContainer();
		try{
			if(mSerializer.loadLogItemContainer()!=null)
				bigContainer = mSerializer.loadLogItemContainer();
			else
				bigContainer = new ArrayList<OutputLogItemContainer>();
			Log.d(TAG, Integer.valueOf(bigContainer.size()).toString());
			if (bigContainer.size() !=0){
				otherLIC=bigContainer.get(bigContainer.size()-1);
				Calendar calendar = Calendar.getInstance();
				Calendar otherCalendar = Calendar.getInstance();
				calendar.setTime(date);
				otherCalendar.setTime(otherLIC.getDateCreated());
				String dateString = new String("MMMM dd, yyyy");
				String currentDate = (String) DateFormat.format(dateString, calendar);
				String containerDate = (String)DateFormat.format(dateString, otherCalendar);
				
					
					if(currentDate.compareTo(containerDate)!=0){
				logItemContainer = new OutputLogItemContainer();
				bigContainer.add(logItemContainer);
				}
			else{
				logItemContainer = bigContainer.get(bigContainer.size()-1);
				bigContainer.set(bigContainer.size()-1, logItemContainer);
			}
			}
			else{
				logItemContainer = new OutputLogItemContainer();
				bigContainer.add(logItemContainer);
			}
		}catch(Exception e){
			Log.e(TAG,"Error loading logItems:", e);
		}
	}
	public static OutputLogItemStore sharedStore(Context c){
		if (mLogItemStore ==null){
			mLogItemStore = new OutputLogItemStore(c.getApplicationContext());//when singleton is app wide, use this
		}
		return mLogItemStore;
	}
	public OutputLogItem createItem(){
		OutputLogItem item= new OutputLogItem();
		item.setTimeDateCreated(new Date());
		logItemContainer.addItem(item);
		return item;
	}
	public boolean saveItems(){
		try{
			mSerializer.saveLogItemContainers(bigContainer);
			return true;
		}catch(Exception e){
			Log.e(TAG, "Error saving files: ",e);
			return false;
		}
	}
	public OutputLogItemContainer mostRecentLogContainer(){
		return bigContainer.get(bigContainer.size()-1);
	}
	public int logItemCount(){
		return logItemContainer.getLogItems().size();
	}
	public ArrayList<OutputLogItem> getLogItems(){
		return logItemContainer.getLogItems();
	}
	public OutputLogItemContainer getLogItemContainer(){
		return logItemContainer;
	}
	public OutputLogItemContainer goBack(int index){
		int counter = bigContainer.size()-index;
		if (counter>=0){
			logItemContainer = bigContainer.get(counter);
		return logItemContainer;
		}
		else
			return null;
	}
	public OutputLogItemContainer goForward(int index){
		int counter = bigContainer.size()-index;
		if (counter <0){
			return null;
		}
		else if(counter >= bigContainer.size()){
			logItemContainer = bigContainer.get(counter-1);
			return logItemContainer;
		}
			
		else{
			logItemContainer = bigContainer.get(counter);
			return logItemContainer;
		}
			
	}
	public void removeItem(OutputLogItem item){
		logItemContainer.getLogItems().remove(item);
		int count = logItemContainer.getLogItems().size();
		OutputLogItem nextItem = new OutputLogItem();
		boolean previousWetness = false;
		boolean poopAward = false;
		if(item.getTypeIntake().compareTo("Pee Accident")==0){
			for (int i = 0; i < count;i++){
				nextItem = logItemContainer.getLogItems().get(i);
				if(nextItem.getTypeIntake().compareTo("Pee Accident")==0){
					previousWetness = true;
					break;
				}
			}
			if(!previousWetness)
				logItemContainer.setHadPeeAccident(false);
		}
			
		else if (item.getTypeIntake().compareTo("Poop")==0)
		{ 	
			if(item.getInfoType() ==1)
			{
				
				for(int i = logItemContainer.getLogItems().size()-1; i>-1; i--)
				{
					OutputLogItem localItem = logItemContainer.getLogItems().get(i);
					if(localItem.getTypeIntake().compareTo("Poop")==0)
					{
						logItemContainer.getLogItems().get(i).setInfoType(1);
						poopAward =true;
						break;
					}
				}
				if(!poopAward)
					logItemContainer.setAlreadySetPoopAward(false);
			}
		}
		else if (item.getTypeIntake().compareTo("Pee")==0)
		{
			if(item.getInfoType()==2)
			{
				for(int i = logItemContainer.getLogItems().size()-1; i>-1; i--)
				{
					OutputLogItem localItem = logItemContainer.getLogItems().get(i);
					if(localItem.getTypeIntake().compareTo("Pee")==0)
					{
						logItemContainer.getLogItems().get(i).setInfoType(2);
						poopAward =true;
						break;
					}
				}
				if(!poopAward)
					logItemContainer.setAlreadySetPeeAward(false);
					
				
			}
		}
		
	}
	public void createNewLogItemContainer(){
		if (mLogItemStore!=null)
		{
			logItemContainer = new OutputLogItemContainer();
			bigContainer.add(logItemContainer);
		}
	}
	
	public int countPoopAwardsSet(){
		int count = bigContainer.size();
		int numberSet = 0;
		
		for (int i =0; i<count; i++){
			OutputLogItemContainer container = bigContainer.get(i);
			if (container.isAlreadySetPoopAward()==true){
				numberSet++;
			}
		}
		return numberSet;
	}
	public int countPeeAwardsSet(){
		int count = bigContainer.size();
		int numberSet = 0;
		
		for (int i =0; i<count; i++){
			OutputLogItemContainer container = bigContainer.get(i);
			if (container.isAlreadySetPeeAward()==true){
				numberSet++;
			}
		}
		return numberSet;
	}
	public Integer returnNumberOfPoopRows(){
		 int rows = countPoopAwardsSet();
	        
	        int rowsToReturn = rows / 5;
	        int remainder = rows % 5;
	        if (rows == 0) {
	            return 0;
	        }
	        if (rowsToReturn >0)
	        {
	            if (remainder >0)
	            {
	                return rowsToReturn + 1;
	            }
	            return rowsToReturn ;
	            
	        }
	        else
	            return 1;
	}
	public Integer returnNumberOfPeeRows(){
		 int rows = countPeeAwardsSet();
	        
	        int rowsToReturn = rows / 5;
	        int remainder = rows % 5;
	        if (rows == 0) {
	            return 0;
	        }
	        if (rowsToReturn >0)
	        {
	            if (remainder >0)
	            {
	                return rowsToReturn + 1;
	            }
	            return rowsToReturn ;
	            
	        }
	        else
	            return 1;
	}
	
	public OutputLogItemSerializer getSerializer(){
		return mSerializer;
	}
	public ArrayList<OutputLogItemContainer> getBigContainer(){
		return bigContainer;
	}

}
