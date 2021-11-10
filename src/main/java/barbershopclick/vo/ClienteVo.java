package barbershopclick.vo;

import java.util.List;

import barbershopclick.entity.ClienteEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class ClienteVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
	private String nome;
	@CsvInterface(colClass = Classes.STRING, property = "telefone", header = "\"Telefone\"")
	private String telefone;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
	private String dataCadastro;
	private String dataDesativacao;
	private Integer idMaxItem;
	private List<CorteClienteVo> itensVo;

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public ClienteVo(ClienteEntity clienteEntity) {

		this.id = clienteEntity.getId();
		this.nome = clienteEntity.getNome();
		this.dataDesativacao = clienteEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(clienteEntity.getDataDesativacao());
		this.dataCadastro = clienteEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(clienteEntity.getDataCadastro());
		this.telefone = clienteEntity.getTelefone();

	}

	public Integer getIdMaxItem() {
		return idMaxItem;
	}

	public void setIdMaxItem(Integer idMaxItem) {
		this.idMaxItem = idMaxItem;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<CorteClienteVo> getItensVo() {
		return itensVo;
	}

	public void setItensVo(List<CorteClienteVo> itensVo) {
		this.itensVo = itensVo;
	}

}
