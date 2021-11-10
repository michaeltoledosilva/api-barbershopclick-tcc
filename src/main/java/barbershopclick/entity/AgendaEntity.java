package barbershopclick.entity;

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
@Table(name = "cadagenda")
public class AgendaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "idagenda")
	private Integer id;

	@Column(name = "dat_agenda")
	private Date dataAgenda;

	@Column(name = "flg_tipo")
	private Integer tipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	private ClienteEntity cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario_cadastro")
	private UsuarioEntity usuarioCadastro;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario_agendado")
	private UsuarioEntity usuarioAgendado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataAgenda() {
		return dataAgenda;
	}

	public void setDataAgenda(Date dataAgenda) {
		this.dataAgenda = dataAgenda;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public UsuarioEntity getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(UsuarioEntity usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public UsuarioEntity getUsuarioAgendado() {
		return usuarioAgendado;
	}

	public void setUsuarioAgendado(UsuarioEntity usuarioAgendado) {
		this.usuarioAgendado = usuarioAgendado;
	}

}
