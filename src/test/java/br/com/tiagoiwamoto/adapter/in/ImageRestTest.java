package br.com.tiagoiwamoto.adapter.in;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
class ImageRestTest {

    @Inject
    private ImageRest rest;

    @Test
    @TestSecurity(authorizationEnabled = false)
    void index() {
        var resposta = this.rest.index("test.png");

        Assertions.assertEquals(200, resposta.getStatus());
    }

    @Test
    @TestSecurity(authorizationEnabled = false)
    void indexArquivoNaoExistente() {
        var resposta = this.rest.index(UUID.randomUUID().toString());

        Assertions.assertEquals(404, resposta.getStatus());
    }

}