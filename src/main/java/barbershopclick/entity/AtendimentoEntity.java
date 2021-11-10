package barbershopclick.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "cadatendimentos")
public class AtendimentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "idatendimento")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuarios_lancamento")
	private UsuarioEntity usuarioLancamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario_atendimento")
	private UsuarioEntity usuarioAtendimento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClienteEntity cliente;

	@Column(name = "dat_atendimento")
	private Date dataAtendimento;

	@Column(name = "dcr_descricao")
	private String descricao;

	@Column(name = "val_total")
	private BigDecimal valorTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UsuarioEntity getUsuarioLancamento() {
		return usuarioLancamento;
	}

	public void setUsuarioLancamento(UsuarioEntity usuarioLancamento) {
		this.usuarioLancamento = usuarioLancamento;
	}

	public UsuarioEntity getUsuarioAtendimento() {
		return usuarioAtendimento;
	}

	public void setUsuarioAtendimento(UsuarioEntity usuarioAtendimento) {
		this.usuarioAtendimento = usuarioAtendimento;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
