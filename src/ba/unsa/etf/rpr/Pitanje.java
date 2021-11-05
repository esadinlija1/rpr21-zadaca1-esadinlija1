package ba.unsa.etf.rpr;

import java.util.*;


public class Pitanje {
    HashMap<String, Odgovor> odgovori;
    String tekst;
    double brojPoena;

    Pitanje(String tekst, double brojPoena) {
        this.tekst = tekst;
        this.brojPoena = brojPoena;
        odgovori = new HashMap<String, Odgovor>();
    }

    public HashMap<String, Odgovor> getOdgovori() {
        return odgovori;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public void setOdgovori(HashMap<String, Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public double getBrojPoena() {
        return brojPoena;
    }

    public void setBrojPoena(double brojPoena) {
        this.brojPoena = brojPoena;
    }

    public void dodajOdgovor(String id, String tekst, boolean tacno) {
        odgovori.put(id, new Odgovor(tekst, tacno));
    }

    public void obrisiOdgovor(String id) {
        if (odgovori.containsKey(id) == false)
            throw new IllegalArgumentException("Id odgovora mora biti jedinstven");
        odgovori.remove(id);
    }

    public ArrayList<Odgovor> dajListuOdgovora() {
        ArrayList<Odgovor> listaOdgovora = new ArrayList<Odgovor>();
        for (Odgovor i : odgovori.values()) {
            listaOdgovora.add(i);
        }
        return listaOdgovora;
    }

    private String mapaUString() {
        String mapa = new String("");
        int brojac=0;
        for (String i : odgovori.keySet()) {
            if(brojac+1==odgovori.size())
                mapa = mapa + "    " + i + ": " + odgovori.get(i).getTekstOdgovora();
            else
            mapa = mapa + "    " + i + ": " + odgovori.get(i).getTekstOdgovora() + "\n";
            brojac++;
        }
        return mapa;
    }

    @Override
    public String toString() {
        String s = new String("");
        s = s + tekst + "(" + brojPoena + "b)" + "\n" + mapaUString();
        return s;
    }

    public double izracunajPoene(List<String> listaOdgovora,SistemBodovanja sistemBodovanja){
         for(String i:listaOdgovora) {
             if (odgovori.containsKey(i) == false)
                 throw new IllegalArgumentException("Odabran je nepostojeći odgovor");
         }
             TreeSet<String> kopijaListe=new TreeSet<String>(listaOdgovora); //provjera ima li duplikata
             if(kopijaListe.size()!=listaOdgovora.size())
                 throw new IllegalArgumentException("Postoje duplikati među odabrnanim odgovorima");
           TreeSet<String> tacniOdgovori=new TreeSet<String>();
           for(String s:odgovori.keySet()) {
               if(odgovori.get(s).isTacno())
                   tacniOdgovori.add(s);
           }
           if(kopijaListe.equals(tacniOdgovori))
               return brojPoena;
           int brojTacnihZaokruzenih=0;
           for(String k:listaOdgovora){
               if(odgovori.get(k).isTacno())
                   brojTacnihZaokruzenih=brojTacnihZaokruzenih+1;
           }
           switch(sistemBodovanja){
               case BINARNO:
                   if(kopijaListe.equals(tacniOdgovori)==false)
                       return 0;
               case PARCIJALNO:
                   if(brojTacnihZaokruzenih== kopijaListe.size() && brojTacnihZaokruzenih<tacniOdgovori.size())
                       return (brojTacnihZaokruzenih/tacniOdgovori.size())*brojPoena;
                   else if(brojTacnihZaokruzenih<kopijaListe.size())
                       return 0;
               case PARCIJALNO_SA_NEGATIVNIM:
                   if(brojTacnihZaokruzenih== kopijaListe.size() && brojTacnihZaokruzenih<tacniOdgovori.size())
                       return (brojTacnihZaokruzenih/tacniOdgovori.size())*brojPoena;
                   else if(brojTacnihZaokruzenih!=0 && brojTacnihZaokruzenih< kopijaListe.size())
                       return brojPoena/2;



           }
           return 0;

    }

}
