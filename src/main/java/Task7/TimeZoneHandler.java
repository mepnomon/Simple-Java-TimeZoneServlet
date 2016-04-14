package Task7;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * ICP-2052 - Mini Project 4: Java Servlets
 * Class: TimeZoneHandler
 * Created: 2016-03-12
 * 
 * @author Dorian Dressler (eeu436), James Ashford (eeu48c)
 */
public class TimeZoneHandler {
    
    private DateFormat timeFormatter;
    private  String city;
    private TimeZone zone;
    //private String ids[];
    
    
    /**
     * Constructor for TimeZone
     */
    public TimeZoneHandler(){
        
        timeFormatter = DateFormat.getTimeInstance();
        //ids = TimeZone.getAvailableIDs();
    }
    
    /**
     * Sets the city name.
     * @param aCity 
     */
    public void setCity(String aCity){
        city = aCity;
    }
    
    /**
     * Gets the city variable
     * @return the name of the city
     */
    public String getCity(){
        return city;
    }
    
    /**
     * Gets current time.
     * @return the formatted time
     */
    public String getTime(){
        checkCity();
        if(zone == null) return "not available";
        timeFormatter.setTimeZone(zone);
        Date time = new Date();
        String timeString = timeFormatter.format(time);
        return timeString;
    }
    
    /**
     * Checks if city's time zone is inside
     * @return 
     */
    private String checkCity(){
        
        zone = getTimeZone(city);
        if(zone == null) return "error";
        return "next";
    }
    
    /**
     * Parses all time zones and calls a method that checks if  the city exists.
     * @param aCity the chosen city
     * @return the time zone of the selected city.
     */
    public static TimeZone getTimeZone(String aCity){
        
        String[] ids = TimeZone.getAvailableIDs();
        System.out.println("ID's");
        
        for(int i = 0; i < ids.length; i++) {
            if(timeZoneIDMatch(ids[i], aCity)) {
                return TimeZone.getTimeZone(ids[i]);
            }            
        }
        return null;
    }
    
    /**
     * Checks whether the user's input matches one of the time IDs
     * @param id the time ID
     * @param aCity the selected city
     * @return a boolean
     */
    private static boolean timeZoneIDMatch(String id, String aCity){
        aCity = aCity.replace(" ", "_");
        
        if (id.contains("/")) {
            String[] parts = id.split("/"); //splits the strings in class
            
            //sets everything to uppercase and compares 2nd half of string to user entry
            if (parts[1].toUpperCase().equals(aCity.toUpperCase())) {
                return true;
            }
        } else {
            if (id.toUpperCase().equals(aCity.toUpperCase())) {
                return true;
            }
        }
        return false;
    }
}
