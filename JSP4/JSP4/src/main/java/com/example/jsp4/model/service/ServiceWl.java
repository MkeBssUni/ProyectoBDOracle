package com.example.jsp4.model.service;
import com.example.jsp4.model.wl.*;
import com.example.jsp4.model.utils.ResultAction;
import com.example.jsp4.model.wl.DaoWl;

import java.util.List;

public class ServiceWl {
    DaoWl daoWl = new DaoWl();

    public List<BeanWl> getAll() {
        return daoWl.findAll();
    }

    public ResultAction save(String titulo){
        ResultAction result = new ResultAction();
        if(daoWl.save(titulo)) {
            result.setResult(true);
            result.setMessage("Pelicula agregada correctamente");
            result.setStatus(200);
        } else {
            result.setResult(false);
            result.setMessage("Ocurrio un error al registrar");
            result.setStatus(400);
        }
        return result;
    }
}
