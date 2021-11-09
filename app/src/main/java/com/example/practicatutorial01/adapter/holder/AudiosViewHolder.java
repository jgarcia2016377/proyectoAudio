package com.example.practicatutorial01.adapter.holder;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicatutorial01.R;
import com.example.practicatutorial01.dto.AudioDto;
import com.example.practicatutorial01.adapter.holder.OnClickAudioListener;

public class AudiosViewHolder extends RecyclerView.ViewHolder {

    public TextView txtUsuario;
    public TextView txtFecha;
    public Button btnAudio;
    public ProgressBar pgbPorcentaje;

    public AudiosViewHolder(@NonNull View itemView) {
        super(itemView);
        this.txtUsuario = (TextView) itemView.findViewById(R.id.txtUsuario);
        this.txtFecha = (TextView) itemView.findViewById(R.id.txtFechaAudio);
        this.btnAudio = (Button) itemView.findViewById(R.id.btnAudio);
        this.pgbPorcentaje = (ProgressBar) itemView.findViewById(R.id.progreso);
    }

    public void bind(final AudioDto audioDto, final OnClickAudioListener listener){
        this.txtUsuario.append(audioDto.getUsuario());
        this.txtFecha.append(audioDto.getFechaGrabacion());
        this.btnAudio.setOnClickListener(view -> listener.onClickAudioListener(audioDto, getAdapterPosition()));
    }
}
