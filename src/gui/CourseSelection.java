/**
 * This class will display multiple checkboxes representing Course class intances.
 * Selecting and deselecting "Course"s, making it possible for the user to try different possibilities for thier schedule.
 * Adding and deleting "Course"s, the most important.
 */
package gui;

import backend.Schedule;
import backend.Course;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;

public class CourseSelection extends JScrollPane{
    private ArrayList<Course> allCourses;
    private ArrayList<JCheckBox> checkBoxes;
    private Schedule activeCourses;

    private final int H_GAP = 10;
    private final int V_GAP = 10;

    public CourseSelection(){
        allCourses = new ArrayList<Course>();
        checkBoxes = new ArrayList<JCheckBox>();

        this.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        this.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );

        /* layout of JScrollPane must be a ScrollPaneLayout */
        /* 
        FlowLayout layout = new FlowLayout( FlowLayout.LEFT, H_GAP, V_GAP);
        this.getViewport().setLayout(layout);
        */
    }

    public boolean addCourse( Course course ){
        if ( allCourses.contains(course) ) {return false;}

        allCourses.add(course);
        this.addCheckBox(course);

        return true;
    }

    public boolean removeCourse( Course course ){
        if ( ! allCourses.contains(course) ) {return false;}

        this.removeCheckBox( course );
        allCourses.remove(course);
        if ( activeCourses.contains(course)) {
            activeCourses.removeCourse(course);
        }

        return true;
    }

    private void addCheckBox( Course course ){
        JCheckBox cBox = new JCheckBox( 
            String.format( "%s %s\n%s" , (course.getName()), (course.getSection()), (course.getInstructor()) ), 
            false
        );

        // have to change the size of the checkbox
        // will use a method to size it, which will use the parameters taken from an outside class by setCheckBoxSize()

        checkBoxes.add(cBox);
        this.add(cBox);
    }

    private void removeCheckBox( Course course ){
        int index = allCourses.indexOf( course );
        this.remove(index);
    }

    public void setActiveCourses(Schedule activeCourses) {
        for ( Course course : activeCourses.getCourses() ){
            this.addCourse(course);

            System.out.println(this.addCourse(course));
        }

        this.activeCourses = activeCourses;
    }

    /*
    public CourseSelection(Schedule schedule){
        this();
        allCourses = schedule.getCourses();
        activeCourses = schedule;
    }
    */
}
