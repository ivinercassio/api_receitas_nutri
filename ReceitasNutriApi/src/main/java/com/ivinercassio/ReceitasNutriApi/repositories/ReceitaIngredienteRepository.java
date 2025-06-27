@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByTituloContainingIgnoreCase(String titulo);
    List<Receita> findByUsuarioNutricionista(Long nutricionistaId);
}
