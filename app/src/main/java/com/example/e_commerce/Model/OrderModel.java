package com.example.e_commerce.Model;

public class OrderModel {
    String ProductName,Total_Price,Product_Image,Quantity,OrderID
            ,Phone,Address,Product_Price,UserEmail,Amount,Datetime,Payment_Status,Delivery_Status;

    public OrderModel() {
    }

    public OrderModel(String productName, String total_Price, String product_Image, String quantity, String orderID, String phone, String address, String product_Price, String userEmail, String amount, String datetime, String payment_Status, String delivery_Status) {
        ProductName = productName;
        Total_Price = total_Price;
        Product_Image = product_Image;
        Quantity = quantity;
        OrderID = orderID;
        Phone = phone;
        Address = address;
        Product_Price = product_Price;
        UserEmail = userEmail;
        Amount = amount;
        Datetime = datetime;
        Payment_Status = payment_Status;
        Delivery_Status = delivery_Status;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String total_Price) {
        Total_Price = total_Price;
    }

    public String getProduct_Image() {
        return Product_Image;
    }

    public void setProduct_Image(String product_Image) {
        Product_Image = product_Image;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getProduct_Price() {
        return Product_Price;
    }

    public void setProduct_Price(String product_Price) {
        Product_Price = product_Price;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public String getPayment_Status() {
        return Payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        Payment_Status = payment_Status;
    }

    public String getDelivery_Status() {
        return Delivery_Status;
    }

    public void setDelivery_Status(String delivery_Status) {
        Delivery_Status = delivery_Status;
    }
}
