package com.graduation.farmerfriend.e_commerce.search.pojo;

public class Search_Item {

    private int Image ;
    private String Name;
    private String price ;
    private String Description;

    public Search_Item(int image, String name, String price,String description) {
        Image = image;
        Name = name;
        Description = description ;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
