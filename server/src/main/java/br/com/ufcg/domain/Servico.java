package br.com.ufcg.domain;

import br.com.ufcg.domain.enums.TipoStatus;
import com.fasterxml.jackson.annotation.JsonFormat;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "TAB_SERVICO")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SERVICO", unique = true, nullable = false)
    private Long id;

    @Column(name="CD_TIPO", nullable = false)
    private String tipo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "DT_DATA", nullable = false)
    private LocalDate data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm")
    @Column(name = "HR_HORARIO", nullable = false)
    private LocalTime horario;

    @Column(name = "VL_VALOR",  nullable = false)
    private BigDecimal valor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ED_ENDERECO", referencedColumnName = "ID_ENDERECO", updatable = false)
    private Endereco endereco;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "CLIENTE", referencedColumnName = "ID_USUARIO", updatable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Fornecedor.class)
    @JoinColumn(name = "FORNECEDOR", referencedColumnName = "ID_USUARIO")
    private Fornecedor fornecedor;

    @Enumerated
    @Column(name = "CD_STATUS")
    private TipoStatus status;

    public Servico() {
    }

    public Servico(String tipo, LocalDate data, LocalTime horario, BigDecimal valor, Endereco endereco) {
        this.tipo = tipo;
        this.data = data;
        this.horario = horario;
        this.valor = valor;
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public TipoStatus getStatus() {
        return status;
    }

    public void setStatus(TipoStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servico servico = (Servico) o;
        return Objects.equals(data, servico.data) &&
                Objects.equals(horario, servico.horario) &&
                Objects.equals(valor, servico.valor) &&
                Objects.equals(endereco, servico.endereco) &&
                Objects.equals(tipo, servico.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, horario, valor, endereco, tipo);
    }
}
