package backend;
import java.util.ArrayList;

/**
 * This class provides utilities for testing Lesson and Course classes.
 * Planner class will have the same functionalities as this class, however it will also visualize the process.
 */
public class Schedule {
    private int[][] schedule;
    private ArrayList<Course> courses;

    public static final int DAYS = 7;
    public static final int TIME = 11;

    public static final int AVAILABLE = 0;
    public static final int TAKEN = 1;
    public static final int CONFLICT = 2;

    public Schedule(){
        schedule = new int[TIME][DAYS];
        emptySchedule();
        courses = new ArrayList<Course>();
    }

    /**
     * Assigns all the spaces of the array "schedule" to "0";
     */
    private void emptySchedule(){
        for ( int i = 0; i < TIME; i++ ){
            for ( int j = 0; j < DAYS ; j++){
                schedule[i][j] = 0;
            }
        }
    }

    /**
     * Modifies the spaces of "Lesson"s in given Course object, according to the boolean "add".
     * Increments the number at place if the boolean "add" is true, decrement if false.
     */
    private boolean scheduleCourse( Course course, Boolean add ){
        // the following line of code checks if the course to be added is already been added or the opposite,
        // in those situations it returns false and stops the process
        if ( (add && courses.contains(course)) || (!add && !courses.contains(course)) ){return false;}

        int operation;
        if (add) {operation = 1;}
        else {operation = -1;}

        int day;
        int time;

        for ( Lesson lesson : course.getLessons() ){
            day = lesson.getDay();
            time = lesson.getTime();

            schedule[time][day]+=operation;
        }

        return true;
    }

    public void addCourse( Course course ){
        if (scheduleCourse( course, true ))
            courses.add( course );
    }
    public void addCourse( ArrayList<Course> courses){
        for ( Course course : courses){
            addCourse(course);
        }
    }

    public void removeCourse( Course course ){
        if (scheduleCourse( course, false))
            courses.remove( course );
    }
    public void removeCourse( ArrayList<Course> courses){
        for ( Course course : courses){
            removeCourse(course);
        }
    }

    /* METHOD */
    /* SOMEHOW detects all the enabled courses and schedules them*/

    /* METHOD */
    /* prints out the schedule with different symbols for different availabilities */

    /**
     * Returns the schedule as columns being days and rows being times.
     */
    public String toString(){
        String plan = "";

        for (int i = 0; i < TIME; i++){
            for (int j = 0; j < DAYS; j++){
                plan += schedule[i][j];
            }
            plan += "\n";
        }

        /*
        int time = 0;
        while ( time < TIME ){
            for ( int day = 0; day < DAYS; day++){
                plan += schedule[day][time];
                plan += " ";
            }
            plan += "\n";
            time++;
        }*/
        return plan;
    }

    public int[][] getSchedule() {
        return schedule;
    }
}
