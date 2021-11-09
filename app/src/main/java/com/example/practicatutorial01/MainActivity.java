package com.example.practicatutorial01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private View btnPrincipal;
    private final String GREETER = "Hola desde el activity principal ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btnPrincipal = (Button) findViewById(R.id.btnPrincipal);

        btnPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Accerder a la actyiti
                Intent intent = new  Intent(MainActivity.this, Principal2.class);
                intent.putExtra("greeter", GREETER); //declaración del mensaje a enviar al otro activity
                startActivity(intent);
            }
        });
    }
}