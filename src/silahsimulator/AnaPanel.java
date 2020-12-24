
package silahsimulator;

import com.sun.tools.javac.Main;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import static java.text.NumberFormat.Field.INTEGER;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.openmbean.SimpleType.INTEGER;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.Timer;
import static silahsimulator.AnaPanel.btnAtes;
import static silahsimulator.AnaPanel.btnCoklu;
import static silahsimulator.AnaPanel.btnEkle;
import static silahsimulator.AnaPanel.btnSarjor;
import static silahsimulator.AnaPanel.durumEkrani;
import static silahsimulator.AnaPanel.gun1;
import static silahsimulator.AnaPanel.gun2;
import static silahsimulator.AnaPanel.gunFeatures;
import static silahsimulator.AnaPanel.none;
import static silahsimulator.AnaPanel.sarjorSayisi;
import static silahsimulator.AnaPanel.secilenO;
import static silahsimulator.AnaPanel.secilenT;
import static silahsimulator.AnaPanel.status;
import static silahsimulator.AnaPanel.menzil;
import static silahsimulator.Asker.OTOMATIK;
import static silahsimulator.Asker.TABANCA;
import static silahsimulator.PanelO.jOto;


public class AnaPanel extends JPanel{
    // Bu classta tamamen seçimlere ve silah kuşanmaya dayalı öğeler vardır
    //silah seçilene kadar görünürlük(visible) değişkeni false olan öğeler vardır bu öğeler silah kuşanılırsa görünür hale geçer
    // btnOto ve btnTab butonları otomatik ve tabanca silahına göre özelliklerini düzenleyebileceğimiz bir panel gösterir
    JButton btnOto,btnTab;
    static JButton btnAtes,btnSarjor,btnCoklu,btnEkle;
    PanelO panelO;
    PanelT panelT;
    static JSlider menzil;
    static JLabel gun1,gun2,gunFeatures,shadow,shadow2,shadow3,status,sarjorSayisi,durumEkrani,lblMenzil;
    static final int none = -1;
    static int secilenO = none,secilenT = none;
    public AnaPanel(){
        this.setBackground(Color.darkGray);
        
        JLabel l = new JLabel();
        l.setBounds(20, 15, 250, 20);
        l.setText("Kuşanmak istediğiniz silah tipini seçiniz:");
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l);
        
        panelO = new PanelO();
        panelO.setBounds(20, 90, 300, 600);
        panelO.setBackground(Color.GRAY);
        panelO.setLayout(null);
        add(panelO);
        
        panelT = new PanelT();
        panelT.setBounds(20, 90, 300, 600);
        panelT.setBackground(Color.GRAY);
        panelT.setLayout(null);
        add(panelT);
        
        panelO.setVisible(false);
        panelT.setVisible(false);
        
