package marcogaray.ittepic.edu.mx.tpdm_u2_practica1_marcogaray;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    //------------------------------------------------------- CONSULTAR ------------------------------------------
    EditText iddesc;
    TextView descripcion, ubicacion, fecha, presupuesto;
    Button consultar, cerrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        iddesc = findViewById(R.id.iddesc);
        descripcion = findViewById(R.id.descripcion);
        ubicacion = findViewById(R.id.ubicacion);
        fecha = findViewById(R.id.fecha);
        presupuesto = findViewById(R.id.presupuesto);
        cerrar = findViewById(R.id.cerrar);


       consultar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mostrar();
           }
       });
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void mostrar(){
        Bundle parametros = getIntent().getExtras();
        descripcion.setText(parametros.getString("descripcion"));
        ubicacion.setText(parametros.getString("ubicacion"));
        fecha.setText(parametros.getString("fecha"));
        presupuesto.setText(parametros.getFloat("presupuesto")+"");
    }
}
