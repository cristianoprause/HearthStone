package br.com.hearthstone.domain.validation;

import br.com.hearthstone.domain.Carta;

@FunctionalInterface
public interface ValidationListener {

	boolean isInvalid(Carta carta);
	
}
