package com.devsuperior.bds04.controllers.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    //não precisa criar construtor sem argumentos pq já tem na lista com o FieldMessage
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) { //passa nome do campo(fieldName) e a mensagem(message)
        errors.add(new FieldMessage(fieldName, message));
    }
}