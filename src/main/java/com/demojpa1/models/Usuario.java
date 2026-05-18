package com.demojpa1.models;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String username;
    private String password;
    private String estatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_Perfil",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfiles;

    public void agregarPerfil1(Perfil tempPerfil) {
        if (perfiles == null)
            perfiles = new ArrayList<Perfil>();
        perfiles.add(tempPerfil);
    }

    public Integer getId() {
        return id;
        
        }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;  
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }


    public void agregarPerfil(Perfil tempPerfil) {
        if (perfiles == null)
            perfiles = new ArrayList<Perfil>();
        perfiles.add(tempPerfil);
    }

    public String getNombre() {
        return nombre;
    }
}