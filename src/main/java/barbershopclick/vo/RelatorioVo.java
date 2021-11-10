package barbershopclick.vo;

import java.util.List;

public class RelatorioVo {

    private List<Integer> quantidade;
    private List<String> descricao;

    public RelatorioVo (List<Integer> quantidade, List<String> descricao) {
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

	public List<Integer> getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(List<Integer> quantidade) {
		this.quantidade = quantidade;
	}

	public List<String> getDescricao() {
		return descricao;
	}

	public void setDescricao(List<String> descricao) {
		this.descricao = descricao;
	}

}
