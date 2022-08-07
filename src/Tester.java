import java.util.ArrayList;

/**
 * This class provides utilities for testing Lesson and Course classes.
 * Planner class will have the same functionalities as this class, however it will also visualize the process.
 */
public class Tester {
    private int[][] schedule;
    private ArrayList<Course> courses;
    private final int DAYS = 7;
    private final int LESSONS = 11;

    public Tester(){
        schedule = new int[DAYS][LESSONS];
        emptySchedule();
        courses = new ArrayList<Course>();
    }

    public Tester ( ArrayList<Course> courses ){
        schedule = new int[DAYS][LESSONS];
        emptySchedule();
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
     * increments the spaces in schedule by "1", according to the "Lesson"s in the given "Course" object
     * @param course
     */
    private void schedule( Course course ){
        int day;
        int time;

        for ( Lesson lesson : course.getLessons() ){
            day = lesson.getDay();
            time = lesson.getTime();

            schedule[day][time]++;
        }
    }
}
