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

public class CheckButton extends JButton{
    private boolean selected;

    private Color available = GridViewer.AVAILABLE;
    private Color taken = GridViewer.TAKEN;

    public CheckButton(){
        super();

        selected = false;

        this.setBackground(available);

        this.addActionListener(e -> updateButton());
    }

    private void updateButton(){
        selected = !selected;

        if (selected){
            this.setBackground(taken);
        }
        else{
            this.setBackground(available);
        }
    }

    public boolean isSelected(){
        return selected;
    }
}
