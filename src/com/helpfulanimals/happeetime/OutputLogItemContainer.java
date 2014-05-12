package com.helpfulanimals.happeetime;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OutputLogItemContainer {
	private static final String logItems_JSON = "logItems";
	private static final String dateCreated_JSON ="date";
	private static final String poopAward_JSON="poopAward";
	private static final String peeAward_JSON= "peeAward";
	private static final String peeAccident_JSON="peeAccident";
	private static final String didPoop_JSON="didPoop";
	
	private ArrayList<OutputLogItem> logItems;
	private Date dateCreated;
	private boolean alreadySetPoopAward;
	private boolean alreadySetPeeAward;
	private boolean didPoop;
	private boolean hadPeeAccident;
	
	
	public OutputLogItemContainer(){
		dateCreated = new Date();
		logItems = new ArrayList<OutputLogItem>();
		alreadySetPoopAward = false;
		alreadySetPeeAward = false;
		didPoop=false;
		hadPeeAccident=false;
		
	}
	public OutputLogItemContainer(JSONObject newLogItems) throws JSONException{
		JSONArray arrayOfItems = new JSONArray();
		JSONObject object = new JSONObject();
		logItems = new ArrayList<OutputLogItem>();
			arrayOfItems = newLogItems.getJSONArray(logItems_JSON);
			for (int i = 0; i < arrayOfItems.length(); i++){
			object = arrayOfItems.getJSONObject(i);
			logItems.add(new OutputLogItem(object));
	}
		alreadySetPoopAward = newLogItems.getBoolean(poopAward_JSON);
		alreadySetPeeAward = newLogItems.getBoolean(peeAward_JSON);
		didPoop = newLogItems.getBoolean(didPoop_JSON);
		hadPeeAccident = newLogItems.getBoolean(peeAccident_JSON);
		dateCreated = new Date (newLogItems.getLong(dateCreated_JSON));
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < logItems.size(); i++){
			array.put(logItems.get(i).toJSON());
		}
		json.put(logItems_JSON, array);
		json.put(poopAward_JSON, alreadySetPoopAward);
		json.put(peeAward_JSON, alreadySetPeeAward);
		json.put(dateCreated_JSON, dateCreated.getTime());
		json.put(peeAccident_JSON, hadPeeAccident);
		json.put(didPoop_JSON, didPoop);
		return json;
	}
	public ArrayList<OutputLogItem> getLogItems() {
		return logItems;
	}
	public void setLogItems(ArrayList<OutputLogItem> logItems) {
		this.logItems = logItems;
	}
	public void addItem(OutputLogItem item) {
		logItems.add(item);
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isAlreadySetPeeAward() {
		return alreadySetPeeAward;
	}
	public void setAlreadySetPeeAward(boolean alreadySetPeeAward) {
		this.alreadySetPeeAward = alreadySetPeeAward;
	}
	public boolean isAlreadySetPoopAward() {
		return alreadySetPoopAward;
	}
	public void setAlreadySetPoopAward(boolean alreadySetPoopAward) {
		this.alreadySetPoopAward = alreadySetPoopAward;
	}
	public void setHadPeeAccident(boolean peeAccident){
		hadPeeAccident = peeAccident;
	}
	public boolean hadPeeAccident(){
		return hadPeeAccident;
	}
	public void setDidPoop(boolean didPoopToday){
		didPoop = didPoopToday;
	}
	public boolean getDidPoop(){
		return didPoop;
	}
}
