package marcogaray.ittepic.edu.mx.tpdm_u2_practica1_marcogaray;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    //---------------------------------------------------Insertar
    EditText descripcion, ubicacion, fecha, presupuesto;
    Button insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        insertar = findViewById(R.id.insertar);

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inserta();
            }
        });

    }
    public void inserta(){
        String mensaje = "";
        Proyecto proyecto = new Proyecto(this);
        boolean respuesta = proyecto.insertar(new Proyecto(0, descripcion.getText().toString(), ubicacion.getText().toString(), fecha.getText().toString(), Float.parseFloat(presupuesto.getText().toString())));
        if(respuesta){
            mensaje = "Se logró insertar el proyecto "+ descripcion.getText().toString();
        }else{
            mensaje = "Error, no se creó el proyecto, respuesta de SQLite: "+proyecto.error;
        }

        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención").setMessage(mensaje).setPositiveButton("Ok", null).show();

    }
}
