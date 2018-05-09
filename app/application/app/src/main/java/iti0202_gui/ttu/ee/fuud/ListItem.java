package iti0202_gui.ttu.ee.fuud;

public class ListItem {

    private String provider;
    private String name_est;
    private String name_eng;
    private String price;


    public ListItem(String provider, String price, String name_eng, String name_est) {
        this.provider = provider;
        this.name_est = name_est;
        this.name_eng = name_eng;
        this.price = price;
    }

    public String getProvider() {
        return provider;
    }

    public String getName_est() {
        return name_est;
    }

    public String getName_eng() {
        return name_eng;
    }

    public String getPrice() {
        return price;
    }
}
