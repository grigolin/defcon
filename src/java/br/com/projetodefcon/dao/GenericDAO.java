package br.com.projetodefcon.dao;

import java.util.List;

public interface GenericDAO {
    
    public Boolean cadastrar(Object object);
    
    public List<Object> listar();
    
    public Boolean excluir(int idOject);
    
    public Object carregar(int idObject);
    
    public Boolean alterar(Object object);

      
}
