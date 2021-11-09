package com.example.practicatutorial01.dto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicatutorial01.adapter.holder.AudiosViewHolder;
import com.example.practicatutorial01.adapter.holder.OnClickAudioListener;

import java.util.List;

public class AuidosRecycler extends RecyclerView.Adapter<AudiosViewHolder> {

    private List<AudioDto> audiosList;
    private int layout;
    private OnClickAudioListener listener;

    public AuidosRecycler(List<AudioDto> audiosList, int layout, OnClickAudioListener listener){
        this.audiosList = audiosList;
        this.layout = layout;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AudiosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new AudiosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudiosViewHolder holder, int position) {
        holder.bind(audiosList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return audiosList.size();
        //return 0;
    }
}
