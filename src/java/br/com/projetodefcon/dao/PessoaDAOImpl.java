package br.com.projetodefcon.dao;
import br.com.projetodefcon.model.Advogado;
import br.com.projetodefcon.model.Pessoa;
import br.com.projetodefcon.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public PessoaDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public Integer cadastrar(Pessoa pessoa) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Integer idPessoa = null;

        String sql = "Insert into pessoa (nomePessoa,loginPessoa,senhaPessoa,cepPessoa,estadoPessoa,cidadePessoa,enderecoPessoa,bairroPessoa,telefonePessoa,tipoPessoa) values (?,?,?,?,?,?,?,?,?,?) returning idPessoa;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getLoginPessoa());
            stmt.setString(3, pessoa.getSenhaPessoa());
            stmt.setString(4, pessoa.getCepPessoa());
            stmt.setString(5, pessoa.getEstadoPessoa());
            stmt.setString(6, pessoa.getCidadePessoa());
            stmt.setString(7, pessoa.getEnderecoPessoa());
            stmt.setString(8, pessoa.getBairroPessoa());
            stmt.setString(9, pessoa.getTelefonePessoa());
            stmt.setString(10, pessoa.getTipoPessoa());
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                idPessoa = rs.getInt("idPessoa");
            }            
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar pessoa! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return idPessoa;
    }
    
    public Object logarPessoa (String loginPessoa, String senhaPessoa) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa sessaoP = null;
        String sql = "select * from pessoa where loginPessoa = ? and senhaPessoa= ?";
        
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, loginPessoa);
            stmt.setString(2, senhaPessoa);
            rs = stmt.executeQuery();
            while (rs.next()) {
                sessaoP = new Pessoa();
                sessaoP.setNomePessoa(rs.getString("nomePessoa"));
                sessaoP.setLoginPessoa(rs.getString("loginPessoa"));
                sessaoP.setSenhaPessoa(rs.getString("senhaPessoa"));
                sessaoP.setIdPessoa(rs.getInt("idPessoa"));
                sessaoP.setTipoPessoa(rs.getString("tipoPessoa"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao logar pessoa. Erro:" + ex.getMessage());
            ex.printStackTrace();            
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessaoP;
    }
    
    public Boolean alterar(Pessoa pessoa) {
        PreparedStatement stmt = null;
        String sql = "update pessoa set nomePessoa = ?, loginPessoa = ?, senhaPessoa = ?, cepPessoa = ?, estadoPessoa = ?, enderecoPessoa = ?, telefonePessoa = ?, bairroPessoa = ?, cidadePessoa = ? where idPessoa = ?;";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomePessoa());
            stmt.setString(2, pessoa.getLoginPessoa());
            stmt.setString(3, pessoa.getSenhaPessoa());
            stmt.setString(4, pessoa.getCepPessoa());
            stmt.setString(5, pessoa.getEstadoPessoa());
            stmt.setString(6, pessoa.getEnderecoPessoa());
            stmt.setString(7, pessoa.getTelefonePessoa());
            stmt.setString(8,pessoa.getBairroPessoa());
            stmt.setString(9, pessoa.getCidadePessoa());
            stmt.setInt(10, pessoa.getIdPessoa());
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
    public Boolean alterar(Object object) {
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
}
    

