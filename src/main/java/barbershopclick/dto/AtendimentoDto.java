package barbershopclick.dto;

import java.math.BigDecimal;
import java.util.List;

public class AtendimentoDto {

	private Integer id;
	private String dataAtendimento;
	private Integer codigoHora;
	private Integer idUsuarioLancamento;
	private Integer idUsuarioAtendimento;
	private Integer idCliente;
	private String descricao;
	private BigDecimal valorTotal;
	private List<AtendimentoProdutoDto> itensVo;

	private AtendimentoDto() {

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

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDataAtendimento() {
		return dataAtendimento;
	}

	public void setDataAtendimento(String dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public Integer getCodigoHora() {
		return codigoHora;
	}

	public void setCodigoHora(Integer codigoHora) {
		this.codigoHora = codigoHora;
	}

	public List<AtendimentoProdutoDto> getItensVo() {
		return itensVo;
	}

	public void setItensVo(List<AtendimentoProdutoDto> itensVo) {
		this.itensVo = itensVo;
	}
}
