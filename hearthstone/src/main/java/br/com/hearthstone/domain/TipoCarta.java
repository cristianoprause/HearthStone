package br.com.hearthstone.domain;

import java.util.Arrays;
import java.util.Optional;

public enum TipoCarta {

	MAGIA("Magia"),
	CRIATURA("Criatura");
	
	private String descricao;
	
	private TipoCarta(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCarta findByDescricao(String descricao) {
		Optional<TipoCarta> tipoEncontrado = Arrays.
												asList(values()).
												stream().
												filter(tipo -> tipo.getDescricao().toLowerCase().matches(descricao.toLowerCase()) ||
															   tipo.toString().toLowerCase().matches(descricao.toLowerCase())).
												findFirst();
		
		if(!tipoEncontrado.isPresent())
			throw new RuntimeException("Tipo não encontrada");
		
		return tipoEncontrado.get();
	}
}
