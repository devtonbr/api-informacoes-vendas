package br.edu.infnet.api.informacoes.vendas.model.service;

import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import br.edu.infnet.api.informacoes.vendas.model.enums.TipoLoja;
import br.edu.infnet.api.informacoes.vendas.model.exception.ElementNotFoundException;
import br.edu.infnet.api.informacoes.vendas.model.exception.ListNotFoundException;
import br.edu.infnet.api.informacoes.vendas.model.repository.InformacaoRepository;
import jakarta.validation.ConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static br.edu.infnet.api.informacoes.vendas.utils.Constants.NUMBER_PAGES;
import static br.edu.infnet.api.informacoes.vendas.utils.Constants.SORT_ATTRIBUTE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class InformacaoServiceTest extends AbstractServiceTest {


    @Mock
    private InformacaoRepository informacaoRepository;

    @InjectMocks
    private InformacaoService informacaoService;

    @BeforeEach
    void setup() {
        informacaoService = setupServiceTest(informacaoService);
    }

    @Test
    void deveListarInformacoesByNomeSucesso() {
        when(informacaoRepository.findAllByNomeLojaContains(anyString(),any(Pageable.class))).thenReturn(new PageImpl<>(List.of(new Informacao())));
        var listaVendedores = informacaoService.filtrar("nome", PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)));
        assertNotNull(listaVendedores);
    }

    @Test
    void deveLancarListNotFoundException_FiltrarByNome_QuandoDadosForVazio() {
        when(informacaoRepository.findAllByNomeLojaContains(anyString(),any(Pageable.class))).thenReturn(Page.empty());
        assertThrows(ListNotFoundException.class, () -> informacaoService.filtrar("nome", PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))));
    }

    @Test
    void deveListarInformacoesSucesso() {
        when(informacaoRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(new Informacao())));
        var listaVendedores = informacaoService.filtrar(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE)));
        assertNotNull(listaVendedores);
    }

    @Test
    void deveLancarListNotFoundException_Filtrar_QuandoDadosForVazio() {
        when(informacaoRepository.findAll(any(Pageable.class))).thenReturn(Page.empty());
        assertThrows(ListNotFoundException.class, () -> informacaoService.filtrar(PageRequest.of(1, NUMBER_PAGES, Sort.by(SORT_ATTRIBUTE))));
    }

    @Test
    void deveObterInformacaoPorIdSucesso() {
        when(informacaoRepository.findById(anyLong())).thenReturn(Optional.of(new Informacao()));
        var informacao = informacaoService.obterInformacaoPorId(1L);
        assertNotNull(informacao);
    }

    void deveLancarListNotFoundException_ObterInformacaoPorId_QuandoDadosForVazio(){
        when(informacaoRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> informacaoService.obterInformacaoPorId(1L));
    }

    @Test
    void deveSalvarInformacaoSucesso() {
        var informacao = new Informacao();
        informacao.setNomeLoja("Nome Loja");
        informacao.setUnidadeLoja("Unidade Loja");
        informacao.setTipoLoja(TipoLoja.FRANQUIA);
        Set<ConstraintViolation<Informacao>> violations = this.validator.validate(informacao);
        var status = informacaoService.salvar(informacao);
        assertNotNull(status);
        assertTrue(violations.isEmpty());
        verify(informacaoRepository, times(1)).save(any(Informacao.class));
    }

    @Test
    void deveRetornarErro_QuandoTipoInformacaoFor_sapato_ENomeFor_bo_EDescricaoFor_s(){
        var informacao = new Informacao();
        informacao.setNomeLoja("N");
        informacao.setUnidadeLoja("U");
        informacao.setTipoLoja(TipoLoja.FRANQUIA);

        Set<ConstraintViolation<Informacao>> violations = this.validator.validate(informacao);
        informacaoService.salvar(informacao);
        assertEquals(2, violations.size());
        assertTrue(violations.stream().filter( item -> "nomeLoja deve ter entre 2 e 50 caracteres".equals(item.getMessage())).findFirst().isPresent());
        assertTrue(violations.stream().filter( item -> "unidadeLoja deve ter entre 2 e 50 caracteres".equals(item.getMessage())).findFirst().isPresent());
        verify(informacaoRepository, times(1)).save(any(Informacao.class));
    }

    @Test
    void deveExcluirCalcadoSucesso() {
        var status =informacaoService.excluir(1L);
        assertNotNull(status);
        verify(informacaoRepository, times(1)).deleteById(any(Long.class));
    }


}
