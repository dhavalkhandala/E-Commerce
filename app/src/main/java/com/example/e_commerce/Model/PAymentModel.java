package com.example.e_commerce.Model;

public class PAymentModel {
    String ProductName,Total_Price,Product_desc,Product_Image,Quantity
            ,City,Phone,Address,Product_Price,UserEmail,PaymentId,Amount,Datetime,transactionid,Payment_Status,Response;

    public PAymentModel() {
    }

    public PAymentModel(String productName, String total_Price, String product_desc, String product_Image, String quantity, String city, String phone, String address, String product_Price, String userEmail, String paymentId, String amount, String datetime, String transactionid, String payment_Status, String response) {
        ProductName = productName;
        Total_Price = total_Price;
        Product_desc = product_desc;
        Product_Image = product_Image;
        Quantity = quantity;
        City = city;
        Phone = phone;
        Address = address;
        Product_Price = product_Price;
        UserEmail = userEmail;
        PaymentId = paymentId;
        Amount = amount;
        Datetime = datetime;
        this.transactionid = transactionid;
        Payment_Status = payment_Status;
        Response = response;
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

    public String getProduct_desc() {
        return Product_desc;
    }

    public void setProduct_desc(String product_desc) {
        Product_desc = product_desc;
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

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
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

    public String getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(String paymentId) {
        PaymentId = paymentId;
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

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getPayment_Status() {
        return Payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        Payment_Status = payment_Status;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
