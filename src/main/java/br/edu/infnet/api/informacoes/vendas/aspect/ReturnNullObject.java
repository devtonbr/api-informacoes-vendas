package br.edu.infnet.api.informacoes.vendas.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnNullObject {
	ObjectReturnType value() default ObjectReturnType.OBJECT;
}
