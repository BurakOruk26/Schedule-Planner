package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel utilities;
    private JButton addCourse;
    private JPanel lessonSelection; // will be changed to a custom-made class
    private JPanel scheduleViewer;

    public MainFrame(){

        this.setLayout( new FlowLayout(FlowLayout.CENTER, 0,0) );

        // setting "scheduleViewer"
        scheduleViewer = new JPanel();
        scheduleViewer.setLayout(new FlowLayout(FlowLayout.CENTER,70,70));
        scheduleViewer.setPreferredSize(new Dimension(1100,900));
        scheduleViewer.setBackground(Color.DARK_GRAY);

        // setting the "utilities"
        utilities = new JPanel();
        utilities.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        utilities.setPreferredSize(new Dimension(400,900));
        utilities.setBackground( new Color(90,30,30));

        addCourse = new JButton("Add Course");
        addCourse.setPreferredSize(new Dimension(150,100));
        addCourse.setBackground( new Color(90,50,50));
        addCourse.setForeground(new Color(150,130,130));

        lessonSelection = new JPanel();
        lessonSelection.setPreferredSize(new Dimension(350,650));
        lessonSelection.setBackground( new Color(55,30,30));

        utilities.add(addCourse);
        utilities.add(lessonSelection);

        // adding the components to the frame
        this.add(scheduleViewer);
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

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame( "SCHEDULE PLANNER by Burak Oruk" );
        mainFrame.setVisible(true);
    }
}
