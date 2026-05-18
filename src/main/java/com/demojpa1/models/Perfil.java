package com.demojpa1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfil")
public class Perfil {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        
    }

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public char[] getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void add(Perfil tempPerfil) {
		// TODO Auto-generated method stub
		
	}

	
		
	}
    
    
    
    
	 
	
	


