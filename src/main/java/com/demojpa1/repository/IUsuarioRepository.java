package com.demojpa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demojpa1.models.Usuario;


public interface IUsuarioRepository extends JpaRepository<Usuario, Integer>{

}
