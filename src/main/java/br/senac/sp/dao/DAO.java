/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.sp.dao;

import br.senac.sp.bd.ConexaoDB;
import br.senac.sp.entidade.Cliente;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.senac.sp.servlet.ServletDB;

public class DAO {

    public static List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList();
        try {
            Connection con = ConexaoDB.getConexao();
            String query = "select * from cliente";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                long cpf = rs.getLong("cpf");
                String data = rs.getString("data");

                listaClientes.add(new Cliente(nome, email, cpf, data));
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServletDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServletDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaClientes;

    }

    public static void addCliente(Cliente cliente) throws SQLException, ClassNotFoundException {

        Connection con = ConexaoDB.getConexao();
        String query = "insert into cliente(nome,email,cpf,data) values (?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEmail());
        ps.setLong(3, cliente.getCpf());
        ps.setString(4, cliente.getData());
        ps.execute();

    }

    //QUERY PARA ALTERAR OS DADOS DO CLIENTE
    /*public static void updateCliente(Cliente cliente) throws ClassNotFoundException, SQLException {

        Connection con = ConexaoDB.getConexao();
        String query = "update cliente set nome=?,email=? where cpf=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, cliente.getNome());
        ps.setString(2, cliente.getEmail());
        ps.setLong(3, cliente.getCpf());
        ps.setString(4, cliente.getData());

        ps.execute();

    }*/

    public static Cliente getCliente(Long cpf) {
        Cliente cliente = null;
        try {
            Connection con = ConexaoDB.getConexao();
            String query = "select * from cliente where cpf=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String data = rs.getString("data");
                cliente = new Cliente(nome, email, cpf, data);

            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ServletDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cliente;

    }
}
