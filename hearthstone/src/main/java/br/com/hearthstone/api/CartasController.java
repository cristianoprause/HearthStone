package br.com.hearthstone.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hearthstone.domain.Carta;
import br.com.hearthstone.domain.CartaService;
import br.com.hearthstone.domain.Classe;
import br.com.hearthstone.domain.TipoCarta;

@RestController
@RequestMapping("/api/v1/carta")
public class CartasController {

	@Autowired
	private CartaService service;
	
	@PostMapping
	public String post(@RequestBody Carta carta) {
		return service.insert(carta);
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable Integer id, @RequestBody Carta carta) {
		return service.update(id, carta);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		return service.delete(id);
	}
	
	@GetMapping("/{id}")
	public Optional<Carta> find(@PathVariable Integer id){
		return service.find(id);
	}
	
	@GetMapping("/nome/{nome}")
	public Optional<Carta> findByNome(@PathVariable String nome){
		return service.findByNome(nome);
	}
	
	@GetMapping("/classe/{classe}")
	public Optional<Carta> findByClasse(@PathVariable String classe){
		return service.findByClasse(Classe.findByDescricao(classe));
	}
	
	@GetMapping("/tipo/{tipo}")
	public Optional<Carta> findByTipo(@PathVariable String tipo){
		return service.findByTipo(TipoCarta.findByDescricao(tipo));
	}
	
	@GetMapping
	public List<Carta> findAll(){
		return service.findAll();
	}
}
