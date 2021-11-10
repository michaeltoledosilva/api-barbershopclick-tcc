package barbershopclick.dto;

import java.util.List;

public class ClienteDto {

	private Integer id;
	private String nome;
	private String dataDesativacao;
	private String telefone;
	private List<CorteClienteDto> itensVo;

	public ClienteDto() {

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

	public List<CorteClienteDto> getItensVo() {
		return itensVo;
	}

	public void setItensVo(List<CorteClienteDto> itensVo) {
		this.itensVo = itensVo;
	}

}
