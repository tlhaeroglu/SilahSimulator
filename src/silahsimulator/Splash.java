
package silahsimulator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSlider;

public class Splash extends JFrame{
    
    Launcher l;
    public Splash(){
        l = new Launcher();
        l.setBounds(0, 0, 800, 600);
        l.setLayout(null);
        add(l);
    }
    
    
    
}

class Launcher extends JPanel {
    private JTextField a,b;
    private JButton btnPlay;
    private JLabel lblA,lblB,lbl,icon1,icon2,icon3,label1,label2,label3,control;
    
    public Launcher(){
        this.setBackground(Color.gray);
        
        icon1 = new JLabel(new ImageIcon("src/icons/ates.png"));
        icon1.setBounds(5, 5, 60, 50);
        add(icon1);
        
        label1 = new JLabel("--> Bu simge ateş etmenize yarar eğer şarjörünüz var ve dolu ise ateş edebilirsiniz.");
        label1.setBounds(80, 10, 600, 30);
        add(label1);
        
        icon2 = new JLabel(new ImageIcon("src/icons/coklu.png"));
        icon2.setBounds(5, 60, 60, 50);
        add(icon2);
        
        label2 = new JLabel("--> Eğer bir otomatik silaha sahipseniz çoklu atış özelliğini kullanabilirsiniz.");
        label2.setBounds(80, 60, 600, 30);
        add(label2);
        
        icon3 = new JLabel(new ImageIcon("src/icons/sarjor.png"));
        icon3.setBounds(5, 120, 60, 110);
        add(icon3);
        
        label3 = new JLabel("--> Eğer şarjörünüzdeki mermi tükendiyse ve fazladan şarjörünüz varsa silahı doldurabilirsiniz.");
        label3.setBounds(80, 130, 600, 30);
        add(label3);
        
        lbl = new JLabel("<html>Merhaba Asker! <br/>  Adını ve rütbeni gir, BAŞLA! <html>");
        lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
        lbl.setBounds(300, 200, 200, 70);
        lbl.setForeground(Color.YELLOW);
        add(lbl);
        
        lblA = new JLabel("Asker Adı:");
        lblA.setBounds(220, 320, 100, 30);
        lblA.setForeground(Color.YELLOW);
        add(lblA);
        
        a = new JTextField();
        a.setBounds(333,320,120,30);
        add(a);
        
        b = new JTextField();
        b.setBounds(333,400,120,30);
        add(b);
        
        lblB = new JLabel("Asker Rütbesi:");
        lblB.setBounds(220, 400, 100, 30);
        lblB.setForeground(Color.YELLOW);
        add(lblB);
        
        btnPlay = new JButton("OYNA!");
        btnPlay.setBounds(320,500,150,50);
        btnPlay.setBackground(new Color(59, 89, 182));
        btnPlay.setForeground(Color.YELLOW);
        btnPlay.setFocusPainted(false);
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(acilsinMi()){
                    Asker.askerIsim = a.getText();
                    Asker.askerRutbe = b.getText();
                    setVisible(false);
                    //window classına geçilir oyunun ana classıdır
                    Window window = new Window();
                    window.setLayout(null);
                    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    window.setSize(1300,750);
                    window.setResizable(false);
                    window.setTitle("T&M Gun Simulator");
                    window.setLocationRelativeTo(null);
                    window.setVisible(true);
                    
                }
                else{
                    control.setVisible(true);
                }
            }
        });
        add(btnPlay);
        
        control = new JLabel("Alanları doldurmalısınız!");
        control.setBounds(320, 450, 150, 50);
        control.setForeground(Color.red);
        add(control);
        control.setVisible(false);
        
        
        
    }
    
    public boolean acilsinMi(){
        return !(a.getText().equals("") || b.getText().equals(""));         
    }
}
