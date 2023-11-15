package br.edu.infnet.api.informacoes.vendas.controller;

import br.edu.infnet.api.informacoes.vendas.model.domain.FiltroBusca;
import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import br.edu.infnet.api.informacoes.vendas.model.domain.Status;
import br.edu.infnet.api.informacoes.vendas.model.service.InformacaoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static br.edu.infnet.api.informacoes.vendas.utils.Constants.NUMBER_PAGES;
import static br.edu.infnet.api.informacoes.vendas.utils.Constants.SORT_ATTRIBUTE;


@RestController
@RequestMapping("/api/vendas/informacoes/v1")
public class InformacoesController {

    private final InformacaoService informacaoService;

    public InformacoesController(InformacaoService informacaoService) {
        this.informacaoService = informacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<Informacao>> filtrarInformacoes() {
        return new ResponseEntity<Page<Informacao>>(informacaoService.filtrar(PageRequest.of(0, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))), HttpStatus.OK);
    }

    @PostMapping("/listar")
    public ResponseEntity<Page<Informacao>> filtrarInformacoes(@RequestBody FiltroBusca filtroBusca) {
        return new ResponseEntity<Page<Informacao>>(informacaoService.filtrar(filtroBusca.getFiltro(), PageRequest.of(filtroBusca.getPagina() - 1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Informacao> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<Informacao>(informacaoService.obterInformacaoPorId(id).get(),HttpStatus.OK);
    }

    @PostMapping("/salvar")
    public ResponseEntity<Status> save(@RequestBody Informacao informacao) {
        return new ResponseEntity<Status>(informacaoService.salvar(informacao),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Status> deleteById(@PathVariable("id") Long id) {
        return new ResponseEntity<Status>(informacaoService.excluir(id),HttpStatus.OK);
    }

}
