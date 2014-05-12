package com.helpfulanimals.happeetime;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import com.helpfulanimals.happeetime.IntakePageFragment.Callbacks;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MenuPageFragment extends Fragment{
	private Button mailButton, infoButton, intakeInfoButton, outputInfoButton, awardsInfoButton;
	
 private static final String TAG ="MenuPageFragment";
 	private Callbacks mCallbacks;
 	
 	public interface Callbacks{
 		void onInfoPageSelected(int page);
 	}
 	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		mCallbacks=(Callbacks)activity;
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		ActionBar bar = getActivity().getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		
}
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.activity_menu_page, parent, false);
		mailButton = (Button)v.findViewById(R.id.mail_button);
		mailButton.setBackgroundColor(Color.TRANSPARENT);
		mailButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent emailIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
				emailIntent.setType("application/octet-stream");
		        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
				ArrayList<Uri> uris= new ArrayList<Uri>();
				uris.add(Uri.parse("file://" + 
				Environment.getExternalStorageDirectory().getAbsolutePath() + "/LogItems.txt"));
				uris.add(Uri.parse("file://" + 
						Environment.getExternalStorageDirectory().getAbsolutePath() + "/OutputLogItems.txt"));
				
				emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "HapPee Time Info");
				startActivity(emailIntent);
			}
		});
		infoButton = (Button)v.findViewById(R.id.info_button);
		infoButton.setBackgroundColor(Color.TRANSPARENT);
		infoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCallbacks.onInfoPageSelected(1);
			}
		});
		intakeInfoButton = (Button)v.findViewById(R.id.intake_info_button);
		intakeInfoButton.setBackgroundColor(Color.TRANSPARENT);
		intakeInfoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCallbacks.onInfoPageSelected(2);
			}
		});
		outputInfoButton = (Button)v.findViewById(R.id.output_info_button);
		outputInfoButton.setBackgroundColor(Color.TRANSPARENT);
		outputInfoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCallbacks.onInfoPageSelected(3);
			}
		});
		awardsInfoButton = (Button)v.findViewById(R.id.awards_info_button);
		awardsInfoButton.setBackgroundColor(Color.TRANSPARENT);
		awardsInfoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCallbacks.onInfoPageSelected(4);
			}
		});
		
		return v;
	}
	@Override
	public void onResume(){
		super.onResume();
		
	}
}
