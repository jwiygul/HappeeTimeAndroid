package com.helpfulanimals.happeetime;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class OutputLogItem extends JSONObject{
	private static final String amountIntake_JSON ="amount";
	private static final String typeIntake_JSON = "type";
	private static final String timeDateCreated_JSON = "timeDate";
	private static final String infoType_JSON = "infoType";
	private String amountIntake;
	private String typeIntake;
	private Date timeDateCreated;
	private int infoType;
	
	public OutputLogItem(){
		amountIntake = "";
		typeIntake= "";
		
		timeDateCreated = new Date();
		infoType= 0;
	}
	
public OutputLogItem(JSONObject json) throws JSONException{
		if(json.has(amountIntake_JSON))
		amountIntake=json.getString(amountIntake_JSON);
		if (json.has(typeIntake_JSON))
		typeIntake = json.getString(typeIntake_JSON);
		
		if (json.has(timeDateCreated_JSON))
		timeDateCreated = new Date(json.getLong(timeDateCreated_JSON));
		if (json.has(infoType_JSON))
			infoType=json.getInt(infoType_JSON);
		
	}
	public JSONObject toJSON() throws JSONException{
		JSONObject json = new JSONObject();
		json.put(amountIntake_JSON, amountIntake);
		json.put(typeIntake_JSON,typeIntake);
		
		json.put(timeDateCreated_JSON, timeDateCreated.getTime());
		json.put(infoType_JSON, infoType);
		return json;
	}
	public String getAmountIntake() {
		return amountIntake;
	}
	public void setAmountIntake(String amountIntake) {
		this.amountIntake = amountIntake;
	}
	public String getTypeIntake() {
		return typeIntake;
	}
	public void setTypeIntake(String typeIntake) {
		this.typeIntake = typeIntake;
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

}
