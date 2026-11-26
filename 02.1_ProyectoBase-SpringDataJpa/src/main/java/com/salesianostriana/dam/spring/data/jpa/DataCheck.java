package com.salesianostriana.dam.spring.data.jpa;

import jakarta.annotation.PostConstruct;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("dataSeed")
public class DataCheck {
    private final TransactionTemplate transactionalTemplate;

    public DataCheck(PlatformTransactionManager transactionManager){
        this.transactionalTemplate = new TransactionTemplate(transactionManager);
    }

    @PostConstruct
    public void comprobarDatos() {
        transactionalTemplate.executeWithoutResult(status ->
        );
    }
}
