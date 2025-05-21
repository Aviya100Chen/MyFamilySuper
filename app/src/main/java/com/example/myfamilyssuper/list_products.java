package com.example.myfamilyssuper;

import static android.content.ContentValues.TAG;

import static java.sql.DriverManager.println;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class list_products extends AppCompatActivity {
    private static final String TAG = "GetProductData";
    private static final String COLLECTION_NAME = "Products";
    private static final String DOCUMENT_ID = "2C5VncQZvZBMSuIPXht6";
    private ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



   //     for (int i = 0; i < 50; i++) {
   //         products.add(new Product("מלפפון" + i,
   //                 "ירקות ופירות",
   //                 "8.4" + (i % 6),
   //                 i
   //         ));
   //     }

        String category = getIntent().getStringExtra("category");

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        RecyclerView recyclerView= findViewById(R.id.recyclerView_products);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this);
        recyclerView.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(products);
        recyclerView.setAdapter(productAdapter);

        getProductsByCategory(category, new ProductRetrievalCallback() {
            @Override
            public void onSuccess(ArrayList<Product> products) {
                // Update UI with the list of products
                //Example:
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle the error appropriately (show an error message, etc.)
                Toast.makeText(getApplicationContext(),"Error getting products",Toast.LENGTH_SHORT).show();
            }
        });



    }


    public interface ProductRetrievalCallback {
        void onSuccess(ArrayList<Product> products);
        void onFailure(Exception e);
    }


    public void getProductsByCategory(String category, ProductRetrievalCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(COLLECTION_NAME)
                .whereEqualTo("category", category)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            Product product = document.toObject(Product.class);
                            if (product != null) {
                                products.add(product);
                            }
                        }
                        // Notify the adapter that the data has changed
                        productAdapter.notifyDataSetChanged();
                        callback.onSuccess(products);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        callback.onFailure(e);
                        Log.e("FirestoreError", "Error getting products: ", e); // Log the exception for debugging
                    }
                });
    }
}

