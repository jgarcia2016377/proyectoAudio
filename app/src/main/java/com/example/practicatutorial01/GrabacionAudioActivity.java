package com.example.practicatutorial01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicatutorial01.dto.AudioDto;

import org.w3c.dom.Text;

import java.io.IOException;

public class GrabacionAudioActivity extends AppCompatActivity {

    public EditText usuarioTxt;
    public EditText fechaGrabacionTxt;
    public Button audioGrabar;

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private final AudioDto audio = new AudioDto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabacion_audio);

        audioGrabar = (Button)findViewById(R.id.btnGrabacion);

        View grabacion = findViewById(R.id.btnGrabacion);
        grabacion.setOnClickListener(v -> {
            recorder();
        });

        View guardar = findViewById(R.id.btnGuardar);
        guardar.setOnClickListener(v -> {
            guardar();
        });

        View reproductor = findViewById(R.id.btnReproduccion);
        reproductor.setOnClickListener(v -> {
            reproducir();
        });
    }

    public void guardar(){
        this.usuarioTxt = (EditText) findViewById(R.id.txtEnterUsuario);
        this.fechaGrabacionTxt = (EditText) findViewById(R.id.txtEnterFecha);
        audio.setArchivoRuta(archivoSalida);
        audio.setAudio(grabacion);
        audio.setUsuario(usuarioTxt.getEditableText().toString());
        audio.setFechaGrabacion(fechaGrabacionTxt.getEditableText().toString());
        Intent intentGuardar = new Intent(GrabacionAudioActivity.this, Principal2.class);
        intentGuardar.putExtra("audio", audio);
        startActivity(intentGuardar);
    }

    @SuppressLint("WrongConstant")
    public void recorder(){
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
            audioGrabar.setText("Pausar");
            Toast.makeText(GrabacionAudioActivity.this, "Inicio la grabacion", Toast.LENGTH_SHORT).show();
        }else if(grabacion != null){
            grabacion.stop();
            grabacion.release();
            grabacion = null;
            audioGrabar.setText("Grabar");
            Toast.makeText(GrabacionAudioActivity.this, "Se paro", Toast.LENGTH_SHORT).show();

        }
    }

    public void reproducir(){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();
        }catch (IOException e){
        }

        mediaPlayer.start();
        Toast.makeText(GrabacionAudioActivity.this, "Reproduciendo", Toast.LENGTH_SHORT).show();
    }

}