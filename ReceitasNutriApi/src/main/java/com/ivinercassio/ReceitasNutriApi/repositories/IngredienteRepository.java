@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByDescricaoContainingIgnoreCase(String descricao);
}
