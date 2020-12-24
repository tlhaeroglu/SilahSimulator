package silahsimulator;

import java.awt.Graphics;
import javax.swing.JFrame;

// 1300-750
public class Window extends JFrame{
    AnaPanel ana;
    
    public Window (){
        super();
        ana = new AnaPanel();
        ana.setBounds(0, 0, 1300, 750);
        ana.setLayout(null);
        add(ana);
        
    }
    
   
    
    public void paint(Graphics g){
        super.paint(g);
    }
    
    
}
