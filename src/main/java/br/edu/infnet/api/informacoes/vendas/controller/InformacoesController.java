package br.edu.infnet.api.informacoes.vendas.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class InformacoesController {

    @GetMapping(value = "/informacoes")
    public List<String> obterInformacao(){

        return new ArrayList<String>(
                Arrays.asList(
                        "Aluno: Clayton Morais de Oliveira",
                        "Disciplina: Arquitetura Java",
                        "Instituição: Instituto Infnet"
                )
        );
    }
}
