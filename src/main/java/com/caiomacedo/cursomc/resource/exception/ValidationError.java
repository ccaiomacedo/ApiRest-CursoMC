package com.caiomacedo.cursomc.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID=1l;

    // lista pra armazenar os erros, já que são mais de um
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    //método pra adicionar erros na lista
    public void addError(String fieldName,String message){
        errors.add(new FieldMessage(fieldName,message));
    }
}
