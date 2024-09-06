package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        // Referencias a los elementos de la interfaz
        tvResultado = findViewById(R.id.tv_resultado);
        btnVolver = findViewById(R.id.btn_volver);

        // Obtener el resultado desde el Intent
        Intent intent = getIntent();
        String resultado = intent.getStringExtra("resultado");

        // Mostrar el resultado en el TextView
        tvResultado.setText("Resultado: " + resultado);

        // Configurar el botón para volver a la pantalla principal
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Cierra la actividad y vuelve a la anterior
            }
        });
    }
}
