package com.demojpa1;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demojpa1.models.Categoria;
import com.demojpa1.repository.ICategoriaRepository;


             @SpringBootApplication
               // extend es herencia
              // implements es Abstraccion

public class DemojpaApplication implements CommandLineRunner {
	@Autowired
	private ICategoriaRepository repoCategoria;

	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);

	}


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//testConexion();
		guardar();
		//buscarPorId();
	}


	private void guardar() {
	//codigo guardar
		Categoria categoria = new Categoria();
		categoria.setNombre("Trips en la playa");
		categoria.setDescripcion("Todo tipo de paseos en la playa");
		repoCategoria.save(categoria);
}
	
	private void buscarPorId() {
		Optional<Categoria> optional = repoCategoria.findById(1);
		if (optional.isPresent()) {
			System.out.println(optional.get() .getNombre());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	

	private void testConexion() {
		System.out.println("probando conexion...");

		if (repoCategoria != null) {
			System.out.println("Conexion Exitosa : " + repoCategoria);
		} else {
			System.out.println("error de Conexion");
		}

	}
}