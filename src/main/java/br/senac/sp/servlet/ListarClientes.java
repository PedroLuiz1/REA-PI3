/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.servlet;

import br.senac.sp.dao.DAO;
import br.senac.sp.entidade.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListarClientes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      List<Cliente> clientes = DAO.getClientes();
      
      //Aqui  o sistema captura a requisição coloca dentro dela alista de clientes e manda para outra página.
      request.setAttribute("listaClientes",clientes);
      
      RequestDispatcher requestDispatcher = 
              getServletContext().getRequestDispatcher("/listarClientes.jsp");
      requestDispatcher.forward(request, response);
    }
}
