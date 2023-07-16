package com.jahircelorio.spotmelody;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jahircelorio.spotmelody.Service.model.Data;
import com.jahircelorio.spotmelody.Service.model.DreezerMusicService;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    NavigationView navigationView;

    ActionBarDrawerToggle actionBarDrawerToggle;

    DreezerMusicService service = null;

    ListView listView = null;

    private List<Data> data = null;

    private TextView txtname;


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


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_drawer);

        // Configurar el NavigationView
        navigationView = findViewById(R.id.nav_viewP);

        toolbar = findViewById(R.id.toolbarP); // Agrega esta línea para inicializar la variable Toolbar
        toolbar.setTitle("Principal");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Agrega esta línea para mostrar el botón de inicio en la barra de acción
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24); // Reemplaza "ic_menu" con el ícono de tu elección para el botón de inicio

        drawerLayout = findViewById(R.id.drawer_layoutP);
        navigationView = findViewById(R.id.nav_viewP);
        actionBarDrawerToggle = new ActionBarDrawerToggle(PrincipalActivity.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



        service = new DreezerMusicService();

        // Traer los generos

        this.getAllGenre();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Principal" : {    // Verificar si ya estás en PrincipalActivity
                        if (!(PrincipalActivity.this instanceof PrincipalActivity)) {
                            // Finalizar la actividad actual
                            finish();

                            // Iniciar PrincipalActivity
                            Intent intent = new Intent(PrincipalActivity.this, PrincipalActivity.class);
                            startActivity(intent);
                        }
                        break;
                    }
                    case  "Buscar" : {
                        Intent intent = new Intent(PrincipalActivity.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Artistas" : {
                        Intent intent = new Intent(PrincipalActivity.this,artistasRecomendados.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Lista de Reproducción" : {
                        Intent intent = new Intent(PrincipalActivity.this,listaReproduccion.class);
                        startActivity(intent);
                        break;
                    }

                    case "Ver Cuenta" :{
                        Toast.makeText(PrincipalActivity.this,"Ver Cuenta",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case "Cerrar Sesión" : {
                        Toast.makeText(PrincipalActivity.this,"Cerraste Sesión",Toast.LENGTH_SHORT).show();
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

    // Obtener todos los generos de música

    public void getAllGenre(){
        data = new ArrayList<>();

        service.getAllGenres((isNetworkError, statusCode, post) -> {
            if (!isNetworkError){
                if (statusCode==200){
                    data.addAll(post.getData());
                    runOnUiThread(()->{
                        CustomListAdapter2 customListAdapter2 = new CustomListAdapter2(this,data);
                        listView.setAdapter(customListAdapter2);
                    });
                }else{
                    Log.d("iTunes", "Service error");
                }
            }else{
                Log.d("Super Hero", "Network error");
            }
        });
    }

}
