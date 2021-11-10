package barbershopclick.vo;

import barbershopclick.entity.CorteClienteEntity;
import barbershopclick.enums.CortesEnum;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class CorteClienteVo {

	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "descricao", header = "\"DESCRIÇÃO\"")
	private String descricao;
	private Integer tipo;
	@CsvInterface(colClass = Classes.STRING, property = "tipoDescricao", header = "\"TIPO\"")
	private String tipoDescricao;
	private Integer idCliente;
	private String cliente;

	public CorteClienteVo(CorteClienteEntity corteClienteEntity) {

		this.id = corteClienteEntity.getId();
		this.descricao = corteClienteEntity.getDescricao();
		this.tipo = corteClienteEntity.getTipo();

		if (corteClienteEntity.getCliente() == null) {
			this.idCliente = null;
			this.cliente = null;
		} else {
			this.idCliente = corteClienteEntity.getCliente().getId();
			this.cliente = corteClienteEntity.getCliente().getNome();
		}
		this.tipoDescricao = CortesEnum.valueOf(this.tipo).getText();
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getTipoDescricao() {
		return tipoDescricao;
	}

	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
	}

}
