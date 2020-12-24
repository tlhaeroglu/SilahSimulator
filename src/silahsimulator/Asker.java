
package silahsimulator;

import java.util.ArrayList;

public class Asker {
    static String askerIsim;
    static String askerRutbe;
    public static ArrayList<OtomatikSilah> OTOMATIK = new ArrayList<>();
    public static ArrayList<Tabanca> TABANCA = new ArrayList<>();
    
    public Asker(String isim, String rutbe){
        this.askerIsim = isim;
        this.askerRutbe = rutbe;
    }

    
    public static String getAskerIsim() {
        return askerIsim;
    }

    
    public static String getAskerRutbe() {
        return askerRutbe;
    }

   
    

    
}
