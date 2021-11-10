package barbershopclick.dto;

public class CorteClienteDto {

	private Integer id;
	private String descricao;
	private String dataDesativacao;
	private Integer tipo;
	private Integer idCliente;
	private boolean isNovo;

	public boolean isNovo() {
		return isNovo;
	}

	public void setNovo(boolean isNovo) {
		this.isNovo = isNovo;
	}

	public CorteClienteDto() {

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

	public String getDataDesativacao() {
		return dataDesativacao;
	}

	public void setDataDesativacao(String dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
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

}
