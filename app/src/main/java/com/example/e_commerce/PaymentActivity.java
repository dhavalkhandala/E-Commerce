package com.example.e_commerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.e_commerce.Model.OrderModel;
import com.example.e_commerce.Model.PAymentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.payumoney.core.PayUmoneySdkInitializer;
import com.payumoney.core.entity.TransactionResponse;
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PaymentActivity extends AppCompatActivity {

    long theRandomNum = (long) (Math.random()*Math.pow(10,10));
    String key = "XETbPqjK";
    String salt = "H14dXurnO3";
    String merchantId = "7402653";
    String transactionId = String.valueOf(theRandomNum);
    String charges = "";
    String totalAmount = "10";
    String firstname = "Dhaval";
    String email = "dhavalkhandala.8.ce@iite.indusuni.ac.in";
    String udf1 = "";
    String udf2 = "";
    String udf3 = "";
    String udf4 = "";
    String udf5 = "";
    String udf6 = "";
    String udf7 = "";
    String udf8 = "";
    String udf9 = "";
    String udf10 = "";
    String productName = "LifeCare Demo";
    String productInfo = "LifeCare Demo";
    String generatedHash = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {

            String amount = getIntent().getStringExtra("total price");

            String hashSequence = key + "|" + transactionId + "|" + amount + "|" + productInfo + "|" + firstname + "|" + email + "|" + udf1 + "|" + udf2 + "|" + udf3 + "|" + udf4 + "|" + udf5 + "||||||" + salt;
            PayUmoneySdkInitializer.PaymentParam.Builder builder = new
                    PayUmoneySdkInitializer.PaymentParam.Builder();

            builder.setAmount(amount)                          // Payment amount
                    .setTxnId(transactionId)         // Transaction ID//
                    .setPhone("9104261130")                     // User Phone number
                    .setProductName(productInfo)                   // Product Name or description
                    .setFirstName(firstname)                              // User First name
                    .setEmail(email)                                            // User Email ID
                    .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php")                    // Success URL (surl)
                    .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php")                     //Failure URL (furl)
                    .setUdf1(udf1)
                    .setUdf2(udf2)
                    .setUdf3(udf3)
                    .setUdf4(udf4)
                    .setUdf5(udf5)
                    .setUdf6(udf6)
                    .setUdf7(udf7)
                    .setUdf8(udf8)
                    .setUdf9(udf9)
                    .setUdf10(udf10)
                    .setIsDebug(true)                             // Integration environment - true (Debug)/ false(Production)
                    .setKey(key)                        // Merchant key
                    .setMerchantId(merchantId);


            PayUmoneySdkInitializer.PaymentParam paymentParam = null;
            try {
                paymentParam = builder.build();
            } catch (Exception e) {
                e.printStackTrace();
            }
//set the hash
            String hash = hashCal("sha512", hashSequence);
            paymentParam.setMerchantHash(hash);

            PayUmoneyFlowManager.startPayUMoneyFlow(
                    paymentParam,
                    this,
                    R.style.AppTheme_default,
                    true);
        }
        else {
            Toast.makeText(this, "Please Login Your account", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PaymentActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
    public static String hashCal(String type, String hashString) {
        StringBuilder hash = new StringBuilder();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(type);
            messageDigest.update(hashString.getBytes());

            byte[] mdbytes = messageDigest.digest();
            for (byte hashByte : mdbytes) {
                hash.append(Integer.toString((hashByte & 0xff) + 0x100, 16).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("MainActivity", "request code " + requestCode + " resultcode " + resultCode);
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            TransactionResponse transactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE);

            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {

                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        String userEmail = user.getEmail();
                        String res = transactionResponse.getPayuResponse();
                        try {
                            JSONObject root = new JSONObject(res);
                            JSONObject array = root.getJSONObject("result");

                            PAymentModel model = new PAymentModel();
                            model.setUserEmail(userEmail);
                            model.setAddress(getIntent().getStringExtra("address"));
                            model.setAmount(array.getString("amount"));
                            model.setCity(getIntent().getStringExtra("city"));
                            model.setDatetime(array.getString("addedon"));
                            model.setPayment_Status(array.getString("status"));
                            model.setPaymentId(array.getString("paymentId"));
                            model.setPhone(getIntent().getStringExtra("phone"));
                            model.setProduct_desc(getIntent().getStringExtra("desc"));
                            model.setProduct_Image(getIntent().getStringExtra("pimage"));
                            model.setProduct_Price(getIntent().getStringExtra("price"));
                            model.setProductName(getIntent().getStringExtra("pname"));
                            model.setQuantity(getIntent().getStringExtra("quantity"));
                            model.setResponse(res);
                            model.setTotal_Price(getIntent().getStringExtra("total price"));
                            model.setTransactionid(transactionId);

                            OrderModel model1 = new OrderModel();
                            model1.setUserEmail(userEmail);
                            model1.setAddress(getIntent().getStringExtra("address") + getIntent().getStringExtra("city"));
                            model1.setAmount(array.getString("amount"));
                            model1.setDatetime(array.getString("addedon"));
                            model1.setPayment_Status(array.getString("status"));
                            model1.setOrderID(array.getString("paymentId"));
                            model1.setPhone(getIntent().getStringExtra("phone"));
                            model1.setProduct_Image(getIntent().getStringExtra("pimage"));
                            model1.setProduct_Price(getIntent().getStringExtra("price"));
                            model1.setProductName(getIntent().getStringExtra("pname"));
                            model1.setQuantity(getIntent().getStringExtra("quantity"));
                            model1.setDelivery_Status("Pending......");
                            model1.setTotal_Price(getIntent().getStringExtra("total price"));

                            FirebaseFirestore.getInstance().collection("Payment Details")
                                    .document(transactionId).set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseFirestore.getInstance().collection("Order")
                                                .document(model1.getOrderID()).set(model1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                FirebaseFirestore.getInstance().collection("Users")
                                                        .document(userEmail).collection("Orders")
                                                        .document(model1.getOrderID()).set(model1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {

                                                        new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                                                .setTitleText("Congratulation")
                                                                .setConfirmText("Done"+ ("\uD83D\uDC4C"))
                                                                .setContentText("Order Id:"+transactionId+"\n"+"Payment Id:"+transactionId)
                                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                    @Override
                                                                    public void onClick(SweetAlertDialog sDialog) {
                                                                        sDialog.dismissWithAnimation();
                                                                        Intent intent = new Intent(PaymentActivity.this,DasboardActivity.class);
                                                                        startActivity(intent);
                                                                        finish();
                                                                    }
                                                                }).show();
                                                    }
                                                });
                                            }
                                        });

                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .show();
                        Intent intent = new Intent(PaymentActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }else {
                    new SweetAlertDialog(PaymentActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show();
                }

            }
        }

    }
}
