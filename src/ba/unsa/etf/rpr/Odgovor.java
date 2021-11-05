package ba.unsa.etf.rpr;

public class Odgovor {
    String TekstOdgovora;
    boolean Tacno;


    Odgovor(String tekst, boolean tacan){ //konstruktor klase  Odgovor
        this.TekstOdgovora =tekst;
        this.Tacno =tacan;
    }
    public String getTekstOdgovora() {
        return TekstOdgovora;
    }

    public void setTekstOdgovora(String tekstOdgovora) {
        this.TekstOdgovora = tekstOdgovora;
    }

    public void setTacno(boolean tacno) {
        this.Tacno = tacno;
    }

    public boolean isTacno() {
        return Tacno;
    }
}
