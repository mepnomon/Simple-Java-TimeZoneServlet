package Task7;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author Dorian Dressler
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
    
    public void setCity(String aCity){
        city = aCity;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getTime(){
        checkCity();
        if(zone == null) return "not available";
        timeFormatter.setTimeZone(zone);
        Date time = new Date();
        String timeString = timeFormatter.format(time);
        return timeString;
    }
    
    private String checkCity(){
        
        zone = getTimeZone(city);
        if(zone == null) return "error";
        return "next";
    }
    
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
    
    private static boolean timeZoneIDMatch(String id, String aCity){
        aCity = aCity.replace(" ", "_");
        
        if (id.contains("/")) {
            String[] parts = id.split("/");
            if (parts[1].toUpperCase().equals(aCity.toUpperCase())) {
                return true;
            }
        } else {
            if (id.toUpperCase().equals(aCity.toUpperCase())) {
                return true;
            }
        }
        /*
        String idCity = id.substring(id.indexOf('/') + 1);
        return idCity.replace('_', ' ').equals(aCity);
*/
        return false;
    }
}
