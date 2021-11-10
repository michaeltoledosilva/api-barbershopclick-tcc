package barbershopclick.vo;

import barbershopclick.entity.UsuarioEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class UsuarioVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"NOME\"")
	private String nome;
	private String senha;
	@CsvInterface(colClass = Classes.STRING, property = "login", header = "\"LOGIN\"")
	private String login;
	@CsvInterface(colClass = Classes.STRING, property = "perfil", header = "\"PERFIL\"")
	private String perfil;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"DATA CADASTRO\"")
	private String dataDesativacao;
	private String dataCadastro;
	private Integer idPerfil;
	private String acessaAgenda;
	private String acessaAtendimento;
	private String acessaCargos;
	private String acessaClientes;
	private String acessaFuncionarios;
	private String acessaPerfil;
	private String acessaProdutos;
	private String acessaUsuarios;

	public UsuarioVo(UsuarioEntity usuarioEntity) {
		this.id = usuarioEntity.getId();
		this.nome = usuarioEntity.getNome();
		this.senha = usuarioEntity.getSenha();
		this.login = usuarioEntity.getLogin();
		this.dataCadastro = usuarioEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(usuarioEntity.getDataCadastro());
		this.dataDesativacao = usuarioEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(usuarioEntity.getDataDesativacao());
		if (usuarioEntity.getPerfil() != null) {
			this.idPerfil = usuarioEntity.getPerfil().getId();
			this.perfil = usuarioEntity.getPerfil().getNome();
		} else {
			this.idPerfil = null;
			this.perfil = null;
		}

		if (usuarioEntity.getPerfil() != null) {
			this.acessaAgenda = usuarioEntity.getPerfil().getAcessaAgenda();
			this.acessaAtendimento = usuarioEntity.getPerfil().getAcessaAtendimento();
			this.acessaCargos = usuarioEntity.getPerfil().getAcessaCargos();
			this.acessaClientes = usuarioEntity.getPerfil().getAcessaClientes();
			this.acessaFuncionarios = usuarioEntity.getPerfil().getAcessaFuncionarios();
			this.acessaPerfil = usuarioEntity.getPerfil().getAcessaPerfil();
			this.acessaProdutos = usuarioEntity.getPerfil().getAcessaProdutos();
			this.acessaUsuarios = usuarioEntity.getPerfil().getAcessaUsuarios();
		}

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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(String dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
