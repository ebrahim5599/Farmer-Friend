package com.graduation.farmerfriend.store.pojo;

public class StoreItems {

    private int itemID;
    private String itemName;
    private String itemDetails;
    private int numberOfItems;
    private byte[] imageResource;

    public StoreItems(String itemName, String itemDetails, int numberOfItems, byte[] imageResource) {
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.numberOfItems = numberOfItems;
        this.imageResource = imageResource;
    }

    public StoreItems(int itemID, String itemName, String itemDetails, int numberOfItems) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.numberOfItems = numberOfItems;
    }

    public StoreItems(int itemID, String itemName, String itemDetails, int numberOfItems, byte[] imageResource) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.numberOfItems = numberOfItems;
        this.imageResource = imageResource;
    }

    public StoreItems(String itemName, String itemDetails, int numberOfItems) {
        this.itemName = itemName;
        this.itemDetails = itemDetails;
        this.numberOfItems = numberOfItems;
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

    public byte[] getImageResource() {
        return imageResource;
    }

    public void setImageResource(byte[] imageResource) {
        this.imageResource = imageResource;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