        btnOto = new JButton("Otomatik");
        btnOto.setBounds(20,50,90,30);
        btnOto.setBackground(new Color(59, 89, 182));
        btnOto.setForeground(Color.WHITE);
        btnOto.setFocusPainted(false);
        btnOto.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnOto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelT.setVisible(false);
                panelO.setVisible(true);
                
                
                
             
            }
        });
        add(btnOto);
        btnTab = new JButton("Tabanca");
        btnTab.setBounds(130, 50, 90, 30);
        btnTab.setBackground(new Color(59, 89, 182));
        btnTab.setForeground(Color.WHITE);
        btnTab.setFocusPainted(false);
        btnTab.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnTab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelO.setVisible(false);
                panelT.setVisible(true);
                
            }
        });
        add(btnTab);
        

        gun1 = new JLabel();
        gun1.setBounds(400,200,500,200);
        gun1.setIcon(new ImageIcon("src/icons/otoGun.png"));       
        add(gun1);
        
        gun2 = new JLabel();
        gun2.setBounds(525,248,500,200);
        gun2.setIcon(new ImageIcon("src/icons/tabGun.png"));       
        add(gun2);
        
        gun1.setVisible(false);
        gun2.setVisible(false);

       gunFeatures = new JLabel();
       gunFeatures.setBounds(1030, 2, 270, 150);
       gunFeatures.setForeground(Color.WHITE);
       gunFeatures.setFont(new Font("Tahoma", Font.BOLD, 13));
       String z = "Silah kuşanmadınız.";
       gunFeatures.setText(yaz(z,z,z,z));
       add(gunFeatures);
       
       
       
       shadow = new JLabel();
       shadow.setBounds(900,240,110,70);
       shadow.setIcon(new ImageIcon("src/icons/autoFire.png")); 
       add(shadow);
       
       shadow2 = new JLabel();
       shadow2.setBounds(920,210,110,70);
       shadow2.setIcon(new ImageIcon("src/icons/autoFire.png")); 
       add(shadow2);
       
       shadow3 = new JLabel();
       shadow3.setBounds(920,270,110,70);
       shadow3.setIcon(new ImageIcon("src/icons/autoFire.png")); 
       add(shadow3);
       
       
       shadow.setVisible(false);
       shadow2.setVisible(false);
       shadow3.setVisible(false);
       
       lblMenzil = new JLabel("Atış yapacağınız menzil(metre) : ");
       lblMenzil.setBounds(340, 470, 250, 20);
       lblMenzil.setForeground(Color.WHITE);
       lblMenzil.setFont(new Font("Tahoma", Font.BOLD, 13));
       add(lblMenzil);
       

        menzil = new JSlider(JSlider.HORIZONTAL,0, 1000, 400);
        menzil.setBounds(580, 460,380,50);
        menzil.setMajorTickSpacing(100);
        menzil.setMinorTickSpacing(10);
        menzil.setPaintTicks(true);
        menzil.setPaintLabels(true);
        menzil.setOpaque(false);
        menzil.setForeground(Color.WHITE);
        add(menzil);
       
       lblMenzil.setVisible(false);
       menzil.setVisible(false);
       
       btnAtes = new JButton(new ImageIcon("src/icons/ates.png"));
       btnAtes.setBounds(1030, 420, 100, 40);
       btnAtes.setFocusPainted(false);
       btnAtes.setBorderPainted(false);
       btnAtes.setBackground(Color.darkGray);
       btnAtes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                    if(secilenO == none){
                    TABANCA.get(secilenT).atesEt(Integer.valueOf(menzil.getValue()));
                    
                    
                } else {
                    OTOMATIK.get(secilenO).atesEt(Integer.valueOf(menzil.getValue()));
                    
                }
                
                
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shadow.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
       add(btnAtes);
       
       btnSarjor = new JButton(new ImageIcon("src/icons/sarjor.png"));
       btnSarjor.setBounds(1060, 480, 50, 100);
       btnSarjor.setFocusPainted(false);
       btnSarjor.setBorderPainted(false);
       btnSarjor.setBackground(Color.darkGray);
       btnSarjor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(secilenO != none){
                   OTOMATIK.get(secilenO).doldur();
               }
               else{
                   TABANCA.get(secilenT).doldur();
               }
            }
        });
       add(btnSarjor);
       
       btnCoklu = new JButton(new ImageIcon("src/icons/coklu.png"));
       btnCoklu.setBounds(1130, 400, 100, 60);
       btnCoklu.setFocusPainted(false);
       btnCoklu.setBorderPainted(false);
       btnCoklu.setBackground(Color.darkGray);
       btnCoklu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
                    if(secilenO != none){
                    OTOMATIK.get(secilenO).atesEt(Integer.valueOf(menzil.getValue()),0);     
                } 
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shadow.setVisible(false);
                shadow2.setVisible(false);
                shadow3.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
       add(btnCoklu);
       
       btnAtes.setVisible(false);
       btnSarjor.setVisible(false);
       btnCoklu.setVisible(false);
       
       status = new JLabel();
       status.setBounds(1065, 180, 200, 100);
       status.setForeground(Color.WHITE);
       status.setFont(new Font("A", Font.ROMAN_BASELINE, 30));
       add(status);
       
       sarjorSayisi = new JLabel();
       sarjorSayisi.setBounds(550, 50, 300, 100);
       sarjorSayisi.setForeground(Color.WHITE);
       sarjorSayisi.setFont(new Font("A", Font.ROMAN_BASELINE, 17));
       add(sarjorSayisi);
       
        btnEkle = new JButton("Şarjör Ekle");
        btnEkle.setBounds(600,130,120,30);
        btnEkle.setBackground(Color.RED);
        btnEkle.setForeground(Color.WHITE);
        btnEkle.setFocusPainted(false);
        btnEkle.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secilenO != none){
                   OTOMATIK.get(secilenO).sarjorEkle();
               }
               else{
                   TABANCA.get(secilenT).sarjorEkle();
               }
            }
        });
        add(btnEkle);
       
       status.setVisible(false);
       sarjorSayisi.setVisible(false);
       btnEkle.setVisible(false);
       
       durumEkrani = new JLabel();
       durumEkrani.setBounds(460, 570, 450, 100);
       durumEkrani.setForeground(Color.YELLOW);
       durumEkrani.setFont(new Font("Tahoma", Font.BOLD, 15));
       durumEkrani.setText("<html>Envanterinize silah ekleyin, kuşanın,<br/>şarjörünü ekleyin,doldurun ateş edin.<html>");
       add(durumEkrani);
    }
    
    static String yaz(String isim,String kapasite,String menzil,String atim){
        String askerAd = Asker.getAskerIsim();
        String askerRutbe = Asker.getAskerRutbe();
        String tag,line,a,b,c,d,ad,rutbe;
        tag = "<html>";
        line = "<br/>";
        ad = "Asker adı : ";
        rutbe = "Asker rütbesi : ";
        a = "Silah ismi : ";
        b = "Şarjör kapasitesi : ";
        c = "Silah menzili : ";
        d = "Çoklu atım sayısı : ";
        return tag+ad+askerAd+line+rutbe+askerRutbe+line+line+a+isim+line+b+kapasite+line+c+menzil+line+d+atim+tag;
    }
    
    static String yaz(String isim,String kapasite,String menzil){
        String askerAd = Asker.getAskerIsim();
        String askerRutbe = Asker.getAskerRutbe();
        String tag,line,a,b,c,d,ad,rutbe;
        tag = "<html>";
        line = "<br/>";
        ad = "Asker adı : ";
        rutbe = "Asker rütbesi : ";
        a = "Silah ismi : ";
        b = "Şarjör kapasitesi : ";
        c = "Silah menzili : ";
        d = "Çoklu atım sayısı : N/A";
        return tag+ad+askerAd+line+rutbe+askerRutbe+line+line+a+isim+line+b+kapasite+line+c+menzil+line+d+tag;
    }
    
     
    
     
    
     public void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }
    
}

 class PanelO extends JPanel{
    private JTextField txtName, txtCapacity, txtRange,txtThrow;
    private JButton btnCreate,btnHide,btnTake;
    DefaultListModel<String> dOto;
    static JList <String> jOto;
     public PanelO(){
         JLabel l1 = new JLabel();
         l1.setBounds(30, 30, 250, 20);
         l1.setText("Silahınıza vermek istediğiniz ismi girin:");
         l1.setForeground(Color.YELLOW);
         l1.setFont(new Font("Tahoma", Font.BOLD, 11));
         add(l1);
        
         txtName = new JTextField("Otomatik");        
         txtName.setBounds(80,75, 130, 25);
         txtName.setColumns(12);
         add(txtName);
         
         
        JLabel l2 = new JLabel();
        l2.setBounds(45, 120, 250, 20);
        l2.setText("Silahınızın şarjör kapasitesini girin:");
        l2.setForeground(Color.YELLOW);
        l2.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l2);
        
        btnHide = new JButton("x");
        btnHide.setBounds(255, 2, 45, 20);
        btnHide.setBackground(Color.GRAY);
        btnHide.setForeground(Color.WHITE);
        btnHide.setFocusPainted(false);
        btnHide.setFont(new Font("Tahoma", Font.BOLD, 11)); 
        btnHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                jOto.clearSelection();
            }
        });
        add(btnHide);
        

         
         txtCapacity = new JTextField("30");
         txtCapacity.setColumns(2);
         txtCapacity.setBounds(80,150, 130, 25);
                  txtCapacity.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = txtCapacity.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
               txtCapacity.setEditable(true);
               
            }else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                txtCapacity.setEditable(true);
            } else {
               txtCapacity.setEditable(false);
            }
         }
      });
         add(txtCapacity);
         
         
        JLabel l3 = new JLabel();
        l3.setBounds(75, 190, 250, 20);
        l3.setText("Silahınızın menzilini girin:");
        l3.setForeground(Color.YELLOW);
        l3.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l3);
        
        JLabel m = new JLabel();
        m.setBounds(220,220, 130, 25);
        m.setText("(metre)");
        m.setForeground(Color.YELLOW);
        m.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(m);
        
        txtRange = new JTextField("400");
        txtRange.setColumns(2);
        txtRange.setBounds(80,220, 130, 25);
        txtRange.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = txtRange.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
               txtRange.setEditable(true);
               
            }else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                txtRange.setEditable(true);
            } else {
               txtRange.setEditable(false);
            }
         }
      });
        add(txtRange);
        
        
        JLabel l4 = new JLabel();
        l4.setBounds(80, 260, 250, 20);
        l4.setText("Seri atım sayısını girin:");
        l4.setForeground(Color.YELLOW);
        l4.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l4);
        
        txtThrow = new JTextField("3");
        txtThrow.setColumns(16);
        txtThrow.setBounds(80,290, 130, 25);
        txtThrow.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = txtThrow.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
               txtThrow.setEditable(true);
               
            }else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                txtThrow.setEditable(true);
            } else {
               txtThrow.setEditable(false);
            }
         }
      });
        add(txtThrow);
        
        btnCreate = new JButton("EKLE");
        btnCreate.setBounds(100, 350, 80, 30);
        btnCreate.setBackground(Color.BLACK);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setFocusPainted(false);
        btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11)); 
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(txtName.getText().equals("") || txtCapacity.getText().equals("") || txtRange.getText().equals("") || txtThrow.getText().equals(""))){
                    OTOMATIK.add(new OtomatikSilah(txtName.getText(),Integer.valueOf(txtCapacity.getText()),Integer.valueOf(txtRange.getText()),Integer.valueOf(txtThrow.getText())));
                    dOto.addElement(OTOMATIK.get(OTOMATIK.size()-1).getSilahIsmi());
                }
                else {
                    durumEkrani.setText("Boş alan bırakmamalısınız");
                }
                

            }
        });
        add(btnCreate);
        
        dOto = new DefaultListModel<>();
        
        jOto = new JList<>(dOto);
        jOto.setBounds(80,420,130,175);
        jOto.setBackground(Color.DARK_GRAY);
        jOto.setForeground(Color.YELLOW);
        add(jOto);
        
        btnTake = new JButton("KUŞAN");
        btnTake.setBounds(220, 480, 70, 30);
        btnTake.setBackground(Color.RED);
        btnTake.setForeground(Color.WHITE);
        btnTake.setFocusPainted(false);
        btnTake.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jOto.getSelectedIndex() >= 0){
                    int index = jOto.getSelectedIndex();
                    setVisible(false);
                    menzil.setVisible(true);
                    btnAtes.setVisible(true);
                    btnSarjor.setVisible(true);
                    btnCoklu.setVisible(true);
                    status.setVisible(true);
                    if(OTOMATIK.get(index).getSecilenS() == -1){
                        status.setText("0");
                    }
                    else{
                        status.setText(String.valueOf(OTOMATIK.get(index).getOtoList().get(0).getMermiSayisi()));
                    }
                    durumEkrani.setText("");
                    sarjorSayisi.setVisible(true);
                    btnEkle.setVisible(true);
                    AnaPanel.lblMenzil.setVisible(true);
                    AnaPanel.menzil.setVisible(true);
                    sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(OTOMATIK.get(index).getOtoList().size()));
                    gun2.setVisible(false);
                    gun1.setVisible(true);
                    secilenT = none;
                    secilenO = index;
                    jOto.clearSelection();
                    gunFeatures.setText(AnaPanel.yaz(OTOMATIK.get(index).getSilahIsmi(),String.valueOf(OTOMATIK.get(index).getSilahKapasitesi())
                            ,String.valueOf(OTOMATIK.get(index).getSilahMenzili()),String.valueOf(OTOMATIK.get(index).getSeriAtim())));
                    
                }
                
            }
        });
        add(btnTake);
     }
   
}


