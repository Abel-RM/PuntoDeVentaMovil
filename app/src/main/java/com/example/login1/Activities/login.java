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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login1.R;
import com.example.login1.Utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    private TextView textViewEmail;
    private TextView textViewPassword;
    private Button btnLogin;
    private Switch switchRemember;
    private SharedPreferences prefs;
    public static String UserToken="";



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
                if (login(email)){
                    getToken(email,password,com.example.login1.Activities.login.this);
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
            editor.putString("token",UserToken);
            editor.apply();
        }
    }

    private boolean login(String email){
        if (!isValidEmail(email)){
            Toast.makeText(this,"Email is not valid, plase try again",Toast.LENGTH_LONG).show();
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


    private void goToMain( ){
        Intent intent= new Intent(this, ActivityVendedor.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void getToken(final String email, final String password,Context context){
        String url = "http://pvmovil.westus.azurecontainer.io/api/Usuarios/Login/";
        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("Email",email);
            jsonBody.put("Password",password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            UserToken=response.getString("Token");

                            saveOnPreferences(email,password);
                            goToMain();

                        } catch (JSONException e) {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(com.example.login1.Activities.login.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
