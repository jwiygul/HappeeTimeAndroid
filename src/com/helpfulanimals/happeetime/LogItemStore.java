package com.helpfulanimals.happeetime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;



public class LogItemStore {
	private static final String TAG = "LogItemStore";
	private static final String FILENAME="LogItems.json";
	private static LogItemStore mLogItemStore;
	private static Context mAppContext;
	private static LogItemSerializer mSerializer;
	private static ArrayList<LogItemContainer> bigContainer;
	private static LogItemContainer logItemContainer;
	
	private LogItemStore(Context appContext){
		mAppContext = appContext;
		mSerializer = new LogItemSerializer(mAppContext,FILENAME);
		Date date = new Date();
		LogItemContainer otherLIC = new LogItemContainer();
		try{
			if(mSerializer.loadLogItemContainer()!=null){
				bigContainer = mSerializer.loadLogItemContainer();
				if (bigContainer.size() !=0)
				{
					otherLIC=bigContainer.get(bigContainer.size()-1);
					Calendar calendar = Calendar.getInstance();
					Calendar otherCalendar = Calendar.getInstance();
					calendar.setTime(date);
					otherCalendar.setTime(otherLIC.getDateCreated());
					String dateString = new String("MMMM dd, yyyy");
					String currentDate = (String) DateFormat.format(dateString, calendar);
					String containerDate = (String)DateFormat.format(dateString, otherCalendar);
					if(currentDate.compareTo(containerDate)!=0){
						logItemContainer = new LogItemContainer();
						bigContainer.add(logItemContainer);
						}
					else{
						logItemContainer = bigContainer.get(bigContainer.size()-1);
						bigContainer.set(bigContainer.size()-1, logItemContainer);
					}
				}
				else{
					logItemContainer = new LogItemContainer();
					bigContainer.add(logItemContainer);
				}
			}
			else{
				bigContainer = new ArrayList<LogItemContainer>();
				logItemContainer = new LogItemContainer();
				bigContainer.add(logItemContainer);
			}
		}catch(Exception e){
			Log.e(TAG,"Error loading logItems:", e);
		}
	}
	public static LogItemStore sharedStore(Context c){
		if (mLogItemStore ==null){
			mLogItemStore = new LogItemStore(c.getApplicationContext());//when singleton is app wide, use this
		}
		return mLogItemStore;
	}
	public LogItem createItem(){
		LogItem item= new LogItem();
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
	public LogItemContainer mostRecentLogContainer(){
		return bigContainer.get(bigContainer.size()-1);
	}
	public int logItemCount(){
		return logItemContainer.getLogItems().size();
	}
	public ArrayList<LogItem> getLogItems(){
		return logItemContainer.getLogItems();
	}
	public LogItemContainer getLogItemContainer(){
		return logItemContainer;
	}
	public LogItemContainer goBack(int index){
		int counter = bigContainer.size()-index;
		if (counter>=0){
			logItemContainer = bigContainer.get(counter);
		return logItemContainer;
		}
		else
			return null;
	}
	public LogItemContainer goForward(int index){
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
	public void removeItem(LogItem item){
		logItemContainer.getLogItems().remove(item);
		int count =0;
		if (item.getTypeIntake().compareTo("Fiber")==0){
			for(int i = 0; i<logItemContainer.getLogItems().size(); i++){
				LogItem countItem = logItemContainer.getLogItems().get(i);
				if(countItem.getTypeIntake().compareTo("Fiber")==0)
					count+=countItem.getAmountIntake();
				
			}
			if (count <=25){
				logItemContainer.setAlreadySetFiberAward(false);
			
			}
		}
		else if (item.getTypeIntake().compareTo("Drink")==0){
			for(int i = 0; i<logItemContainer.getLogItems().size(); i++){
				LogItem countItem = logItemContainer.getLogItems().get(i);
				if(countItem.getTypeIntake().compareTo("Drink")==0)
					count+=countItem.getAmountIntake();
			}
			if (count <=40){
				logItemContainer.setAlreadySetFluidAward(false);
			}
		}
		
	}
	public void createNewLogItemContainer(){
		if (mLogItemStore!=null)
		{
			logItemContainer = new LogItemContainer();
			bigContainer.add(logItemContainer);
		}
	}
	
	public int countFiberAwardsSet(){
		int count = bigContainer.size();
		int numberSet = 0;
		
		for (int i =0; i<count; i++){
			LogItemContainer container = bigContainer.get(i);
			if (container.isAlreadySetFiberAward()==true){
				numberSet++;
			}
		}
		return numberSet;
	}
	public int countFluidAwardsSet(){
		int count = bigContainer.size();
		int numberSet = 0;
		
		for (int i =0; i<count; i++){
			LogItemContainer container = bigContainer.get(i);
			if (container.isAlreadySetFluidAward()==true){
				numberSet++;
			}
		}
		return numberSet;
	}
	public Integer returnNumberOfFiberRows(){
		 int rows = countFiberAwardsSet();
	       int fiberAwardsCount = countFiberAwardsSet();
	        
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
	public Integer returnNumberOfFluidRows(){
		 int rows = countFluidAwardsSet();
	       int fiberAwardsCount = countFiberAwardsSet();
	        
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
	
	public LogItemSerializer getSerializer(){
		return mSerializer;
	}
	
	
}
