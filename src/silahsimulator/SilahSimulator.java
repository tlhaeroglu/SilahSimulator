
package silahsimulator;

import java.awt.Color;
import javax.swing.JFrame;


public class SilahSimulator {

                                
    public static void main(String[] args) {
        // Main metodunda ilk başta açılacak olan oyunun launcher kısmı gelir
        // Burdan splash classına yönlendirilir
        Splash splash = new Splash();
        splash.setSize(800, 600);
        splash.setTitle("Gun Simulator Launcher");
        splash.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        splash.setLayout(null);
        splash.setResizable(false);
        splash.setLocationRelativeTo(null);
        splash.setVisible(true);   
    }
    
}
