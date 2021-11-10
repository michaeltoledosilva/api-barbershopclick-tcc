package barbershopclick.vo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import barbershopclick.entity.AtendimentoEntity;
import barbershopclick.enums.HorariosEnum;
import barbershopclick.helper.DataManager;
import barbershopclick.helper.csvmanager.Classes;
import barbershopclick.helper.csvmanager.CsvInterface;

public class AtendimentoVo {

	@CsvInterface(colClass = Classes.STRING, property = "id", header = "\"ID\"")
	private Integer id;
	@CsvInterface(colClass = Classes.STRING, property = "descricao", header = "\"DESCRIÇÃO\"")
	private String descricao;
	@CsvInterface(colClass = Classes.STRING, property = "dataAtendimento", header = "\"DATA\"")
	private String dataAtendimento;
	@CsvInterface(colClass = Classes.STRING, property = "horaAtendimento", header = "\"HORA\"")
	private String horaAtendimento;
	private Integer codigoHora;
	private Integer idUsuarioLancamento;
	private Integer idUsuarioAtendimento;
	private Integer idCliente;
	private String usuarioLancamento;
	@CsvInterface(colClass = Classes.STRING, property = "cliente", header = "\"CLIENTE\"")
	private String cliente;
	@CsvInterface(colClass = Classes.STRING, property = "usuarioAtendimento", header = "\"FUNCIONÁRIO\"")
	private String usuarioAtendimento;
	@CsvInterface(colClass = Classes.STRING, property = "valorTotal", header = "\"VALOR\"")
	private String valorTotalDescricao;
	private BigDecimal valorTotal;
	private Integer idMaxItem;
	private List<AtendimentoProdutoVo> itensVo;

	public AtendimentoVo(AtendimentoEntity atendimentoEntity) {
		this.id = atendimentoEntity.getId();
		this.dataAtendimento = atendimentoEntity.getDataAtendimento() == null ? null
				: DataManager.getInstance().formatDate(atendimentoEntity.getDataAtendimento());
		this.horaAtendimento = atendimentoEntity.getDataAtendimento() == null ? null
				: DataManager.getInstance().formatTime(atendimentoEntity.getDataAtendimento());
		this.codigoHora = HorariosEnum
				.valueOf(Integer.valueOf(DataManager.getInstance().getHora(atendimentoEntity.getDataAtendimento())))
				.getValue();
		this.descricao = atendimentoEntity.getDescricao();
		this.valorTotalDescricao = NumberFormat.getCurrencyInstance().format(atendimentoEntity.getValorTotal());
		this.valorTotal = atendimentoEntity.getValorTotal();

		if (atendimentoEntity.getUsuarioAtendimento() == null) {
			this.idUsuarioAtendimento = null;
			this.usuarioAtendimento = null;
		} else {
			this.idUsuarioAtendimento = atendimentoEntity.getUsuarioAtendimento().getId();
			this.usuarioAtendimento = atendimentoEntity.getUsuarioAtendimento().getNome();
		}

		if (atendimentoEntity.getUsuarioLancamento() == null) {
			this.idUsuarioLancamento = null;
			this.usuarioLancamento = null;
		} else {
			this.idUsuarioLancamento = atendimentoEntity.getUsuarioLancamento().getId();
			this.usuarioLancamento = atendimentoEntity.getUsuarioLancamento().getNome();
		}

		if (atendimentoEntity.getCliente() == null) {
			this.idCliente = null;
			this.cliente = null;
		} else {
			this.idCliente = atendimentoEntity.getCliente().getId();
			this.cliente = atendimentoEntity.getCliente().getNome();
		}
	}

	public Integer getIdMaxItem() {
		return idMaxItem;
	}

	public void setIdMaxItem(Integer idMaxItem) {
		this.idMaxItem = idMaxItem;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getHoraAtendimento() {
		return horaAtendimento;
	}

	public void setHoraAtendimento(String horaAtendimento) {
		this.horaAtendimento = horaAtendimento;
	}

	public Integer getCodigoHora() {
		return codigoHora;
	}

	public void setCodigoHora(Integer codigoHora) {
		this.codigoHora = codigoHora;
	}

	public String getUsuarioLancamento() {
		return usuarioLancamento;
	}

	public void setUsuarioLancamento(String usuarioLancamento) {
		this.usuarioLancamento = usuarioLancamento;
	}

	public String getUsuarioAtendimento() {
		return usuarioAtendimento;
	}

	public void setUsuarioAtendimento(String usuarioAtendimento) {
		this.usuarioAtendimento = usuarioAtendimento;
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

	public Integer getIdUsuarioLancamento() {
		return idUsuarioLancamento;
	}

	public void setIdUsuarioLancamento(Integer idUsuarioLancamento) {
		this.idUsuarioLancamento = idUsuarioLancamento;
	}

	public Integer getIdUsuarioAtendimento() {
		return idUsuarioAtendimento;
	}

	public void setIdUsuarioAtendimento(Integer idUsuarioAtendimento) {
		this.idUsuarioAtendimento = idUsuarioAtendimento;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValorTotalDescricao() {
		return valorTotalDescricao;
	}

	public void setValorTotalDescricao(String valorTotalDescricao) {
		this.valorTotalDescricao = valorTotalDescricao;
	}

	public List<AtendimentoProdutoVo> getItensVo() {
		return itensVo;
	}

	public void setItensVo(List<AtendimentoProdutoVo> itensVo) {
		this.itensVo = itensVo;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

}
