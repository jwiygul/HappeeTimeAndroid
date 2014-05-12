package com.helpfulanimals.happeetime;

import java.util.Date;



import org.json.JSONException;
import org.json.JSONObject;



public class LogItem extends JSONObject{
	private static final String amountIntake_JSON ="amount";
	private static final String typeIntake_JSON = "type";
	private static final String whatIntake_JSON = "what";
	private static final String timeDateCreated_JSON = "timeDate";
	private static final String infoType_JSON = "infoType";
	private int amountIntake;
	private String typeIntake;
	private String whatIntake;
	private Date timeDateCreated;
	private int infoType;
	
	public LogItem(){
		amountIntake = 0;
		typeIntake= "";
		whatIntake = "";
		timeDateCreated = new Date();
		infoType= 0;
	}
	
public LogItem(JSONObject json) throws JSONException{
		if(json.has(amountIntake_JSON))
		amountIntake=json.getInt(amountIntake_JSON);
		if (json.has(typeIntake_JSON))
		typeIntake = json.getString(typeIntake_JSON);
		if(json.has(whatIntake_JSON))
		whatIntake = json.getString(whatIntake_JSON);
		if (json.has(timeDateCreated_JSON))
		timeDateCreated = new Date(json.getLong(timeDateCreated_JSON));
		if (json.has(infoType_JSON))
			infoType=json.getInt(infoType_JSON);
		
	}
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		json.put(amountIntake_JSON, amountIntake);
		json.put(typeIntake_JSON,typeIntake);
		json.put(whatIntake_JSON, whatIntake);
		json.put(timeDateCreated_JSON, timeDateCreated.getTime());
		json.put(infoType_JSON, infoType);
		return json;
	}
	public int getAmountIntake() {
		return amountIntake;
	}
	public void setAmountIntake(int amountIntake) {
		this.amountIntake = amountIntake;
	}
	public String getTypeIntake() {
		return typeIntake;
	}
	public void setTypeIntake(String typeIntake) {
		this.typeIntake = typeIntake;
	}
	public String getWhatIntake() {
		return whatIntake;
	}
	public void setWhatIntake(String whatIntake) {
		this.whatIntake = whatIntake;
	}
	public Date getTimeDateCreated() {
		return timeDateCreated;
	}
	public void setTimeDateCreated(Date timeDateCreated) {
		this.timeDateCreated = timeDateCreated;
	}
	public int getInfoType() {
		return infoType;
	}
	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}
	public static String getWhatIntakeJSON(){
		return whatIntake_JSON;
	}

}
