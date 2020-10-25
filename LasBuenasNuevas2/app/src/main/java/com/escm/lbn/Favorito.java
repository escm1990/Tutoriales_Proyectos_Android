package com.escm.lbn;

public class Favorito {

    private String codigo, titulo, url, content;

    public Favorito(String codigo, String titulo, String url, String content) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.url = url;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Favorito() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
