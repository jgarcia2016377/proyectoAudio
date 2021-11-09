package com.example.practicatutorial01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import android.widget.Toast;

import com.example.practicatutorial01.adapter.holder.OnClickAudioListener;
import com.example.practicatutorial01.dto.AudioDto;
import com.example.practicatutorial01.dto.AuidosRecycler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Principal2 extends AppCompatActivity {

    private TextView txtMain;
    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btnRecorder, btnAudio;
    private Button btnReproducir;
    private ProgressBar progresBar;
    private boolean enReproduccion = false;

    private MediaPlayer mediaPlayer;
    private List<AudioDto> audiosList;
    private RecyclerView audiosRecycler;
    private RecyclerView.Adapter audioAdapter;
    private RecyclerView.LayoutManager audioLayoutManager;

    int milisegundos = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);
        Objects.requireNonNull(getSupportActionBar()).hide();
        txtMain = (TextView) findViewById(R.id.textViewMain);
        progresBar = (ProgressBar) findViewById(R.id.progreso);
        btnAudio = (Button) findViewById(R.id.btnAudio);
        btnRecorder = (Button)findViewById(R.id.btn_grabar);
        btnReproducir = (Button) findViewById(R.id.btn_reproducir);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Principal2.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }

        //Tomar los datos del intent
        /*
         Bundle bundle = getIntent().getExtras();
         if(bundle != null){
             String greeter2 = bundle.getString("greeter");
             Toast.makeText(Principal2.this, greeter2, Toast.LENGTH_LONG).show();
             txtMain.setText(greeter2);
         }else{
            Toast.makeText(Principal2.this, "It is empty!", Toast.LENGTH_LONG).show();
         }

         */

         audiosList = this.getRegistros();
         Intent i = getIntent();
         AudioDto audioNuevo = (AudioDto)i.getSerializableExtra("audio");
         if(audioNuevo != null){
             audiosList.add(audioNuevo);

             Toast.makeText(Principal2.this, audioNuevo.getFechaGrabacion(), Toast.LENGTH_LONG).show();
         }
         //Recycler
         audiosRecycler =(RecyclerView)findViewById(R.id.audio_recycler);
         audioLayoutManager = new LinearLayoutManager(this);
         audioAdapter = new AuidosRecycler(audiosList, R.layout.recycler_audios, new OnClickAudioListener() {
             @Override
             public void onClickAudioListener(AudioDto audioDto, int position) {

                 if(enReproduccion == false){
                     int accion = 0;

                     mediaPlayer = new MediaPlayer();
                     try{
                         mediaPlayer.setDataSource(audioDto.getArchivoRuta());
                         mediaPlayer.prepare();
                     }catch (IOException e){
                     }
                     mediaPlayer.start();
                     int durationMili =  mediaPlayer.getDuration();
                         Thread hilo = new Thread(new Runnable() {
                             @Override
                             public void run() {

                                 Toast.makeText(Principal2.this, "mediaPlayer " + mediaPlayer.getCurrentPosition(), Toast.LENGTH_SHORT);
                                 while(mediaPlayer.getCurrentPosition()<=durationMili){
                                     Toast.makeText(Principal2.this, "mediaPlayer Si llega", Toast.LENGTH_SHORT);
                                     handler.postDelayed(new Runnable() {
                                         @Override
                                         public void run() {
                                            progresBar.setProgress(((100 * mediaPlayer.getCurrentPosition())/durationMili));
                                         }

                                     }, 200);
                                 }
                             }
                         });
                     enReproduccion = true;
                 }else{
                     Toast.makeText(Principal2.this, "Se esta reproduciendo algo ", Toast.LENGTH_SHORT).show();
                 }

             }
         });

         audiosRecycler.setLayoutManager(audioLayoutManager);
         audiosRecycler.setAdapter(audioAdapter);

         View nuevoAudio = findViewById(R.id.nuevoAudio);
         nuevoAudio.setOnClickListener(v -> {
            nuevoAudio();
         });
    }

    @SuppressLint("WrongConstant")
    public void Recorder(View view){
        if(grabacion == null){
             archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
             grabacion = new MediaRecorder();
             grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
             grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
             grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
             grabacion.setOutputFile(archivoSalida);

             try {
                  grabacion.prepare();
                  grabacion.start();
             }catch (IOException e){

             }
             btnRecorder.setText("Pausar");
             Toast.makeText(Principal2.this, "Inicio la grabacion", Toast.LENGTH_SHORT).show();
        }else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            btnRecorder.setText("Grabar");
            Toast.makeText(Principal2.this, "Se paro", Toast.LENGTH_SHORT).show();

        }
    }

    public void reproducir(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch (IOException e){
        }

        mediaPlayer.start();
        Toast.makeText(Principal2.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }

    private void nuevoAudio(){
        Intent nuevoAudio = new Intent(Principal2.this, GrabacionAudioActivity.class);
        startActivity(nuevoAudio);
    }

    public List<AudioDto> getRegistros(){
        List<AudioDto> listAudios = new ArrayList<>();
        listAudios.add(new AudioDto("luis",null,"hoy","dflkdfj"));
        listAudios.add(new AudioDto("luis",null,"hoy","dflkdfj"));
        return listAudios;
    }
}