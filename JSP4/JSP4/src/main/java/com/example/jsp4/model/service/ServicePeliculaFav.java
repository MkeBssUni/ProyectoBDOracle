package com.example.jsp4.model.service;
import com.example.jsp4.model.favorita.*;
import com.example.jsp4.model.categoria.BeanCategoria;
import com.example.jsp4.model.favorita.DaoFavorita;
import com.example.jsp4.model.utils.ResultAction;

import java.util.List;

public class ServicePeliculaFav {
    DaoFavorita daoFavorita = new DaoFavorita();

    public List<BeanFavorita> getAll() {
        return daoFavorita.findAll();
    }

}
