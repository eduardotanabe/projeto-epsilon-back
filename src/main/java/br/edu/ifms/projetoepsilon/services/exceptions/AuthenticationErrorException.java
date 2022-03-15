package br.edu.ifms.projetoepsilon.services.exceptions;

public class AuthenticationErrorException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationErrorException(String msg) {
		super(msg);
	}
	
}
