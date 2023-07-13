package com.jahircelorio.spotmelody;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class artistasRecomendados extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artista_recomendado_drawer);

        // Configurar el NavigationView
        navigationView = findViewById(R.id.nav_viewAR);

        toolbar = findViewById(R.id.toolbarAR); // Agrega esta línea para inicializar la variable Toolbar
        toolbar.setTitle("Artista Recomendados");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Agrega esta línea para mostrar el botón de inicio en la barra de acción
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24); // Reemplaza "ic_menu" con el ícono de tu elección para el botón de inicio

        drawerLayout = findViewById(R.id.drawer_layoutAR);
        navigationView = findViewById(R.id.nav_viewAR);
        actionBarDrawerToggle = new ActionBarDrawerToggle(artistasRecomendados.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Principal" : {    // Verificar si ya estás en PrincipalActivity
                            // Iniciar PrincipalActivity
                            Intent intent = new Intent(artistasRecomendados.this, PrincipalActivity.class);
                            startActivity(intent);
                        break;
                    }
                    case  "Buscar" : {
                        Intent intent = new Intent(artistasRecomendados.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Artistas" : {
                        Intent intent = new Intent(artistasRecomendados.this,artistasRecomendados.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Lista de Reproducción" : {
                        Toast.makeText(artistasRecomendados.this,"Lista de Reproducción",Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case "Ver Cuenta" :{
                        Toast.makeText(artistasRecomendados.this,"Ver Cuenta",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case "Cerrar Sesión" : {
                        Toast.makeText(artistasRecomendados.this,"Cerraste Sesión",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }


                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
