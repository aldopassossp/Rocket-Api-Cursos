package com.cursos.cursosapi.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super("Usuário já existe!");
    }
}