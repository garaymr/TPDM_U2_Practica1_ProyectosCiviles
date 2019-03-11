package marcogaray.ittepic.edu.mx.tpdm_u2_practica1_marcogaray;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.SQLInput;

public class Proyecto {
    private BaseDatos base;
    private int idproyecto;
    private String descripcion, ubicacion, fecha;
    private float presupuesto;
    protected String error = "";

    public Proyecto(Activity activity){
        base = new BaseDatos(activity, "civil", null, 1);
    }

    public Proyecto(int id, String desc, String ubi, String f, float p){
        this.idproyecto = id;
        this.descripcion = desc;
        this.ubicacion = ubi;
        this.fecha = f;
        this.presupuesto = p;
    }

    public boolean insertar(Proyecto proyecto){
        try{
            SQLiteDatabase transaccionInsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("descripcion", proyecto.getDescripcion());
            datos.put("ubicacion", proyecto.getUbicacion());
            datos.put("fecha", proyecto.getFecha());
            datos.put("presupuesto", proyecto.getPresupuesto());

            long resultado = transaccionInsertar.insert("proyectos", "idproyecto", datos);
            transaccionInsertar.close();
            if(resultado == -1){
                return false;
            }
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(Proyecto p){
        int resultado;
        try{
            SQLiteDatabase transaccionEliminar = base.getWritableDatabase();
            String idproyecto = p.getIdproyecto()+"";
            String clave[] ={idproyecto};

            resultado = transaccionEliminar.delete("proyectos", "idproyecto = ?", clave);
            transaccionEliminar.close();

        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return resultado > 0;
    }

    public Proyecto[] consultar(String columna, String clave){
        Proyecto[] resultado = null;
        try{
            SQLiteDatabase transaccionConsulta =base.getReadableDatabase();
            String SQL ="SELECT * FROM proyectos WHERE idproyecto = " + clave;

            if(columna.startsWith("descripcion")){
                SQL = "SELECT * FROM proyectos WHERE descripcion = " + clave;
            }

            Cursor c = transaccionConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Proyecto[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos] = new Proyecto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getFloat(4));
                    pos++;
                }while (c.moveToNext());
            }
            transaccionConsulta.close();
        }catch(SQLiteException e){
            return null;
        }
        return resultado;
    }

    public boolean actualizar(Proyecto p){
        try{
            SQLiteDatabase transaccionActualizar = base.getWritableDatabase();

            String idproyecto = p.getIdproyecto()+"";
            String clave[] ={idproyecto};
            ContentValues datos = new ContentValues();
            datos.put("desripcion", p.getDescripcion());
            datos.put("ubicacion", p.getUbicacion());
            datos.put("fecha", p.getFecha());
            datos.put("presupuesto", p.getPresupuesto());

            long resultado = transaccionActualizar.update("proyectos", datos, "idproyecto = ?", clave);
            transaccionActualizar.close();
            if (resultado == -1){
                return false;
            }
        }catch(SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return false;
    }

    public Proyecto[] consultarT(){
        Proyecto[] resultado= null;
        try{
            SQLiteDatabase transacciónConsultaT = base.getReadableDatabase();
            String SQL = "SELECT * FROM proyectos";
            Cursor c = transacciónConsultaT.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Proyecto[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos]=new Proyecto(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getFloat(4));
                    pos++;
                }while (c.moveToNext());
            }
            transacciónConsultaT.close();
        }catch (SQLiteException e){
            return null;
        }
        return resultado;
    }


    public BaseDatos getBase() {
        return base;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }
}
