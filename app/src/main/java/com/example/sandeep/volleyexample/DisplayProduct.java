package com.example.sandeep.volleyexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayProduct extends AppCompatActivity {

    ListView listView;
    ArrayList<Product> list;
    ProductListAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_product);

        listView = (ListView)findViewById(R.id.product_listview);
        list = new ArrayList<>();
        adapter = new ProductListAdapter(this,R.layout.row_item,list);
        listView.setAdapter(adapter);

        Cursor cursor = AddProduct.dbHelper.getData("SELECT * FROM FOOD");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Product(name, price, image, id));
        }
        adapter.notifyDataSetChanged();
    }
}
