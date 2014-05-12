package com.helpfulanimals.happeetime;







import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;





import android.annotation.TargetApi;
import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;


import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.AdapterContextMenuInfo;


public class IntakePageFragment extends ListFragment{
	public static final String TAG = "IntakePageFragment";
	private ArrayList<LogItem> entries;
	private TextView dateText;
	private Callbacks mCallbacks;
	private LogItemContainer logContainer;
	private static int counter = 1;
	private MenuItem goForward;
	private MenuItem goBack;
	private MenuItem addItem;
	private ListView listView;
	
	
	public interface Callbacks{
		 void onItemAdded(); 
		 void onImagetouch(int position);
	}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
	{
		View v = super.onCreateView(inflater, parent, savedInstanceState);
		v.setBackgroundResource(R.drawable.background_blue);
		listView = (ListView)v.findViewById(android.R.id.list);
		listView.setPadding(10, 10, 10, 10);
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
			registerForContextMenu(listView);
		else//allows for contextual action bar on later OS versions
		{
			listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			listView.setMultiChoiceModeListener(new MultiChoiceModeListener(){
			public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked){
				
			}
			public boolean onCreateActionMode(ActionMode mode, Menu menu){
				if (!addItem.isEnabled())
					return true;
				MenuInflater inflater = mode.getMenuInflater();//notice inflater not retrieved from activity
				inflater.inflate(R.menu.item_list_context, menu);
				return true;
			}
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()){
				case R.id.menu_item_delete:
					EntryAdapter adapter = (EntryAdapter)getListAdapter();
					LogItem logItem= new LogItem();
					
					for (int i = adapter.getCount()-1; i>=0; i--){
						if (getListView().isItemChecked(i)){
							logItem = adapter.getItem(i);
							break;
						}
					}
					LogItemStore.sharedStore(getActivity()).removeItem(logItem);
					int i =0;
					if (logItem.getInfoType()==1&& logContainer.isAlreadySetFiberAward()&&(logItem.getTypeIntake().compareTo("Fiber")==0)){
						for (i = logContainer.getLogItems().size()-1; i>0; i--){
							LogItem compareI = logContainer.getLogItems().get(i);
							if (compareI.getTypeIntake().compareTo("Fiber")==0)
								break;
						}
						logContainer.getLogItems().get(i).setInfoType(1);
					}
					else if (logItem.getInfoType()==2&&logContainer.isAlreadySetFluidAward()&&(logItem.getTypeIntake().compareTo("Drink")==0)){
						for (i=logContainer.getLogItems().size()-1; i>0;i--){
							LogItem compareI=logContainer.getLogItems().get(i);
							if(compareI.getTypeIntake().compareTo("Drink")==0)
								break;
						}
						logContainer.getLogItems().get(i).setInfoType(2);
					}
					
					mode.finish();
					return true;
				default: 
					return false;
				}	
			}
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				
			}
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
		});
		}
		
		return v;
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_page, menu);
		Date date = new Date();
		if (LogItemStore.sharedStore(getActivity()).getLogItemContainer().getDateCreated()!=null)
		{
			date = LogItemStore.sharedStore(getActivity()).getLogItemContainer().getDateCreated();
			dateText = (TextView)menu.findItem(R.id.menu_date).getActionView();
			goForward = (MenuItem)menu.findItem(R.id.menu_item_forward);
			goForward.setTitle("FORWARD");
			goBack = (MenuItem)menu.findItem(R.id.menu_item_back);
			goBack.setTitle("BACK");
			addItem = (MenuItem)menu.findItem(R.id.menu_item_new_entry);
			addItem.setTitle("ADD");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String dateString = new String("MMM dd, yyyy");
			String currentDate = (String) DateFormat.format(dateString, calendar);
			dateText.setText(currentDate);
			dateText.setTextSize(20);
			
			Date otherD = LogItemStore.sharedStore(getActivity()).getLogItemContainer().getDateCreated();
			Calendar newCalendar = Calendar.getInstance();
			Calendar otherC = Calendar.getInstance();
			newCalendar.setTime(new Date());
			otherC.setTime(otherD);
			String myDate = (String) DateFormat.format(dateString, newCalendar);
			String otherDate =(String)DateFormat.format(dateString, otherC);
			if (myDate.compareTo(otherDate)!=0)
				{
					addItem.setEnabled(false);
					goForward.setEnabled(true);
				}
			else
			{
				addItem.setEnabled(true);
				goForward.setEnabled(false);
			}
			}
			else
				goForward.setEnabled(false);
		
		
		
	}
	
	@TargetApi(11)
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.menu_item_new_entry:
			mCallbacks.onItemAdded();
			return true;
		
		case R.id.menu_item_back:
			goBack();
			if (!goForward.isEnabled())
				goForward.setEnabled(true);
			if(addItem.isEnabled()){
				addItem.setEnabled(false);
				listView.setChoiceMode(ListView.INVISIBLE);
			}
			return true;
		case R.id.menu_item_forward:
			boolean turnoff = goForward();
			if(!turnoff){
				item.setEnabled(false);
				addItem.setEnabled(true);
				listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
			}
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		getActivity().getMenuInflater().inflate(R.menu.item_list_context, menu);
	}

	private class EntryAdapter extends ArrayAdapter<LogItem>{
		public EntryAdapter(ArrayList<LogItem> entries){
			super(getActivity(),0,entries);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			final int logItemPosition = position;
			if (convertView == null)
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_items, null);
			LogItem c= getItem(position);
			ImageView image = (ImageView)convertView.findViewById(R.id.imageView);
			image.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mCallbacks.onImagetouch(logItemPosition);
				}
			});
			TextView timeView = (TextView)convertView.findViewById(R.id.list_item_timeView);
			Date date = new Date();
			if (c.getTimeDateCreated()!=null)
				 date = (Date)c.getTimeDateCreated();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			String dateString = new String("h:mmaa");
			String currentDate = (String) DateFormat.format(dateString, calendar);
			timeView.setText(currentDate);
			TextView typeView = (TextView)convertView.findViewById(R.id.list_item_typeView);
			typeView.setText(c.getTypeIntake());
			TextView whatView = (TextView)convertView.findViewById(R.id.list_item_whatView);
			whatView.setText(c.getWhatIntake());
			TextView amountView = (TextView)convertView.findViewById(R.id.list_item_amountView);
			if(Integer.valueOf(c.getAmountIntake()).toString().compareTo("0")!=0){
				if(c.getTypeIntake().toString().compareTo("Fiber")==0)
					amountView.setText(Integer.valueOf(c.getAmountIntake()).toString() + " gms");
				else if(c.getTypeIntake().toString().compareTo("Drink")==0)
					amountView.setText(Integer.valueOf(c.getAmountIntake()).toString() + " oz");
			}else
			amountView.setText("");
			if(c.getInfoType()==1&&logContainer.isAlreadySetFiberAward()){
				Drawable icon = getResources().getDrawable(R.drawable.explosion_phone);
				image.setVisibility(View.VISIBLE);
				image.setImageDrawable(icon);
			}
			else if(c.getInfoType()==2&& logContainer.isAlreadySetFluidAward()){
				Drawable icon = getResources().getDrawable(R.drawable.thumbs_up);
				image.setVisibility(View.VISIBLE);
				image.setImageDrawable(icon);
			}
			else{
				image.setVisibility(View.INVISIBLE);
				c.setInfoType(0);
			}
			convertView.setBackgroundColor(Color.WHITE);
			
			return convertView;
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		logContainer = LogItemStore.sharedStore(getActivity()).getLogItemContainer();
		//equivalent to setting delegate of listarray
		entries = LogItemStore.sharedStore(getActivity()).getLogItemContainer().getLogItems();
		EntryAdapter adapter = new EntryAdapter(entries);
		setListAdapter(adapter);
		setRetainInstance(true);
	}
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mCallbacks=(Callbacks)activity;
	}
	@Override
	public void onResume(){
		super.onResume();
		((EntryAdapter)getListAdapter()).notifyDataSetChanged();
		Date date = new Date();
		
		Date otherD = LogItemStore.sharedStore(getActivity()).mostRecentLogContainer().getDateCreated();
		Calendar calendar = Calendar.getInstance();
		Calendar otherC = Calendar.getInstance();
		calendar.setTime(date);
		otherC.setTime(otherD);
		String dateString = new String("MMM dd, yyyy");
		String currentDate = (String) DateFormat.format(dateString, calendar);
		String otherDate =(String)DateFormat.format(dateString, otherC);
		
		if (currentDate.compareTo(otherDate)!=0)
		{
			Log.d(TAG, "getting through");
			LogItemStore.sharedStore(getActivity()).createNewLogItemContainer();
			//equivalent to setting delegate of listarray
			entries = LogItemStore.sharedStore(getActivity()).getLogItemContainer().getLogItems();
			EntryAdapter adapter = new EntryAdapter(entries);
			setListAdapter(adapter);
			dateText.setText(currentDate);
			counter = 1;
		}
	}
	@Override 
	public void onPause(){
		super.onPause();
		LogItemStore.sharedStore(getActivity()).saveItems();
	}
	private void goBack(){
		counter++;
	
		logContainer= LogItemStore.sharedStore(getActivity()).goBack(counter);
		if (logContainer !=null){
		entries = logContainer.getLogItems();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(logContainer.getDateCreated());
		String dateString = new String("MMM dd, yyyy");
		String currentDate = (String) DateFormat.format(dateString, calendar);
		dateText.setText(currentDate);
		
		EntryAdapter adapter = new EntryAdapter(entries);
		setListAdapter(adapter);
		}
		else
			counter--;
		
	}
	private boolean goForward(){
		counter--;
		logContainer= LogItemStore.sharedStore(getActivity()).goForward(counter);
		Calendar calendar = Calendar.getInstance();
		Calendar otherCal = Calendar.getInstance();
		calendar.setTime(logContainer.getDateCreated());
		String dateString = new String("MMM dd, yyyy");
		Date date = new Date();
		otherCal.setTime(date);
		String currentDate = (String) DateFormat.format(dateString, calendar);
		String logDate = (String)DateFormat.format(dateString, otherCal);
		
		entries = logContainer.getLogItems();
		EntryAdapter adapter = new EntryAdapter(entries);
		setListAdapter(adapter);
		dateText.setText(currentDate);
		if (currentDate.compareTo(logDate)==0){ 
			  return false;
			}
		return true;
		
	}
}