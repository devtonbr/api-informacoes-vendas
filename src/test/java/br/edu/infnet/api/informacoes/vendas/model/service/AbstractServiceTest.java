package br.edu.infnet.api.informacoes.vendas.model.service;

import br.edu.infnet.api.informacoes.vendas.aspect.ReturnRepositoryElement;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public abstract class AbstractServiceTest {

    protected Validator validator;

    protected AspectJProxyFactory factory;

    public <T> T setupServiceTest(T service) {
        this.factory = new AspectJProxyFactory(service);
        this.factory.addAspect(new ReturnRepositoryElement());
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        return service = this.factory.getProxy();
    }

}
