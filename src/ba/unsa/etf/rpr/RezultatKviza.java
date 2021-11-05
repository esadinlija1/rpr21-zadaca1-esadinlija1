package ba.unsa.etf.rpr;

import java.util.Map;

public class RezultatKviza {
Kviz kviz;
double total;
Map<Pitanje,Double> bodovi;
RezultatKviza(Kviz kviz){
    this.kviz=kviz;
    this.total=0;
}

    public Kviz getKviz() {
        return kviz;
    }

    public void setKviz(Kviz kviz) {
        this.kviz = kviz;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Map<Pitanje, Double> getBodovi() {
        return bodovi;
    }

    public void setBodovi(Map<Pitanje, Double> bodovi) {
        this.bodovi = bodovi;
    }

    @Override
    public String toString() {
       String s=new String("");
       s="Na kvizu \"" + kviz.getNaziv() + "\" ostavarili ste ukupno " + String.valueOf(total) + " poena. Raspored po pitanjima:\n";
       for(Pitanje p:this.bodovi.keySet()){
           s=s + p.getTekst() + "-" + this.bodovi.get(p).toString()+"b\n";
       }
       return s;
    }
}