class PanelT extends JPanel{
    private JTextField txtName, txtCapacity, txtRange;
    private JButton btnCreate,btnHide,btnTake;
    DefaultListModel<String> dTab;
    JList <String> jTab;
    public PanelT(){
        JLabel l1 = new JLabel();
         l1.setBounds(30, 30, 250, 20);
         l1.setText("Silahınıza vermek istediğiniz ismi girin:");
         l1.setForeground(Color.YELLOW);
         l1.setFont(new Font("Tahoma", Font.BOLD, 11));
         add(l1);
        
         txtName = new JTextField("Tabanca");        
         txtName.setBounds(80,75, 130, 25);
         txtName.setColumns(12);
         add(txtName);
         
         
        JLabel l2 = new JLabel();
        l2.setBounds(45, 120, 250, 20);
        l2.setText("Silahınızın şarjör kapasitesini girin:");
        l2.setForeground(Color.YELLOW);
        l2.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l2);
        
        btnHide = new JButton("x");
        btnHide.setBounds(255, 2, 45, 20);
        btnHide.setBackground(Color.GRAY);
        btnHide.setForeground(Color.WHITE);
        btnHide.setFocusPainted(false);
        btnHide.setFont(new Font("Tahoma", Font.BOLD, 11)); 
        btnHide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                jTab.clearSelection();

            }
        });
        add(btnHide);

         
         txtCapacity = new JTextField("12");
         txtCapacity.setColumns(2);
         txtCapacity.setBounds(80,150, 130, 25);
                  txtCapacity.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = txtCapacity.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
               txtCapacity.setEditable(true);
               
            }else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                txtCapacity.setEditable(true);
            } else {
               txtCapacity.setEditable(false);
            }
         }
      });
         add(txtCapacity);
         
         
        JLabel l3 = new JLabel();
        l3.setBounds(75, 190, 250, 20);
        l3.setText("Silahınızın menzilini girin:");
        l3.setForeground(Color.YELLOW);
        l3.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(l3);
        
        JLabel m = new JLabel();
        m.setBounds(220,220, 130, 25);
        m.setText("(metre)");
        m.setForeground(Color.YELLOW);
        m.setFont(new Font("Tahoma", Font.BOLD, 11));
        add(m);
        
        txtRange = new JTextField("200");
        txtRange.setColumns(2);
        txtRange.setBounds(80,220, 130, 25);
        txtRange.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent ke) {
            String value = txtRange.getText();
            int l = value.length();
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
               txtRange.setEditable(true);
               
            }else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                txtRange.setEditable(true);
            } else {
               txtRange.setEditable(false);
            }
         }
      });
        add(txtRange);
        
        btnCreate = new JButton("EKLE");
        btnCreate.setBounds(100, 350, 80, 30);
        btnCreate.setBackground(Color.BLACK);
        btnCreate.setForeground(Color.WHITE);
        btnCreate.setFocusPainted(false);
        btnCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
         btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(txtName.getText().equals("") || txtCapacity.getText().equals("") || txtRange.getText().equals(""))){
                    TABANCA.add(new Tabanca(txtName.getText(),Integer.valueOf(txtCapacity.getText()),Integer.valueOf(txtRange.getText())));
                    dTab.addElement(TABANCA.get(TABANCA.size()-1).getSilahIsmi());
                } else {
                    durumEkrani.setText("Boş alan bırakmamalısınız");
                }
                

            }
        });
        add(btnCreate);
        
        dTab = new DefaultListModel<>();
        
        jTab = new JList<>(dTab);
        jTab.setBounds(80,420,130,175);
        jTab.setBackground(Color.DARK_GRAY);
        jTab.setForeground(Color.YELLOW);
        add(jTab);
        
        btnTake = new JButton("KUŞAN");
        btnTake.setBounds(220, 480, 70, 30);
        btnTake.setBackground(Color.RED);
        btnTake.setForeground(Color.WHITE);
        btnTake.setFocusPainted(false);
        btnTake.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnTake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jTab.getSelectedIndex() >= 0){
                    int index = jTab.getSelectedIndex();
                    setVisible(false);
                    menzil.setVisible(true);
                    btnAtes.setVisible(true);
                    btnSarjor.setVisible(true);
                    btnCoklu.setVisible(false);
                    status.setVisible(true);
                    if(TABANCA.get(index).getSecilenS() == -1){
                        status.setText("0");
                    }
                    else{
                        status.setText(String.valueOf(TABANCA.get(index).getTabList().get(0).getMermiSayisi()));
                    }
                    durumEkrani.setText("");
                    btnEkle.setVisible(true);
                    AnaPanel.lblMenzil.setVisible(true);
                    AnaPanel.menzil.setVisible(true);
                    sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(TABANCA.get(index).getTabList().size()));
                    sarjorSayisi.setVisible(true);
                    gun2.setVisible(true);
                    gun1.setVisible(false);
                    secilenT = index;
                    secilenO = none;
                    jTab.clearSelection();
                    gunFeatures.setText(AnaPanel.yaz(TABANCA.get(index).getSilahIsmi(),String.valueOf(TABANCA.get(index).getSilahKapasitesi())
                            ,String.valueOf(TABANCA.get(index).getSilahMenzili()))); 
                }
                
            }
        });
        add(btnTake);
    }
}
