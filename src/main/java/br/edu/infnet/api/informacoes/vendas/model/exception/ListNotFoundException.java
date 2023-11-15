package br.edu.infnet.api.informacoes.vendas.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ListNotFoundException extends IAuthException {

    private static final long serialVersionUID = 320544027921912540L;

    public ListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListNotFoundException(String message) {
        super(message);
    }



}
