package gui;

import javax.swing.*;
import java.awt.*;

import backend.Schedule;

public class MainFrame extends JFrame {
    private JPanel utilities;
    private JButton addCourse;
    private CourseSelection courseSelection; // will be changed to a custom-made class
    private JPanel viewerPanel;
    private ScheduleViewer scheduleViewer;

    public static final int FRAME_WIDTH = 1500;
    public static final int HEIGHT = 900;      
    public static final int VIEWER_WIDTH = 1100;
    public static final int VIEWER_GAP = 70;
    public static final int UTILITIES_WIDTH = FRAME_WIDTH - VIEWER_WIDTH;

    public final int C_BOX_HEIGHT = 25;
    public final int SELECTION_PANEL_WIDTH = 350;
    public final int SELECTION_PANEL_HEIGHT = 650;

    public MainFrame(){

        this.setLayout( new FlowLayout(FlowLayout.CENTER, 0,0) );

        // setting "viewerPanel"
        viewerPanel = new JPanel();
        viewerPanel.setLayout(new FlowLayout( FlowLayout.CENTER, VIEWER_GAP, VIEWER_GAP ));
        viewerPanel.setPreferredSize(new Dimension( VIEWER_WIDTH, HEIGHT ));
        viewerPanel.setBackground(Color.DARK_GRAY);

        // initializing and adding ScheduleViewer object to "viewerPanel"
        scheduleViewer = new ScheduleViewer();
        viewerPanel.add(scheduleViewer);   

        // setting the "utilities"

        utilities = new JPanel();
        utilities.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        utilities.setPreferredSize(new Dimension( UTILITIES_WIDTH, HEIGHT ));
        utilities.setBackground( new Color(90,30,30));

        // addCourse button
        addCourse = new JButton("Add Course");
        addCourse.setPreferredSize(new Dimension(150,100));
        addCourse.setBackground( new Color(90,50,50));
        addCourse.setForeground(new Color(170,150,150));

        // setting "courseSelection"
        courseSelection = new CourseSelection( SELECTION_PANEL_WIDTH, C_BOX_HEIGHT, 
            new Color(40,30,30), new Color(130,120,120) );
        courseSelection.setPreferredSize(new Dimension(SELECTION_PANEL_WIDTH, SELECTION_PANEL_HEIGHT));
        courseSelection.setBackground( new Color(55,30,30));

        utilities.add(addCourse);
        utilities.add(courseSelection);

        // adding the components to the frame
        this.add(viewerPanel);
        this.add(utilities);

        // making our JFrame intact
        this.pack();
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setPreferredSize(new Dimension(1500,900));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    public MainFrame(String name){
        this();
        this.setTitle(name);
    }

    public void setSchedule(Schedule schedule){
        scheduleViewer.setSchedule(schedule);
        scheduleViewer.updateSchedule();

        courseSelection.setActiveCourses(schedule);
    }

    // probably won't need this
    public void updateSchedule(){
        scheduleViewer.updateSchedule();
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame( "SCHEDULE PLANNER by Burak Oruk" );
        mainFrame.setVisible(true);
    }
}
