package com.helpfulanimals.happeetime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class GenericInfoPageFragment extends Fragment {
	 private int position;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		setHasOptionsMenu(false);
		setRetainInstance(false);

	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
		View v = inflater.inflate(R.layout.award_info_fragment, parent, false);
		ScrollView sv = (ScrollView)v.findViewById(R.id.ScrollView);
		//TextView tv =(TextView)v.findViewById(R.id.TextView);
		TextView tv = new TextView(getActivity());
		tv.setPadding(10, 10, 10, 10);
		tv.setTextSize(16);
		sv.addView(tv);
		//tv.setMovementMethod(new ScrollingMovementMethod());
		switch(position){
		case 1:
		tv.setText("Welcome to HapPee Time!\n\nThis application is designed to help young patients record their eating and bathroom " +
				"behaviors, so their doctors can help them help themselves. It’s easy to send the recorded information: tap the " +
				"Envelope icon on the Home screen, enter the address of the person you want to send to, tap Send, and you are " +
				"done (you do have to have a default email set up on your device for this to work). With this application, you and " +
				"your childcan focus on good bathroom habits, as well as learn about the foods and behaviors that can lead to " +
				"problems with going to the bathroom. \n\n" +
				"There are three activities in the HapPee Time application. \n" +
				"1) Intake, where your child records all the things that go into their body. \n" +
				"2) Output, where your child records what comes out of their body.\n" +
				"3) Awards, where badges for good bathroom habits are recorded. \n\n" +
				"Touch any of the associated icons on the Home screen, and you’ll get a more in-depth explanation of each. You " +
				"enter each activity by tapping the icon in the Tab bar at the bottom of the screen.  " +
				"We hope you find our application helpful, and encourage any feedback (good or bad!) that you may have. It is " +
				"designed for children 8–10 years old, but older and younger children can use HapPee Time. Because this " +
				"application encourages certain types of behavior, it should only be used under the guidance of a physician. \n\n" +
				"DISCLAIMER: This application is not intended as a substitute for treatment from a physician for any medical " +
				"condition. It should be used only in conjunction with consultation with a health care provider. This application is " +
				"provided “as is” and no warranty is expressed or implied. It is designed for children aged 8–10 years, including the " +
				"award goals, and use of the application by users not within that age range should be considered only after " +
				"instruction from a health care provider. This application is intended to incent young children to record dietary and " +
				"elimination behaviors.");
		break;
		case 2:
			tv.setText("You’ve selected the Intake icon. \n\n" +
					"This activity is where you or your child record everything they eat and drink. You enter Intake by tapping the " +
					"associated tab in the Tab bar (at the top of the screen). You record things by type: Food, Drink, and Fiber. After " +
					"you’ve identified the type, you enter what your child took in (chicken? water? tea?). Try to record everything, even " +
					"if it means multiple entries at the same time. Your doctor will want to know how much your child took in, " +
					"specifically how much fluid they are drinking. \n\n" +
					"Each entry is automatically time stamped in the log. This means that the time of day that you enter a record will be " +
					"time entered in the log. You can select the time too if you want to enter something eaten earlier in the day. " +
					"Fiber is a separate category because it’s so important for pooping regularly. Why is it important? Learn why by " +
					"tapping the Awards icon. \n\n" +
					"Your child can win two types of awards with their entries - The RockBreaker and the Thumbs Up. When your child wins an award, an image pops up, representing what they’ve won. When they tap it, " +
					"they’ll get an explanation of why they won and the encouragement to keep going! Only one of each type can be " +
					"won each day, so eating twice the amount of fiber or drinking twice the amount of fluid will give them a tummy " +
					"ache, but no more awards!");
			break;
		case 3:
tv.setText("You’ve selected the Output icon. \n\n" +
		"This activity is where you and your child record every time they go to the bathroom. You start Output by tapping " +
		"the associated tab in the Tab bar (at the top of the screen). There are four actions that should be recorded: " +
		"when your child pooped, when they peed, if they wet their pants (or the bed), and if they pooped in their pants. " +
		"We know— it’s a little embarrassing, but it’s really important for the doctor or nurse taking care of your child to " +
		"know what is happening at home. Plus, as things get better for your child (and they will!) and there are fewer " +
		"accidents or more days pooping, they will earn awards that will keep them going! \n\n" +
		"Keep in mind that each entry is time stamped, which means when you enter it will be the time recorded in the log. " +
		"However, if you want to enter something that happened earlier in the day, you can select the time too. \n\n" +
		"Your child can win two awards based their entries in Output (but only one award each per day) - The Toilet Flush " +
		"and Mr. Consistency. When your child wins an award, an image pops up, representing what they’ve won. When " +
		"they tap it, they’ll get an explanation of why they won and the encouragement to keep going!");
	break;
case 4:
		tv.setText("You’ve selected the Awards icon. \n\n" +
				"The Awards activity is where your child can see their awards, representing all the great things they have done. You " +
				"start Awards by tapping the associated tab in the Tab bar (at the top of the screen). We believe that if your " +
				"child does what their doctor asked, they should win awards – and who doesn’t like awards in recognition of hard " +
				"work! \n\nHere are the four awards: \nTHE ROCKBREAKER \nThis is awarded when your child eats more than 25 grams of fiber a day. Eating fiber is important. It helps push all " +
				"the other food your child eats through their body, so they don’t get constipated. What’s constipation? That’s when" +
				"your child doesn’t poop enough, and the poop builds up inside. This can make them feel bad. It can also make it " +
				"hard to know when to pee, when to poop, and well, you probably get the picture.\n\n" +
				"THE THUMBS UP	\n" +
				"This is awarded when your child drinks more than 40 ounces of fluid in a day. If fiber is really important to keep	" +
				"constipation away, fluid is SUPER important. Almost everyone who has pooping (and peeing) problems doesn’t	" +
				"drink enough fluid. We know—it doesn’t make sense. But not drinking enough fluid makes the poop hard, and		" +
				"when it’s hard, it doesn’t come out easily. Heard enough? Well, encourage your child to keep drinking!		\n\n" +
				"THE TOILET FLUSH \n" +
				"This is awarded when your child goes at least 3 days without a wetting accident. For many children using this " +
				"application, this is the whole reason they are doing this in the first place. So they may focus on this award. But" +
				"don’t forget the others! Your child gets better by doing everything better. \n\n" +
				"MR.(or MS) CONSISTENCY \nThis is awarded when your child poops at least 4 days in a row. \n\nRemember, the whole point of the application is to help people go to the bathroom better, so encourage your child" +
				"to focus on the recording (although winning is good – and fun!). They’ll be able to keep count of the awards they’ve won when they visit the Awards activity. They’ll know what they’re doing well, and what they need to" +
				" work on. We hope everyone finds our application helpful, and we wish everyone all the awards in the world!");
				break;
				default:
					break;
		}
		return v;
	}
	public static GenericInfoPageFragment newInstance(int position){
		GenericInfoPageFragment fragment = new GenericInfoPageFragment();
		fragment.position = position;
		return fragment;
	}
}
