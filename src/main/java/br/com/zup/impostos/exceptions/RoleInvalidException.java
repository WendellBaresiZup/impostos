package br.com.zup.impostos.exceptions;

public class RoleInvalidException extends RuntimeException{
    public RoleInvalidException(String message){
        super(message);
    }
}
