package br.com.projetodefcon.model;
public class Advogado extends Pessoa{
    private Integer idAdvogado;
    private String oabAdvogado;

    public Advogado() {
    }

    public Advogado(Integer idAdvogado, String oabAdvogado) {
        this.idAdvogado = idAdvogado;
        this.oabAdvogado = oabAdvogado;
    }

    public Integer getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(Integer idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getOabAdvogado() {
        return oabAdvogado;
    }

    public void setOabAdvogado(String oabAdvogado) {
        this.oabAdvogado = oabAdvogado;
    }
    
    
}
