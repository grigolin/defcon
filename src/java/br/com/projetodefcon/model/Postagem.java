package br.com.projetodefcon.model;
public class Postagem extends Pessoa{
    private Integer idPostagem;
    private String tituloPostagem;
    private String descricaoPostagem;

    public Postagem() {
    }

    public Postagem(Integer idPostagem, String tituloPostagem, String descricaoPostagem) {
        this.idPostagem = idPostagem;
        this.tituloPostagem = tituloPostagem;
        this.descricaoPostagem = descricaoPostagem;
    }

    public Integer getIdPostagem() {
        return idPostagem;
    }

    public void setIdPostagem(Integer idPostagem) {
        this.idPostagem = idPostagem;
    }

    public String getTituloPostagem() {
        return tituloPostagem;
    }

    public void setTituloPostagem(String tituloPostagem) {
        this.tituloPostagem = tituloPostagem;
    }

    public String getDescricaoPostagem() {
        return descricaoPostagem;
    }

    public void setDescricaoPostagem(String descricaoPostagem) {
        this.descricaoPostagem = descricaoPostagem;
    }
}
