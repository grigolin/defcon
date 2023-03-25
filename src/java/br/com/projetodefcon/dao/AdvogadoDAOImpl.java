package br.com.projetodefcon.dao;
import br.com.projetodefcon.model.Advogado;
import br.com.projetodefcon.util.ConnectionFactory;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AdvogadoDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public AdvogadoDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {

        Advogado advogado = (Advogado) object;
        PreparedStatement stmt = null;
        
        String sql = "Insert into advogado(oabAdvogado, idPessoa) values (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, advogado.getOabAdvogado());
            stmt.setInt(2, new PessoaDAOImpl().cadastrar(advogado));
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar usuário! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> advogados = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select distinct a.*,p.* from advogado a, pessoa p where a.idPessoa = p.idPessoa;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Advogado advogado = new Advogado();
                advogado.setIdAdvogado(rs.getInt("idAdvogado"));
                advogado.setNomePessoa(rs.getString("nomePessoa"));
                advogado.setEstadoPessoa(rs.getString("estadoPessoa"));
                advogado.setCidadePessoa(rs.getString("cidadePessoa"));
                advogados.add(advogado);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar advogados. Erro:" + ex.getMessage());
            ex.printStackTrace();
            
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return advogados;
    }

    @Override
    public Boolean excluir(int idOject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Advogado advogado = null;
        String sql = "select p.*, a.* from advogado a, pessoa p where a.idPessoa = p.idPessoa and p.idPessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            while (rs.next()) {
                advogado = new Advogado();
                advogado.setIdPessoa(rs.getInt("idPessoa"));
                advogado.setIdAdvogado(rs.getInt("idAdvogado"));
                advogado.setNomePessoa(rs.getString("nomePessoa"));
                advogado.setLoginPessoa(rs.getString("loginPessoa"));
                advogado.setSenhaPessoa(rs.getString("senhaPessoa"));
                advogado.setCepPessoa(rs.getString("cepPessoa"));
                advogado.setEnderecoPessoa(rs.getString("enderecoPessoa"));
                advogado.setBairroPessoa(rs.getString("bairroPessoa"));
                advogado.setCidadePessoa(rs.getString("cidadePessoa"));
                advogado.setEstadoPessoa(rs.getString("estadoPessoa"));
                advogado.setTelefonePessoa(rs.getString("telefonePessoa"));
                advogado.setOabAdvogado(rs.getString("oabAdvogado"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar bicicleta. Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return advogado;
    }

    @Override
    public Boolean alterar(Object object) {
        Advogado advogado = (Advogado) object;
        PreparedStatement stmt = null;
        String sql = "update advogado set oabAdvogado = ? where idPessoa = ?;";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, advogado.getOabAdvogado());
            stmt.setInt(2, advogado.getIdPessoa());
            if (new PessoaDAOImpl().alterar(advogado)) {
                stmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar bicicleta! Erro: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            Logger.getLogger(AdvogadoDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + ex.getMessage());
            }
        }
        return null;
    }

}
