package gui;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;

import backend.*;

/**
 * This class creates a grid-like looking JLabel with an obvious GridLayout.
 * It has the following two type of functionalities:
 *  View, allowing a JLabel based grid to be displayed, it shows the current situation of the Schedule.
 *  Select, allowing a CheckButton.java based grid to be displayed, users are able to choose the time and day for their Courses to be added.
 * @author Burak Oruk
 * date: 10.08.2022 (the codes have been migrated from ScheduleViewer.java, hence files creation date may differ)
 */
public class GridViewer extends JPanel {
    private int type;
    public static final int VIEW = 0;
    public static final int SELECT = 1;

    private final int COLOR_GAP = 5;
    private GridLayout layout;
    private Schedule schedule;
    private JComponent[][] colors;

    public static final Color AVAILABLE = new Color(40,40,40);
    public static final Color TAKEN = new Color(65,130,50);
    public static final Color CONFLICT = new Color(130,50,50);
    private final int DAYS = Schedule.DAYS;
    private final int TIMES = Schedule.TIME;
    
    private GridViewer(){

        // setting layout
        /* 
         *  leaving gap between componenets and setting a background to make the view more distinguishable 
         */
        this.layout = new GridLayout( TIMES, DAYS );
        layout.setHgap( COLOR_GAP ); 
        layout.setVgap( COLOR_GAP );
        this.setLayout( layout );

        this.setBackground(new Color(120,120,120));

    }

    public GridViewer( int type ){
        this();
        this.type = type;

        // calculating the size of the Component to be put on "colors" array
        int height = MainFrame.HEIGHT - ( (COLOR_GAP * (TIMES + 1)) + (MainFrame.VIEWER_GAP * 2) );
        int width = MainFrame.VIEWER_WIDTH - ( MainFrame.VIEWER_GAP * 2 );

        /*
        * but what about integer division ?
        */
        int labelWidth = width / DAYS;
        int labelHeight = height / TIMES;

        // creating and adding the needed objects to place at the grid
        if ( type == VIEW ){
            
            // setting all the spaces in "colors" to JLabels in required size and the color AVAILABLE
            this.colors = new JLabel[TIMES][DAYS];
            for ( int i = 0; i < TIMES; i++ ){
                for ( int j = 0; j < DAYS; j++){  
                    JLabel color = new JLabel();
                    color.setPreferredSize( new Dimension (labelWidth, labelHeight));
                    color.setBackground(AVAILABLE);
                    color.setForeground( new Color (220,200,200));
                    color.setFont( new Font("Times New Roman", Font.BOLD, 14));
                    color.setOpaque(true);              
                    colors[i][j] = color;
                    this.add(color);
                }
            }
        }
        else if ( type == SELECT ){

            // setting all the spaces in "colors" to JLabels in required size and the color AVAILABLE
            this.colors = new CheckButton[TIMES][DAYS];
            for ( int i = 0; i < TIMES; i++ ){
                for ( int j = 0; j < DAYS; j++){  
                    CheckButton color = new CheckButton();
                    color.setPreferredSize( new Dimension (labelWidth, labelHeight));
                    colors[i][j] = color;
                    this.add(color);
                }
            }
        }
    }

    protected void createCourse(MainFrame mainFrame, String title, String instructor, String section){
        if ( type == SELECT ){
            Course course = new Course(title,instructor,section);
            for (int i = 0; i < TIMES; i++){
                for (int j = 0; j< DAYS; j++){
                    CheckButton current = (CheckButton)colors[i][j];
                    if ( current.isSelected()){
                        Lesson lesson = new Lesson(i,j);
                        course.addLesson(lesson);
                    }
                }
            }
            mainFrame.addCourse(course);
        }
    }

    /**
     * This methods checks all the places on the schedule and updates the color of the tile if needed.
     * Extra control are done to prevent unnecessary actions like coloring the JLabel the same color as before.
     */
    public void updateSchedule(){

        if ( this.type == VIEW ){

            int[][] tempSchedule = schedule.getSchedule();

            JLabel color;
            int status;

            this.nameCourses();

            for (int i = 0; i < tempSchedule.length; i++){
                for (int j = 0; j < tempSchedule[0].length; j++){
                    color = (JLabel)colors[i][j];
                    status = tempSchedule[i][j];

                    if (status == Schedule.AVAILABLE && !(color.getBackground().equals(AVAILABLE)) ){
                        color.setBackground(AVAILABLE);
                        // The method "nameCourses()" only checks the places with lessons on them,
                        // hence at this line JLabel's text is set to empty to prevent unwelcome text appearing.
                        color.setText(""); 
                    }
                    else if (status == Schedule.TAKEN  && !(color.getBackground().equals(TAKEN)) ){
                        color.setBackground(TAKEN);
                    }
                    else if (status >= Schedule.CONFLICT  && !(color.getBackground().equals(CONFLICT)) ){
                        color.setBackground(CONFLICT);
                    }
                }
            }

            // update everything, will never regret this
            this.revalidate();
            this.repaint();
        }
    }

    /**
     * Adds the title of courses to their respective presenters.
     */
     
    private void nameCourses(){
        // variable decleration
        JLabel color;
        int time;
        int day;

        for ( Course course : schedule.getCourses() ){
            String text = String.format( " %s %s", course.getTitle(),course.getSection() );

            for ( Lesson lesson : course.getLessons() ){

                time = lesson.getTime();
                day = lesson.getDay();
                color =(JLabel)colors[time][day];
                /* 
                 * If the label's text is already empty, obviosly "text" has to be setted to the label.
                 * The other part is a little tricky, 
                 *  if the label's text already contains "text" we set it it only to the "text".
                 * This works because it resets the label's text to first lesson to be there,
                 *  after that the other lessons to be come are concatinated to label's text by the else if condition's body.
                 */
                if ( color.getText().equals("") || color.getText().contains(text)){
                    color.setText(text);
                }
                else if (! color.getText().contains(text) ){
                    color.setText(color.getText() + "/ " + text);
                }
            }
        }
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public Schedule getSchedule() {
        return schedule;
    }
}