package com.helpfulanimals.happeetime;





import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class IntakePageActivity extends FragmentActivity {

	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState){
		 //Used for theme switching in samples
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.intake_activity);
	        FragmentManager fm = getSupportFragmentManager();
	       
	        FragmentTransaction transaction = fm.beginTransaction();
	        IntakePageFragment fragment = new IntakePageFragment();
	        transaction.add(R.id.fragmentContainer,fragment);
	        transaction.commit();
	}
   
	
}
