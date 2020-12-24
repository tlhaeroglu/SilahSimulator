
package silahsimulator;

import com.sun.tools.javac.Main;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static silahsimulator.AnaPanel.durumEkrani;
import static silahsimulator.AnaPanel.sarjorSayisi;
import static silahsimulator.AnaPanel.shadow;
import static silahsimulator.AnaPanel.shadow2;
import static silahsimulator.AnaPanel.shadow3;
import static silahsimulator.Asker.OTOMATIK;


public class OtomatikSilah extends Silah implements Techizat {

    private String isim;
    private int kapasite;
    private int menzil;
    private int seriAtim;
    private ArrayList<Sarjor> otoList = new ArrayList<>();
    private int secilenS = -1;
    private int doldur = -1;
    
    public OtomatikSilah(String isim, int kapasite, int menzil,int seriAtim){
        this.isim = isim;
        this.kapasite = kapasite;
        this.menzil = menzil;
        this.seriAtim = seriAtim;
    }

    @Override
    public String getSilahIsmi() {
        return this.isim;
    }

    @Override
    public int getSilahMenzili() {
        return this.menzil;
    }

    @Override
    public int getSilahKapasitesi() {
        return this.kapasite;
    }
    public int getSeriAtim() {
        return this.seriAtim;
    }
    
    public int getSecilenS(){
        return this.secilenS;
    }

    @Override
    public void sarjorEkle() {
        secilenS = 0;
        getOtoList().add(new Sarjor(kapasite,kapasite));
        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()));
        
    }
    //tekli atışlar için
    @Override
    public void atesEt(int menzil) {
        //"src/sounds/shoot.wav"
      
        if(secilenS != -1 && doldur==0){
            if(getOtoList().get(secilenS).getMermiSayisi() != 0){
                if(this.menzil >= menzil){
                    durumEkrani.setText("Atış başarılı hedefe ulaştı");
                    getOtoList().get(secilenS).azalt();
                    shadow.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi()));
                    if(getOtoList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()-1));
                        getOtoList().remove(secilenS);
                        durumEkrani.setText("Merminiz tükendi.");  
                        secilenS=-1;
                        doldur = -1;
                    }
                    
                } else {
                    getOtoList().get(secilenS).azalt();
                    shadow.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi()));
                    durumEkrani.setText("Atış hedefe ulaşmadı");
                    if(getOtoList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()-1));
                        getOtoList().remove(secilenS);
                        durumEkrani.setText("Merminiz tükendi.");  
                        secilenS=-1;
                        doldur = -1;
                    }
                }
            } else{
                durumEkrani.setText("Yeterli Mermi yok");  
            }
        } else{
            durumEkrani.setText("Merminiz yok, şarjör eklemeli veya varsa doldurmalısınız");
        }
        
    }
     // çoklu atışlar için
    public void atesEt(int menzil,int seriAtim){
        seriAtim = this.seriAtim;
        if(secilenS != -1 && doldur ==0){
            if(getOtoList().get(secilenS).getMermiSayisi() != 0 && getOtoList().get(secilenS).getMermiSayisi() - seriAtim >= 0 ){
                if(this.menzil >= menzil){
                    durumEkrani.setText("Atış başarılı hedefe ulaştı");
                    for(int i = 0; i<seriAtim ; i++){
                      getOtoList().get(secilenS).azalt();  
                    }
                    shadow.setVisible(true);
                    shadow2.setVisible(true);
                    shadow3.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi()));
                    if(getOtoList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()-1));
                        getOtoList().remove(secilenS);
                        durumEkrani.setText("Merminiz tükendi.");  
                        secilenS=-1;
                    }
                    
                } else {
                    for(int i = 0; i<seriAtim ; i++){
                      getOtoList().get(secilenS).azalt();  
                    }
                    shadow.setVisible(true);
                    shadow2.setVisible(true);
                    shadow3.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi()));
                    durumEkrani.setText("Atış hedefe ulaşmadı");
                    if(getOtoList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()-1));
                        getOtoList().remove(secilenS);
                        durumEkrani.setText("Merminiz tükendi.");  
                        secilenS=-1;
                    }
                }
            } else{
                durumEkrani.setText("Yeterli Mermi yok");  
            }
        } else{
            durumEkrani.setText("Merminiz yok, şarjör eklemeli veya varsa doldurmalısınız");
        }
    }
    
    public ArrayList<Sarjor> getOtoList() {
        return otoList;
    }

    @Override
    public void doldur() {
        if(otoList.size() == 0){
            durumEkrani.setText("Şarjörünüz yok");
        } else {
            if(otoList.size() > 1){
                getOtoList().remove(secilenS);
                sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getOtoList().size()));
                AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi())); 
                doldur =0;
            }else{
               doldur =0;
               durumEkrani.setText("Son şarjörü sıkıyorsunuz");
            AnaPanel.status.setText(String.valueOf(getOtoList().get(secilenS).getMermiSayisi())); 
            }
            
        }
            
    }

   
    
    
    
    
    
    
}
