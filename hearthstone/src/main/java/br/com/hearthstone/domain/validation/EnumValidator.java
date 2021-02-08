package br.com.hearthstone.domain.validation;

import br.com.hearthstone.domain.Carta;

public enum EnumValidator {

	NOME_OBRIGATORIO("Informe o nome", carta -> carta.getNome() == null || carta.getNome().isEmpty()),
	DESCRICAO_OBRIGATORIA("Informe a descrição", carta -> carta.getDescricao() == null || carta.getDescricao().isEmpty()),
	ATAQUE_OBRIGATORIO("Informe o valor do ataque", carta -> carta.getAtaque() == null),
	DEFESA_OBRIGATORIA("Informe o valor da defesa", carta -> carta.getDefesa() == null),
	TIPO_OBRIGATORIO("Informe o tipo", carta -> carta.getTipo() == null),
	CLASSE_OBRIGATORIA("Informe a Classe", carta -> carta.getClasse() == null),
	ATAQUE_MAIOR_IGUAL_ZERO("O valor do ataque deve ser maior ou igual a zero", carta -> carta.getAtaque().compareTo(0) < 0),
	DEFESA_MAIOR_IGUAL_ZERO("O valor da defesa deve ser maior ou igual a zero", carta -> carta.getDefesa().compareTo(0) < 0),
	ATAQUE_MENOR_IGUAL_DEZ("O valor do ataque deve ser menor ou igual a dez", carta -> carta.getAtaque().compareTo(10) > 0),
	DEFESA_MENOR_IGUAL_DEZ("O valor da defesa deve ser menor ou igual a dez", carta -> carta.getDefesa().compareTo(10) > 0);
	
	private String message;
	private ValidationListener listener;
	
	private EnumValidator(String message, ValidationListener listener) {
		this.message = message;
		this.listener = listener;
	}
	
	private boolean isInvalid(Carta carta) {
		return listener.isInvalid(carta);
	}
	
	public static void validate(Carta carta) throws ValidationException{
		for(EnumValidator validacao : values()) {
			if(validacao.isInvalid(carta))
				throw new ValidationException(validacao.message);
		}
	}
}
