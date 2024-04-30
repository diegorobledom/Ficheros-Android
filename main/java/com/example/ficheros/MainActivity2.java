package com.example.ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity2 extends AppCompatActivity
{
    EditText etFecha, etMensaje;
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Inicializar();

        bt1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nomarchivo=etFecha.getText().toString();
                nomarchivo = nomarchivo.replace('/','-');

                try
                {
                    OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                            nomarchivo, MainActivity2.MODE_PRIVATE));
                    archivo.write(etMensaje.getText().toString());
                    archivo.flush();
                    archivo.close();
                } catch (IOException e)
                {

                }

                Toast t = Toast.makeText(getApplicationContext(), "Los datos fueron grabados",Toast.LENGTH_SHORT);
                t.show();
                etFecha.setText("");
                etMensaje.setText("");
            }
        });

        bt2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String nomarchivo=etFecha.getText().toString();
                nomarchivo=nomarchivo.replace('/','-');

                boolean enco=false;
                String[] archivos = fileList();

                for (int f = 0; f < archivos.length; f++)
                {
                    if (nomarchivo.equals(archivos[f]))
                    {
                        enco= true;
                    }
                }

                if (enco)
                {
                    try
                    {
                        InputStreamReader archivo = new InputStreamReader(
                                openFileInput(nomarchivo));
                        BufferedReader br = new BufferedReader(archivo);

                        String linea = br.readLine();
                        String todo = "";

                        while (linea != null)
                        {
                            todo = todo + linea + "\n";
                            linea = br.readLine();
                        }

                        br.close();
                        archivo.close();
                        etMensaje.setText(todo);
                    } catch (IOException e)
                    {

                    }
                } else
                {
                    Toast.makeText(getApplicationContext(),"No hay datos grabados para dicha fecha", Toast.LENGTH_LONG).show();
                    etMensaje.setText("");
                }
            }
        });
    }

    private void Inicializar()
    {
        etFecha = (EditText) findViewById(R.id.editTextDate);
        etMensaje = (EditText) findViewById(R.id.editTextTextMensajeA2);
        bt1 = (Button) findViewById(R.id.button3);
        bt2 = (Button) findViewById(R.id.button4);
    }
}