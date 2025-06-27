@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {
    void deleteByReceitaId(Long receitaId);
}
