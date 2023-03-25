package br.com.projetodefcon.controller;

import br.com.projetodefcon.dao.PessoaDAOImpl;
import br.com.projetodefcon.model.Pessoa;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogarPessoa", urlPatterns = {"/LogarPessoa"})
public class LogarPessoa extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            try {
                String loginPessoa = request.getParameter("loginPessoa");
                String senhaPessoa = request.getParameter("senhaPessoa");
                String message = null;
                if (!loginPessoa.equals("") && !senhaPessoa.equals("")) {
                    PessoaDAOImpl dao = new PessoaDAOImpl();
                    Pessoa sessaoP = (Pessoa) dao.logarPessoa(loginPessoa, senhaPessoa);
                    if (sessaoP != null && (sessaoP.getTipoPessoa().equalsIgnoreCase("c"))) {
                        HttpSession sessao = request.getSession(true);
                        sessao.setAttribute("sessaoP", sessaoP);
                        request.setAttribute("message", message);
                        message = "Seja bem-vindo(a) "+sessaoP.getNomePessoa();
                        request.getRequestDispatcher("homeConsumidor.jsp").forward(request, response);
                    } else if (sessaoP != null && (sessaoP.getTipoPessoa().equalsIgnoreCase("a"))) {
                        HttpSession sessao = request.getSession(true);
                        sessao.setAttribute("sessaoP", sessaoP);
                        request.setAttribute("message", message);
                        message = "Seja bem-vindo(a) "+sessaoP.getNomePessoa();
                        request.getRequestDispatcher("homeAdvogado.jsp").forward(request, response);
                    } else {
                        message = "Login ou senha inválidos!";
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("index.html").forward(request, response);
                    }
                } else {
                    message = "É necessário digitar Login e Senha!";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println("Problemas ao logar! Erro: " + e.getMessage());
                e.printStackTrace();
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
