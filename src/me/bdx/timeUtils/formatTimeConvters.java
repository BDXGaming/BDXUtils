package timeUtils;

public class formatTimeConvters {

    private String minutePlaceholder = "m";
    private String hourPlaceholder = "hr";
    private String dayPlaceholder = "d";

    /**
     * Creates a new instance of formatTimeConverters with default time placeholders (m, hr, d)
     */
    public formatTimeConvters(){}

    /**
     * Creates a new instance of formatTimeConverters with the given time unit placeholders
     * @param minutePlaceholder String
     * @param hourPlaceholder String
     * @param dayPlaceholder String
     */
    public formatTimeConvters(String minutePlaceholder, String hourPlaceholder, String dayPlaceholder){
        this.dayPlaceholder = dayPlaceholder;
        this.hourPlaceholder = hourPlaceholder;
        this.minutePlaceholder = minutePlaceholder;
    }

    /**
     * Converts the given string duration to a second-based integer
     * Supports formats provided in constructor
     * @param dur String
     * @return dur int
     */
    public int getDuration(String dur){
        if(dur.contains(minutePlaceholder)){
            int index = dur.indexOf(minutePlaceholder);
            return 60*Integer.parseInt(dur.substring(0,index));
        }
        else if(dur.contains(hourPlaceholder)){
            int index = dur.indexOf(hourPlaceholder);
            return 3600*Integer.parseInt(dur.substring(0,index));
        }
        else if(dur.contains(dayPlaceholder)){
            int index = dur.indexOf(dayPlaceholder);
            return (24*3600)*Integer.parseInt(dur.substring(0,index));
        }
        else{
            return Integer.parseInt(dur);
        }
    }

    /**
     * Converts the given integer of time (in seconds) into a string
     * @param duration int
     * @return dur String
     */
    public String getStringDuration(int duration){
        String dur;
        if(duration <=60){
            dur = duration + "s";
        }
        else if (duration <3600 ){
            dur = (duration/60) + minutePlaceholder;
        }
        else if(duration < 86400){
            dur = (duration/3600) + hourPlaceholder;
        }
        else{
            dur = (duration/86400) +dayPlaceholder;
        }
        return dur;
    }


}
