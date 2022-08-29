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
import javax.swing.JPanel;
import javax.swing.JScrollBar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class CourseSelection extends JPanel{
    private ArrayList<Course> allCourses;
    private ArrayList<JCheckBox> checkBoxes;
    private Schedule activeCourses;

    private final int H_GAP = 10;
    private final int V_GAP = 10;
    private int checkboxHeight;
    private int checkboxWidth;
    private Dimension checkboxSize;

    private boolean scrollbar;

    public CourseSelection( int checkboxWidth, int checkboxHeight){

        this.checkboxWidth = checkboxWidth - 2*H_GAP;
        this.checkboxHeight = checkboxHeight;
        checkboxSize = new Dimension ( this.checkboxWidth, this.checkboxHeight );

        allCourses = new ArrayList<Course>();
        checkBoxes = new ArrayList<JCheckBox>();

        scrollbar = false;
        /* 
        this.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        this.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        */

        /* layout of JScrollPane must be a ScrollPaneLayout */
        
        FlowLayout layout = new FlowLayout( FlowLayout.LEFT, H_GAP, V_GAP);
        this.setLayout(layout);
        
    }

    public boolean addCourse( Course course ){
        if ( allCourses.contains(course) ) {return false;}

        allCourses.add(course);
        
        updateSize();
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
        updateSize();

        return true;
    }

    private void addCheckBox( Course course ){
        JCheckBox cBox = new JCheckBox( 
            String.format( "%s %s %s" , (course.getName()), (course.getSection()), (course.getInstructor()) ), 
            false
        );

        cBox.setPreferredSize( checkboxSize );
    
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
        }

        this.activeCourses = activeCourses;
    }

    private void updateSize(){
        int cBoxCount = allCourses.size();
        int height = (cBoxCount * checkboxHeight) + ((cBoxCount + 1) * H_GAP);

        if ( height <= this.getHeight() ){ // // // change this line for removeCourse() method // // //
            return;
        }
        else if (height > this.getHeight() && !scrollbar ){
            this.setSize( this.getWidth(), height);
            
            this.add(new JScrollBar(JScrollBar.VERTICAL), BorderLayout.EAST);
            scrollbar = true;
        }
        else{
            this.setSize( this.getWidth(), height);
        }
    }

    /*
    public CourseSelection(Schedule schedule){
        this();
        allCourses = schedule.getCourses();
        activeCourses = schedule;
    }
    */
}
