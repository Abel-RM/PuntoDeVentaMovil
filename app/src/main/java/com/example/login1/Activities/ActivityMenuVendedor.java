package com.example.login1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login1.R;

public class ActivityMenuVendedor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_vendedor);

        Button vender = findViewById(R.id.btn_vender);
        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPantalla(0);
            }
        });

        Button historial = findViewById(R.id.btn_historial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPantalla(1);
            }
        });

        Button categorias = findViewById(R.id.btn_categorias);
        categorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarPantalla(2);
            }
        });
    }

    private void cambiarPantalla(int pantalla){
        Intent intent=null;
        if (pantalla==0){
            intent= new Intent(this,ActivityVendedor.class);
        }else
            if (pantalla==1){
                intent= new Intent(this,ActivityHistorial.class);
            }else
                if (pantalla==2){
                    intent= new Intent(this,ActivityCatalogo.class);
                }
         startActivity(intent);
    }
}
