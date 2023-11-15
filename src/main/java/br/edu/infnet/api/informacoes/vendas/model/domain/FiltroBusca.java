package br.edu.infnet.api.informacoes.vendas.model.domain;

public class FiltroBusca {

    private String cacheFiltro;

    private String filtro;

    private int pagina;

    public String getCacheFiltro() {
        return cacheFiltro;
    }

    public FiltroBusca setCacheFiltro(String cacheFiltro) {
        this.cacheFiltro = cacheFiltro;
        return this;
    }

    public String getFiltro() {
        return filtro;
    }

    public FiltroBusca setFiltro(String filtro) {
        this.filtro = filtro;
        return this;
    }

    public int getPagina() {
        return pagina;
    }

    public FiltroBusca setPagina(int pagina) {
        this.pagina = pagina;
        return this;
    }
}
