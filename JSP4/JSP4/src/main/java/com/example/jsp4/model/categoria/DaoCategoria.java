package com.example.jsp4.model.categoria;

import com.example.jsp4.model.utils.Conexion;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.taglibs.standard.lang.jstl.BeanInfoManager;

public class DaoCategoria {
    Connection con;
    PreparedStatement pstm;
    CallableStatement cstm;
    ResultSet rs;

    public List<BeanCategoria> findAll() {
        List<BeanCategoria> categorias = new LinkedList<>();
        BeanCategoria categoria = null;
        try {
            con = new Conexion().getConnection();
            String query =  "select * from categorias";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                categoria = new BeanCategoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName())
                    .log(Level.SEVERE,"Error findAll", e);
        } finally {
            closeConnections();
        }
        return categorias;
    }

    public BeanCategoria findOne(int id) {
        try {
            con = new Conexion().getConnection();
            String query = "select * from categorias where id = ?";
            pstm = con.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                BeanCategoria categoria = new BeanCategoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                return categoria;
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName())
                    .log(Level.SEVERE,"Error findOne", e);
        } finally {
            closeConnections();
        }
        return null;
    }

    public boolean save(BeanCategoria categoria) {
        try {
            con = new Conexion().getConnection();
            String query = "insert into CATEGORIAS (nombre) values (?)";
            pstm = con.prepareStatement(query);
            pstm.setString(1, categoria.getNombre());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName())
                    .log(Level.SEVERE,"Error save", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    public boolean update(BeanCategoria categoria) {
        try {
            con = new Conexion().getConnection();
            String query = "update categorias set nombre = ? where id = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, categoria.getNombre());
            pstm.setInt(2, categoria.getId());
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName())
                    .log(Level.SEVERE,"Error update", e);
            return false;
        } finally {
            closeConnections();
        }
    }

    public boolean delete(int id) {
        try {
            con = new Conexion().getConnection();
            String query = "delete from categorias where id = " + id;
            pstm = con.prepareStatement(query);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCategoria.class.getName())
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

