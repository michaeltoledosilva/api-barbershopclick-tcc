package barbershopclick.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.AtendimentoProdutoEntity;
import barbershopclick.entity.ClienteEntity;
import barbershopclick.entity.ProdutoEntity;
import barbershopclick.entity.UsuarioEntity;
import barbershopclick.vo.AtendimentoProdutoVo;

public interface AtendimentoProdutoRepository extends CrudRepository<AtendimentoProdutoEntity, Integer> {
	
	@Query("select new barbershopclick.vo.AtendimentoProdutoVo(a)"
			  +"  from AtendimentoProdutoEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.cliente.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +" order by a.cliente.nome")
	public List<AtendimentoProdutoVo> findByFilters(@Param("descricao") String descricao);
	
	@Query("select new barbershopclick.vo.AtendimentoProdutoVo(a)"
			  +"  from AtendimentoProdutoEntity a"
			  +" where atendimento.id = :idAtendimento")
	public List<AtendimentoProdutoVo> findByIdAtendimento(@Param("idAtendimento") Integer idAtendimento);
	
	@Query("select a"
			  +"  from AtendimentoProdutoEntity a"
			  +" where atendimento.id = :idAtendimento")
	public List<AtendimentoProdutoEntity> findEntityByIdAtendimento(@Param("idAtendimento") Integer idAtendimento);	
	
	@Query("select a.produto from AtendimentoProdutoEntity a group by 1")
	public List<ProdutoEntity> getProdutosVendidos();

	@Query("select COUNT(a.produto) from AtendimentoProdutoEntity a where a.produto.id = :idProduto")
	public Integer getNumeroVendas(@Param("idProduto") Integer idProduto);
	
	@Query("select SUM(a.produto.valor) from AtendimentoProdutoEntity a where a.produto.id = :idProduto")
	public BigDecimal getValoresVendas(@Param("idProduto") Integer idProduto);
	
	@Query("select a.cliente from AtendimentoProdutoEntity a group by 1")
	public List<ClienteEntity> getAtendimentosClientes();
	
	@Query("select COUNT(a.cliente) from AtendimentoProdutoEntity a where a.cliente.id = :idCliente")
	public Integer getNumeroAtendimentosClientes(@Param("idCliente") Integer idCliente);
	
	@Query("select SUM(a.produto.valor) from AtendimentoProdutoEntity a where a.cliente.id = :idCliente")
	public BigDecimal getValoresAtendimentosClientes(@Param("idCliente") Integer idCliente);
	
	@Query("select a.atendimento.usuarioAtendimento from AtendimentoProdutoEntity a group by 1")
	public List<UsuarioEntity> getAtendimentosFuncionarios();
	
	@Query("select COUNT(a.atendimento.usuarioAtendimento) from AtendimentoProdutoEntity a where a.atendimento.usuarioAtendimento.id = :idUsuario")
	public Integer getNumeroAtendimentosFuncionarios(@Param("idUsuario") Integer idUsuario);
	
	@Query("select SUM(a.produto.valor) from AtendimentoProdutoEntity a where a.atendimento.usuarioAtendimento.id = :idUsuario")
	public BigDecimal getValoresAtendimentosFuncionarios(@Param("idUsuario") Integer idUsuario);
}
