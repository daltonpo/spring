package br.com.dpo.estudos.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dpo.estudos.model.Cliente;
import br.com.dpo.estudos.repository.ClienteRepository;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

	private ClienteRepository repository;
	
	ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Cliente> findAll(){
	   return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cliente> findById(@PathVariable long id){
	   return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente){
	   return repository.save(cliente);
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<Object> delete(@PathVariable long id){
		return repository.findById(id)
		           .map(record -> {
		               repository.deleteById(id);
		               return ResponseEntity.ok().build();
		           }).orElse(ResponseEntity.notFound().build());
	}
}
