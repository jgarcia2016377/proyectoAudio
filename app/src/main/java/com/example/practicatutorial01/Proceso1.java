package com.example.practicatutorial01;

import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Proceso1 extends Thread{

    Handler h = new Handler();
    ProgressBar progressBar;

    @Override
    public void run(){
        progressBar = progressBar.findViewById(R.id.progreso);
        for(int i = 0; i<=5; i++){
            System.out.println("Proceso 1");
        }
    }
}
