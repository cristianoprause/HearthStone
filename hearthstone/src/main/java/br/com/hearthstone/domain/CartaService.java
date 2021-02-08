package br.com.hearthstone.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.hearthstone.domain.validation.EnumValidator;
import br.com.hearthstone.domain.validation.ValidationException;

@Service
public class CartaService {

private Map<Integer, Carta> mapCarta = new HashMap<>();
	
	public String insert(Carta carta) {
		try {
			EnumValidator.validate(carta);
			carta.setId(getNextId());
			mapCarta.put(carta.getId(), carta);
			return String.format("Carta registrada com sucesso! [id:%s]", carta.getId());
		}catch(ValidationException e) {
			return e.getMessage();
		}
	}
	
	public String update(Integer id, Carta carta) {
		try {
			EnumValidator.validate(carta);
			Optional<Carta> cartaEncontrada = find(id);
			if(cartaEncontrada.isPresent()){
				cartaEncontrada.get().setNome(carta.getNome());
				cartaEncontrada.get().setDescricao(carta.getDescricao());
				cartaEncontrada.get().setAtaque(carta.getAtaque());
				cartaEncontrada.get().setDefesa(carta.getDefesa());
				cartaEncontrada.get().setTipo(carta.getTipo());
				cartaEncontrada.get().setClasse(carta.getClasse());
				return "Carta atualizada com sucesso!";
			}

			throw new ValidationException("Não foi pissível localizar a carta");
		}catch(ValidationException | RuntimeException e) {
			return e.getLocalizedMessage();
		}
		
	}
	
	public String delete(Integer id) {
		try {
			mapCarta.remove(find(id).get().getId());
			return "Carta excluída com sucesso!";
		}catch(RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public Optional<Carta> find(Integer id){
		Carta carta = mapCarta.get(id);
		if(carta == null)
			throw new RuntimeException("Não foi possível localizar a carta");
		
		return Optional.of(carta);
	}
	
	public Optional<Carta> findByNome(String nome){
		Optional<Carta> cartaEncontrada = mapCarta.values().stream().filter(carta -> carta.getNome().toLowerCase().matches(nome.toLowerCase())).findFirst();
		if(!cartaEncontrada.isPresent())
			throw new RuntimeException("Não foi possível localizar a carta");
		
		return cartaEncontrada;
	}
	
	public Optional<Carta> findByClasse(Classe classe){
		Optional<Carta> cartaEncontrada = mapCarta.values().stream().filter(carta -> carta.getClasse().equals(classe)).findFirst();
		if(!cartaEncontrada.isPresent())
			throw new RuntimeException("Não foi possível localizar a carta");
		
		return cartaEncontrada;
	}
	
	public Optional<Carta> findByTipo(TipoCarta tipo){
		Optional<Carta> cartaEncontrada = mapCarta.values().stream().filter(carta -> carta.getTipo().equals(tipo)).findFirst();
		if(!cartaEncontrada.isPresent())
			throw new RuntimeException("Não foi possível localizar a carta");
		
		return cartaEncontrada;
	}
	
	public List<Carta> findAll(){
		return new ArrayList<>(mapCarta.values());
	}
	
	private Integer getNextId() {
		return mapCarta.keySet().stream().max(Comparator.comparing(Integer::valueOf)).orElse(0) + 1;
	}
}
