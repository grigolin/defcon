package br.com.projetodefcon.dao;

import br.com.projetodefcon.model.Advogado;
import br.com.projetodefcon.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdministradorDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public AdministradorDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public Boolean alterar(Advogado advogado) {
        PreparedStatement stmt = null;
        String sql = "update advogado set statusAdvogado = A where idPessoa = ?;";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, advogado.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar bicicleta! Erro: " + ex.getMessage());
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
            }
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean excluir(int idOject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean alterar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
