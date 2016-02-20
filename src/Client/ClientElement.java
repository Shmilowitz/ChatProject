/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.swing.JCheckBox;

/**
 *
 * @author Kristian Nielsen
 */
public class ClientElement {
    String name;
    JCheckBox box;
    
    public ClientElement(String name){
        this.name = name;
        box = new JCheckBox();
    }
    
    public boolean getChecked(){
        return box.isSelected();
    }
    
}
