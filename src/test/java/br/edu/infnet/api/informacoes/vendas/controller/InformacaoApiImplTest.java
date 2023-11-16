package br.edu.infnet.api.informacoes.vendas.controller;


import br.edu.infnet.api.informacoes.vendas.handler.ControllerErrorHandler;
import br.edu.infnet.api.informacoes.vendas.mapper.InformacoesMapper;
import br.edu.infnet.api.informacoes.vendas.model.domain.FiltroBusca;
import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import br.edu.infnet.api.informacoes.vendas.model.domain.Status;
import br.edu.infnet.api.informacoes.vendas.model.enums.TipoLoja;
import br.edu.infnet.api.informacoes.vendas.model.exception.ElementNotFoundException;
import br.edu.infnet.api.informacoes.vendas.model.exception.ListNotFoundException;
import br.edu.infnet.api.informacoes.vendas.model.service.InformacaoService;
import br.edu.infnet.api.informacoes.vendas.openapi.model.domain.FiltroBuscaRequest;
import br.edu.infnet.api.informacoes.vendas.openapi.model.domain.InformacaoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class InformacaoApiImplTest {

    private MockMvc mockMvc;

    @Mock
    private InformacaoService informacaoService;

    @Spy
    private InformacoesMapper informacoesMapper;

    @InjectMocks
    private InformacaoApiImpl controller;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerErrorHandler())
                .build();
    }

    @Test
    public void deveRetornarHttp200_FiltrarInformacoesSucesso() throws Exception {
        when(informacaoService.filtrar(any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getInformacao())));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/vendas/informacoes/v1/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    public void deveRetornarHttp204_FiltrarInformacoesSucesso() throws Exception {
        when(informacaoService.filtrar(any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/vendas/informacoes/v1/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    public void deveRetornarHttp200_FiltrarNomeInformacoesSucesso() throws Exception {
        when(informacaoService.filtrar(anyString(), any(PageRequest.class))).thenReturn(new PageImpl<>(List.of(getInformacao())));
        when(informacoesMapper.map(any(FiltroBuscaRequest.class))).thenReturn(new FiltroBusca());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/vendas/informacoes/v1/listar")
                        .content(new ObjectMapper().writeValueAsString(getFiltroBusca()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }



    @Test
    public void deveRetornarHttp204_FiltrarNomeInformacoesSucesso() throws Exception {
        when(informacoesMapper.map(any(FiltroBuscaRequest.class))).thenReturn(new FiltroBusca().setFiltro("nome").setPagina(1));
        when(informacaoService.filtrar(anyString(), any(PageRequest.class))).thenThrow(new ListNotFoundException("Erro"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/vendas/informacoes/v1/listar")
                        .content(new ObjectMapper().writeValueAsString(getFiltroBusca()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNoContent());
    }

    @Test
    public void deveRetornarHttp200_ObterInformacaoPorIdSucesso() throws Exception {
        when(informacaoService.obterInformacaoPorId(anyLong())).thenReturn(Optional.of(new Informacao()));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/vendas/informacoes/v1/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    public void deveRetornarHttp404_ObterInformacaoPorIdSucesso() throws Exception {
        when(informacaoService.obterInformacaoPorId(anyLong())).thenThrow(new ElementNotFoundException("ERRO"));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/vendas/informacoes/v1/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }

    @Test
    public void deveRetornarHttp200_ExcluirInformacaoPorIdSucesso() throws Exception {
        when(informacaoService.excluir(anyLong())).thenReturn(new Status(0, ""));
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/api/vendas/informacoes/v1/{id}", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk());
    }

    @Test
    public void deveRetornarHttp200_SalvarInformacaoPorIdSucesso() throws Exception {
        when(informacaoService.salvar(any(Informacao.class))).thenReturn(new Status(0, ""));
        when(informacoesMapper.map(any(InformacaoRequest.class))).thenReturn(new Informacao());
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/vendas/informacoes/v1/salvar")
                        .content(new ObjectMapper().writeValueAsString(getInformacaoRequest()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

    private InformacaoRequest getInformacaoRequest() {
        var informacaoRequest = new InformacaoRequest();
        informacaoRequest.setNomeLoja("NOME");
        informacaoRequest.setUnidadeLoja("UNIDADE");
        informacaoRequest.setTipoLoja("FRANQUIA");
        return informacaoRequest;

    }

    private static Informacao getInformacao() {
        return new Informacao()
                .setNomeLoja("nome")
                .setUnidadeLoja("Unidade")
                .setTipoLoja(TipoLoja.FRANQUIA);
    }



    private static FiltroBuscaRequest getFiltroBusca() {
        var filtroBusca = new FiltroBuscaRequest();
        filtroBusca.setPagina(1);
        filtroBusca.setFiltro("nome");
        filtroBusca.setCacheFiltro("nome");
        return filtroBusca;
    }


}
