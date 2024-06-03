package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    
    public boolean RegistarCliente(Cliente cl){
    
        String sql = "INSERT INTO clientes (dni, nombre, telefono, direccion, razon) VALUES (?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
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

}
