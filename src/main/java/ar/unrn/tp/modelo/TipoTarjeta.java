package ar.unrn.tp.modelo;


public enum TipoTarjeta {

    MASTERCARD("mastercard"), VISA("visa"), MACRO("macro");
    private String nombre;
    TipoTarjeta(String nombre){
        this.nombre = nombre;
    }
    public String nombre(){
        return this.nombre;
    }
}
