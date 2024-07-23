package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentaDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    int r;

    public int RegistrarVenta(Venta v) {
        
        String sql = "INSERT INTO ventas (cliente, vendedor, total) VALUES (?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2,v.getVendedor());
            ps.setDouble(3, v.getTotal());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;       
    }
    
    public int RegistrarDetalle(Detalle Dv) {
        String sql = "INSERT INTO detalle (cod_prod, cantidad, precio, id_venta) VALUES (?,?,?,?)";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Dv.getCod_prod());
            ps.setInt(2, Dv.getCantidad());
            ps.setDouble(3, Dv.getPrecio());
            ps.setInt(4, Dv.getId());
            ps.execute();
        }catch(SQLException e){
            System.out.println(e.toString());
        }finally{
            try{
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
        
    }
    
}
