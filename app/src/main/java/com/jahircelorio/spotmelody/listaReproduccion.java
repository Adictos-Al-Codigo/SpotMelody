package com.jahircelorio.spotmelody;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class listaReproduccion extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6,imageView7,imageView8,imageView9,imageView10,
            imageView11, imageView12, imageView13, imageView14, imageView15, imageView16, imageView17, imageView18, imageView19, imageView20
            ;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    private MediaPlayer mediaPlayer;
    private int selectedMusicResId;
    private ImageView lastClickedButton;

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
        setContentView(R.layout.activity_listareproduccion);

        toolbar = findViewById(R.id.toolbarLR);
        toolbar.setTitle("Lista de Reproducción");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24);

        drawerLayout = findViewById(R.id.drawer_layoutLR);
        navigationView = findViewById(R.id.nav_viewLR);
        actionBarDrawerToggle = new ActionBarDrawerToggle(listaReproduccion.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        imageView1 = findViewById(R.id.imageView1);
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

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView imageView = (ImageView) view;
                reproducirMusica(imageView);
            }
        };

        imageView1.setOnClickListener(onClickListener);
        imageView2.setOnClickListener(onClickListener);
        imageView3.setOnClickListener(onClickListener);
        imageView4.setOnClickListener(onClickListener);
        imageView5.setOnClickListener(onClickListener);
        imageView6.setOnClickListener(onClickListener);
        imageView7.setOnClickListener(onClickListener);
        imageView8.setOnClickListener(onClickListener);
        imageView9.setOnClickListener(onClickListener);
        imageView10.setOnClickListener(onClickListener);
        imageView11.setOnClickListener(onClickListener);
        imageView12.setOnClickListener(onClickListener);
        imageView13.setOnClickListener(onClickListener);
        imageView14.setOnClickListener(onClickListener);
        imageView15.setOnClickListener(onClickListener);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()) {
                    case "Principal": {
                        Intent intent = new Intent(listaReproduccion.this, PrincipalActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Buscar": {
                        Intent intent = new Intent(listaReproduccion.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Artistas": {
                        Intent intent = new Intent(listaReproduccion.this, artistasRecomendados.class);
                        startActivity(intent);
                        break;
                    }
                    case "Lista de Reproducción": {
                        Intent intent = new Intent(listaReproduccion.this, listaReproduccion.class);
                        startActivity(intent);
                        break;
                    }

                    case "Ver Cuenta": {
                        Toast.makeText(listaReproduccion.this, "Ver Cuenta", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case "Cerrar Sesión": {
                        Toast.makeText(listaReproduccion.this, "Cerraste Sesión", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return true;
            }
        });
    }

    private void reproducirMusica(ImageView view) {
        int CancionId = 0;

        switch (view.getId()) {
            case R.id.imageView1:
                CancionId = R.raw.lalamusic;
                break;
            case R.id.imageView2:
                CancionId = R.raw.copavacia;
                break;
            case R.id.imageView3:
                CancionId = R.raw.docientosnoventauno;
                break;
            case R.id.imageView4:
                CancionId = R.raw.vagabundo;
                break;
            case R.id.imageView5:
                    CancionId = R.raw.ojitoslindos;
                            break;
            case R.id.imageView6:
                CancionId = R.raw.cocoloco;
            break;
            case  R.id.imageView7:
                CancionId = R.raw.acrostico;
                break;
            case R.id.imageView8:
                CancionId = R.raw.mojabighost;
                break;
            case R.id.imageView9:
                    CancionId = R.raw.despecha;
                    break;
            case R.id.imageView10:
                    CancionId = R.raw.dejaloenvisto;
                    break;
            case R.id.imageView11:
                CancionId = R.raw.unx100to;
                break;
            case R.id.imageView12:
                CancionId = R.raw.comoyo;
                break;
            case R.id.imageView13:
                CancionId = R.raw.corazonvacio;
                break;
            case R.id.imageView14:
                CancionId = R.raw.elmerengue;
                break;
            case R.id.imageView15:
                CancionId = R.raw.bizarrap;
                break;
        }

        if (lastClickedButton != null && lastClickedButton != view && mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            lastClickedButton.setImageResource(R.drawable.playnegro);
        }

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            view.setImageResource(R.drawable.playnegro);
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, CancionId);
            mediaPlayer.start();
            view.setImageResource(R.drawable.pausenegro);
        }

        lastClickedButton = view;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

