package barbershopclick.vo;

import java.math.BigDecimal;
import java.text.ParseException;

import barbershopclick.entity.ProdutoEntity;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class ProdutoVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "nome", header = "\"NOME\"")
	private String nome;
	@CsvInterface(colClass = Classes.STRING, property = "dataCadastro", header = "\"DATA CADASTRO\"")
	private String dataCadastro;
	private BigDecimal valor;
	private String dataDesativacao;

	public ProdutoVo(ProdutoEntity produtoEntity) throws ParseException {

		this.id = produtoEntity.getId();
		this.nome = produtoEntity.getNome();
		this.dataCadastro = produtoEntity.getDataCadastro() == null ? null
				: DataManager.getInstance().formatDateTime(produtoEntity.getDataCadastro());
		this.valor = produtoEntity.getValor();
		this.dataDesativacao = produtoEntity.getDataDesativacao() == null ? null
				: DataManager.getInstance().formatDateTime(produtoEntity.getDataDesativacao());

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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(String dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

}
