package servlet;

import dao.UsuarioDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;

@WebServlet(name="CadastroServlet", urlPatterns={"/cadastro"})
public class CadastroServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Usuario u = new Usuario();
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String senhaRepetida = request.getParameter("senhaRepetida");
        u.setEmail(email);
        u.setSenha(senha);
        
        if(UsuarioDAO.checarUsuarioPorEmail(email).equals(null)){
            UsuarioDAO.inserirUsuario(u);
            
        }else{
            request.setAttribute("erro","E-mail já cadastrado, tente com outro");
            
            RequestDispatcher rd = request.getRequestDispatcher("cadastro.jsp");
            rd.forward(request, response);
        }
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
