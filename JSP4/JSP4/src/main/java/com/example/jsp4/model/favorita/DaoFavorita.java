package com.example.jsp4.model.favorita;
import com.example.jsp4.model.utils.Conexion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoFavorita {
    Connection con;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;

    public List<BeanFavorita> findAll() {
        List<BeanFavorita> peliculas = new LinkedList<>();
        BeanFavorita peliculaFav = null;
        try {
            con = new Conexion().getConnection();
            String query =  "select * from FAVORITAS"; 
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                peliculaFav = new BeanFavorita();
                peliculaFav.setId(rs.getLong(1));
                peliculaFav.setTitulo(rs.getString(2));
                peliculaFav.setCalificacion(rs.getDouble(3));
                peliculas.add(peliculaFav);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoFavorita.class.getName())
                    .log(Level.SEVERE,"Error findAll", e);
        } finally {
            closeConnections();
        }
        return peliculas;
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
