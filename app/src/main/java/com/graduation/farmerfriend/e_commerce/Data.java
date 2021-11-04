package com.graduation.farmerfriend.e_commerce;

public class Data {

    private int Image ;
    private String Name;
    private String price ;
    private String discount ;

    public Data(int image, String name, String price, String discount) {
        Image = image;
        Name = name;
        this.price = price;
        this.discount = discount;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
