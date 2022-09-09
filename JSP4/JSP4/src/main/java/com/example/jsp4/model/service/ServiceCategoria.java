package com.example.jsp4.model.service;

import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.categoria.DaoCategoria;
import com.example.jsp4.model.utils.ResultAction;

import java.util.List;

public class ServiceCategoria {
    DaoCategoria daoCategoria = new DaoCategoria();

    public List<BeanCategoria> getAll() {
        return daoCategoria.findAll();
    }

    public ResultAction save(BeanCategoria categoria){
        ResultAction result = new ResultAction();
        if(daoCategoria.save(categoria)) {
            result.setResult(true);
            result.setMessage("categoria registrada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(false);
            result.setMessage("Ocurrio un error al registrar");
            result.setStatus(400);
        }
        return result;
    }
     
    public BeanCategoria categoria(int id){
        return daoCategoria.findOne(id);
    }
    
        public ResultAction update(BeanCategoria categoria){
        ResultAction result = new ResultAction();
        if(daoCategoria.update(categoria)) {
            result.setResult(false);
            result.setMessage("categoria actualizada correctamente");
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
        if(daoCategoria.delete(identificador)) {
            result.setResult(false);
            result.setMessage("categoria eliminada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(true);
            result.setMessage("Ocurrio un error al eliminar");
            result.setStatus(400);
        }
        return result;
    }
}

