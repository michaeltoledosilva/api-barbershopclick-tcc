package barbershopclick.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.CorteClienteEntity;
import barbershopclick.vo.CorteClienteVo;

public interface CorteClienteRepository extends CrudRepository<CorteClienteEntity, Integer> {
	
	@Query("select new barbershopclick.vo.CorteClienteVo(a)"
			  +"  from CorteClienteEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.descricao LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +" order by a.descricao")
	public List<CorteClienteVo> findByFilters(@Param("descricao") String descricao);
	
	@Query("select new barbershopclick.vo.CorteClienteVo(a)"
			  +"  from CorteClienteEntity a"
			  +" where cliente.id = :idCliente")
	public List<CorteClienteVo> findByIdCliente(@Param("idCliente") Integer idCliente);
	
	@Query("select a"
			  +"  from CorteClienteEntity a"
			  +" where cliente.id = :idCliente")
	public List<CorteClienteEntity> findEntityByIdCliente(@Param("idCliente") Integer idCliente);
}
