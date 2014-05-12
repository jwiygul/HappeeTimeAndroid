package com.helpfulanimals.happeetime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.os.Environment;
import android.text.format.DateFormat;
import android.util.Log;

public class OutputLogItemSerializer {
	private static final String TAG="OutputLogItemSerializer";
	private Context mContext;
	private String mFilename;
	
	public OutputLogItemSerializer(Context c, String f){
		mContext=c;
		mFilename=f;
	}
	public ArrayList<OutputLogItemContainer> loadLogItemContainer() throws IOException, JSONException{
		ArrayList<OutputLogItemContainer> logItemContainers = new ArrayList<OutputLogItemContainer>();
		BufferedReader reader = null;
		try{
			
			InputStream in = mContext.openFileInput(mFilename);
			//File root = new File(Environment.getExternalStorageDirectory(),mFilename);
		
			//InputStream newIn = new FileInputStream(root);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) !=null)
				jsonString.append(line);
			
			//this parse JSON using tokener
			JSONArray array = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
			for (int i = 0; i < array.length(); i++){
				logItemContainers.add(new OutputLogItemContainer(array.getJSONObject(i)));
			}
		}catch (FileNotFoundException e){
			Log.e(TAG, "file not found:"+e); 
		}finally{
			if (reader !=null)
				reader.close();
		}
		
		return logItemContainers;
	}

	public void saveLogItemContainers(ArrayList<OutputLogItemContainer> logItemContainers) throws JSONException, IOException{
		JSONArray array = new JSONArray();
		OutputLogItemContainer container = new OutputLogItemContainer();
		for (int i = 0; i <logItemContainers.size(); i++){
			container = logItemContainers.get(i);
			array.put(container.toJSON());
		}
		Writer writer = null;
		try{
			//writing logitems.json to internal files
			OutputStream out = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);//writes to internal storage 
			writer.write(array.toString());//json packaging
			
		}finally{
			if (writer !=null)
				writer.close();
		}
		
		
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
		    // We can read and write the media
		    mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		    // We can only read the media
		    mExternalStorageAvailable = true;
		    mExternalStorageWriteable = false;
		} else {
		    // Something else is wrong. It may be one of many other states, but all we need
		    //  to know is we can neither read nor write
		    mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	
	if(mExternalStorageWriteable){
	
		Writer mailWriter = null;
		try{
			//writes text file to external storage for mailing
			File root = new File(Environment.getExternalStorageDirectory(),"OutputLogItems.txt");
			OutputStream externalOut = new FileOutputStream(root);
			mailWriter = new OutputStreamWriter(externalOut);
			int logContainerCount = logItemContainers.size();
			mailWriter.write("Output \n\n");
			String timeString = new String("h:mmaa");
			String dateString = new String("MMMM dd, yyyy");
			Calendar timeCalendar = Calendar.getInstance();
			Calendar dateCalendar = Calendar.getInstance();
			for (int i = 0; i<logContainerCount; i++){
				OutputLogItemContainer newContainer = logItemContainers.get(i);
				Date dateDate = newContainer.getDateCreated();
				dateCalendar.setTime(dateDate);
				String date = (String)DateFormat.format(dateString, dateCalendar);
				mailWriter.write("Date:"  + date.toString()+"\n");
				ArrayList<OutputLogItem> logItemArray = newContainer.getLogItems();
				int logItemCount = logItemArray.size();
				for (int y = 0; y < logItemCount; y++){
					Date timeDate = logItemArray.get(y).getTimeDateCreated();
					timeCalendar.setTime(timeDate);
					String time = (String)DateFormat.format(timeString, timeCalendar);
					mailWriter.write(time.toString()+"  ");
					mailWriter.write(logItemArray.get(y).getTypeIntake().toString() + "  ");
					mailWriter.write(logItemArray.get(y).getAmountIntake().toString());
					mailWriter.write("\n");
				}
				mailWriter.write("\n\n");
			
			}
			} finally{
			if (mailWriter !=null)
				mailWriter.close();
		}
	}
}
}
