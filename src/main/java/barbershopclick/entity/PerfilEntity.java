package barbershopclick.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "cadperfis")
public class PerfilEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "idperfil")
	private Integer id;

	@Column(name = "dcr_nome")
	private String nome;

	@Column(name = "dat_desativacao")
	private Date dataDesativacao;

	@Column(name = "dat_cadastro")
	private Date dataCadastro;

	@Column(name = "flb_acessa_agenda")
	private String acessaAgenda;

	@Column(name = "flb_acessa_atendimento")
	private String acessaAtendimento;

	@Column(name = "flb_acessa_cargos")
	private String acessaCargos;

	@Column(name = "flb_acessa_clientes")
	private String acessaClientes;

	@Column(name = "flb_acessa_funcionarios")
	private String acessaFuncionarios;

	@Column(name = "flb_acessa_perfil")
	private String acessaPerfil;

	@Column(name = "flb_acessa_produtos")
	private String acessaProdutos;

	@Column(name = "flb_acessa_usuarios")
	private String acessaUsuarios;

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

	public Date getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(Date dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getAcessaAgenda() {
		return acessaAgenda;
	}

	public void setAcessaAgenda(String acessaAgenda) {
		this.acessaAgenda = acessaAgenda;
	}

	public String getAcessaAtendimento() {
		return acessaAtendimento;
	}

	public void setAcessaAtendimento(String acessaAtendimento) {
		this.acessaAtendimento = acessaAtendimento;
	}

	public String getAcessaCargos() {
		return acessaCargos;
	}

	public void setAcessaCargos(String acessaCargos) {
		this.acessaCargos = acessaCargos;
	}

	public String getAcessaClientes() {
		return acessaClientes;
	}

	public void setAcessaClientes(String acessaClientes) {
		this.acessaClientes = acessaClientes;
	}

	public String getAcessaFuncionarios() {
		return acessaFuncionarios;
	}

	public void setAcessaFuncionarios(String acessaFuncionarios) {
		this.acessaFuncionarios = acessaFuncionarios;
	}

	public String getAcessaPerfil() {
		return acessaPerfil;
	}

	public void setAcessaPerfil(String acessaPerfil) {
		this.acessaPerfil = acessaPerfil;
	}

	public String getAcessaProdutos() {
		return acessaProdutos;
	}

	public void setAcessaProdutos(String acessaProdutos) {
		this.acessaProdutos = acessaProdutos;
	}

	public String getAcessaUsuarios() {
		return acessaUsuarios;
	}

	public void setAcessaUsuarios(String acessaUsuarios) {
		this.acessaUsuarios = acessaUsuarios;
	}

}
