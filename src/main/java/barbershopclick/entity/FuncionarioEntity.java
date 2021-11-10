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
@Table(name = "cadfuncionarios")
public class FuncionarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "idfuncionario")
	private Integer id;

	@Column(name = "dcr_nome")
	private String nome;

	@Column(name = "dcr_telefone")
	private String telefone;

	@Column(name = "dcr_email")
	private String email;

	@Column(name = "dat_desativacao")
	private Date dataDesativacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcargo")
	private CargoEntity cargo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private UsuarioEntity usuario;

	@Column(name = "dat_cadastro")
	private Date dataCadastro;

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public CargoEntity getCargo() {
		return cargo;
	}

	public void setCargo(CargoEntity cargo) {
		this.cargo = cargo;
	}

	public UsuarioEntity getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}
