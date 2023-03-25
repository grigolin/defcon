package br.com.projetodefcon.controller;

import br.com.projetodefcon.dao.ComentarioDAOImpl;
import br.com.projetodefcon.dao.GenericDAO;
import br.com.projetodefcon.model.Comentario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CadastrarComentario", urlPatterns = {"/CadastrarComentario"})
public class CadastrarComentario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String descricaoComentario = request.getParameter("descricaoComentario");
            int idPostagem = Integer.parseInt(request.getParameter("idPostagem"));

            String mensagem = null;

            Comentario comentario = new Comentario();

            comentario.setDescricaoComentario(descricaoComentario);
            comentario.setIdPostagem(idPostagem);
            
            try {
                GenericDAO dao = new ComentarioDAOImpl();
                if (dao.cadastrar(comentario)) {
                    mensagem = "Comentario enviada com sucesso!";
                } else {
                    mensagem = "Problemas ao enviar Comentario. "
                            + "Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("ListarComentarioAdvogado").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Problemas no Servlet ao cadastrar Consumidor! Erro: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
