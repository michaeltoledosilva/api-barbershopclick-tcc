package barbershopclick.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.AtendimentoEntity;
import barbershopclick.vo.AtendimentoVo;

public interface AtendimentoRepository extends CrudRepository<AtendimentoEntity, Integer> {
	
	@Query("select new barbershopclick.vo.AtendimentoVo(a)"
			  +"  from AtendimentoEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.cliente.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +" order by a.dataAtendimento")
	public List<AtendimentoVo> findByFilters(@Param("descricao") String descricao);

	
	@Query("select new barbershopclick.vo.AtendimentoVo(a)"
			  +"  from AtendimentoEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.cliente.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  + "  and a.dataAtendimento between :dataInicial and :dataFinal"
			  +" order by a.dataAtendimento")
	public List<AtendimentoVo> findByFilters(@Param("descricao") String descricao, @Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

}