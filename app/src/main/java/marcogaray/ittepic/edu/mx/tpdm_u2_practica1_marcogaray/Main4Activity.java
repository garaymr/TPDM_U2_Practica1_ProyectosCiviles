package marcogaray.ittepic.edu.mx.tpdm_u2_practica1_marcogaray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    EditText descripcionE, ubicacionE, fechaE, presupuestoE;
    Button modificar, eliminar;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        descripcionE = findViewById(R.id.descripcionE);
        ubicacionE = findViewById(R.id.ubicacionE);
        fechaE = findViewById(R.id.fechaE);
        presupuestoE = findViewById(R.id.presupuestoE);
        modificar = findViewById(R.id.modificar);
        eliminar = findViewById(R.id.eliminar);


        Bundle parametros = getIntent().getExtras();
        descripcionE.setText(parametros.getString("descripcion"));
        ubicacionE.setText(parametros.getString("ubicacion"));
        fechaE.setText(parametros.getString("fecha"));
        presupuestoE.setText(parametros.getFloat("presupuesto")+"");
        id = parametros.getInt("idproyecto");

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifica();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elimina();
            }
        });

    }

    public void modifica(){
        Proyecto p = new Proyecto(this);
        String mensaje = "";
        boolean respuesta = p.actualizar(new Proyecto(id, descripcionE.getText().toString(), ubicacionE.getText().toString(), fechaE.getText().toString(), Float.parseFloat(presupuestoE.getText().toString())));
        if(respuesta){
            mensaje = "Se actualizó correctamente el abogado: "+descripcionE.getText().toString();
        }else{
            mensaje = "Error, no se pudo actualizar, error: "+ p.error;
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public void elimina(){
        Proyecto p = new Proyecto(this);
        String mensaje = "";
        boolean respuesta = p.eliminar(new Proyecto(id, descripcionE.getText().toString(), ubicacionE.getText().toString(), fechaE.getText().toString(), Float.parseFloat(presupuestoE.getText().toString())));
        if(respuesta){
            mensaje = "Se eliminó correctamente el abogado: "+descripcionE.getText().toString();
        }else{
            mensaje = "Error, no se pudo eliminar, error: "+ p.error;
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
