package barbershopclick.vo;

import barbershopclick.entity.FuncionarioEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class FuncionarioVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
	private String nome;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
	private String dataCadastro;
	private String telefone;
	private String email;
	private String dataDesativacao;
	private Integer usuario;
	private Integer cargo;

	public FuncionarioVo(FuncionarioEntity funcionarioEntity) {

		this.id = funcionarioEntity.getId();
		this.nome = funcionarioEntity.getNome();
		this.telefone = funcionarioEntity.getTelefone();
		this.email = funcionarioEntity.getEmail();
		this.dataDesativacao = funcionarioEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(funcionarioEntity.getDataDesativacao());
		this.dataCadastro = funcionarioEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(funcionarioEntity.getDataCadastro());
		this.usuario = funcionarioEntity.getUsuario() == null ? null : funcionarioEntity.getUsuario().getId();
		this.cargo = funcionarioEntity.getCargo() == null ? null : funcionarioEntity.getCargo().getId();

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

	public String getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(String dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

	public Integer getCargo() {
		return cargo;
	}

	public void setCargo(Integer cargo) {
		this.cargo = cargo;
	}

}
