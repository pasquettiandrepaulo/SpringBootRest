package com.andre.compasso.entity.cliente;

import com.andre.compasso.entity.cidade.Cidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente", allocationSize = 1)
    @GeneratedValue(generator = "seq_cliente", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nomecompleto")
    private String nomeCompleto;

    @Column(name = "sexo")
    private Integer sexo;

    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @Column(name = "idade")
    private Integer idade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Cliente() {}

    public Cliente(String nomeCompleto, EnumSexo sexo, Date dataNascimento, Integer idade, Cidade cidade) {
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo.ordinal();
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.cidade = cidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSexo() {
        return EnumSexo.getSexoByCodigo(this.sexo).getNome();
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo.ordinal();
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) &&
                nomeCompleto.equals(cliente.nomeCompleto) &&
                sexo.equals(cliente.sexo) &&
                dataNascimento.equals(cliente.dataNascimento) &&
                idade.equals(cliente.idade) &&
                cidade.equals(cliente.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nomeCompleto, sexo, dataNascimento, idade, cidade);
    }
}
