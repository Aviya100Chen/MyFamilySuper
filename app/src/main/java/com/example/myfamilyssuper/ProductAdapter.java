package com.example.myfamilyssuper;

import android.view.LayoutInflater;
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
       View productview= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products_item, parent, false);
        return new ProductViewHolder(productview);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    Product currentProduct = products.get(position);
    holder.categoryTextView.setText((currentProduct.getCategory()));
    //holder.imageImageView.setImageResource(holder.nameTextView.getResources().
       //     getIdentifier(currentProduct.getImage(), "drawable",
               //     holder.nameTextView.getContext().getPackageName()));
    holder.nameTextView.setText(currentProduct.getName());
    holder.priceTextView.setText(Integer.toString(currentProduct.getPrice()));
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

           categoryTextView = itemView.findViewById(R.id.textView_category_2);
          //// imageImageView = itemView.findViewById(R.id.imageView_image);
           nameTextView = itemView.findViewById(R.id.textView_name_2);
           priceTextView = itemView.findViewById(R.id.textView_price_2);



        }
    }

}
