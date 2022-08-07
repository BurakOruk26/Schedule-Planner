import java.util.ArrayList;

/**
 * This class provides utilities for testing Lesson and Course classes.
 * Planner class will have the same functionalities as this class, however it will also visualize the process.
 */
public class Tester {
    private int[][] schedule;
    private ArrayList<Course> courses;
    private final int DAYS = 7;
    private final int TIME = 11;

    public Tester(){
        schedule = new int[DAYS][TIME];
        emptySchedule();
        courses = new ArrayList<Course>();
    }

    public Tester ( ArrayList<Course> courses ){
        this();
        this.courses = courses;
    }

    /**
     * Assigns all the spaces of the array "schedule" to "0";
     */
    private void emptySchedule(){
        for ( int i = 0; i < schedule.length; i++ ){
            for ( int j = 0; j < schedule[0].length; j++){
                schedule[i][j] = 0;
            }
        }
    }

    /**
     * Modifies the spaces of "Lesson"s in given Course object, according to the boolean "add".
     * Increments the number at place if the boolean "add" is true, decrement if false.
     * @param course
     */
    private void scheduleCourse( Course course, Boolean add ){
        if ( (add && courses.contains(course)) || (!add && !courses.contains(course)) ){return;}

        int operation;
        if (add) {operation = 1;}
        else {operation = -1;}

        int day;
        int time;

        for ( Lesson lesson : course.getLessons() ){
            day = lesson.getDay();
            time = lesson.getTime();

            schedule[day][time]+=operation;
        }
    }

    public void addCourse( Course course ){
        courses.add( course );
        scheduleCourse( course, true );
    }

    public void removeCourse( Course course ){
        courses.remove( course );
        scheduleCourse( course, false);
    }

    /* METHOD */
    /* SOMEHOW detects all the enabled courses and schedules them*/

    /* METHOD */
    /* prints out the schedule with different symbols for different availabilities */
}
