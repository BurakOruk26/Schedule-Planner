import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class checks whether the added "Course"s are compatible or not.
 * This class extends JPanel to visualize the process and show the user the table of courses clearly.
 * @author Burak Oruk
 * starting date: 06.08.2022
 */
public class Planner extends JPanel {
    private ArrayList<Course> allCourses;
    private ArrayList<Course> activeCourses;
    //private JPanel available; // having a darker color for background and a lighter one for the available spaces will make the grid more accessible
    private JPanel busy;
    private JPanel conflict;
    private JPanel[][] schedule;
    private Dimension panelSize;
}
