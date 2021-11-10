package barbershopclick.vo;

import java.math.BigDecimal;
import java.util.List;

public class RelatorioDecimalVo {

    private List<BigDecimal> valor;
    private List<String> descricao;

    public RelatorioDecimalVo (List<BigDecimal> valor, List<String> descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }
    
	public List<BigDecimal> getValor() {
		return valor;
	}

	public void setValor(List<BigDecimal> valor) {
		this.valor = valor;
	}

	public List<String> getDescricao() {
		return descricao;
	}

	public void setDescricao(List<String> descricao) {
		this.descricao = descricao;
	}

}
