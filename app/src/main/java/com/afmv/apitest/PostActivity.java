package com.afmv.apitest;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostActivity extends AppCompatActivity {

    TextView tvCategory,tvDescription,tvProduct,tvId;
    EditText etProductName,etDescription,etIdentification,etCategory;
    Button btnDelete,btnEdit,btnSave,btnCancel;
    View layoutEdit;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tvCategory = findViewById(R.id.tvCategory);
        tvDescription = findViewById(R.id.tvDescription);
        tvProduct = findViewById(R.id.tvProduct);
        tvId = findViewById(R.id.tvIdentification);
        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        layoutEdit = findViewById(R.id.layoutEdit);
        etProductName = findViewById(R.id.etProductName);
        etCategory = findViewById(R.id.etCategory);
        etIdentification = findViewById(R.id.etIdentification);
        etDescription = findViewById(R.id.etDescription);

        String category = getIntent().getStringExtra("categoryPost");
        String description = getIntent().getStringExtra("descriptionPost");
        String identification = getIntent().getStringExtra("identificationPost");
        String productname = getIntent().getStringExtra("productnamePost");
        String keyItem = getIntent().getStringExtra("keyItem");
        tvDescription.setText(description);
        tvProduct.setText(productname);
        tvId.setText(identification);
        tvCategory.setText(category);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("products").child("0").child(keyItem);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue();
                Toast.makeText(PostActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                finish();
                onBackPressed();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutEdit.setVisibility(View.VISIBLE);
                etProductName.setText(productname);
                etCategory.setText(category);
                etDescription.setText(description);
                etIdentification.setText(identification);

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newProduct = etProductName.getText().toString();
                String newCategory = etCategory.getText().toString();
                String newDescription = etDescription.getText().toString();
                String newIdentification = etIdentification.getText().toString();
                reference.child("productname").setValue(newProduct);
                reference.child("category").setValue(newCategory);
                reference.child("description").setValue(newDescription);
                reference.child("identification").setValue(newIdentification);
                Toast.makeText(PostActivity.this, "Saved Succesfully!", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutEdit.setVisibility(View.INVISIBLE);
            }
        });
    }
}