package ba.unsa.etf.rpr;

public enum SistemBodovanja {
    BINARNO("binarno"),PARCIJALNO("parcijalno"),PARCIJALNO_SA_NEGATIVNIM("parcijalno_sa_negativnim");
    private String naziv;
    private SistemBodovanja(String naziv){
        this.naziv=naziv;
    }
    @Override
    public String toString(){
        return this.naziv;
    }

}
