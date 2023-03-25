package br.com.projetodefcon.controller;

import br.com.projetodefcon.dao.ConsumidorDAOImpl;
import br.com.projetodefcon.dao.GenericDAO;
import br.com.projetodefcon.model.Consumidor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AlterarConsumidor", urlPatterns = {"/AlterarConsumidor"})
public class AlterarConsumidor extends HttpServlet {

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
            String cpfConsumidor = request.getParameter("cpfConsumidor");
            Integer idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
            Integer idConsumidor = Integer.parseInt(request.getParameter("idConsumidor"));


            String mensagem = null;

            Consumidor consumidor = new Consumidor();
            consumidor.setNomePessoa(nomePessoa);
            consumidor.setLoginPessoa(loginPessoa);
            consumidor.setSenhaPessoa(senhaPessoa);
            consumidor.setCepPessoa(cepPessoa);
            consumidor.setEstadoPessoa(estadoPessoa);
            consumidor.setCidadePessoa(cidadePessoa);
            consumidor.setEnderecoPessoa(enderecoPessoa);
            consumidor.setBairroPessoa(bairroPessoa);
            consumidor.setTelefonePessoa(telefonePessoa);
            consumidor.setCpfConsumidor(cpfConsumidor);
            consumidor.setIdPessoa(idPessoa);
            consumidor.setIdConsumidor(idConsumidor);

            try {
                GenericDAO dao = new ConsumidorDAOImpl();
                if (dao.alterar(consumidor)) {
                    mensagem = "Cadastro do consumidor alterado com sucesso";
                } else {
                    mensagem = "Problemas ao alterar cadastro do consumidor!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("homeConsumidor.jsp").forward(request, response);
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
