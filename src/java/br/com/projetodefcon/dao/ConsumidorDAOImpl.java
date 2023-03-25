package br.com.projetodefcon.dao;
import br.com.projetodefcon.model.Advogado;
import br.com.projetodefcon.model.Consumidor;
import br.com.projetodefcon.model.Pessoa;
import br.com.projetodefcon.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumidorDAOImpl implements GenericDAO{
    
    private Connection conn;

    public ConsumidorDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {

        Consumidor consumidor = (Consumidor) object;
        PreparedStatement stmt = null;
        
        String sql = "Insert into consumidor(cpfConsumidor, idPessoa) values (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consumidor.getCpfConsumidor());
            stmt.setInt(2, new PessoaDAOImpl().cadastrar(consumidor));
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
        List<Object> consumidores = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select distinct c.*,p.* from consumidor c, pessoa p where c.idPessoa = p.idPessoa;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Consumidor consumidor = new Consumidor();
                consumidor.setIdConsumidor(rs.getInt("idConsumidor"));
                consumidor.setNomePessoa(rs.getString("nomePessoa"));
                consumidor.setEstadoPessoa(rs.getString("estadoPessoa"));
                consumidor.setCidadePessoa(rs.getString("cidadePessoa"));
                consumidores.add(consumidor);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar consumidores. Erro:" + ex.getMessage());
            ex.printStackTrace();
            
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return consumidores;
    }

    @Override
    public Boolean excluir(int idOject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Consumidor consumidor = null;
        String sql = "select p.*,c.* FROM pessoa p, consumidor c WHERE p.idPessoa = ? and p.idPessoa = c.idPessoa;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                consumidor = new Consumidor();
                consumidor.setIdPessoa(rs.getInt("idPessoa"));
                consumidor.setIdConsumidor(rs.getInt("idConsumidor"));
                consumidor.setNomePessoa(rs.getString("nomePessoa"));
                consumidor.setEstadoPessoa(rs.getString("estadoPessoa"));
                consumidor.setLoginPessoa(rs.getString("loginPessoa"));
                consumidor.setSenhaPessoa(rs.getString("senhaPessoa"));
                consumidor.setCepPessoa(rs.getString("cepPessoa"));
                consumidor.setEnderecoPessoa(rs.getString("enderecoPessoa"));
                consumidor.setTelefonePessoa(rs.getString("telefonePessoa"));
                consumidor.setBairroPessoa(rs.getString("bairroPessoa"));
                consumidor.setCidadePessoa(rs.getString("cidadePessoa"));
                consumidor.setCpfConsumidor(rs.getString("cpfConsumidor"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar consumidor. Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return consumidor;
    }

    @Override
    public Boolean alterar(Object object) {
        Consumidor consumidor = (Consumidor) object;
        PreparedStatement stmt = null;
        String sql = "update consumidor set cpfConsumidor = ? where idPessoa = ?;";

        try {

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, consumidor.getCpfConsumidor());
            stmt.setInt(2, consumidor.getIdPessoa());
            if (new PessoaDAOImpl().alterar(consumidor)) {
                stmt.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao cadastrar bicicleta! Erro: " + ex.getMessage());
            return false;
        } catch (Exception ex) {
            Logger.getLogger(ConsumidorDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
