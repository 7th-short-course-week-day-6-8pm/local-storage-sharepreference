package com.rathana.local_storage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.local_storage.data.UserPref;
import com.rathana.local_storage.model.Product;
import com.rathana.local_storage.model.User;

public class MainActivity extends AppCompatActivity {


    EditText productName,productQTY;
    Button btnSave;
    TextView tvProductName, tvProductQty;
    Button btnRead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productName=findViewById(R.id.productName);
        productQTY=findViewById(R.id.productqty);
        btnSave=findViewById(R.id.btnSave);
        tvProductName=findViewById(R.id.tvProductName);
        tvProductQty=findViewById(R.id.tvProductQty);
        btnRead=findViewById(R.id.btnRead);

        checkUserSession();

        btnSave.setOnClickListener(v->{
            Product product=new Product(
                    productName.getText().toString(),
                    Integer.parseInt(productQTY.getText().toString()));

            saveProduct(product);

        });

        btnRead.setOnClickListener(v->{
            Product product= readProduct();
            tvProductName.setText(product.getName());
            tvProductQty.setText(""+product.getQty());
        });

    }


    static final String PRODUCT_PREF="product_pref";
    private void saveProduct(Product product){
        //get preference object
        //SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences preferences= getSharedPreferences(PRODUCT_PREF,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =preferences.edit();
        editor.putString("name",product.getName());
        editor.putInt("qty",product.getQty());
        //editor.commit();
        editor.apply();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    private Product readProduct(){
        SharedPreferences preferences =
                getSharedPreferences(PRODUCT_PREF,Context.MODE_PRIVATE);
        return new Product(
               preferences.getString("name",""),
                preferences.getInt("qty",0)
        );
    }

    private void checkUserSession(){
        if(!UserPref.isLogin(this)){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }

}
