package logico;

import java.util.Date;

public class Factura {
    private String codigo;
    private float total;
    private Cliente cliente;
    private Plan plan;
    private Date fecha;

    public Factura(String codigo, float total, Cliente cliente, Plan plan, Date fecha) {
        this.codigo = codigo;
        this.total = total;
        this.cliente = cliente;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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