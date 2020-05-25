package com.andre.compasso.entity.cliente;

public enum EnumSexo {

    FEMININO(0, "Feminino"),
    MASCULINO(1, "Masculino");

    private int codigo;
    private String nome;

    EnumSexo(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static EnumSexo getSexoByCodigo(int codigo){

        for(EnumSexo sexo : EnumSexo.values()){
            if (sexo.codigo == codigo) return sexo;
        }
        return null;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}