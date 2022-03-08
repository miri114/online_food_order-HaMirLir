package com.example.hamirlir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hamirlir.adapter.DishesAdapter;
import com.example.hamirlir.databasehelper.DBHelper;
import com.example.hamirlir.models.Dishes;

import java.util.ArrayList;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Dishes> dishesArrayList;
    DBHelper DB;
    DishesAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // here we reference to our toolbar cause we remove default toolbar
        Toolbar toolbari = (Toolbar) findViewById(R.id.toolbari);
        setSupportActionBar(toolbari);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ///////////////////////////////////////////////////////////////////
        /////// Ketu iniciohen databasa, dishArraylist, recyclerView, adapter //////
        /////// Dhe vendosen ne Layout /////////////////////////////////////////////
        DB = new DBHelper(MenuActivity.this);
        dishesArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rsViewDish);
        myAdapter = new DishesAdapter(this, dishesArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        displayData();
    }


    ////////////////////////////////////////////////////////////////
    ///// This is to load Data from dishes table in db /////////
    private void displayData()
    {
        Cursor cursor = DB.viewDataDish();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data to load", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                String name = cursor.getString(0);
                String imgUrl = cursor.getString(1);
                int price = cursor.getInt(2);
                dishesArrayList.add(new Dishes(price,name,imgUrl));
            }
        }
    }

}