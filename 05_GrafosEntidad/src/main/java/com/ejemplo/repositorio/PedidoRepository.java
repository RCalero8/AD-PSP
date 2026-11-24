package com.ejemplo.repositorio;
import com.ejemplo.modelo.*;
import com.ejemplo.dto.ResumenPedidoDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //1. Aplica a un método derivado por nombre
    // Carga: Pedido -> cliente -> dirección AND Pedido -> lineas -> producto -> categoria
    @EntityGraph(attributePaths = {"cliente.direccion", "lineas.producto.categoria"})
    List<Pedido> findByEstado(String estado);

    @Override
    @EntityGraph(attributePaths = {"cliente", "lineas.producto"})
    Optional<Pedido> findById(Long id);

    @EntityGraph(attributePaths = {"lineas.producto.etiquetas"})
    @Query("SELECT p FROM Pedido p WHERE p.total > :minimo")
    List<Pedido> buscarPedidosGrandesConEtiquetas(@Param("minimo") BigDecimal minimo);

    @EntityGraph(value = "Pedido.completoConDetalles", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Pedido> findCompletoById(Long id);

    @EntityGraph(value = "Pedido.completoConDetalles")
    List<Pedido> findCompletoByClienteId(Long clienteId);

    @EntityGraph(value = "Pedido.completoConDetalles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Pedido> findCompletoLoadById(Long id);

    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :inicio AND :fin AND p.estado IN :estados")
    List<Pedido> buscarPorRangoFechasYEstados(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin,
            @Param("estados") Collection<String> estados
    );

    @Query("SELECT new com.ejemplo.dto.ResumenPedidoDTO(p.id, p.cliente.nombre, p.total) " +
            "FROM Pedido p " +
            "WHERE p.estado = :estado")
    List<ResumenPedidoDTO> obtenerResumenesPorEstado(@Param("estado") String estado);

    @Query("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.estado = :estado")
    List<Pedido> buscarPedidosConClienteCargado(@Param("estado") String estado);

    @Query("SELECT p FROM Pedido p WHERE p.estado IN :estados")
    List<Pedido> buscarPorEstados(@Param("estados") Collection<String> estados);

    @Query("SELECT p FROM Pedido p WHERE p.fecha BETWEEN :desde AND :hasta AND p.total >= :totalMinimo")
    List<Pedido> buscarPedidosEnRangoYTotal(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta,
            @Param("totalMinimo") BigDecimal totalMinimo
    );

    @Query("SELECT new com.ejemplo.dto.ResumenPedidoDTO(p.id, p.cliente.nombre, p.total, p.fecha) " +
            "FROM Pedido p " +
            "WHERE p.estado = :estado")
    List<ResumenPedidoDTO> obtenerResumenesConFechaPorEstado(@Param("estado") String estado);

    //Consulta Derivada
    @EntityGraph(attributePaths = {
            "cliente", //Navega de pedido a cliente
            "cliente.direccion", //Navega de cliente a direccion
            "lineas", //Navega de pedido a lineas
            "lineas.producto", //Navega de lineas a producto
            "lineas.producto.categoria" //Navega de productos a categoria
    })
    List<Pedido> findByClienteActivoTrueAndClienteDireccionCiudadIgnoreCaseAndFechaBetweenAndEstadoInOrderByFechaAscIdAsc(
            String ciudad,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Collection<String> estados // O Collection<String> según tu modelo
    );

    //Solución final:
    @EntityGraph(attributePaths = {
            "cliente.direccion",
            "lineas.producto.categoria"
    })
    List<Pedido> findByCliente_ActivoTrueAndCliente_Direccion_CiudadIgnoreCaseAndFechaBetweenAndEstadoInOrderByFechaAscIdAsc(
            String ciudad,
            LocalDateTime fechaInicio,
            LocalDateTime fechaFin,
            Collection<String> estados // O Collection<String> según tu modelo
    );
    /*1- Cliente_ActivoTrue -> Navega de Pedido a cliente.activo y comprueba si es true
    * 2- Cliente_Direccion_CiudadIgnoreCase -> Navega de cliente a dirección y de dirección a ciudad.
    *  Y aplica modificador para ignorar mayúsculas y minúsculas
    * 3- EstadoBetween -> La palabra clave Between incluye ambos extremos.
    * 4- EstadoIn -> La palabra clave In comprueba si el atributo pertenece a la lista enviada.
    * 5- OrderByFechaAscIdAsc -> El modificador ordena primero por fecha ascendente y desempata por id ascendente */
}
