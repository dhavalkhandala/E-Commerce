package com.example.e_commerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.e_commerce.Model.ProductAddModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class AddIronToyFragment extends Fragment {
    ProgressDialog progressdialog;
    Uri imageUri;
    EditText product_name,price,desc;
    ImageView imageView;
    Button addproductbtn;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_add_ironproduct,container,false);

        product_name = view.findViewById(R.id.input_name);
        price = view.findViewById(R.id.input_price);
        desc = view.findViewById(R.id.input_description);
        imageView = view.findViewById(R.id.image);
        addproductbtn = view.findViewById(R.id.btn_add);

        progressdialog = new ProgressDialog(view.getContext());
        progressdialog.setMessage("Please Wait....");


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
        addproductbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                String valid = "true";
                if (product_name.getText().toString().equals("")) {
                    product_name.setError("Please Enter Product Name");
                    valid = "false";
                }
                if (desc.getText().toString().equals("")) {
                    desc.setError("Please Enter Product Description");
                    valid = "false";
                }
                if (price.getText().toString().equals("")) {
                    price.setError("Please Enter Valid Product Price");
                    valid = "false";
                }
                if (valid == "true") {
                    FirebaseStorage.getInstance().getReference("Products/IronToy_" + System.currentTimeMillis())
                            .putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                                String image = uri.toString();
                                ProductAddModel model = new ProductAddModel(product_name.getText().toString(), price.getText().toString(), desc.getText().toString(), image, "IronToy");
                                FirebaseFirestore.getInstance().collection("Products").document(product_name.getText().toString()).set(model)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(view.getContext(), "Add Successfully", Toast.LENGTH_SHORT).show();
                                                    progressdialog.dismiss();
                                                } else {
                                                    Toast.makeText(view.getContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                    progressdialog.dismiss();
                                                }
                                            }
                                        });
                            });
                        }
                    });
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageUri = data.getData();
        imageView.setImageURI(imageUri);
    }
}
