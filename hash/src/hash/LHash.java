package hash;

class dugum {

    String key;
    int value;
    dugum ileri;

    public dugum(String key, int value) {
        this.key = key;
        this.value = value;
        ileri = null;
    }
}

public class LHash { //linked hash yani

    int mboyut, boyut;
    dugum[] tablo;

    public LHash(int mboyut) {
        this.mboyut = mboyut;
        boyut = 0;
        tablo = new dugum[mboyut];
        for (int i = 0; i < tablo.length; i++) {
            tablo[i] = null;
        }

    }

    int myhash(String key) {
        int h = key.hashCode();
        return h % mboyut;
    }

    int get(String key) {
        int hash = myhash(key);
        if (tablo[hash] == null) {
            return -1;
        } else {
            dugum tmp = tablo[hash];
            while (tmp != null && tmp.key.equals(key)) {
                tmp = tmp.ileri;

            }
            if (tmp == null) {
                return -1;
            } else {
                return tmp.value;
            }
        }
    }

}
