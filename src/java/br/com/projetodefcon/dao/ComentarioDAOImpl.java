package br.com.projetodefcon.dao;

import br.com.projetodefcon.model.Comentario;
import br.com.projetodefcon.model.Postagem;
import br.com.projetodefcon.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAOImpl implements GenericDAO {
    
    private Connection conn;
    
    public ComentarioDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    
    public Object listarComentario(int idPostagem) {
        
        List<Object> comentarios = new ArrayList<>();
        
        PreparedStatement stmt = null;
        
        ResultSet rs = null;
        
        Comentario comentario = null;
        
        String sql = "select c.* from comentario c, postagem p where c.idPostagem = p.idPostagem and p.idPostagem = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPostagem);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                comentario = new Comentario();
                comentario.setIdPostagem(rs.getInt("idPostagem"));
                comentario.setIdComentario(rs.getInt("idComentario"));
                comentario.setDescricaoComentario(rs.getString("descricaoComentario"));
                comentarios.add(comentario);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar comentarios. Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão. Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return comentarios;
    }

    @Override
    public Boolean cadastrar(Object object) {
        Comentario comentario = (Comentario) object;
        PreparedStatement stmt = null;
        
        String sql = "Insert into comentario (descricaoComentario,idPostagem) values (?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comentario.getDescricaoComentario());
            stmt.setInt(2, comentario.getIdPostagem());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao enviar comentario! Erro: " + ex.getMessage());
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
