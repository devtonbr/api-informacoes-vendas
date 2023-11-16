package br.edu.infnet.api.informacoes.vendas.mapper;


import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import br.edu.infnet.api.informacoes.vendas.model.domain.Status;
import br.edu.infnet.api.informacoes.vendas.openapi.model.domain.*;
import br.edu.infnet.api.informacoes.vendas.model.domain.FiltroBusca;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.data.domain.Page;

@Mapper(componentModel= MappingConstants.ComponentModel.SPRING)
public interface InformacoesMapper {

    FiltroBusca map(FiltroBuscaRequest request);

    Informacao map(InformacaoRequest request);

    InformacaoResponse map(Informacao response);

    ListInformacaoResponse map(Page<Informacao> response);

    StatusResponse map(Status response);


}
