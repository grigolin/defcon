package br.com.projetodefcon.model;
public class Pessoa {
    private Integer idPessoa;
    private String nomePessoa;
    private String estadoPessoa;
    private String loginPessoa;
    private String senhaPessoa;
    private String cepPessoa;
    private String enderecoPessoa;
    private String telefonePessoa;
    private String bairroPessoa;
    private String cidadePessoa;
    private String tipoPessoa;
    /**private Date dataPlano; **/
    /**private alguma poha fotoPessoa**/
    public Pessoa() {
    }

    public Pessoa(Integer idPessoa, String nomePessoa, String estadoPessoa, String loginPessoa, String senhaPessoa, String cepPessoa, String enderecoPessoa, String telefonePessoa, String bairroPessoa, String cidadePessoa, String tipoPessoa) {
        this.idPessoa = idPessoa;
        this.nomePessoa = nomePessoa;
        this.estadoPessoa = estadoPessoa;
        this.loginPessoa = loginPessoa;
        this.senhaPessoa = senhaPessoa;
        this.cepPessoa = cepPessoa;
        this.enderecoPessoa = enderecoPessoa;
        this.telefonePessoa = telefonePessoa;
        this.bairroPessoa = bairroPessoa;
        this.cidadePessoa = cidadePessoa;
        this.tipoPessoa = tipoPessoa;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNomePessoa() {
        return nomePessoa;
    }

    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    public String getEstadoPessoa() {
        return estadoPessoa;
    }

    public void setEstadoPessoa(String estadoPessoa) {
        this.estadoPessoa = estadoPessoa;
    }

    public String getLoginPessoa() {
        return loginPessoa;
    }

    public void setLoginPessoa(String loginPessoa) {
        this.loginPessoa = loginPessoa;
    }

    public String getSenhaPessoa() {
        return senhaPessoa;
    }

    public void setSenhaPessoa(String senhaPessoa) {
        this.senhaPessoa = senhaPessoa;
    }

    public String getCepPessoa() {
        return cepPessoa;
    }

    public void setCepPessoa(String cepPessoa) {
        this.cepPessoa = cepPessoa;
    }

    public String getEnderecoPessoa() {
        return enderecoPessoa;
    }

    public void setEnderecoPessoa(String enderecoPessoa) {
        this.enderecoPessoa = enderecoPessoa;
    }

    public String getTelefonePessoa() {
        return telefonePessoa;
    }

    public void setTelefonePessoa(String telefonePessoa) {
        this.telefonePessoa = telefonePessoa;
    }

    public String getBairroPessoa() {
        return bairroPessoa;
    }

    public void setBairroPessoa(String bairroPessoa) {
        this.bairroPessoa = bairroPessoa;
    }

    public String getCidadePessoa() {
        return cidadePessoa;
    }

    public void setCidadePessoa(String cidadePessoa) {
        this.cidadePessoa = cidadePessoa;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }
    
}
