package Modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ClienteDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistarCliente(Cliente cl){
    
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, email) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getEmail());
            ps.execute();
            return true;
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }finally {
            try{
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List ListarCliente(){
    
        List<Cliente> ListaCl =new ArrayList();
        String sql = "SELECT * FROM clientes";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setDni(rs.getString("dni"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEmail(rs.getString("email"));
                ListaCl.add(cl);
            }
        }catch (SQLException e){
            System.out.println(e.toString());
        }
        return ListaCl;
    } 
    
    public boolean EliminarCliente(int id) {
        
        String sql = "DELETE FROM clientes WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());
            return false;
        }finally{
            try {
                con.close();
            }catch(SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        
    }
    
    public boolean ModificarCliente(Cliente cl) {
        
        String sql = "UPDATE clientes SET dni=?, nombre=?, telefono=?, direccion=?, email=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getEmail());
            ps.setInt(6, cl.getId());
            ps.execute();
            return true;
        }catch(SQLException e){
            System.out.println(e.toString());   
            return false;
        }finally{
            try {
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
            }
        }            
    }
    
    public Cliente Buscarcliente(String dni){
        
        Cliente cl = new Cliente();
        String sql = "SELECT * FROM clientes WHERE dni = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if(rs.next()){
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setEmail(rs.getString("email"));
            }
        }catch(SQLException e){
            System.out.println(e.toString());
        }
        return cl;
    }
    
}
