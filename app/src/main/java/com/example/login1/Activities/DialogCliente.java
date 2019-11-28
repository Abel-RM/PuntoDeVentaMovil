package com.example.login1.Activities;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.login1.Models.Cliente;
import com.example.login1.R;

public class DialogCliente extends AppCompatDialogFragment {
    private EditText editTextNombre;
    private EditText editTextApellidoP;
    private EditText editTextApellidoM;
    private EditText editTextTelefono;
    private EditText editTextEmail;
    private EditText editTextColonia;
    private EditText editTextCalle;
    private EditText editTextNumeroIntoExt;
    private DialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Nuevo Cliente")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Cliente cliente = new Cliente();
                        cliente.setNombre(editTextNombre.getText().toString());
                        cliente.setApellidoP(editTextApellidoP.getText().toString());
                        cliente.setApellidoM(editTextApellidoM.getText().toString());
                        cliente.setTelefono(editTextTelefono.getText().toString());
                        cliente.setEmail(editTextEmail.getText().toString());
                        cliente.setColonia(editTextColonia.getText().toString());
                        cliente.setCalle(editTextCalle.getText().toString());
                        cliente.setNumeroIntoExt(editTextNumeroIntoExt.getText().toString());
                        listener.datosCliente(cliente);
                    }
                });

        editTextNombre = view.findViewById(R.id.editTextNombre);
        editTextApellidoP = view.findViewById(R.id.editTextApellidoP);
        editTextApellidoM = view.findViewById(R.id.editTextApellidoM);
        editTextTelefono = view.findViewById(R.id.editTextTelefono);
        editTextEmail = view.findViewById(R.id.editTextEmailCliente);
        editTextColonia = view.findViewById(R.id.editTextColonia);
        editTextCalle = view.findViewById(R.id.editTextCalle);
        editTextNumeroIntoExt = view.findViewById(R.id.editTextNumeroIntoExt);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogListener {
        void datosCliente(Cliente cliente);
    }
}
