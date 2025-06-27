@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    private final ReceitaService service;

    // Constructor injection...

    @PostMapping
    public ResponseEntity<Receita> criarReceita(
        @RequestBody Receita receita,
        @RequestParam List<ReceitaIngredienteDTO> ingredientes) {
        
        Receita novaReceita = service.criarReceita(receita, ingredientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(
        @PathVariable Long id,
        @RequestBody Receita receita,
        @RequestParam List<ReceitaIngredienteDTO> ingredientes) {
        
        return ResponseEntity.ok(service.atualizarReceita(id, receita, ingredientes));
    }

    @GetMapping("/nutricionista/{nutricionistaId}")
    public ResponseEntity<List<Receita>> listarPorNutricionista(
        @PathVariable Long nutricionistaId) {
        
        return ResponseEntity.ok(service.buscarReceitasPorNutricionista(nutricionistaId));
    }
}
