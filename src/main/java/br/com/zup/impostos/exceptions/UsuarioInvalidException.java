package br.com.zup.impostos.exceptions;

public class UsuarioInvalidException extends RuntimeException{
    public UsuarioInvalidException(String message){
        super(message);
    }
}
