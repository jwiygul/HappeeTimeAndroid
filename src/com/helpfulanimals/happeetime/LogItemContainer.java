package com.helpfulanimals.happeetime;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LogItemContainer{
	
	private static final String logItems_JSON = "logItems";
	private static final String dateCreated_JSON ="date";
	private static final String fluidAward_JSON="fluidAward";
	private static final String fiberAward_JSON= "fiberAward";
	private static final String poopBadges_JSON ="poopBadges";
	private static final String peeBadges_JSON ="peeBadges";
	private static final String fiberBadges_JSON ="fiberBadges";
	private static final String fluidBadges_JSON="fluidBadges";
	private ArrayList<LogItem> logItems;
	private Date dateCreated;
	private boolean alreadySetFiberAward;
	private boolean alreadySetFluidAward;
	private int pendingPoopBadges;
	private int pendingPeeBadges;
	private int pendingFiberBadges;
	private int pendingFluidBadges;
	
	
	public LogItemContainer(){
		dateCreated = new Date();
		logItems = new ArrayList<LogItem>();
		alreadySetFiberAward = false;
		alreadySetFluidAward = false;
		pendingPoopBadges= 0;
		pendingPeeBadges= 0;
		pendingFiberBadges= 0;
		pendingFluidBadges= 0;
	}
	public LogItemContainer(JSONObject newLogItems) throws JSONException{
		JSONArray arrayOfItems = new JSONArray();
		JSONObject object = new JSONObject();
		logItems = new ArrayList<LogItem>();
			arrayOfItems = newLogItems.getJSONArray(logItems_JSON);
			for (int i = 0; i < arrayOfItems.length(); i++){
			object = arrayOfItems.getJSONObject(i);
			logItems.add(new LogItem(object));
	}
		
		alreadySetFiberAward = newLogItems.getBoolean(fiberAward_JSON);
		alreadySetFluidAward = newLogItems.getBoolean(fluidAward_JSON);
		pendingPoopBadges = newLogItems.getInt(poopBadges_JSON);
		pendingPeeBadges = newLogItems.getInt(peeBadges_JSON);
		pendingFiberBadges = newLogItems.getInt(fiberBadges_JSON);
		pendingFluidBadges = newLogItems.getInt(fluidBadges_JSON);
		dateCreated = new Date (newLogItems.getLong(dateCreated_JSON));
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		for (int i = 0; i < logItems.size(); i++){
			array.put(logItems.get(i).toJSON());
		}
		json.put(logItems_JSON, array);
		json.put(fiberAward_JSON, alreadySetFiberAward);
		json.put(fluidAward_JSON, alreadySetFluidAward);
		json.put(fiberBadges_JSON, pendingFiberBadges);
		json.put(fluidBadges_JSON, pendingFluidBadges);
		json.put(peeBadges_JSON, pendingPeeBadges);
		json.put(poopBadges_JSON, pendingPoopBadges);
		json.put(dateCreated_JSON, dateCreated.getTime());
		return json;
	}
	public ArrayList<LogItem> getLogItems() {
		return logItems;
	}
	public void setLogItems(ArrayList<LogItem> logItems) {
		this.logItems = logItems;
	}
	public void addItem(LogItem item) {
		logItems.add(item);
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isAlreadySetFiberAward() {
		return alreadySetFiberAward;
	}
	public void setAlreadySetFiberAward(boolean alreadySetFiberAward) {
		this.alreadySetFiberAward = alreadySetFiberAward;
	}
	public boolean isAlreadySetFluidAward() {
		return alreadySetFluidAward;
	}
	public void setAlreadySetFluidAward(boolean alreadySetFluidAward) {
		this.alreadySetFluidAward = alreadySetFluidAward;
	}
	public int getPendingPoopBadges() {
		return pendingPoopBadges;
	}
	public void setPendingPoopBadges(int pendingPoopBadges) {
		this.pendingPoopBadges = pendingPoopBadges;
	}
	public int getPendingPeeBadges() {
		return pendingPeeBadges;
	}
	public void setPendingPeeBadges(int pendingPeeBadges) {
		this.pendingPeeBadges = pendingPeeBadges;
	}
	public int getPendingFiberBadges() {
		return pendingFiberBadges;
	}
	public void setPendingFiberBadges(int pendingFiberBadges) {
		this.pendingFiberBadges = pendingFiberBadges;
	}
	public int getPendingFluidBadges() {
		return pendingFluidBadges;
	}
	public void setPendingFluidBadges(int pendingFluidBadges) {
		this.pendingFluidBadges = pendingFluidBadges;
	}
	


}
