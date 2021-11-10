package barbershopclick.vo;

import barbershopclick.entity.PerfilEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class PerfilVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"NOME\"")
	private String nome;
	private String dataDesativacao;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"DATA CADASTRO\"")
	private String dataCadastro;
	private boolean acessaAgenda;
	private boolean acessaAtendimento;
	private boolean acessaCargos;
	private boolean acessaClientes;
	private boolean acessaFuncionarios;
	private boolean acessaPerfil;
	private boolean acessaProdutos;
	private boolean acessaUsuarios;

	public PerfilVo(PerfilEntity perfilEntity) {

		this.id = perfilEntity.getId();
		this.nome = perfilEntity.getNome();
		this.dataCadastro = perfilEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(perfilEntity.getDataCadastro());
		this.dataDesativacao = perfilEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(perfilEntity.getDataDesativacao());
		this.acessaAgenda = perfilEntity.getAcessaAgenda().equals("1");
		this.acessaAtendimento = perfilEntity.getAcessaAtendimento().equals("1");
		this.acessaCargos = perfilEntity.getAcessaCargos().equals("1");
		this.acessaClientes = perfilEntity.getAcessaClientes().equals("1");
		this.acessaFuncionarios = perfilEntity.getAcessaFuncionarios().equals("1");
		this.acessaPerfil = perfilEntity.getAcessaPerfil().equals("1");
		this.acessaProdutos = perfilEntity.getAcessaProdutos().equals("1");
		this.acessaUsuarios = perfilEntity.getAcessaUsuarios().equals("1");

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

	public String getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(String dataDesativacao) {
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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
