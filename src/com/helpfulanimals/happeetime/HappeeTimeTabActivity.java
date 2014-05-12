package com.helpfulanimals.happeetime;



import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TabHost;
import android.widget.TabWidget;


@SuppressWarnings("deprecation")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class HappeeTimeTabActivity extends FragmentActivity implements TabListener, IntakePageFragment.Callbacks, OutputPageFragment.Callbacks, MenuPageFragment.Callbacks{
	private static final String TAG="HappeeTimeTabActivity";
	private ActionBar actionBar=null;
	private IntakePageFragment intakeFragment=null;
	private OutputPageFragment outputFragment=null;
	private MenuPageFragment menuFragment=null;
	private int tabPosition;
	public static final String EXTRA_POSITION="com.helpfulanimals.happeetime.logItemPosition";
	public static final String EXTRA_INFO_PAGE_POSITION = "com.helpfulanimals.happeetime.infoPagePosition";
	@Override
	public void onCreate(Bundle savedInstanceState){
	 super.onCreate(savedInstanceState);
	 	
	    setContentView(R.layout.activity_fragment);
	    actionBar=getActionBar();
	    actionBar.setDisplayShowHomeEnabled(true);
	    actionBar.setDisplayShowTitleEnabled(false);
	    actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM|ActionBar.DISPLAY_SHOW_HOME);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        actionBar.addTab(actionBar.newTab().setTag("first").setText("Home").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setTag("second").setText("Intake").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setTag("third").setText("Output").setTabListener(this));
        actionBar.addTab(actionBar.newTab().setTag("fourth").setText("Awards").setTabListener(this));
        menuFragment = new MenuPageFragment();
        intakeFragment = new IntakePageFragment();
        outputFragment = new OutputPageFragment();
        
        
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,menuFragment).commit();
       
        	
    
		
	   
	}
	
	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	}
	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		
		 if(tab.getPosition()==0)
	        {	
			 	if (menuFragment !=null)
	            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,menuFragment).commit();
	            
	        }
	        else if(tab.getPosition()==1)
	        {	
	            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,intakeFragment).commit();
	           
	        }
	        else if(tab.getPosition()==2)
	        {
	            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,outputFragment).commit();
	            
	        }
	        else if(tab.getPosition()==3){
	        	Intent i = new Intent(this, AwardsPagerActivity.class);
	        	startActivity(i);
	        }
			
	}
	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		tabPosition = tab.getPosition();
	}
	@Override
	public void onResume(){
		super.onResume();
		if (tabPosition!=3)
		getActionBar().setSelectedNavigationItem(tabPosition);
	}

	@Override
	public void onPause(){
		super.onPause();
		if(getActionBar().getSelectedTab().getPosition()!=3)
		tabPosition = getActionBar().getSelectedTab().getPosition();
	}
	@Override
	public void onItemAdded() {
		Intent intent = new Intent(this, IntakeDataEntryActivity.class);
		
		startActivity(intent);
	}

	@Override
	public void onImagetouch(int position) {
		Intent intent = new Intent(this, AwardInfoActivity.class);
		intent.putExtra(EXTRA_POSITION, position);
		startActivity(intent);
		
	}

	@Override
	public void onOutputItemAdded() {
		Intent intent = new Intent(this, OutputDataEntryActivity.class);
		startActivity(intent);
	}

	@Override
	public void onOutputImagetouch(int position) {
		Intent intent = new Intent(this, OutputAwardsInfoActivity.class);
		
		intent.putExtra(EXTRA_POSITION, position);
		startActivity(intent);
	}

	@Override
	public void onInfoPageSelected(int page) {
		Intent i = new Intent(this, GenericInfoPageActivity.class);
		i.putExtra(EXTRA_INFO_PAGE_POSITION, page);
		startActivity(i);
	}
}
