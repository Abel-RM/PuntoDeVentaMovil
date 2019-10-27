package com.example.login1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login1.R;
import com.example.login1.Utils.MetodosApi;
import com.example.login1.Utils.Util;

public class login extends AppCompatActivity {
    private TextView textViewEmail;
    private TextView textViewPassword;
    private Button btnLogin;
    private Switch switchRemember;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindUI();

        prefs=getSharedPreferences("preferences", Context.MODE_PRIVATE);
        setCredentialsIfExist();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= textViewEmail.getText().toString();
                String password = textViewPassword.getText().toString();
                if (login(email,password)){
                    //guardarToken(email,password);
                    goToMain();
                    saveOnPreferences(email,password);
                }
            }
        });

    }
    private void setCredentialsIfExist(){
        String email= Util.getUserMailPrefs(prefs);
        String pass= Util.getUserPassPrefs(prefs);
        if (!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)){
            textViewEmail.setText(email);
            textViewPassword.setText(pass);
        }
    }
    private void saveOnPreferences(String email,String password){
        if (switchRemember.isChecked()){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email",email);
            editor.putString("pass",password);
            editor.apply();
        }
    }
    private boolean guardarToken(String email,String password){
        String token=MetodosApi.getToken(email,password,this);
        if (!TextUtils.isEmpty(token)){
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("token",token);
            editor.apply();
            return true;
        }else
            return false;
    }

    private boolean login(String email, String password){
        if (!isValidEmail(email)){
            Toast.makeText(this,"Email is not valid, plase try again",Toast.LENGTH_LONG).show();
            return false;
        }else if (!isValidPassword(password)){
            Toast.makeText(this,"password is not valid, plase try again",Toast.LENGTH_LONG).show();
            return false;
        }else
            return true;

    }
    private void bindUI(){
        textViewEmail = (TextView) findViewById(R.id.editTextEmail);
        textViewPassword = (TextView) findViewById(R.id.editTextPassword);
        btnLogin= (Button) findViewById(R.id.buttonLogin);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
    }
    private boolean isValidEmail(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String email){
        return email.length()>4;
    }

    private void goToMain(){
        Intent intent= new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
