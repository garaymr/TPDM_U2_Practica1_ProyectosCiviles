package marcogaray.ittepic.edu.mx.tpdm_u2_practica1_marcogaray;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    Proyecto vector[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaproyectos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mostrarAlerta(position);
            }
        });
    }

    public void mostrarAlerta(final int pos ){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atención").setMessage("Deseas modificar/eliminar el proyecto " + vector[pos].getDescripcion() + "?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                invocarEliminarActualizar(pos);
            }
        })
            .setNegativeButton("No", null).show();
    }

    public void invocarEliminarActualizar(int pos){
        Intent eliminaactualiza = new Intent(this, Main2Activity.class);
        eliminaactualiza.putExtra("idproyecto", vector[pos].getIdproyecto());
        eliminaactualiza.putExtra("descripcion", vector[pos].getDescripcion());
        eliminaactualiza.putExtra("ubicacion", vector[pos].getUbicacion());
        eliminaactualiza.putExtra("fecha", vector[pos].getFecha());
        eliminaactualiza.putExtra("presupuesto", vector[pos].getPresupuesto());

        startActivity(eliminaactualiza);
    }

    @Override
    protected void onStart() {
        Proyecto proyecto = new Proyecto(this);
        vector = proyecto.consultarT();
        String[] descripcion = null;

        if(descripcion == null){
            descripcion = new String[1];
            descripcion[0] = "No hay proyectos, sorry";
        }else{
            descripcion = new String[vector.length];
            for(int i = 0; i < vector.length; i++){
                Proyecto temp = vector[i];
                descripcion[i] = temp.getDescripcion()+"\n";
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, descripcion);
        lista.setAdapter(adaptador);

        super.onStart();
    }

    //ctrl+o onCreateMenu -onOptionsItemSelected


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.consultarP:
                Intent consultarProtecto = new Intent(this, Main2Activity.class);
                startActivity(consultarProtecto);
            case R.id.insertarP:
                Intent nuevoProyecto= new Intent(this, Main3Activity.class);
                startActivity(nuevoProyecto);
                break;
            case R.id.modificarP:
                Intent modificarProyecto = new Intent(this, Main4Activity.class);
                startActivity(modificarProyecto);
                break;
            case R.id.eliminarP:
                //Intent eliminarProyecto = new Intent(this, MainA5ctivity.class);
                //startActivity(eliminarProyecto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
