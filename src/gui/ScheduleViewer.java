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
    private final int TIMES = Schedule.TIME;
    
    public ScheduleViewer(){

        // setting layout

        // setting all the spaces in "colors" to JLabels in required size and the color AVAILABLE
        colors = new JLabel[DAYS][TIMES];
        JLabel color = new JLabel();
        /* here I shall set its size */
        color.setBackground(AVAILABLE);
        color.setOpaque(true);
        for ( int i = 0; i < colors.length; i++ ){
            for ( int j = 0; j < colors[0].length; j++){                
                colors[i][j] = color;
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