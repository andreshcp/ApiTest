 package com.afmv.apitest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

    private EditText editText, edt;
    private Button button;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.list);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();
    }

     private void fetch() {
         Query query = FirebaseDatabase.getInstance()
                 .getReference()
                 .child("products").child("0");
         FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product>()
                    .setQuery(query, new SnapshotParser<Product>() {
                        @NonNull
                        @Override
                        public Product parseSnapshot(@NonNull DataSnapshot snapshot) {
                            return new Product(
                                    snapshot.child("category").getValue().toString(),
                                    snapshot.child("description").getValue().toString(),
                                    snapshot.child("identification").getValue().toString(),
                                    snapshot.child("productname").getValue().toString());
                        }
                    })
                 .build();

         adapter = new FirebaseRecyclerAdapter<Product, ViewHolder>(options) {
             @Override
             public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext())
                         .inflate(R.layout.list_item, parent, false);

                 return new ViewHolder(view);
             }


             @Override
             protected void onBindViewHolder(ViewHolder holder, final int position, Product model) {
                 holder.setTxtCategory(model.getCategory());
                 holder.setTxtDescription(model.getDescription());
                 holder.setTxtIdentification(model.getIdentification());
                 holder.setTxtProductName(model.getProductName());


                 holder.root.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                        String keyItem = adapter.getRef(position).getKey();
                        Intent intent = new Intent(MainActivity.this,PostActivity.class);
                        intent.putExtra("categoryPost",model.getCategory());
                        intent.putExtra("descriptionPost",model.getDescription());
                        intent.putExtra("identificationPost",model.getIdentification());
                        intent.putExtra("productnamePost",model.getProductName());
                        intent.putExtra("keyItem",keyItem);
                        startActivity(intent);
                     }
                 });
             }

         };

         recyclerView.setAdapter(adapter);
         adapter.startListening();
     }

 }