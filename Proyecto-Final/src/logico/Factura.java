package logico;

import java.util.Date;

public class Factura {
    private String codigo;
    private float total;
    private Plan plan;
    private Date fecha;

    public Factura(String codigo, float total, Plan plan, Date fecha) {
        this.codigo = codigo;
        this.total = total;
        this.plan = plan;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}