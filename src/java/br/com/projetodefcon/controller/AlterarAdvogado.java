package br.com.projetodefcon.controller;

import br.com.projetodefcon.dao.AdvogadoDAOImpl;
import br.com.projetodefcon.dao.GenericDAO;
import br.com.projetodefcon.model.Advogado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AlterarAdvogado", urlPatterns = {"/AlterarAdvogado"})
public class AlterarAdvogado extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;

    HttpSession sessao = httpServletRequest.getSession();

    if (sessao.getAttribute("sessaoP") != null) {
        
            String nomePessoa = request.getParameter("nomePessoa");
            String loginPessoa = request.getParameter("loginPessoa");
            String senhaPessoa = request.getParameter("senhaPessoa");
            String cepPessoa = request.getParameter("cepPessoa");
            String estadoPessoa = request.getParameter("estadoPessoa");
            String cidadePessoa = request.getParameter("cidadePessoa");
            String enderecoPessoa = request.getParameter("enderecoPessoa");
            String bairroPessoa = request.getParameter("bairroPessoa");
            String telefonePessoa = request.getParameter("telefonePessoa");
            String oabAdvogado = request.getParameter("oabAdvogado");
            Integer idAdvogado = Integer.parseInt(request.getParameter("idAdvogado"));
            Integer idPessoa = Integer.parseInt(request.getParameter("idPessoa"));


            String mensagem = null;

            Advogado advogado = new Advogado();
            advogado.setNomePessoa(nomePessoa);
            advogado.setLoginPessoa(loginPessoa);
            advogado.setSenhaPessoa(senhaPessoa);
            advogado.setCepPessoa(cepPessoa);
            advogado.setEstadoPessoa(estadoPessoa);
            advogado.setCidadePessoa(cidadePessoa);
            advogado.setEnderecoPessoa(enderecoPessoa);
            advogado.setBairroPessoa(bairroPessoa);
            advogado.setTelefonePessoa(telefonePessoa);
            advogado.setOabAdvogado(oabAdvogado);
            advogado.setIdAdvogado(idAdvogado);
            advogado.setIdPessoa(idPessoa);

            try {
                GenericDAO dao = new AdvogadoDAOImpl();
                if (dao.alterar(advogado)) {
                    mensagem = "Cadastro do advogado alterado com sucesso";
                } else {
                    mensagem = "Problemas ao alterar cadastro do advogado!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("homeAdvogado.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Problemas ao alterar cliente! Erro:"
                        + e.getMessage());
                e.printStackTrace();
            }
            } else {
        ((HttpServletResponse) response).sendRedirect("index.html");
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
