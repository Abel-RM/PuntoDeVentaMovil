package com.example.login1.Activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.login1.Models.Producto;
import com.example.login1.Utils.Util;
import com.google.zxing.Result;

import java.io.Serializable;
import java.util.HashSet;
import java.util.ArrayList;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    private ArrayList<Producto> productos= new ArrayList<>();
    private ArrayList<Producto> productosSelec= new ArrayList<>();
    private ArrayList<String> codes= new ArrayList<>();

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        productos = (ArrayList<Producto>) args.getSerializable("Productos");
        productosSelec = (ArrayList<Producto>) args.getSerializable("ProductosSeleccionados");

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        Intent intent = new Intent(SimpleScannerActivity.this, ActivityVendedor.class);
        Util.auxiliar =productosSelec;
        startActivity(intent);

        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        codes.add(rawResult.getText());
        try {
            for(String item : codes){
                Producto p= searchProductByCode(item);
                p.setPedidos(1);
                if (buscar(item)){
                    productosSelec.add(p);
                    Toast.makeText(this,"Producto Guardado" ,Toast.LENGTH_SHORT).show();
                }

            }
            codes.clear();

        }catch (Exception e){ }

        mScannerView.resumeCameraPreview(this);
    }
    public Producto searchProductByCode(String cod){
        for (Producto item : productos){
            if(item.getCodigoBarra().equals(cod) ){

                return item;
            }
        }
        Toast.makeText(this,"Producto no encontrado" ,Toast.LENGTH_SHORT).show();
        return null;
    }
    public boolean buscar(String name){
        for (Producto item: productosSelec){
            if (item.getCodigoBarra().equals(name)){
                return false;
            }
        }
        return true;
    }

}
