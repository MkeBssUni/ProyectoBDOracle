package com.example.jsp4.model.service;
import com.example.jsp4.model.pelicula.DaoPelicula;
import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.pelicula.BeanPelicula;
import com.example.jsp4.model.utils.ResultAction;

import java.util.List;

public class ServicePelicula {
    DaoPelicula daoPelicula = new DaoPelicula();

    public List<BeanPelicula> getAll() {
        return daoPelicula.findAll();
    }

    public ResultAction save(BeanPelicula pelicula){
        ResultAction result = new ResultAction();
        if(daoPelicula.save(pelicula)) {
            result.setResult(true);
            result.setMessage("Pelicula registrada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(false);
            result.setMessage("Ocurrio un error al registrar");
            result.setStatus(400);
        }
        return result;
    }

    public BeanPelicula getPelicula(Long id){
        return daoPelicula.findOne(id);
    }

        public ResultAction update(BeanPelicula pelicula){
        ResultAction result = new ResultAction();
        if(daoPelicula.update(pelicula)) {
            result.setResult(false);
            result.setMessage("Pelicula actualizada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(true);
            result.setMessage("Ocurrio un error al actualizar");
            result.setStatus(400);
        }
        return result;
    }

    public ResultAction delete(String id){
        int identificador = Integer.parseInt(id);
        ResultAction result = new ResultAction();
        if(daoPelicula.delete(identificador)) {
            result.setResult(false);
            result.setMessage("Pelicula eliminada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(true);
            result.setMessage("Ocurrio un error al eliminar");
            result.setStatus(400);
        }
        return result;
    }
}
