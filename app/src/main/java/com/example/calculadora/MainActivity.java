package com.example.calculadora;

import android.os.Bundle;
import android.util.Log;
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
    Float valor= (float) 0;
    StringBuffer buffer = new StringBuffer();
    TextView pantalla;
    Boolean calc_in_progess = false;
    String operando;
    Float memoria= (float) 0;


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
        if (savedInstanceState != null) {
            total = savedInstanceState.getFloat("total");
            buffer.append(savedInstanceState.getString("buffer"));
            String pantalla_text = savedInstanceState.getString("pantalla"); pantalla.setText(savedInstanceState.getString("pantalla"));
            operando = savedInstanceState.getString("operando");
            memoria = savedInstanceState.getFloat("memoria");
            calc_in_progess=savedInstanceState.getBoolean("calc_in_progess");

            pantalla.setText(pantalla_text);

        }
    }

    public void boton_num(View v){
        Button  boton = (Button) v;
        buffer.append(boton.getText().toString());
        pantalla.setText(buffer.toString());

    }
    public void boton_operacion(View v){
        Button  boton = (Button) v;
        if(!calc_in_progess){
            if (buffer.length() > 0) {
                calc_in_progess=true;
                operando = boton.getText().toString();
                total = Float.parseFloat(buffer.toString());
                buffer.delete(0,buffer.length());
            }
        }
        else{
            Log.d("operacion", operando);
             String next_operando= boton.getText().toString();
            if (buffer.length() > 0) {
                valor = Float.parseFloat(buffer.toString());
                switch (operando) {
                    case "+":
                        total = total + valor;
                        Log.d("operacion", operando+" "+total);
                        break;
                    case "-":
                        total = total - valor;
                        Log.d("operacion", operando+" "+total);
                        break;
                    case "*":
                        total = total * valor;
                        Log.d("operacion", operando+" "+total);

                        break;
                    case "/":
                        total = total / valor;
                        Log.d("operacion", operando+" "+total);
                        break;

                }

            }
            if(next_operando == "="){
                Log.d("operacion", "igualdad");
                calc_in_progess=false;
            }else{
                Log.d("operacion", "seguimos");
                operando = next_operando;
            }
            buffer.delete(0, buffer.length());
            pantalla.setText(total.toString());
        }


    }

    public void reset(View v){
        buffer.delete(0, buffer.length());
        pantalla.setText("0");
        calc_in_progess=false;
        total= (float) 0;
        valor= (float) 0;
        operando="";
    }
    public void borrar(View v){
        if(buffer.length()>0){
            buffer.deleteCharAt(buffer.length()-1);
            pantalla.setText(buffer.toString());
        }
    }
    public void sumar_memoria(View v){
        memoria = memoria + Float.parseFloat(pantalla.getText().toString());
    }
    public void restar_memoria(View v){
        memoria = memoria - Float.parseFloat(pantalla.getText().toString());
    }
    public void borrar_memoria(View v){
        memoria= (float) 0;
    }
    public void leer_memoria(View v){
        pantalla.setText(memoria.toString());
    }
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putFloat("total",total);

        savedInstanceState.putString("buffer",buffer.toString());
        savedInstanceState.putString("pantalla",pantalla.getText().toString());
        savedInstanceState.putString("operando",operando);
        savedInstanceState.putFloat("memoria",memoria);
        savedInstanceState.putBoolean("calc_in_progess",calc_in_progess);
        Log.d("SavedInstanceState", "total = " + total);
        Log.d("SavedInstanceState", "buffer = " + buffer.toString());
        Log.d("SavedInstanceState", "pantalla = " + pantalla.getText().toString());
        Log.d("SavedInstanceState", "operando = " + operando);
        Log.d("SavedInstanceState", "memoria = " + memoria);
        Log.d("SavedInstanceState", "calc_in_progess = " + calc_in_progess);

    }

}