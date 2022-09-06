/**
 * This class exists because of aestethic concerns.
 * It will act as a more primal JCheckBox.
 * It will change its background color depending on being checked or not.
 * 
 * @author Burak Oruk
 * date it created: 06.09.2022
 */

package gui;

import javax.swing.JButton;

import java.awt.Color;

public class ChechkButton extends JButton{
    private boolean selected;

    private Color available = ScheduleViewer.AVAILABLE;
    private Color taken = ScheduleViewer.TAKEN;

    public ChechkButton(){
        super();

        selected = false;

        this.setBackground(available);

        this.addActionListener(e -> updateButton());
    }

    private void updateButton(){
        selected = !selected;

        if (selected){
            this.setBackground(available);
        }
        else{
            this.setBackground(taken);
        }
    }

    public boolean isSelected(){
        return selected;
    }
}
