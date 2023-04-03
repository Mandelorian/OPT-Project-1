package com.mandelorian.klant;

public class Klant  {



    private String naam;
    private String bedrijfNaam;
    private String stad;
    private KlantType klanttype;
    private String postcode;
    private String email;


    private String straat;


    public Klant(String bedrijfsnaam, String email, String stad,KlantType klanttype, String postcode, String naam,String straat) {
        this.bedrijfNaam = bedrijfsnaam;
        this.email = email;
        this.stad = stad;
        this.klanttype = klanttype;
        this.postcode=postcode;
        this.naam=naam;
        this.straat=straat;
    }

    // Getters en setters
    public String getBedrijfNaam() {
        return bedrijfNaam;
    }

    public void setBedrijfNaam(String bedrijfNaam) {
        this.bedrijfNaam = bedrijfNaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public KlantType getKlanttype() {
        return klanttype;
    }

    public void setKlanttype(KlantType klanttype) {
        this.klanttype = klanttype;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

}



