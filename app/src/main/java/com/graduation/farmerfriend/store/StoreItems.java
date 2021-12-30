package com.graduation.farmerfriend.store;

public class StoreItems {

    private String itemName;
    private String itemDetails;
    private int numberOfItems;
    private int imageResource;

    public StoreItems(String itemName, String itemDetails, int numberOfItems, int imageResource) {
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.numberOfItems = numberOfItems;
        this.imageResource = imageResource;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
