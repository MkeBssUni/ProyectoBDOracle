package com.example.jsp4.model.pelicula;
import com.example.jsp4.model.utils.Conexion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPelicula {
    Connection con;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;

    public List<BeanPelicula> findAll() {
        List<BeanPelicula> peliculas = new LinkedList<>();
        BeanPelicula pelicula = null;
        try {
            con = new Conexion().getConnection();
            String query =  "select * from CONSULTA_PELICULAS"; /* VISTA */
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                pelicula = new BeanPelicula();
                pelicula.setId(rs.getLong(1));
                pelicula.setTitulo(rs.getString(2));
                pelicula.setDirector(rs.getString(3));
                pelicula.setProductor(rs.getString(4));
                pelicula.setEscritor(rs.getString(5));
                pelicula.setEstreno(rs.getInt(6));
                pelicula.setCalificacion(rs.getDouble(7));
                pelicula.setId_categoria(rs.getInt(8));
                pelicula.setCategoria(rs.getString(9));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoPelicula.class.getName())
                    .log(Level.SEVERE,"Error findAll", e);
        } finally {
            closeConnections();
        }
        return peliculas;
    }

    public boolean save(BeanPelicula pelicula){
        try {
            con = new Conexion().getConnection();
            String query = "insert into peliculas " +
                    "(titulo, director, productor, escritor, estreno, calificacion, id_categoria) " +
                    "values (?,?,?,?,?,?,?)";
            pstm = con.prepareStatement(query);
            pstm.setString(1, pelicula.getTitulo());
            pstm.setString(2, pelicula.getDirector());
            pstm.setString(3, pelicula.getProductor());
            pstm.setString(4, pelicula.getEscritor());
            pstm.setInt( 5, pelicula.getEstreno());
            pstm.setDouble(6, pelicula.getCalificacion());
            pstm.setInt(7, pelicula.getId_categoria());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoPelicula.class.getName())
                    .log(Level.SEVERE,"Error save", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    public BeanPelicula findOne(Long id) {
        try {
            con = new Conexion().getConnection();
            String query = "select * from peliculas where id = ?";
            pstm = con.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                BeanPelicula pelicula = new BeanPelicula();
                pelicula.setId(rs.getLong("id"));
                pelicula.setTitulo(rs.getString("titulo"));
                pelicula.setDirector(rs.getString("director"));
                pelicula.setProductor(rs.getString("productor"));
                pelicula.setEscritor(rs.getString("escritor"));
                pelicula.setEstreno(rs.getInt("estreno"));
                pelicula.setCalificacion(rs.getDouble("calificacion"));
                pelicula.setId_categoria(rs.getInt("id_categoria"));

                return pelicula;
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoPelicula.class.getName())
                    .log(Level.SEVERE,"Error findOne", e);
        } finally {
            closeConnections();
        }
        return null;
    }

    public boolean update(BeanPelicula pelicula) {
        try {
            con = new Conexion().getConnection();
            String query = "update peliculas " +
                    "set titulo = ?, director = ?, productor = ?, escritor = ?, estreno = ?, calificacion = ?, id_categoria=? " +
                    "where id = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, pelicula.getTitulo());
            pstm.setString(2, pelicula.getDirector());
            pstm.setString(3, pelicula.getProductor());
            pstm.setString(4, pelicula.getEscritor());
            pstm.setInt(5, pelicula.getEstreno());
            pstm.setDouble(6, pelicula.getCalificacion());
            pstm.setInt(7, pelicula.getId_categoria());
            pstm.setLong(8, pelicula.getId());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoPelicula.class.getName())
                    .log(Level.SEVERE,"Error update", e);
            return false;
        } finally {
            closeConnections();
        }
    }
    public boolean delete(int id) {
        try {
            con = new Conexion().getConnection();
            String query = "delete from peliculas where id = " + id;
            pstm = con.prepareStatement(query);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoPelicula.class.getName())
                    .log(Level.SEVERE, "Error update", e);
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
