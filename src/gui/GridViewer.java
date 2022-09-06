package gui;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;

import backend.Schedule;

/**
 * ScheduleViewer
 */
public class GridViewer extends JPanel {
    private int type;

    public static final int VIEW = 0;
    public static final int SELECT = 1;

    private final int COLOR_GAP = 5;
    private GridLayout layout;
    private Schedule mySchedule;
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
                    color.setOpaque(true);              
                    colors[i][j] = color;
                    this.add(color);
                }
            }
        }
        else if (type == SELECT ){

        }
    }

    /**
     * this methods checks all the places on the schedule and updates the color of the tile if needed
     */
    public void updateSchedule(){

        if ( this.type == VIEW ){

            int[][] schedule = mySchedule.getSchedule();

            JLabel color;
            int status;

            for (int i = 0; i < schedule.length; i++){
                for (int j = 0; j < schedule[0].length; j++){
                    color = (JLabel)colors[i][j];
                    status = schedule[i][j];

                    if (status == Schedule.AVAILABLE && !(color.getBackground().equals(AVAILABLE)) ){
                        color.setBackground(AVAILABLE);
                    }
                    else if (status == Schedule.TAKEN  && !(color.getBackground().equals(TAKEN)) ){
                        color.setBackground(TAKEN);
                    }
                    else if (status >= Schedule.CONFLICT  && !(color.getBackground().equals(CONFLICT)) ){
                        color.setBackground(CONFLICT);
                    }
                }
            }
        }
    }

    public void setSchedule(Schedule schedule) {
        this.mySchedule = schedule;
    }
    public Schedule getSchedule() {
        return mySchedule;
    }
}