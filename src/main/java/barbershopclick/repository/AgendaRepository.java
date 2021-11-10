package barbershopclick.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import barbershopclick.entity.AgendaEntity;
import barbershopclick.vo.AgendaVo;

public interface AgendaRepository extends CrudRepository<AgendaEntity, Integer> {

	@Query("select new barbershopclick.vo.AgendaVo(a)"
			  +"  from AgendaEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.cliente.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  +" order by a.dataAgenda")
	public List<AgendaVo> findByFilters(@Param("descricao") String descricao);
	
	@Query("select new barbershopclick.vo.AgendaVo(a)"
			  +"  from AgendaEntity a"
			  +" where (CASE WHEN :descricao IS NULL OR a.cliente.nome LIKE CONCAT('%', :descricao, '%') THEN 1 ELSE 0 END) = 1"
			  + "  and a.dataAgenda between :dataInicial and :dataFinal"
			  +" order by a.dataAgenda")
	public List<AgendaVo> findByFilters(@Param("descricao") String descricao, @Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);

	@Query("select a"
			  +"  from AgendaEntity a"
			  +" where a.dataAgenda = :dataAgenda"
			  + "  and a.usuarioAgendado.id = :usuarioAgendado")
	public AgendaEntity findByDataUsuario(@Param("dataAgenda") Date dataAgenda, @Param("usuarioAgendado") Integer usuarioAgendado);

	@Query("select a"
			  +"  from AgendaEntity a"
			  +" where a.dataAgenda = :dataAgenda"
			  + "  and a.cliente.id = :cliente")
	public AgendaEntity findByDataCliente(@Param("dataAgenda") Date dataAgenda, @Param("cliente") Integer cliente);

}
