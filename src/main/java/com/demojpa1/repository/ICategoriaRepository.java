package com.demojpa1.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.demojpa1.models.Categoria;

public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {

}