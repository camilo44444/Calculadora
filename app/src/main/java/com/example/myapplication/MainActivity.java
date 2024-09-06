package com.example.myapplication;

import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtNum1, edtNum2;
    private Spinner spinnerSigno;
    private Button btnCalcular;
    private String operadorSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos de la interfaz
        edtNum1 = findViewById(R.id.edt_num1);
        edtNum2 = findViewById(R.id.edt_num2);
        spinnerSigno = findViewById(R.id.spinner_signo);
        btnCalcular = findViewById(R.id.btn_sumar);

        // Configurar el Spinner con los signos de las operaciones
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operadores_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSigno.setAdapter(adapter);

        // Listener para el Spinner
        spinnerSigno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                operadorSeleccionado = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                operadorSeleccionado = "+";
            }
        });

        // Configurar el listener del botón para realizar la operación
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los números ingresados por el usuario
                String num1Str = edtNum1.getText().toString();
                String num2Str = edtNum2.getText().toString();

                // Convertir los números a enteros
                int num1 = Integer.parseInt(num1Str);
                int num2 = Integer.parseInt(num2Str);
                int resultado = 0;

                // Realizar la operación seleccionada
                switch (operadorSeleccionado) {
                    case "+":
                        resultado = num1 + num2;
                        break;
                    case "-":
                        resultado = num1 - num2;
                        break;
                    case "*":
                        resultado = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            resultado = num1 / num2;
                        } else {
                            Toast.makeText(MainActivity.this, "División por cero no permitida", Toast.LENGTH_LONG).show();
                            return;
                        }
                        break;
                }

                // Crear un Intent para abrir ResultadoActivity
                Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
                intent.putExtra("resultado", String.valueOf(resultado));
                startActivity(intent);
            }
        });
    }
}
