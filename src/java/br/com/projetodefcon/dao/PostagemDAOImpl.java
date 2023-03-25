package br.com.projetodefcon.dao;

import br.com.projetodefcon.model.Postagem;
import br.com.projetodefcon.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostagemDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public PostagemDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        Postagem postagem = (Postagem) object;
        PreparedStatement stmt = null;
        
        String sql = "Insert into postagem(tituloPostagem, descricaoPostagem) values (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, postagem.getTituloPostagem());
            stmt.setString(2, postagem.getDescricaoPostagem());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao enviar postagem! Erro: " + ex.getMessage());
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
        List<Object> postagens = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from postagem;";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Postagem postagem = new Postagem();
                postagem.setIdPostagem(rs.getInt("idPostagem"));
                postagem.setTituloPostagem(rs.getString("tituloPostagem"));
                postagem.setDescricaoPostagem(rs.getString("descricaoPostagem"));
                postagens.add(postagem);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar postagens. Erro:" + ex.getMessage());
            ex.printStackTrace();
            
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return postagens;
    }
    
    public Object listarPostagemComentario(int idPostagem) {
        
        List<Object> postagens = new ArrayList<>();
        
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        Postagem postagem = null;
        
        String sql = "select p.* from postagem p where p.idPostagem = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPostagem);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                postagem = new Postagem();
                postagem.setIdPostagem(rs.getInt("idPostagem"));
                postagem.setTituloPostagem(rs.getString("tituloPostagem"));
                postagem.setDescricaoPostagem(rs.getString("descricaoPostagem"));
                postagens.add(postagem);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar postagens. Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return postagens;
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
