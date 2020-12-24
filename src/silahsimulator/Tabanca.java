
package silahsimulator;

import java.util.ArrayList;
import static silahsimulator.AnaPanel.durumEkrani;
import static silahsimulator.AnaPanel.sarjorSayisi;
import static silahsimulator.AnaPanel.shadow;


public class Tabanca extends Silah implements Techizat {
    private String isim;
    private int kapasite;
    private int menzil;
    private ArrayList<Sarjor> tabList = new ArrayList<>();
    private int secilenS = -1;
    private int doldur = -1;
    
    public Tabanca(String isim, int kapasite, int menzil){
        this.isim = isim;
        this.kapasite = kapasite;
        this.menzil = menzil;
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
    
    public int getSecilenS(){
        return this.secilenS;
    }

    @Override
    public void sarjorEkle() { 
        secilenS = 0;
        getTabList().add(new Sarjor(kapasite,kapasite));
        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getTabList().size()));
    }

    @Override
    public void atesEt(int menzil) {
        if(secilenS != -1 && doldur == 0){
            if(getTabList().get(secilenS).getMermiSayisi() != 0){
                if(this.menzil >= menzil){
                    durumEkrani.setText("Atış başarılı hedefe ulaştı");
                    getTabList().get(secilenS).azalt();
                    shadow.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getTabList().get(secilenS).getMermiSayisi()));
                    if(getTabList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getTabList().size()-1));
                        getTabList().remove(secilenS);
                        durumEkrani.setText("Merminiz tükendi.");  
                        secilenS=-1;
                        doldur = -1;
                    }
                    
                } else {
                    getTabList().get(secilenS).azalt();
                    shadow.setVisible(true);
                    AnaPanel.status.setText(String.valueOf(getTabList().get(secilenS).getMermiSayisi()));
                    durumEkrani.setText("Atış hedefe ulaşmadı");
                    if(getTabList().get(secilenS).getMermiSayisi() == 0){
                        sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getTabList().size()-1));
                        getTabList().remove(secilenS);
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

    public ArrayList<Sarjor> getTabList() {
        return tabList;
    }

    @Override
    public void doldur() {
        if(tabList.size() == 0){
            durumEkrani.setText("Şarjörünüz yok");
        } else {
            if(tabList.size() > 1){
                getTabList().remove(secilenS);
                sarjorSayisi.setText("Sahip olunan şarjör sayısı : "+String.valueOf(getTabList().size()));
                AnaPanel.status.setText(String.valueOf(getTabList().get(secilenS).getMermiSayisi())); 
                doldur = 0;
            }else{
               doldur = 0;
               durumEkrani.setText("Son şarjörü sıkıyorsunuz");
            AnaPanel.status.setText(String.valueOf(getTabList().get(secilenS).getMermiSayisi())); 
            }
            
        }
    }

}
