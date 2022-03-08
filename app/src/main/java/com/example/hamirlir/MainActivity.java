package com.example.hamirlir;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hamirlir.adapter.DishesAdapter;
import com.example.hamirlir.adapter.MyAdapter;
import com.example.hamirlir.adapter.PreferredAdapter;
import com.example.hamirlir.databasehelper.DBHelper;
import com.example.hamirlir.models.Category;
import com.example.hamirlir.models.Dishes;
import com.google.android.material.navigation.NavigationView;
import com.rowland.cartcounter.view.CartCounterActionView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imgKategori,shop, sandwich_nav;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    CartCounterActionView cartCounterActionView;
    //////////////////////////////////////////////
    RecyclerView recyclerView, recyclerView1;
    ArrayList<Category> myArrayList;
    DBHelper DB;
    MyAdapter adapter;
    PreferredAdapter preferredAdapter;
    ArrayList<Dishes> dishesArrayList;

    Button btnPorosit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPorosit = (Button) findViewById(R.id.btnPorositi);

        ////////////////////////////////////////////////////////////////////////////////////
        // This part is for recycler view for Kategori Items
        // Initialize db helper, arrayList of Category model, recycleView and MyAdapter
        DB = new DBHelper(MainActivity.this);
        myArrayList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rsViewKategori);
        adapter = new MyAdapter(this, myArrayList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        displayData();


        /////////////////////////////////////////////////////////////////////////////////////
        ////// This part is for recycleview for preffered items /////////////////////

        dishesArrayList = new ArrayList<>();
        recyclerView1 = (RecyclerView)findViewById(R.id.rsViewPreferred);
        preferredAdapter = new PreferredAdapter(this, dishesArrayList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setAdapter(preferredAdapter);
        recyclerView1.setLayoutManager(layoutManager1);
        displayPreferred();

        /////////////////////////////////////////////////////////////////////////////////////



        imgKategori = (ImageView) findViewById(R.id.kategori_foto);
        shop = (ImageView) findViewById(R.id.shopping_cart);
        sandwich_nav = (ImageView) findViewById(R.id.bt_menu);


        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("https://agroweb.org/wp-content/uploads/2018/07/Supa_ok-750x500.jpg", "Supat me te Mira", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://pizzabellabh.com/wp-content/uploads/2020/06/Alla-Grecca.jpg", "Best Pizza", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("https://d3mvlb3hz2g78.cloudfront.net/wp-content/uploads/2017/01/thumb_720_450_Cook_Meatdreamstime_xxl_52101227.jpg", "Mishera", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel("http://anilakalleshi.com/wp-content/uploads/2018/02/maydiet720.jpg", "Sallata te Shijshme", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);


        //////////////////////////////////////////////////////////////////////////////////////////////

  ////////////////////   This is part of code for navigation Menu  ///////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();

        sandwich_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Navigation sandwich bar clicked", Toast.LENGTH_SHORT).show();
                drawerLayout.openDrawer(navigationView, true);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Shop Cart icon is clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Function on click navigation menu items
        navigationView.setNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_menu:
                    Log.d("Menu_Drawer_TAG", "Menu item is clicked");
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_porosite_e_mia:
                    Log.d("Porosite_e_Mia_TAG", "Porosite e mia item is clicked");
                    Intent intent2 = new Intent(MainActivity.this, CartActivity.class);
                    startActivity(intent2);
                    finish();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_logout:
                    Log.i("Log_Out_TAG", "Log_Out is clicked");
                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent1);
                    finish();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////// For displaying Data from Category Model /////////////////////////
    private void displayData()
    {
        Cursor cursor = DB.viewData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data to load", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String imgUrl = cursor.getString(2);
                myArrayList.add(new Category(id,name,imgUrl));
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////
    /////////// This function is used to load preferred dishes on Main activity ////////
    private void displayPreferred(){
        Cursor cursor = DB.viewDataPreferred();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data to load", Toast.LENGTH_SHORT).show();
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