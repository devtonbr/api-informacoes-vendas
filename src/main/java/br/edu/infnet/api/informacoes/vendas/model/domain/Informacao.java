package br.edu.infnet.api.informacoes.vendas.model.domain;


import br.edu.infnet.api.informacoes.vendas.model.enums.TipoLoja;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Informacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "nomeLoja deve ter entre {min} e {max} caracteres")
    private String nomeLoja;

    @Size(min = 2, max = 50, message = "unidadeLoja deve ter entre {min} e {max} caracteres")
    private String unidadeLoja;

    @Enumerated(EnumType.STRING)
    private TipoLoja tipoLoja;

    public Long getId() {
        return id;
    }

    public Informacao setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public Informacao setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
        return this;
    }

    public String getUnidadeLoja() {
        return unidadeLoja;
    }

    public Informacao setUnidadeLoja(String unidadeLoja) {
        this.unidadeLoja = unidadeLoja;
        return this;
    }

    public TipoLoja getTipoLoja() {
        return tipoLoja;
    }

    public Informacao setTipoLoja(TipoLoja tipoLoja) {
        this.tipoLoja = tipoLoja;
        return this;
    }
}
