package com.ericsson.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DBManager {
	
	public static String DB_PATH = "";//ConfigManager.getProperty("db.path");
	public static String USER_TABLE = "";//DB_PATH + File.separator + "user.json";
	public static String GUESTS_TABLE = "";//DB_PATH + File.separator + "guests";
	public static String QRCODE_TABLE = DB_PATH ;//+ File.separator + "qrcodes";
	public static String USER_EVENTS_TABLE = DB_PATH ;//+ File.separator + "user_events";
	public static String USERID = "userId";
	public static String EVENTID = "eventId";
	public static String EVENTNAME = "eventName";
	public static String MOBILE = "mobileNum";
	public static String EMAIL = "email";
	public static String START_TIME = "startTime";
	public static String END_TIME = "endTime";
	
	
	@SuppressWarnings("unchecked")
	public void signUp(String inputSignUpObj){
	
		JSONObject userTable = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		try (Reader reader = new FileReader(USER_TABLE))
        {
            //Read JSON file
			userTable= (JSONObject) jsonParser.parse(reader);
			           
			//Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_TABLE)) {
 
        	JSONObject signUpObj = (JSONObject) jsonParser.parse(inputSignUpObj);
        	System.out.println("Adding data for : " + inputSignUpObj);
        	userTable.put(signUpObj.get("userId"), signUpObj);
            file.write(userTable.toJSONString());
            file.flush();
 
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
	}
	
	
	@SuppressWarnings("unchecked")
	public void createUserEvent(String inputUserEventObj){
		JSONParser jsonParser = new JSONParser();
		JSONObject userEventsTable = new JSONObject();
		
		try (Reader reader = new FileReader(USER_EVENTS_TABLE))
        {
            //Read JSON file
			userEventsTable= (JSONObject) jsonParser.parse(reader);
			           
			//Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_EVENTS_TABLE)) {
 
        	JSONObject userEventsObj = (JSONObject) jsonParser.parse(inputUserEventObj);
        	
        	JSONArray jsonArray = new JSONArray();
        	if (userEventsTable.containsKey(userEventsObj.get("userId"))){
        		jsonArray = (JSONArray)userEventsTable.get(userEventsObj.get("userId"));
        	}
        	jsonArray.add(userEventsObj);
        	userEventsTable.put(userEventsObj.get("userId"), jsonArray);
        	file.write(userEventsTable.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String searchUser(String userId){
		
		JSONObject userObj = new JSONObject();
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(USER_TABLE))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject allUserObj = (JSONObject) obj;
            userObj = (JSONObject) allUserObj.get(userId);
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == userObj || userObj.isEmpty()){
			userObj = new JSONObject();
		}
		return userObj.toString();
		
	}
	
	public String searchUserEvent(String userId){
		
		JSONArray jsonArray = new JSONArray();
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(USER_EVENTS_TABLE))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject allUserObj = (JSONObject) obj;
            jsonArray = (JSONArray) allUserObj.get(userId);
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (null == jsonArray || jsonArray.isEmpty()){
			jsonArray = new JSONArray();
		}
		return jsonArray.toString();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public String searchEventGuests(String eventId){
		
		JSONArray userArray = new JSONArray();
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(GUESTS_TABLE + "_" + eventId))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject allUserObj = (JSONObject) obj;
            @SuppressWarnings("unchecked")
			Set<String> guests = allUserObj.keySet();
            
            for (String guestId : guests){
            	userArray.add(allUserObj.get(guestId));
            }
            
            /*userArray = (JSONArray) allUserObj.get(eventId);
            if (null == userArray || userArray.isEmpty()){
            	userArray = new JSONArray();
            }*/
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return userArray.toString();
		
	}
	
	@SuppressWarnings("unchecked")
	public void addGuests(String inputGuestsObj){
		JSONParser jsonParser = new JSONParser();
		String eventId = null;
		String guestId = "G-" + KeychainUtils.getAlphaNumericString();
		
		JSONObject eventAttendeeObj = null;
		try {
			eventAttendeeObj = (JSONObject) jsonParser.parse(inputGuestsObj);
			if (null != eventAttendeeObj && !eventAttendeeObj.isEmpty()){
				eventId = (String) eventAttendeeObj.get("eventId");
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JSONObject eventAttendeeTable = new JSONObject();
		try (Reader reader = new FileReader(GUESTS_TABLE + "_" + eventId))
        {
		    //Read JSON file
			eventAttendeeTable = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(GUESTS_TABLE + "_" + eventId)) {
        	eventAttendeeObj.put("guestId", guestId);
        	eventAttendeeTable.put(guestId, eventAttendeeObj);
        	file.write(eventAttendeeTable.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	
	@SuppressWarnings("unchecked")
	public void deleteGuests(String guestId,String eventId){
		JSONParser jsonParser = new JSONParser();
		JSONObject eventAttendeeTable = new JSONObject();
		try (Reader reader = new FileReader(GUESTS_TABLE + "_" + eventId))
        {
		    //Read JSON file
			eventAttendeeTable = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(GUESTS_TABLE + "_" + eventId)) {
        	
        	eventAttendeeTable.remove(guestId);
        	file.write(eventAttendeeTable.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } /*catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	@SuppressWarnings("unchecked")
	public void addQrCode(String inputQrCodeObj){
		JSONParser jsonParser = new JSONParser();
		
		JSONObject allUserObj = new JSONObject(); 
		try (FileReader reader = new FileReader(QRCODE_TABLE))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            allUserObj = (JSONObject) obj;
            
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(QRCODE_TABLE)) {
 
        	JSONObject eventAttendeeObj = (JSONObject) jsonParser.parse(inputQrCodeObj);
        	
        	allUserObj.put(eventAttendeeObj.get("qrCodeId"), eventAttendeeObj);
        	file.write(allUserObj.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String searchqrCode(String qrCodeId){
		
		JSONObject userObj = new JSONObject();
		
		JSONParser jsonParser = new JSONParser();
        
        try (FileReader reader = new FileReader(QRCODE_TABLE))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONObject allUserObj = (JSONObject) obj;
            userObj = (JSONObject) allUserObj.get(qrCodeId);
             
            //Iterate over employee array
            //employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (null == userObj || userObj.isEmpty()){
        	userObj = new JSONObject();
        }
		
		return userObj.toString();
		
	}
	
	public void deleteQrCode(String qrCodeId){
		
		JSONParser jsonParser = new JSONParser();
		JSONObject allUserObj = new JSONObject();
        try (FileReader reader = new FileReader(QRCODE_TABLE))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            allUserObj = (JSONObject) obj;
            
            if (allUserObj.containsKey(qrCodeId)){
            	allUserObj.remove(qrCodeId);
            }
             
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try (FileWriter file = new FileWriter(QRCODE_TABLE)) {

			file.write(allUserObj.toString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	
	/*@SuppressWarnings("unchecked")
	public void createQR(String qrCodeObj){
		JSONParser jsonParser = new JSONParser();
		//Write JSON file
        try (FileWriter file = new FileWriter(QRCODE_TABLE)) {
 
        	JSONObject qrCodeTable = (JSONObject) jsonParser.parse(qrCodeObj);
        	userEventsTable.put(qrCodeTable.get("qrCodeId"), qrCodeTable);
            file.write(userEventsTable.toString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

}
