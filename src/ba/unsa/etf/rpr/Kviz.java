package ba.unsa.etf.rpr;

import java.util.ArrayList;
import java.util.Map;

public class Kviz {
String naziv;
ArrayList<Pitanje> pitanja;
SistemBodovanja sistemBodovanja;
Kviz(String naziv, SistemBodovanja sistemBodovanja){
    this.naziv=naziv;
    this.sistemBodovanja=sistemBodovanja;
    pitanja=new ArrayList<Pitanje>();
}

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public SistemBodovanja getSistemBodovanja() {
        return sistemBodovanja;
    }

    public void setSistemBodovanja(SistemBodovanja sistemBodovanja) {
        this.sistemBodovanja = sistemBodovanja;
    }

    public ArrayList<Pitanje> getPitanja() {
        return pitanja;
    }

    public void setPitanja(ArrayList<Pitanje> pitanja) {
        this.pitanja = pitanja;
    }
    public void dodajPitanje(Pitanje p){
    for(Pitanje i:pitanja){
        if(i.getTekst().equalsIgnoreCase(p.getTekst()))
            throw new IllegalArgumentException("Ne možete dodati pitanje sa tekstom koji već postoji");
    }
    pitanja.add(p);
    }
    @Override
    public String toString(){
        String s=new String("");
        s="Kviz \"" + naziv + "\" boduje se " + sistemBodovanja.toString() + ". Pitanja:\n";
        for(Pitanje i:pitanja){
            s+=i.toString();
        }
        return s;
    }
    public RezultatKviza predajKviz(Map<Pitanje,ArrayList<String>> pitanjaIOdgovori){
        RezultatKviza rezultat=new RezultatKviza(this);
        double total= rezultat.getTotal();
        for(Pitanje p:pitanjaIOdgovori.keySet()){
            total = total + p.izracunajPoene(pitanjaIOdgovori.get(p), this.sistemBodovanja);
            rezultat.getBodovi().put(p,p.izracunajPoene(pitanjaIOdgovori.get(p),this.sistemBodovanja));
        }
        rezultat.setTotal(total);
        return rezultat;
    }

}
