@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private final IngredienteService service;

    // Constructor injection...

    @PostMapping
    public ResponseEntity<Ingrediente> criarIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente salvo = service.salvarIngrediente(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Ingrediente>> buscarPorDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(service.buscarPorDescricao(descricao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirIngrediente(@PathVariable Long id) {
        service.excluirIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
