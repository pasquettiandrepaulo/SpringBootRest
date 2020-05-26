package com.andre.compasso.entity.cliente;

import com.andre.compasso.entity.cidade.Cidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

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
    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(notes = "Nome cliente", example = "Cliente Novo")
    @Column(name = "nomecompleto")
    private String nomeCompleto;

    @Column(name = "sexo")
    @ApiModelProperty(notes = "Sexo cliente 0-Feminino, 1-Masculino")
    private Integer sexo;

    @ApiModelProperty(notes = "Data nascimento cliente dd/mm/yyyy", example = "14/06/1994")
    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;

    @ApiModelProperty(notes = "Idade cliente")
    @Column(name = "idade")
    private Integer idade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    @ApiModelProperty(hidden = true)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @Transient
    @ApiModelProperty(notes = "Cidade id")
    private Integer cidadeId;

    public Cliente() {}

    public Cliente(String nomeCompleto, Integer sexo, Date dataNascimento, Integer idade, Integer cidadeId) {
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.cidadeId = cidadeId;
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

    public Integer getSexo() {
        return this.sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
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

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
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
