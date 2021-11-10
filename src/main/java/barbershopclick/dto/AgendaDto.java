package barbershopclick.dto;

public class AgendaDto {

	private Integer id;
	private String dataAgenda;
	private Integer codigoHora;
	private Integer tipo;
	private Integer idCliente;
	private Integer idUsuarioCadastro;
	private Integer idUsuarioAgendado;

	public AgendaDto() {

	}

	public Integer getCodigoHora() {
		return codigoHora;
	}

	public void setCodigoHora(Integer codigoHora) {
		this.codigoHora = codigoHora;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataAgenda() {
		return dataAgenda;
	}

	public void setDataAgenda(String dataAgenda) {
		this.dataAgenda = dataAgenda;
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

	public Integer getIdUsuarioCadastro() {
		return idUsuarioCadastro;
	}

	public void setIdUsuarioCadastro(Integer idUsuarioCadastro) {
		this.idUsuarioCadastro = idUsuarioCadastro;
	}

	public Integer getIdUsuarioAgendado() {
		return idUsuarioAgendado;
	}

	public void setIdUsuarioAgendado(Integer idUsuarioAgendado) {
		this.idUsuarioAgendado = idUsuarioAgendado;
	}

}
