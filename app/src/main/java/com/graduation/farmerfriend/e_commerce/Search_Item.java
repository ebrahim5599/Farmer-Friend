package com.graduation.farmerfriend.e_commerce;

public class Search_Item {

    private int Image ;
    private String Name;
    private String price ;

    public Search_Item(int image, String name, String price) {
        Image = image;
        Name = name;
        this.price = price;
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
}
