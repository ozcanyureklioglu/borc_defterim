package com.example.ozcanyureklioglu.bordefterim;

public class person {
    private int id;
    private String isim;
    private String soyisim;
    private double borcMiktari;


    public person(int id, String isim, String soyisim, double borcMiktari) {
        this.id = id;
        this.isim = isim;
        this.soyisim = soyisim;
        this.borcMiktari = borcMiktari;
    }

    public int getId() {
        return id;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public double getBorcMiktari() {
        return borcMiktari;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public void setSoyisim(String soyisim) {
        this.soyisim = soyisim;
    }

    public void setBorcMiktari(double borcMiktari) {
        this.borcMiktari = borcMiktari;
    }
}
