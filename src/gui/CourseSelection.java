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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

    private Dimension cardSize;
    private Dimension deleteButtonSize;

    public CourseSelection( int checkboxWidth, int checkboxHeight, Color checkboxBackgraund, Color checkboxForeground){

        plate = new JPanel();

        this.checkboxWidth = checkboxWidth - H_GAP*3 - checkboxHeight;
        this.checkboxHeight = checkboxHeight;
        this.checkboxSize = new Dimension ( this.checkboxWidth, this.checkboxHeight );
        this.cboxBackground = checkboxBackgraund;
        this.cboxForeground= checkboxForeground;

        cardSize = new Dimension( this.checkboxWidth + H_GAP*2 + checkboxHeight, this.checkboxHeight );
        deleteButtonSize = new Dimension( checkboxHeight, checkboxHeight );

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
        
        // initializing a JCheckBox
        JCheckBox cBox = new JCheckBox( 
            String.format( "%s %s %s" , (course.getTitle()), (course.getSection()), (course.getInstructor()) ), 
            true
        );

        cBox.addActionListener(e -> setCourseActivity(cBox, course));

        cBox.setBackground(cboxBackground);
        cBox.setForeground(cboxForeground);
        cBox.setPreferredSize(checkboxSize);
        cBox.setFont( new Font("Bookman Old Style", Font.PLAIN, 16));
        cBox.setFocusPainted(false);
        cBox.setOpaque(true);

        // initializing "deleteCourse"
        JButton deleteCourse = new JButton("X");
        deleteCourse.setBackground(new Color(20,10,10));
        deleteCourse.setForeground(new Color(150,20,20));
        deleteCourse.setFont(new Font("Calibri", Font.BOLD, 17));
        deleteCourse.setPreferredSize(deleteButtonSize);
        deleteCourse.setFocusPainted(false);
        deleteCourse.addActionListener( e -> mainFrame.removeCourse(course));

        // creating a JPanel to hold both the CheckBox and the deleteCourse JButton.
        JPanel card = new JPanel();
        card.setOpaque(false);
        card.setPreferredSize(cardSize);
        card.setMaximumSize(cardSize);
        card.setLayout(new FlowLayout(FlowLayout.LEFT,H_GAP,0));

        // adding components to the "card"
        card.add(cBox);
        card.add(deleteCourse);
    
        plate.add(card);
        this.plate.add(javax.swing.Box.createRigidArea(new Dimension(0,H_GAP)));

        plate.revalidate();
        plate.repaint();
    }

    private void removeCheckBox( Course course ){
        /*
         * The math here is simple, there is a gap coming after every JPanel,
         *  hence we multiply the JPanel's index by 2 to skip one gap per JPanel.
         * After that we increment the index by 1 to skip that one gap which dwells on the top of the "plate".
         */
        int index = allCourses.indexOf(course)*2 + 1;
        
        plate.remove(index); // denelting the JPanel that holds checkBox and deleteButton
        plate.remove(index); // deleting the gap comes after JPanel

        plate.revalidate();
        plate.repaint();
    }

    // will the MainFrame will be updated after this?
    private void setCourseActivity(JCheckBox checkBox, Course course){
        
        if ( checkBox.isSelected() ){
            activeCourses.addCourse(course);
        }
        else {
            activeCourses.removeCourse(course);
        }
        mainFrame.updateSchedule();
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
