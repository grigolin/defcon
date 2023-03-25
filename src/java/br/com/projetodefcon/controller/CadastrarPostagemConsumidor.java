package br.com.projetodefcon.controller;

import br.com.projetodefcon.dao.GenericDAO;
import br.com.projetodefcon.dao.PostagemDAOImpl;
import br.com.projetodefcon.model.Consumidor;
import br.com.projetodefcon.model.Postagem;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarPostagemConsumidor", urlPatterns = {"/CadastrarPostagemConsumidor"})
public class CadastrarPostagemConsumidor extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {

            String tituloPostagem = request.getParameter("tituloPostagem");
            String descricaoPostagem = request.getParameter("descricaoPostagem");

            String mensagem = null;

            Postagem postagem = new Postagem();

            postagem.setTituloPostagem(tituloPostagem);
            postagem.setDescricaoPostagem(descricaoPostagem);
            
            try {
                GenericDAO dao = new PostagemDAOImpl();
                if (dao.cadastrar(postagem)) {
                    mensagem = "Postagem enviada com sucesso!";
                } else {
                    mensagem = "Problemas ao enviar postagem. "
                            + "Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("ListarPostagemConsumidor").forward(request, response);
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
