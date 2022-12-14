package backend;
/**
 * To imitate the schedule of a real life course this class will contain multiple "Lesson" class instances,
 * stored in an ArrayList.
 * @author Burak Oruk
 * starting date: 06.08.2022
 */

import java.util.ArrayList;

public class Course {

    // variables
    private String title;
    private String instructor; // makes it easier for the student to recognize this Course, nothing more
    private String section;
    private ArrayList<Lesson> lessons;

    public Course( String name, String instructor, String section ){
         this.title = name;
         this.instructor = instructor;
         this.section = section;

         this.lessons = new ArrayList<Lesson>();
    }

    public boolean doesConflict( Course course ){
        ArrayList<Lesson> otherLessons = course.lessons;

        for ( Lesson lesson: this.lessons ){
            for ( Lesson otherLesson: otherLessons ){
                if ( lesson.equals(otherLesson) )
                    return true;
            }
        }
        return false;
    }

    // getter methods
    public ArrayList<Lesson> getLessons() {
        return lessons;
    }
    public String getSection() {
        return section;
    }
    public String getInstructor() {
        return instructor;
    }
    public String getTitle() {
        return title;
    }

    // setter methods
    public void setTitle(String title) {
        this.title = title;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public void addLesson( Lesson lesson ){
        lessons.add(lesson);
    }

    public boolean removeLesson ( Lesson lesson ){
        if ( lessons.contains( lesson ) ) {
            lessons.remove(lesson);
            return true;
        }
        else
            return false;
    }
}
