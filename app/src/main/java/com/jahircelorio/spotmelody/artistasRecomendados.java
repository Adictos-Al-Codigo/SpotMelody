package com.jahircelorio.spotmelody;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

public class artistasRecomendados extends AppCompatActivity {

    ImageView imageView, imageView2,imageView3,imageView4,imageView5,imageView6,
            imageView7, imageView8, imageView9, imageView10, imageView11, imageView12,imageView13,imageView14,imageView15
            ;
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

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        imageView10 = findViewById(R.id.imageView10);
        imageView11 = findViewById(R.id.imageView11);
        imageView12 = findViewById(R.id.imageView12);
        imageView13 = findViewById(R.id.imageView13);
        imageView14 = findViewById(R.id.imageView14);
        imageView15 = findViewById(R.id.imageView15);

        int url_img [] =
                {
                R.drawable.myketowers,
                R.drawable.olivia_rodrigo,
                R.drawable.taylorswift,
                        R.drawable.pespluma,
                        R.drawable.badbunny,
                        R.drawable.yng,
                        R.drawable.grupofrontera,
                        R.drawable.mileycyrus,
                        R.drawable.dave,
                        R.drawable.harrystyles,
                        R.drawable.davidkushner,
                        R.drawable.quevedo,
                        R.drawable.feid,
                        R.drawable.fuerzaregida,
                        R.drawable.karolg
                };

        Glide.with(this).load(url_img[0]).into(imageView);
        Glide.with(this).load(url_img[1]).into(imageView2);
        Glide.with(this).load(url_img[2]).into(imageView3);
        Glide.with(this).load(url_img[3]).into(imageView4);
        Glide.with(this).load(url_img[4]).into(imageView5);
        Glide.with(this).load(url_img[5]).into(imageView6);
        Glide.with(this).load(url_img[6]).into(imageView7);
        Glide.with(this).load(url_img[7]).into(imageView8);
        Glide.with(this).load(url_img[8]).into(imageView9);
        Glide.with(this).load(url_img[9]).into(imageView10);
        Glide.with(this).load(url_img[10]).into(imageView11);
        Glide.with(this).load(url_img[11]).into(imageView12);
        Glide.with(this).load(url_img[12]).into(imageView13);
        Glide.with(this).load(url_img[13]).into(imageView14);
        Glide.with(this).load(url_img[14]).into(imageView15);

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
