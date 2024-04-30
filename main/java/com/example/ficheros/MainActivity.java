package com.example.ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    EditText etMensaje;
    Button enviar, segundo;
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Inicializar();

        Comprobar();

        enviar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                grabar();
            }
        });

        segundo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(i);
            }
        });
    }

    private void Inicializar()
    {
        etMensaje = (EditText) findViewById(R.id.editTextMensaje);
        enviar = (Button) findViewById(R.id.button);
        segundo = (Button) findViewById(R.id.button2);
    }

    private void Comprobar()
    {
        String[] archivos = fileList();

        if (existe(archivos, "notas.txt"))
        {
            try
            {
                InputStreamReader archivo = new InputStreamReader(openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                StringBuilder todo = new StringBuilder();

                while (linea != null)
                {
                    todo.append(linea).append("\n");
                    linea = br.readLine();
                }

                br.close();
                archivo.close();
                etMensaje.setText(todo.toString());
            } catch (IOException e)
            {

            }
        }
    }

    public void grabar()
    {
        mensaje = etMensaje.getText().toString();

        try
        {
            OutputStreamWriter archivo  = new OutputStreamWriter(openFileOutput("notas.txt",
                    MainActivity.MODE_PRIVATE));

            archivo.write(mensaje);
            archivo.flush();
            archivo.close();
        } catch (IOException e)
        {

        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",Toast.LENGTH_SHORT);
        t.show();
        //finish();
    }

    private boolean existe(String[] archivos, String archbusca)
    {
        for (int f = 0; f < archivos.length; f++)
        {
            if (archbusca.equals(archivos[f]))
            {
                return true;
            }
        }

        return false;
    }
}