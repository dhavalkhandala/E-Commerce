package com.example.e_commerce.Model;

public class ProductAddModel {
    String Product_Name,Product_Price,Product_Desc,Product_Image,category;

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String product_Name) {
        Product_Name = product_Name;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public String getProduct_Desc() {
        return Product_Desc;
    }

    public void setProduct_Desc(String product_Desc) {
        Product_Desc = product_Desc;
    }

    public String getProduct_Image() {
        return Product_Image;
    }

    public void setProduct_Image(String product_Image) {
        Product_Image = product_Image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ProductAddModel(String product_Name, String product_Price, String product_Desc, String product_Image, String category) {
        Product_Name = product_Name;
        Product_Price = product_Price;
        Product_Desc = product_Desc;
        Product_Image = product_Image;
        this.category = category;
    }

    public ProductAddModel() {
    }
}
