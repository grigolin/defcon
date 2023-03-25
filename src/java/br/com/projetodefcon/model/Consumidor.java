package br.com.projetodefcon.model;
public class Consumidor extends Pessoa{
    private Integer idConsumidor;
    private String cpfConsumidor;

    public Consumidor() {
    }

    public Consumidor(Integer idConsumidor, String cpfConsumidor) {
        this.idConsumidor = idConsumidor;
        this.cpfConsumidor = cpfConsumidor;
    }

    public Integer getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(Integer idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public String getCpfConsumidor() {
        return cpfConsumidor;
    }

    public void setCpfConsumidor(String cpfConsumidor) {
        this.cpfConsumidor = cpfConsumidor;
    }
    
    
}
