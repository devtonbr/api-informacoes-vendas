package br.edu.infnet.api.informacoes.vendas.model.repository;

import br.edu.infnet.api.informacoes.vendas.model.domain.Informacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformacaoRepository extends JpaRepository<Informacao, Long> {

    Page<Informacao> findAllByNomeLojaContains(String nomeLoja, Pageable page);

}
