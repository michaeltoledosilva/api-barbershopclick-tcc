package barbershopclick.dto;

import java.util.Date;

public class PerfilDto {

	private Integer id;
	private String nome;
	private Date dataDesativacao;
	private boolean acessaAgenda;
	private boolean acessaAtendimento;
	private boolean acessaCargos;
	private boolean acessaClientes;
	private boolean acessaFuncionarios;
	private boolean acessaPerfil;
	private boolean acessaProdutos;
	private boolean acessaUsuarios;

	public PerfilDto() {

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

	public boolean isAcessaAgenda() {
		return acessaAgenda;
	}

	public void setAcessaAgenda(boolean acessaAgenda) {
		this.acessaAgenda = acessaAgenda;
	}

	public boolean isAcessaAtendimento() {
		return acessaAtendimento;
	}

	public void setAcessaAtendimento(boolean acessaAtendimento) {
		this.acessaAtendimento = acessaAtendimento;
	}

	public boolean isAcessaCargos() {
		return acessaCargos;
	}

	public void setAcessaCargos(boolean acessaCargos) {
		this.acessaCargos = acessaCargos;
	}

	public boolean isAcessaClientes() {
		return acessaClientes;
	}

	public void setAcessaClientes(boolean acessaClientes) {
		this.acessaClientes = acessaClientes;
	}

	public boolean isAcessaFuncionarios() {
		return acessaFuncionarios;
	}

	public void setAcessaFuncionarios(boolean acessaFuncionarios) {
		this.acessaFuncionarios = acessaFuncionarios;
	}

	public boolean isAcessaPerfil() {
		return acessaPerfil;
	}

	public void setAcessaPerfil(boolean acessaPerfil) {
		this.acessaPerfil = acessaPerfil;
	}

	public boolean isAcessaProdutos() {
		return acessaProdutos;
	}

	public void setAcessaProdutos(boolean acessaProdutos) {
		this.acessaProdutos = acessaProdutos;
	}

	public boolean isAcessaUsuarios() {
		return acessaUsuarios;
	}

	public void setAcessaUsuarios(boolean acessaUsuarios) {
		this.acessaUsuarios = acessaUsuarios;
	}

}
