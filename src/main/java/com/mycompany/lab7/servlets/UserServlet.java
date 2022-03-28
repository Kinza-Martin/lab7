
package com.mycompany.lab7.servlets;

import com.mycompany.lab7.models.Role;
import com.mycompany.lab7.models.User;
import com.mycompany.lab7.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserServlet extends HttpServlet {

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
        
        UserService service = new UserService();
        try {
            List<User> users = service.getAll();
            
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
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
        
        
        String action = request.getParameter("action");
        UserService service = new UserService();
        
        //add user
        if(action != null && action.equals("addUser")){
            
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("role"));
            
            Role role = new Role();
            role.setId(roleID);

            try {
               service.insert(email, true, firstName, lastName, password, role);
               request.setAttribute("message","User added"); 
            } catch (Exception ex) {
               request.setAttribute("message","User not added:Error");
            }
            
          //edit user  
        } else if (action != null && action.equals("UserEdit")) {
            
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            int roleID = Integer.parseInt(request.getParameter("role"));
            
            Role role = new Role();
            role.setId(roleID);
            
            try {
                service.update(email, true, firstName, lastName, password, role);
                request.setAttribute("message","User email: " + email + " successfully updated");
            } catch (Exception ex) {
                request.setAttribute("message","User not edited:Error");
            }
        }
        
        String userToDelete = request.getParameter("delete"); 
        
        //delete user
        if(userToDelete != null){
            try {
                service.delete(userToDelete);
                request.setAttribute("message","User deleted");
            } catch (Exception ex) {
                request.setAttribute("message","User not deleted:Error");
            }
             
        }
        
        //refresh list 
        try {
            List<User> users = service.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    
    }
}
