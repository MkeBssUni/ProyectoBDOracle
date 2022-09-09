package com.example.jsp4.model.wl;

import com.example.jsp4.model.utils.Conexion;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoWl {
    Connection con;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;

    public List<BeanWl> findAll() {
        List<BeanWl> peliculas = new LinkedList<>();
        BeanWl pwl = null;
        try {
            con = new Conexion().getConnection();
            String query =  "select * from WATCHLIST"; 
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pwl = new BeanWl();
                pwl.setId(rs.getLong(1));
                pwl.setTitulo(rs.getString(2));
                peliculas.add(pwl);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoWl.class.getName())
                    .log(Level.SEVERE,"Error findAll", e);
        } finally {
            closeConnections();
        }
        return peliculas;
    }

    public boolean save(String titulo) {
        try {
            con = new Conexion().getConnection();
            CallableStatement cstm = con.prepareCall("{call PAWATCHLIST(?)}");
            cstm.setString(1, titulo);
            cstm.execute();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(DaoWl.class.getName())
                    .log(Level.SEVERE,"Error save", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    
    public void closeConnections() {
        try {
            if (con != null) {
                con.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cstm != null) {
                cstm.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {

        }
    }
}
