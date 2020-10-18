package com.afmv.apitest;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout root;
    public TextView txtCategory;
    public TextView txtDescription;
    public TextView txtIdentification;
    public TextView txtProductname;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        root = itemView.findViewById(R.id.list_root);
        txtCategory = itemView.findViewById(R.id.list_category);
        txtDescription = itemView.findViewById(R.id.list_description);
        txtIdentification = itemView.findViewById(R.id.list_identification);
        txtProductname = itemView.findViewById(R.id.list_productname);


    }
    public void setTxtCategory(String string) {
        txtCategory.setText(string);
    }
    public void setTxtDescription(String string) {
        txtDescription.setText(string);
    }
    public void setTxtIdentification(String string) {
        txtIdentification.setText(string);
    }

    public void setTxtProductName(String string) {
        txtProductname.setText(string);
    }

}
