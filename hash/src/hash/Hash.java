/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hash;

/**
 *
 * @author havva
 */
public class Hash {

    /*
    gboyut hash'in güncel boyutu, maksboyut hash'in maksimum boyutu
    
     */
    private int gboyut, maksboyut;
    private String[] keys, values;

    Hash(int maksboyut) {
        this.maksboyut = maksboyut;
        gboyut = 0;
        keys = new String[maksboyut];
        values = new String[maksboyut];
    }

    boolean doluMu() {
        return gboyut == maksboyut;
    }

    boolean bosMu() {
        return gboyut == 0;
    }

    int hash(String key) {
        return key.hashCode() % maksboyut;
    }

    void ekle(String key, String value) {
        int tmp = hash(key);
        int i = tmp;
        do {
            /*
            eğer keys[i] değeri null sa keys[i] ve values[i] değerine 
            girilen sayıyı atıyoruz
             */

            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;
                gboyut++;
                return;
            }
            /*
            girilen anahtar değeri keyste varsa  value değişkenine
            dizideki değeri atıyoruz
             */
            if (key.equals(keys[i])) {
                values[i] = value;
                return;
            }
            /*
            i'yi döngüsel arttırıyoruz
             */

            i = (i + 1) % maksboyut;
            /*
            aynı anahtar değerini eklemıyoruz
             */
        } while (i != tmp);

    }

    String get(String key) {
        /*
        girmiş oldugun anahtar değerini 
        dizide arıyacak bulduğu zaman o anahtar değerine karşılık
        gelen değeri value dizisinden return edicek
        eğer bulamazsa i değerini döngüsel olarak arttırıcak
        eğer hiç bulamazsa girilen anahtar değerin dizide olmadıgını
        döndürücek
         */

        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % maksboyut;
        }
        return null;
    }

    void sil(String key) {
        /*
        ilk if'te aranan key yoksa  döndürüyor
        değilse 
        i'yi anahtarın değerine atıyoruz
        while döngüsü ile arama yapıcaz
        arama bittikten sonra 
        keys[i] ve values[i] ye null atıyoruz
        
        daha sonra for döngüsü ile diziyi düzenliyoruz
        i'yi sildiğimiz index'in bir fazlasından başlatıyoruz 
        daha sonra null değerine gelene kadar döngüsel olarak
        arttırıyoruz
        tmp1 ve tmp2 değerlerine keys ve value değerlerini
        ekliyoruz
        
        daha sonra keys[i] ve values[i] değerlerine null değerini
        verip güncel boyutu azaltıyoruz
        ardından dizi tamamen dolu olana kadar döngü devam ediyoru
        daha sonra tmp1 ve tmp2'yi diziye ekliyoruz ve tekrardan 
        for döngüsü başa dönüyor
        
        */ 
                
        if (get(key) == null) {
            return;
        } else {
            int i = hash(key);
            while (!key.equals(keys[i])) {
                i = (i + 1) % maksboyut;
            }
            keys[i] = values[i] = null;
            for (i = (i + 1) % maksboyut; keys[i] != null;
                    i = (i + 1) % maksboyut) {
                String tmp1 = keys[i], tmp2 = values[i];
                keys[i] = values[i] = null;
                gboyut--;
                ekle(tmp1, tmp2);
            }
            gboyut--;
        }
    }

    void printHash() {
        System.out.println(" Hash Tablosu");
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                System.out.println(i + "->" + keys[i] + "-" + values[i]);
            }
           
        }
    }

    public static void main(String[] args) {

    }

} //ikili arama ağaçları sınav
