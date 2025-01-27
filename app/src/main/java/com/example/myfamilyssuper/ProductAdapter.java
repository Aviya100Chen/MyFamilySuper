package com.example.myfamilyssuper;

import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

   private ArrayList<Product> products;

   public ProductAdapter(ArrayList<Product> products) {
       this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
       public TextView categoryTextView;
       public TextView imageImageView;
       public TextView nameTextView;
       public TextView priceTextView;


       public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

           categoryTextView = itemView.findViewById(R.id.textView_category);
          //// imageImageView = itemView.findViewById(R.id.imageView_image);
           nameTextView = itemView.findViewById(R.id.textView_name);
           priceTextView = itemView.findViewById(R.id.textView_price);



        }
    }

}
