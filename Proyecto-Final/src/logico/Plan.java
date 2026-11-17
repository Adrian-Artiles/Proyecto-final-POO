package logico;

import java.util.ArrayList;
import java.util.Date; //por si acaso 

public class Plan {
    private String nombre;
    private String codigo;
    private float precio;
    private ArrayList<Servicio> servicios;

    public Plan(String nombre, String codigo, float precio, ArrayList<Servicio> servicios) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.servicios = servicios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }
}