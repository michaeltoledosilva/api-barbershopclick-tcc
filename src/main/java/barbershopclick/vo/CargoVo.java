package barbershopclick.vo;

import barbershopclick.entity.CargoEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class CargoVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"Nome\"")
	private String nome;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"Data Cadastro\"")
	private String dataCadastro;
	private String dataDesativacao;

	public CargoVo(CargoEntity cargoEntity) {

		this.id = cargoEntity.getId();
		this.nome = cargoEntity.getNome();
		this.dataDesativacao = cargoEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(cargoEntity.getDataDesativacao());
		this.dataCadastro = cargoEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(cargoEntity.getDataCadastro());

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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
