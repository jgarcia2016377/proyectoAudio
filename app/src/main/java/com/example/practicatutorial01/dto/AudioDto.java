package com.example.practicatutorial01.dto;

import android.media.MediaRecorder;

import java.io.Serializable;

public class AudioDto implements Serializable {
    public String fechaGrabacion;
    public MediaRecorder audio;
    public String usuario;
    public String archivoRuta;

    public AudioDto(String fechaGrabacion, MediaRecorder audio, String usuario, String archivoRuta){
        this.audio = audio;
        this.fechaGrabacion = fechaGrabacion;
        this.usuario = usuario;
        this.archivoRuta = archivoRuta;
    }

    public AudioDto() {

    }


    public String getFechaGrabacion() {
        return fechaGrabacion;
    }

    public void setFechaGrabacion(String fechaGrabacion) {
        this.fechaGrabacion = fechaGrabacion;
    }

    public MediaRecorder getAudio() {
        return audio;
    }

    public void setAudio(MediaRecorder audio) {
        this.audio = audio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }
}
