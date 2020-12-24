
package silahsimulator;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Features extends JPanel{
    
    JTextField txtName, txtCapacity, txtRange;
    public Features (){
        super();
 
       
        txtName = new JTextField();
        txtCapacity = new JTextField();
        txtRange = new JTextField();
        
        
        
        txtName.setBounds(30,180,90,20);
        txtCapacity.setBounds(30,220,90,20);
        txtRange.setBounds(30,250,90,20);
        
        
        
        
        
        add(txtName);
        add(txtCapacity);
        add(txtRange);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillOval(30, 38, 100, 100);
        
    }
}
