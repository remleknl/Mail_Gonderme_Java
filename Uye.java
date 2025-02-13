public class Uye {
    private String tip;
    private String isim;
    private String soyisim;
    private String email;

    public Uye(String tip, String isim, String soyisim, String email) {
        this.tip = tip;
        this.isim = isim;
        this.soyisim = soyisim;
        this.email = email;
    }

    public String getTip() {
        return tip;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getEmail() {
        return email;
    }
}
