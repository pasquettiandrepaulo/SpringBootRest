package com.andre.compasso.rest.util;

public class ResponseMessage {

    private Integer codigo;
    private String mensagem;
    private Object retorno;

    public ResponseMessage(Integer codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }

    public ResponseMessage(Integer codigo, String mensagem, Object retorno) {
        this.codigo = codigo;
        this.mensagem = mensagem;
        this.retorno = retorno;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getRetorno() {
        return retorno;
    }

    public void setRetorno(Object retorno) {
        this.retorno = retorno;
    }
}
