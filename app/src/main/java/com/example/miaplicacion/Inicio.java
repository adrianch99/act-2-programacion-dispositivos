package com.example.miaplicacion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class Inicio extends AppCompatActivity {

    Button guardar, consultar, upddate, eliminar;
    EditText nombre, email, clave, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        guardar = findViewById(R.id.btnGuardar);
        consultar = findViewById(R.id.btnConsultar);
        upddate = findViewById(R.id.btnActualizar);
        eliminar = findViewById(R.id.btnEliminar);


        nombre = findViewById(R.id.txtNombre);
        email = findViewById(R.id.txtEmail);
        clave = findViewById(R.id.txtClave);
        telefono = findViewById(R.id.txtTelefono);
    }

    public void Guardar(View view) {
        String name = nombre.getText().toString();
        String correo = email.getText().toString();
        String tel = telefono.getText().toString();
        String pass = clave.getText().toString();

        Usuario usu = new Usuario(name,correo,pass,tel);
        usu.save();

        //limpiar cajas de texto
        nombre.setText("");
        email.setText("");
        clave.setText("");
        telefono.setText("");
    }

    public void Consultar(View view) {
        String pass = clave.getText().toString();
        try {

            List<Usuario> lista = Usuario.find(Usuario.class, "clave= ?", pass);
            Usuario usu = lista.get(0);
            nombre.setText(usu.getNombre());
            telefono.setText(usu.getTelefono());
            email.setText(usu.getEmail());

        }catch (Exception e) {
            Toast.makeText(this, "Usuario no existe", Toast.LENGTH_LONG).show();
        }
    }

    public void Actualizar(View view) {
        String pass = clave.getText().toString();
        String name = nombre.getText().toString();
        String correo = email.getText().toString();
        String tel = telefono.getText().toString();

        try {
            List<Usuario> lista = Usuario.find(Usuario.class, "clave= ?", pass);
            Usuario user = lista.get(0);

            user.setNombre(name);
            user.setEmail(correo);
            user.setTelefono(tel);
            user.setClave(pass);
            user.save();

            nombre.setText("");
            telefono.setText("");
            email.setText("");
            clave.setText("");
        }catch (Exception e) {
            Toast.makeText(this, "Usuario no actualizado correctamente", Toast.LENGTH_LONG).show();
        }
    }

    public void Eliminar(View view) {
        String pass = clave.getText().toString();
        try {
            List<Usuario> lista = Usuario.find(Usuario.class, "clave= ?", pass);
            Usuario user = lista.get(0);

            user.delete();
            nombre.setText("");
            telefono.setText("");
            email.setText("");
            clave.setText("");
        }catch (Exception e) {
            Toast.makeText(this, "Usuario no eliminado correctamente", Toast.LENGTH_LONG).show();
        }
    }
}