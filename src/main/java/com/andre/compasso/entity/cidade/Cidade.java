package com.andre.compasso.entity.cidade;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    @ApiModelProperty(hidden = true, accessMode = ApiModelProperty.AccessMode.READ_WRITE)
    private Integer id;

    @Column(name = "nome")
    @ApiModelProperty(notes = "Nome cidade", example = "Passo Fundo")
    private String nome;

    @Column(name = "estado ")
    @ApiModelProperty(notes = "Estado cidade", example = "RS")
    private String estado;

    public Cidade(){}

    public Cidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id) &&
                nome.equals(cidade.nome) &&
                estado.equals(cidade.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
