package barbershopclick.vo;

import barbershopclick.entity.AgendaEntity;
import barbershopclick.enums.HorariosEnum;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class AgendaVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "dataAgenda", header = "\"DATA\"")
	private String dataAgenda;
	@CsvInterface(colClass = Classes.STRING, property = "horaAgenda", header = "\"HORA\"")
	private String horaAgenda;
	private Integer codigoHora;
	private Integer tipo;
	private Integer idCliente;
	private Integer idUsuarioCadastro;
	private Integer idUsuarioAgendado;
	@CsvInterface(colClass = Classes.STRING, property = "cliente", header = "\"Cliente\"")
	private String cliente;
	private String usuarioCadastro;
	@CsvInterface(colClass = Classes.STRING, property = "usuarioAgendado", header = "\"Funcionário\"")
	private String usuarioAgendado;

	public AgendaVo(AgendaEntity agendaEntity) {

		this.id = agendaEntity.getId();
		this.dataAgenda = agendaEntity.getDataAgenda() == null ? null
				: DataManager.getInstance().formatDate(agendaEntity.getDataAgenda());
		this.horaAgenda = agendaEntity.getDataAgenda() == null ? null
				: DataManager.getInstance().formatTime(agendaEntity.getDataAgenda());
		this.codigoHora = HorariosEnum
				.valueOf(Integer.valueOf(DataManager.getInstance().getHora(agendaEntity.getDataAgenda()))).getValue();
		this.tipo = agendaEntity.getTipo();
		if (agendaEntity.getUsuarioAgendado() == null) {
			this.idUsuarioAgendado = null;
			this.usuarioAgendado = null;
		} else {
			this.idUsuarioAgendado = agendaEntity.getUsuarioAgendado().getId();
			this.usuarioAgendado = agendaEntity.getUsuarioAgendado().getNome();
		}

		if (agendaEntity.getUsuarioCadastro() == null) {
			this.idUsuarioCadastro = null;
			this.usuarioCadastro = null;
		} else {
			this.idUsuarioCadastro = agendaEntity.getUsuarioCadastro().getId();
			this.usuarioCadastro = agendaEntity.getUsuarioCadastro().getNome();
		}

		if (agendaEntity.getCliente() == null) {
			this.idCliente = null;
			this.cliente = null;
		} else {
			this.idCliente = agendaEntity.getCliente().getId();
			this.cliente = agendaEntity.getCliente().getNome();
		}

	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(String usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getUsuarioAgendado() {
		return usuarioAgendado;
	}

	public void setUsuarioAgendado(String usuarioAgendado) {
		this.usuarioAgendado = usuarioAgendado;
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

	public String getHoraAgenda() {
		return horaAgenda;
	}

	public void setHoraAgenda(String horaAgenda) {
		this.horaAgenda = horaAgenda;
	}

	public Integer getCodigoHora() {
		return codigoHora;
	}

	public void setCodigoHora(Integer codigoHora) {
		this.codigoHora = codigoHora;
	}

}
