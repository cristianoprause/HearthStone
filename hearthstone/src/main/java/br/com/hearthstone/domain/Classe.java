package br.com.hearthstone.domain;

import java.util.Arrays;
import java.util.Optional;

public enum Classe {

	MAGO("Mago"),
	PALADINO("Paladino"),
	CACADOR("Caçador"),
	DRUIDA("Druida"),
	QUALQUER("Qualquer");
	
	private String descricao;
	
	private Classe(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static Classe findByDescricao(String descricao) {
		Optional<Classe> classeEncontrada = Arrays.
											asList(values()).
											stream().
											filter(classe -> classe.getDescricao().toLowerCase().matches(descricao.toLowerCase()) ||
															 classe.toString().toLowerCase().matches(descricao.toLowerCase())).
											findFirst();
		
		if(!classeEncontrada.isPresent())
			throw new RuntimeException("Classe não encontrada");
		
		return classeEncontrada.get();
	}
}
