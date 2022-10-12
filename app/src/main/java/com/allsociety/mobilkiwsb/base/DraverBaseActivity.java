package com.allsociety.mobilkiwsb.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.allsociety.mobilkiwsb.R;
import com.allsociety.mobilkiwsb.helper.SessionSingleton;
import com.allsociety.mobilkiwsb.view.MainActivity;
import com.google.android.material.navigation.NavigationView;

public abstract class DraverBaseActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout _draverLayout;

    /**
     * Skopiowałem od hindusa z YT. Nie wiem, co tu sie dzieje
     * @param view
     */
    @Override
    public void setContentView(View view) {
        _draverLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_draver_base, null);
        FrameLayout container = _draverLayout.findViewById(R.id.activityContainer);
        container.addView(view);
        super.setContentView(_draverLayout);

        Toolbar toolbar = _draverLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Wyłączenie napisu. Bez tego, zawsze będzie wyświetlał nazwę aplikacji, u samej góry aktywności
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        NavigationView navigationView = _draverLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, _draverLayout, toolbar, R.string.app_name, R.string.app_name);
        _draverLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Wyloguj użytkownika i wyświetl okno logowania
     * @param view
     */
    public void Logout(View view){
        SessionSingleton.Logout();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;

    }
}