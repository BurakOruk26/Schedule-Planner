package gui;

import javax.swing.*;
import java.awt.*;

import backend.Schedule;

public class MainFrame extends JFrame {
    private JPanel utilities;
    private JButton addCourse;
    private CourseSelection courseSelection; // will be changed to a custom-made class
    private JPanel viewerPanel;
    private GridViewer scheduleViewer;

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
        scheduleViewer = new GridViewer( GridViewer.VIEW );
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
        addCourse.addActionListener( e -> this.courseCreation() );

        // setting "courseSelection"
        courseSelection = new CourseSelection( SELECTION_PANEL_WIDTH, C_BOX_HEIGHT, 
            new Color(40,30,30), new Color(130,120,120) );
        courseSelection.setPreferredSize(new Dimension(SELECTION_PANEL_WIDTH, SELECTION_PANEL_HEIGHT));
        courseSelection.setBackgroundColor( new Color(55,30,30));
        courseSelection.setMainFrame(this);

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

    // different variations of constructors, if to be needed
    public MainFrame(String title){
        this();
        this.setTitle(title);
    }
    public MainFrame (Schedule schedule){
        this();
        this.setSchedule(schedule);
    }
    public MainFrame(String title, Schedule schedule){
        this(title);
        this.setSchedule(schedule);
    }

    public void setSchedule(Schedule schedule){
        scheduleViewer.setSchedule(schedule);
        scheduleViewer.updateSchedule();

        courseSelection.setActiveCourses(schedule);
    }

    /**
     * this method is used to graphically update the state of the ScheduleViewer
     */
    protected void updateSchedule(){
        scheduleViewer.updateSchedule();
    }

    private void courseCreation(){
        JFrame frame = new JFrame( "Create a Course" );

        GridViewer grid = new GridViewer(GridViewer.SELECT);

        frame.add(grid, BorderLayout.CENTER);

        int width = VIEWER_WIDTH ;
        int height = HEIGHT - VIEWER_GAP*2;

        JButton done = new JButton("DONE");
        done.setBackground( new Color (30,10,10));
        done.setForeground( new Color(150,170,170));
        done.addActionListener(e -> grid.createCourse());

        JPanel buttons = new JPanel();
        buttons.setPreferredSize( new Dimension( VIEWER_GAP*2, height ));
        buttons.setBackground( new Color(60,40,40));
        buttons.setLayout( new FlowLayout(FlowLayout.CENTER,0, ((height/2) - done.getHeight()) ));

        buttons.add(done);

        frame.add( buttons, BorderLayout.EAST );

        frame.pack();
        frame.setLocationRelativeTo(scheduleViewer);
        frame.setPreferredSize( new Dimension( width, height ));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
        frame.setVisible(true);
    }


    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame( "SCHEDULE PLANNER by Burak Oruk" );

        /*  
            *will be needed, however it may not be at the moment*
        */
        /*
        Schedule schedule = new Schedule();
        mainFrame.setSchedule(schedule);
        */
        mainFrame.setVisible(true);
    }
}
