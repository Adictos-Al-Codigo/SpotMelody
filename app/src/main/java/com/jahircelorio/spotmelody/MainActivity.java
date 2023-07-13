package com.jahircelorio.spotmelody;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.jahircelorio.spotmelody.Service.model.AppleMusicService;
import com.jahircelorio.spotmelody.Service.model.Result;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cafsoft.foundation.HTTPURLResponse;
import cafsoft.foundation.URLSession;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer = null;
    private Result currentSong = null;

    private EditText txtSearch = null;
    private Button btnSearch = null;

    private ListView listViewItems = null;
    private List<Result> results = null;

    private AppleMusicService service = null;

    public String url_mostrar_album;

    DrawerLayout drawerLayout = null;
    NavigationView navigationView = null;
    ActionBarDrawerToggle actionBarDrawerToggle = null;

    public Button getMostrar_album() {
        return mostrar_album;
    }

    public void setMostrar_album(Button mostrar_album) {
        this.mostrar_album = mostrar_album;
    }

    private  Button mostrar_album = null;
    int REQUEST_CODE = 200;

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
        setContentView(R.layout.activity_main);
        verificarPermisos();

        initViews();
        initEvents();

        service = new AppleMusicService();


        // Mostrar la lista predeterminada al iniciar la aplicación
        getMusicInfo("Shakira");
        txtSearch.setText("Shakira");

        Toolbar toolbar = findViewById(R.id.toolbar); // Agrega esta línea para inicializar la variable Toolbar
        toolbar.setTitle("Buscar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Agrega esta línea para mostrar el botón de inicio en la barra de acción
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_home_24); // Reemplaza "ic_menu" con el ícono de tu elección para el botón de inicio


        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getTitle().toString()){
                    case "Principal" : {
                        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Buscar" : {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Artistas" : {
                        Intent intent = new Intent(MainActivity.this, artistasRecomendados.class);
                        startActivity(intent);
                        break;
                    }
                    case  "Lista de Reproducción" : {
                        Toast.makeText(MainActivity.this,"Lista de Reproducción",Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case "Ver Cuenta" :{
                        Toast.makeText(MainActivity.this,"Ver Cuenta",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case "Cerrar Sesión" : {
                        Toast.makeText(MainActivity.this,"Cerraste Sesión",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                return true;
            }
        });
    }

    // Método para pedir permisos al usuario
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void verificarPermisos() {
        int permisoAlmacenamiento = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permisoAlmacenamiento != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    // Inicializar los elementos de la vista
    public void initViews() {
        txtSearch = findViewById(R.id.txtSearch);
        listViewItems = findViewById(R.id.listViewItems);
    }

    // Método para hacer la búsqueda
    public void btnGetInfoOnClick(View view) {
        getMusicInfo(txtSearch.getText().toString());
    }


    // Método para el botón "mostrar_album"
    public void mostrarAlbumOnClick(View view) {
        String url = results.get(0).getTrackViewUrl(); // Aquí debes reemplazar con la URL deseada

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void initEvents() {
        listViewItems.setOnItemClickListener((adapterView, view, i, l) -> {
            CustomListAdapter.ViewHolder viewHolder = (CustomListAdapter.ViewHolder) view.getTag();
            Result song = (Result) listViewItems.getAdapter().getItem(i);

            String destFilename = getCacheDir() + "/" + song.getTrackId() + ".tmp.m4a";
            int state = song.getState();
            switch (state) {
                case 1:
                    try {
                        downloadFile(new URL(song.getPreviewUrl()), destFilename);
                        song.setState(2);
                        viewHolder.imgAction.setImageResource(R.drawable.play);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    if (currentSong != null && currentSong.getState() == 3) {
                        mediaPlayer.pause();
                        currentSong.setState(2);
                        viewHolder.imgAction.setImageResource(R.drawable.play);
                    } else {
                        mediaPlayer = MediaPlayer.create(this, Uri.parse(destFilename));
                        mediaPlayer.start();
                        song.setState(3);
                        viewHolder.imgAction.setImageResource(R.drawable.pause);
                    }
                    break;
                case 3:
                    mediaPlayer.stop();
                    song.setState(2);
                    viewHolder.imgAction.setImageResource(R.drawable.play);
                    break;
            }

            currentSong = song;
        });
    }

    // Obtener la lista de canciones con respecto a la búsqueda y mostrarla en la lista.
    public void getMusicInfo(String name) {
        results = new ArrayList<>();
        service.searchSongsByTerm(name, (isNetworkError, statusCode, root) -> {
            if (!isNetworkError) {
                if (statusCode == 200) {
                    results.addAll(root.getResults());
                    runOnUiThread(() -> {
                        CustomListAdapter adapter = new CustomListAdapter(this, results);
                        listViewItems.setAdapter(adapter);
                    });
                } else {
                    Log.d("iTunes", "Service error");
                }
            } else {
                Log.d("Super Hero", "Network error");
            }
        });
    }

    // Descarga la canción que viene en una URL y le pone el nombre destFilename
    public void downloadFile(URL audioURL, String destFilename) {
        URLSession.getShared().downloadTask(audioURL, (localAudioUrl, response, error) -> {
            if (error == null) {
                int respCode = ((HTTPURLResponse) response).getStatusCode();

                if (respCode == 200) {
                    File file = new File(localAudioUrl.getFile());
                    if (file.renameTo(new File(destFilename))) {
                        if (currentSong != null && currentSong.getState() == 2) {
                            mediaPlayer.release();
                            currentSong.setState(1);
                        }
                    }
                } else {
                    // Error (respCode)
                }
            } else {
                // Connection error
            }
        }).resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (currentSong != null && currentSong.getState() == 3) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
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

