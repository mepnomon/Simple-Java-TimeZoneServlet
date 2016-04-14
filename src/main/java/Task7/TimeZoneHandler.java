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
    private String ids[];
    
    
    /**
     * Constructor for TimeZone
     */
    public TimeZoneHandler(String city){
        
        this.city = city;
        timeFormatter = DateFormat.getTimeInstance();
        ids = TimeZone.getAvailableIDs();
    }
    
    public String getTime(){
        
        if(zone == null) return "not available";
        timeFormatter.setTimeZone(zone);
        Date time = new Date();
        String timeString = timeFormatter.format(time);
        return timeString;
        
    }
    
    
}
