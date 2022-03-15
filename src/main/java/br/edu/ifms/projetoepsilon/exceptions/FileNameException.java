package br.edu.ifms.projetoepsilon.exceptions;

public class FileNameException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FileNameException(String message) {
		super(message);
	}

	public FileNameException(String message, Throwable cause) {
		super(message, cause);
	}

}
