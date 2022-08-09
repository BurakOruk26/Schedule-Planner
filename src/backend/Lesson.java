package backend;
/**
 * This class stores when a lesson is going to happen and has a boolean "active" to make the job of "Planner" class easier.
 * The class "Course" will use multiple instances of class "Lesson" to store data of when the courses will happen.
 * @author Burak Oruk
 * starting date: 06.08.2022
 */
public class Lesson {

    // constants for days
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;

    // constants for lesson times (uses 12-hour clock system)
    public static final int EIGHT_THIRTY = 0;
    public static final int NINE_THIRTY = 1;
    public static final int TEN_THIRTY = 2;
    public static final int ELEVEN_THIRTY = 3;
    public static final int TWELVE_THIRTY = 4;
    public static final int ONE_THIRTY = 5;
    public static final int TWO_THIRTY = 6;
    public static final int THREE_THIRTY = 7;
    public static final int FOUR_THIRTY = 8;
    public static final int FIVE_THIRTY = 9;
    public static final int SIX_THIRTY = 10;

    // variables
    private int day;
    private int time;
    private boolean active;

    public Lesson( int day, int time ){
        this.day = day;
        this.time = time;
        this.active = false; //this is false by default to prevent unnecessary clashes
    }

    public boolean equals( Lesson lesson ){
        return ( (this.day == lesson.day) && (this.time == lesson.time) );
    }

    // setter methods
    public void setDay(int day) {
        this.day = day;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    // getter methods
    public int getDay() {
        return day;
    }
    public int getTime() {
        return time;
    }
    public boolean isActive() {
        return active;
    }
}
