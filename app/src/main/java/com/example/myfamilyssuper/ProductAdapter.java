package com.example.myfamilyssuper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_products_item, parent, false);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentProduct = products.get(position);

        holder.nameTextView.setText(currentProduct.getName());
        holder.categoryTextView.setText(currentProduct.getCategory());
        holder.priceTextView.setText(String.format("%.2f", currentProduct.getPrice()));

        // הגדרת ברירת מחדל
        holder.quantity = 1;
        holder.updateQuantityText(currentProduct);

        holder.btnIncrease.setOnClickListener(v -> {
            holder.quantity++;
            holder.updateQuantityText(currentProduct);
        });

        holder.btnDecrease.setOnClickListener(v -> {
            if (holder.quantity > 1) {
                holder.quantity--;
                holder.updateQuantityText(currentProduct);
            }
        });

        holder.btnAddToCart.setOnClickListener(v -> {
            Product productToAdd = new Product(
                    currentProduct.getName(),
                    currentProduct.getCategory(),
                    currentProduct.getPrice(),
                    holder.quantity
            );
            CartManager.getInstance().addProduct(productToAdd);
            Toast.makeText(holder.itemView.getContext(), "המוצר נוסף לסל!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView, categoryTextView, priceTextView, quantityText;
        public Button btnIncrease, btnDecrease, btnAddToCart;
        public int quantity = 1;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView_name_2);
            categoryTextView = itemView.findViewById(R.id.textView_category_2);
            priceTextView = itemView.findViewById(R.id.textView_price_2);

            quantityText = itemView.findViewById(R.id.tv_quantity);
            btnIncrease = itemView.findViewById(R.id.btn_increase);
            btnDecrease = itemView.findViewById(R.id.btn_decrease);
            btnAddToCart = itemView.findViewById(R.id.btn_add_to_cart);
        }

        public void updateQuantityText(Product product) {
            String unit = (product.getCategory().equals("ירקות ופירות") ||
                    product.getCategory().equals("בשר ודגים")) ? "ק\"ג" : "יח'";
            quantityText.setText(quantity + " " + unit);
        }
    }
}
