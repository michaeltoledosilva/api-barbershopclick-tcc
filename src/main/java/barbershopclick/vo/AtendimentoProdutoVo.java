package barbershopclick.vo;

import java.math.BigDecimal;

import barbershopclick.entity.AtendimentoProdutoEntity;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class AtendimentoProdutoVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	private Integer idProduto;
	@CsvInterface(colClass = Classes.STRING, property = "produto", header = "\"PRODUTO\"")
	private String produto;
	private BigDecimal valor;
	private Integer idCliente;
	private String cliente;

	public AtendimentoProdutoVo(AtendimentoProdutoEntity atendimentoProdutoEntity) {

		this.id = atendimentoProdutoEntity.getId();
		this.valor = atendimentoProdutoEntity.getValor();

		if (atendimentoProdutoEntity.getProduto() != null) {
			this.idProduto = atendimentoProdutoEntity.getProduto().getId();
			this.produto = atendimentoProdutoEntity.getProduto().getNome();
		} else {
			this.idProduto = null;
			this.idProduto = null;
		}

		if (atendimentoProdutoEntity.getCliente() == null) {
			this.idCliente = null;
			this.cliente = null;
		} else {
			this.idCliente = atendimentoProdutoEntity.getCliente().getId();
			this.cliente = atendimentoProdutoEntity.getCliente().getNome();
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
