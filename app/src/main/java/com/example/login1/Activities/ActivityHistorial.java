package com.example.login1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login1.Adapters.AdaptadorHistorial;
import com.example.login1.R;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityHistorial extends AppCompatActivity {
    static AdaptadorHistorial adapterList;
    ArrayList<String> ejem= new ArrayList<>();
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        ejem.add("Luis");

        list=  findViewById(R.id.list_historial);
        adapterList = new AdaptadorHistorial(ActivityHistorial.this,R.layout.custom_list_item,ejem);

        list.setAdapter(adapterList);

    }
}
