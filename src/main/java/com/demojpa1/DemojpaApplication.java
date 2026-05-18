package com.demojpa1;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.demojpa1.models.Categoria;
import com.demojpa1.models.Perfil;
import com.demojpa1.models.Trip;
import com.demojpa1.models.Usuario;
import com.demojpa1.repository.ICategoriaRepository;
import com.demojpa1.repository.IPerfilRepository;
import com.demojpa1.repository.ITripRepository;
import com.demojpa1.repository.IUsuarioRepository;


@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {

    @Autowired
    private ITripRepository repoTrip;

    @Autowired
    private ICategoriaRepository repoCategoria1;
    
    @Autowired
    private IPerfilRepository repoPerfil;

    @Autowired
    private IUsuarioRepository repoUsuario;

	public static void main(String[] args) {
		SpringApplication.run(DemojpaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//testConexion();
		//guardar();
		//buscarPorId();
		//modificar();
		//eliminarPorId();
		//cantidadCategorias();
		//eliminarTodo();
		//encontrarPorIds();
		//buscarTodos();
		//existeId();
		//guardarTodas();
		
		
		//Metodos con JPAPepository
		
		//buscarTodosJpa();
		//borrarEnBatch();
		//buscarTodosOrdenados();
		//buscarTodoEnPaginacion();
		
		
		//guardarTrip();
        //crearPerfiles();
		crearUsuarioConDosPerfiles();
		//getUsuario();
		//buscarTripPorEstatus();
		//buscarTripPorDestacadoEstatusOrdenadosDescId();
		//buscarTripEntreCosto();
		//buscarTripEstosEstatus();
		
	 }

    private void buscarTripEstosEstatus() {
        String[] estatus = new String[] {"Aprobada", "Reprobada"};
        List<Trip> lista = repoTrip.findByEstatusIn(estatus);
        for (Trip t : lista)
            System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus());
	  }

    private void buscarTripEntreCosto() {
        List<Trip> lista = repoTrip.findByCostoBetween(10, 20);
        for (Trip t : lista)
            System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getCosto());
		
	 }

    private void buscarTripPorDestacadoEstatusOrdenadosDescId() {
        List<Trip> lista = repoTrip.findByDestacadoAndEstatusOrderByIdDesc(0, "Aprobada");
        for (Trip t : lista)
            System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus()
                    + " Destacado: " + t.getDestacado());
		
	}

    private void buscarTripPorEstatus() {
        List<Trip> lista = repoTrip.findByEstatus("Aprobada");
        for (Trip t : lista)
            System.out.println(t.getId() + ": " + t.getNombre() + " Estatus: " + t.getEstatus());
    }
		
    private void getUsuario() {
        Optional<Usuario> usuario = repoUsuario.findById(1);
        if (usuario.isPresent()) {
            Usuario usu = usuario.get();
            System.out.println("Usuario: " + usu.getNombre());
            System.out.println("Perfiles del Usuario:");
            for (Perfil p : usu.getPerfiles()) {
                System.out.println(p.getNombre());
            }
        } else {
            System.out.println("Usuario sin perfiles");
        }
    }

    private void crearUsuarioConDosPerfiles() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Cesar Sanchez");
        usuario.setEmail("correo@correo.com");
        usuario.setUsername("csanchez");  // ← con e
        usuario.setPassword("123");
        usuario.setEstatus("Activo");

        Perfil perfil1 = new Perfil();
        perfil1.setId(10);

        Perfil perfil2 = new Perfil();
        perfil2.setId(11);

        usuario.agregarPerfil(perfil1);
        usuario.agregarPerfil(perfil2);

        repoUsuario.save(usuario);
    }

    private void crearPerfiles() {

        repoPerfil.saveAll(getListaPerfiles());

    }
    private List<Perfil> getListaPerfiles() {
        List<Perfil> lista = new LinkedList<Perfil>();
        Perfil perfil1 = new Perfil();
        perfil1.setNombre("SuperAdministrador");
        Perfil perfil2 = new Perfil();
        perfil2.setNombre("Admin");
        Perfil perfil3 = new Perfil();
        perfil3.setNombre("visitante");

        lista.add(perfil1);
        lista.add(perfil2);
        lista.add(perfil3);

        return lista;
    }
		
		// Claser Relaciones
        //buscadrTrips();
        //guardarTrip();
	
	private void guardarTrip() {
	    Trip trip = new Trip();
	    trip.setNombre("Caminatas en la playa");
	    trip.setDescripcion("\"Bonitas caminatas en la playa San Marcelino");
	    trip.setFecha(new Date());
	    trip.setCosto(15.0);
	    trip.setEstatus("Aprobada");
	    trip.setDestacado(0);
	    trip.setImagen("trip1.png");
	    trip.setDescripcion("Esta es una descripcion larga!!!");
	    trip.setDetalles("Detalles del trip");
	    Categoria categoria = new Categoria();
	    categoria.setId(1);
	    trip.setCategoria(categoria);

	    repoTrip.save(trip);
	    
	}
         //metodo buscadrTrips
    private void buscadrTrips() {
        List<Trip> lista = repoTrip.findAll();
        for (Trip trip : lista)
            System.out.println(trip.getId() + " " + trip.getNombre());
    }
    
    
		
	//metodo guardar
	private void guardar() {
		Categoria categoria = new Categoria();
		categoria.setNombre("Trips en la playa");
		categoria.setDescripcion("Todo tipo de paseos en la playa");
		repoCategoria1.save(categoria);
	}
	
	//metodo buscar por id
	private void buscarPorId() {
		Optional<Categoria> optional = repoCategoria1.findById(1);
		if (optional.isPresent()) {
			System.out.println(optional.get() .getNombre());
		} else {
			System.out.println("Categoria no encontrada");
		}
	}
	
	//metodo midificar
	private void modificar() {
	    Optional<Categoria> optional = repoCategoria1.findById(1);
	    if (optional.isPresent()) {
	        Categoria catTemp = new Categoria();
	        catTemp = optional.get();
	        catTemp.setNombre("Caminatas en el Volcan");
	        catTemp.setDescripcion("Exigentes caminatas para....");
	        repoCategoria1.save(catTemp);
	        System.out.println(optional.get());
	    } else
	        System.out.println("Categoria no econtrada");

	}
	
	//metodo eliminar por id
	private void eliminarPorId() {
		repoCategoria1.deleteById(1);
	}
	
	//metodo cantidad categorias
	private void cantidadCategorias() {
		long cantidad = repoCategoria1.count();
		System.out.println("Cantidad: " + cantidad);
	}
	
	//metodo eliminar todo
	private void eliminarTodo() {
		repoCategoria1.deleteAll();
	}
	
	//metodo encontrar por ids
	private void encontrarPorIds() {
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(3);
		ids.add(6);
		Iterable<Categoria> categoria = repoCategoria1.findAllById(ids);
		for (Categoria cat : categoria)
			System.out.println(cat.getNombre() + " " + cat.getDescripcion());
	}
	
	//metodo buscar todos
	private void buscarTodos() {
		Iterable<Categoria> categoria = repoCategoria1.findAll();
		for (Categoria cat : categoria)
			System.out.println(cat.getNombre() + " " + cat.getDescripcion());
	}
	
	//metodo existe (verificar si existe una categoria)
	private void existeId() {
		boolean existe = repoCategoria1.existsById(4);
		System.out.println("La categoria existe: " + existe);
		
		
	}
	
	//metodo que crea y devuelve la lista de categorías
	private List<Categoria> getCategoria() {
			
		List<Categoria> lista = new LinkedList<Categoria>();
			
		Categoria cat1 = new Categoria();
		cat1.setNombre("Trips en la playa");
		cat1.setDescripcion("Paseos en la playa...");
			
		Categoria cat2 = new Categoria();
		cat2.setNombre("Trips en la Ciudad");
		cat2.setDescripcion("Paseos en la Ciudad...");
			
		lista.add(cat1);
		lista.add(cat2);
			
		return lista;
	}
		
	//metodo que obtiene la lista y la manda a guardar a la base de datos
	private void guardarTodas() {
		List<Categoria> lista = getCategoria();
		repoCategoria1.saveAll(lista);
	}
	
	
	
	
	
	//Metodos con JPARepository
	
	
	//metodo para buscar todos y retornar una List directamente
	private void buscarTodosJpa() {
	    List<Categoria> lista = repoCategoria1.findAll();
	    for (Categoria cat : lista) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	//metodo borrar en batch
	private void borrarEnBatch() {
		repoCategoria1.deleteAllInBatch();
	}
	
	//metodo buscar todos ordenados
	private void buscarTodosOrdenados() {
	    List<Categoria> lista = repoCategoria1.findAll(Sort.by("nombre"));
	    for (Categoria cat : lista) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	//metodo buscar todo en paginacion
	private void buscarTodoEnPaginacion() {
	    Page<Categoria> page = repoCategoria1.findAll(PageRequest.of(0, 5));
	    System.out.println("Total Categorias: " + page.getTotalElements());
	    System.out.println("Total Paginas: " + page.getTotalPages());
	    for (Categoria cat : page) {
	        System.out.println(cat.getId() + " " + cat.getNombre());
	    }
	}
	
	

	
	
	
	private void testConexion() {
		System.out.println("probando conexion...");

		if (repoCategoria1 != null) {
			System.out.println("Conexion Exitosa : " + repoCategoria1);
		} else {
			System.out.println("error de Conexion");
		}

	}
}

