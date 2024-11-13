package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Float total= (float) 0;
    Float valor;
    StringBuffer buffer = new StringBuffer();
    TextView pantalla;
    Boolean calc_in_progess = false;
    String operando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pantalla = findViewById(R.id.pantalla);
    }
    public void boton_num(View v){
        Button  boton = (Button) v;
        buffer.append(boton.getText().toString());
        pantalla.setText(buffer.toString());
    }
    public void boton_operacion(View v){
        Button  boton = (Button) v;

        if(!calc_in_progess){
            calc_in_progess=true;
            operando = boton.getText().toString();
            total = Float.parseFloat(buffer.toString());
            buffer.delete(0,buffer.length());
        }
        else {
            if (buffer.length() > 0) {
                valor = Float.parseFloat(buffer.toString());
                switch (operando) {
                    case "+":
                        total = total + valor;
                        break;
                    case "-":
                        total = total - valor;
                        break;
                    case "*":
                        total = total * valor;
                        break;
                    case "/":
                        total = total / valor;
                        break;
                    case "=":
                        calc_in_progess = false;
                        break;

                }
                operando = boton.getText().toString();
                buffer.delete(0, buffer.length());
                pantalla.setText(total.toString());
            }
        }


    }
}