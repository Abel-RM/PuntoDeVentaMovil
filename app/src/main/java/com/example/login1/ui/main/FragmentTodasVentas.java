package com.example.login1.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.example.login1.Activities.ActivityVendedor;
import com.example.login1.Activities.login;
import com.example.login1.Adapters.AdaptadorSurtidorT;
import com.example.login1.Models.Producto;
import com.example.login1.Models.Venta;
import com.example.login1.Models.VentaResultado;
import com.example.login1.R;
import com.example.login1.Utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentTodasVentas extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static ListView list;
    private static View v;
    static AdaptadorSurtidorT adapterList;
    private RequestQueue mQueue;
    private static ArrayList<VentaResultado> ventaResultados = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentTodasVentas() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentTodasVentas newInstance(String param1, String param2) {
        FragmentTodasVentas fragment = new FragmentTodasVentas();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_fragment_todas_ventas, container, false);
        ventaResultados.clear();
        mQueue = VolleySingleton.getInstance(v.getContext()).getRequestQueue();
        getVentas();


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void getVentas() {
        String url = "http://pvmovilbackend.eastus.azurecontainer.io/api/Ventas?fecha1=01-01-2019&fecha2=12-30-2019";
        final String tok="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6Imtva2lfb3JsYW5kOTdAaG90bWFpbC5jb20iLCJyb2xlIjoiQWRtaW4iLCJuYmYiOjE1NzU1MTAwMzMsImV4cCI6MTU3NjExNDgzMywiaWF0IjoxNTc1NTEwMDMzfQ.SGfFFiU6W1m7Qw2AsvBPFdEsY6O1k_v6_YyeAo1NKTo";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Gson gson = new Gson();
                            JSONArray array =response.getJSONArray("Data");
                            for (int i=0;i<array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                if (obj.getString("SurtidorId").equals("null"))
                                ventaResultados.add(gson.fromJson(obj.toString(), VentaResultado.class));
                            }
                            list=  v.findViewById(R.id.list_fragment_todas);

                            adapterList = new AdaptadorSurtidorT(v.getContext(),R.layout.custom_list_item,ventaResultados);
                            list.setAdapter(adapterList);

                        } catch (Exception e) {
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + tok);
                return headers;
            }
        };
        mQueue.add(jsonObjectRequest);

    }
    public static void actualizar(){
        adapterList.notifyDataSetChanged();
    }



}
