package com.demojpa1.repository;



import org.springframework.data.repository.CrudRepository;

import com.demojpa1.models.Categoria;

public interface ICategoriaRepository extends CrudRepository<Categoria, Integer> {

}