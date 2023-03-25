package br.com.projetodefcon.model;
public class Comentario extends Postagem{
    private Integer idComentario;
    private String descricaoComentario;
    private Postagem idPostagem;

    public Comentario() {
    }

    public Comentario(Postagem idPostagem) {
        this.idPostagem = idPostagem;
    }

    public Comentario(Integer idComentario, String descricaoComentario, Postagem idPostagem) {
        this.idComentario = idComentario;
        this.descricaoComentario = descricaoComentario;
        this.idPostagem = idPostagem;
    }

    public Integer getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(Integer idComentario) {
        this.idComentario = idComentario;
    }

    public String getDescricaoComentario() {
        return descricaoComentario;
    }

    public void setDescricaoComentario(String descricaoComentario) {
        this.descricaoComentario = descricaoComentario;
    }


    public void setIdPostagem(Postagem idPostagem) {
        this.idPostagem = idPostagem;
    }
    
    
}
