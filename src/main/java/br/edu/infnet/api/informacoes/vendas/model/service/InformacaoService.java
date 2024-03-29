package br.edu.infnet.api.informacoes.vendas.model.service;

import br.edu.infnet.api.informacoes.vendas.aspect.ObjectReturnType;
import br.edu.infnet.api.informacoes.vendas.aspect.ReturnNullObject;
import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import br.edu.infnet.api.informacoes.vendas.model.domain.Status;
import br.edu.infnet.api.informacoes.vendas.model.repository.InformacaoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InformacaoService {

    private final InformacaoRepository informacaoRepository;

    public InformacaoService(InformacaoRepository informacaoRepository) {
        this.informacaoRepository = informacaoRepository;
    }

    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public Page<Informacao> filtrar(Pageable page) {
        return informacaoRepository.findAll(page);
    }


    @ReturnNullObject(ObjectReturnType.SPRING_PAGE)
    public Page<Informacao> filtrar(String nomeLoja, Pageable page) {
        return informacaoRepository.findAllByNomeLojaContains(nomeLoja, page);
    }

    public Status salvar(Informacao acessorio) {
        informacaoRepository.save(acessorio);
        return new Status(0,"SUCESSO");
    }

    @ReturnNullObject(ObjectReturnType.OPTIONAL)
    public Optional<Informacao> obterInformacaoPorId(Long id) {
        return informacaoRepository.findById(id);
    }

    public Status excluir(Long id) {
        informacaoRepository.deleteById(id);
        return new Status(0,"SUCESSO");
    }

}
