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
    private final int COLOR_GAP = 5;
    private GridLayout layout;
    private Schedule schedule;
    private JLabel[][] colors;

    public static final Color AVAILABLE = new Color(40,40,40);
    public static final Color TAKEN = new Color(65,120,50);
    public static final Color CONFLICT = new Color(100,50,50);
    /*public static final Dimension spaceSize = new Dimension(); */
    private final int DAYS = Schedule.DAYS;
    private final int TIMES = Schedule.TIME;
    
    public ScheduleViewer(){

        // setting layout
        /* 
         *  leaving gap between componenets and setting a background to make the view more distinguishable 
         */
        this.layout = new GridLayout(TIMES,DAYS);
        layout.setHgap(COLOR_GAP); 
        layout.setVgap(COLOR_GAP);
        this.setLayout( layout );

        this.setBackground(new Color(120,120,120));

        // calculating the size of a JLabel to be put on "colors" array
        int width = MainFrame.VIEWER_WIDTH - ( MainFrame.VIEWER_GAP * 2 ); // one gap at the top one at the bottom
        int height = MainFrame.HEIGHT - ( (COLOR_GAP * (TIMES + 1)) + (MainFrame.VIEWER_GAP * 2) );

        /*
         * but what about integer division ?
         */
        int labelWidth = width / DAYS;
        int labelHeight = height / TIMES;

        // setting all the spaces in "colors" to JLabels in required size and the color AVAILABLE
        this.colors = new JLabel[TIMES][DAYS];
        for ( int i = 0; i < TIMES; i++ ){
            for ( int j = 0; j < DAYS; j++){  
                JLabel color = new JLabel();
                color.setPreferredSize( new Dimension (labelWidth, labelHeight));
                color.setBackground(AVAILABLE);
                color.setOpaque(true);              
                colors[i][j] = color;
                this.add(color);
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