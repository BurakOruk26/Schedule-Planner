package gui;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import backend.*;

/**
 * ScheduleViewer
 */
public class ScheduleViewer extends JPanel {
    private GridLayout layout;
    private Schedule schedule;
    private JLabel[][] colors;

    public static final Color AVAILABLE = new Color(40,40,40);
    public static final Color TAKEN = new Color(65,120,50);
    public static final Color CONFLICT = new Color(100,50,50);
    /*public static final Dimension spaceSize = new Dimension(); */
    private final int DAYS = Schedule.DAYS;
    private final int TIME = Schedule.TIME;
    
    public ScheduleViewer(){

        // setting layout
        /* 
         *  leaving gap between componenets and setting a background to make the view more distinguishable 
         */
        this.layout = new GridLayout(DAYS,TIME);
        layout.setHgap(5); 
        layout.setVgap(5);
        this.setLayout( layout );

        this.setBackground(new Color(120,120,120));

        // calculating the size of a JLabel to be put on "colors" array
        int width = MainFrame.VIEWER_WIDTH - ( MainFrame.VIEWER_GAP * 2 ); // one gap at the top one at the bottom
        int height = MainFrame.HEIGHT - ( MainFrame.VIEWER_GAP * 2 );

        /*
         * but what about integer division ?
         */
        int labelWidth = width / DAYS;
        int labelHeight = height / TIME;

        // setting all the spaces in "colors" to JLabels in required size and the color AVAILABLE
        this.colors = new JLabel[DAYS][TIME];
        for ( int i = 0; i < DAYS; i++ ){
            for ( int j = 0; j < TIME; j++){  
                JLabel color = new JLabel();
                color.setPreferredSize( new Dimension (labelWidth, labelHeight));
                color.setBackground(AVAILABLE);
                color.setOpaque(true);              
                colors[i][j] = color;
                this.add(color, j, i); // the place is just a guess, might not work as intended ( DOES NOT WORK)
            }
        }

        // some crazy thing that extending JLabel requires
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    public Schedule getSchedule() {
        return schedule;
    }
}