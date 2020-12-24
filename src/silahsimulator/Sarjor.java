
package silahsimulator;

import static silahsimulator.AnaPanel.secilenO;
import static silahsimulator.AnaPanel.secilenT;
import static silahsimulator.Asker.OTOMATIK;


public class Sarjor {
    private int mermiKapasitesi; //alabilceği toplam mermi
    private int mermiSayisi; //anlık mermiyi tutar
    
    public Sarjor(int mermiKapasitesi, int mermiSayisi){
        this.mermiKapasitesi = mermiKapasitesi;
        this.mermiSayisi = mermiSayisi;
    }
    
    
    public void azalt(){
        if(mermiSayisi !=0){
            mermiSayisi--;
        }
        
    }
    
    public void doldur(){
        mermiSayisi=mermiKapasitesi;
    }       

    
    public int getMermiSayisi() {
        return mermiSayisi;
    }
    
}
