package com.example.mynavigationdrawer;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout        drawerLayout;
    NavigationView      navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.dlayout);
        navigationView= findViewById(R.id.dView);
        toolbar = findViewById(R.id.mytoolbar);

        //step    set toolbar as actionbar
        setSupportActionBar(toolbar);

        //step 2  set toggle for drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar,R.string.opendrawer,R.string.closedrawer);

        //step3  set drawer click listner for toggle
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.d_home)
            {
                load_fragment(new AFragment());
            } else if (id == R.id.d_about) {
                load_fragment(new BFragment());
            } else if (id == R.id.d_setting) {
                load_fragment(new CFragment());
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    // if backpress than drawer close first
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
    public void load_fragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_container,fragment);
        ft.commit();
    }


}