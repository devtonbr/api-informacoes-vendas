package br.edu.infnet.api.informacoes.vendas.controller;

import br.edu.infnet.api.informacoes.vendas.mapper.InformacoesMapper;
import br.edu.infnet.api.informacoes.vendas.model.service.InformacaoService;
import br.edu.infnet.api.informacoes.vendas.openapi.model.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static br.edu.infnet.api.informacoes.vendas.utils.Constants.NUMBER_PAGES;
import static br.edu.infnet.api.informacoes.vendas.utils.Constants.SORT_ATTRIBUTE;


@RestController
@RequestMapping("/")
public class InformacaoApiImpl implements InformacaoApi {

    private final InformacaoService informacaoService;

    private final InformacoesMapper informacoesMapper;


    public InformacaoApiImpl(InformacaoService informacaoService, InformacoesMapper informacoesMapper) {
        this.informacaoService = informacaoService;
        this.informacoesMapper = informacoesMapper;
    }

    @Override
    public ResponseEntity<StatusResponse> excluir(BigDecimal id) {
        return ResponseEntity.ok(informacoesMapper.map(informacaoService.excluir(id.longValue())));
    }

    @Override
    public ResponseEntity<ListInformacaoResponse> filtrar() {
        return ResponseEntity.ok(informacoesMapper.map(informacaoService.filtrar(PageRequest.of(0, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<ListInformacaoResponse> filtrarPorNome(FiltroBuscaRequest filtroBuscaRequest) {
        var filtroBusca = informacoesMapper.map(filtroBuscaRequest);
        return ResponseEntity.ok(informacoesMapper.map(informacaoService.filtrar(filtroBusca.getFiltro(), PageRequest.of(filtroBuscaRequest.getPagina(), NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)))));
    }

    @Override
    public ResponseEntity<InformacaoResponse> obterInformacaoPorId(BigDecimal id) {
        return ResponseEntity.ok(informacoesMapper.map(informacaoService.obterInformacaoPorId(id.longValue()).get()));
    }

    @Override
    public ResponseEntity<StatusResponse> salvar(InformacaoRequest informacaoRequest) {
        return ResponseEntity.status(201).body(informacoesMapper.map(informacaoService.salvar(informacoesMapper.map(informacaoRequest))));
    }
}
