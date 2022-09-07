/**
 * This class will display multiple checkboxes representing Course class intances.
 * Selecting and deselecting "Course"s, making it possible for the user to try different possibilities for thier schedule.
 * Adding and deleting "Course"s, the most important.
 */
package gui;

import backend.Schedule;
import backend.Course;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Color;

public class CourseSelection extends JScrollPane{
    private ArrayList<Course> allCourses;
    private Schedule activeCourses;
    private MainFrame mainFrame;

    private JPanel plate;

    private final int H_GAP = 10;
    private int checkboxHeight;
    private int checkboxWidth;
    private Dimension checkboxSize;
    private Color cboxBackground;
    private Color cboxForeground;

    public CourseSelection( int checkboxWidth, int checkboxHeight, Color checkboxBackgraund, Color checkboxForeground){

        plate = new JPanel();

        this.checkboxWidth = checkboxWidth - 2*H_GAP;
        this.checkboxHeight = checkboxHeight;
        this.checkboxSize = new Dimension ( this.checkboxWidth, this.checkboxHeight );
        this.cboxBackground = checkboxBackgraund;
        this.cboxForeground= checkboxForeground;

        allCourses = new ArrayList<Course>();
        
        this.plate.setLayout( new BoxLayout(plate, BoxLayout.Y_AXIS));
        this.plate.add(javax.swing.Box.createRigidArea(new Dimension(0,H_GAP)));

        this.getViewport().setView(plate);

        this.getVerticalScrollBar().setUnitIncrement(16);
        
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
            String.format( "%s %s %s" , (course.getName()), (course.getSection()), (course.getInstructor()) ), 
            true
        );

        // this code is not relaible, will this button have the course assigned to it all the times?
        cBox.addActionListener(e -> setCourseActivity(cBox, course));

        cBox.setPreferredSize( checkboxSize );
        cBox.setBackground(cboxBackground);
        cBox.setForeground(cboxForeground);
        cBox.setOpaque(true);
    
        plate.add(cBox);
        this.plate.add(javax.swing.Box.createRigidArea(new Dimension(0,H_GAP)));

        plate.revalidate();
        plate.repaint();
    }

    private void removeCheckBox( Course course ){
        int index = allCourses.indexOf( course );
        plate.remove(index);
    }

    // will the MainFrame will be updated after this?
    private void setCourseActivity(JCheckBox checkBox, Course course){
        
        if ( checkBox.isSelected() ){
            activeCourses.addCourse(course);
            mainFrame.updateSchedule();
        }
        else {
            activeCourses.removeCourse(course);
            mainFrame.updateSchedule();
        }
    }

    public void setActiveCourses(Schedule activeCourses) {
        for ( Course course : activeCourses.getCourses() ){
            this.addCourse(course);
        }

        this.activeCourses = activeCourses;
    }

    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }

    public void setBackgroundColor( Color color ){
        this.plate.setBackground(color);
    }
}
