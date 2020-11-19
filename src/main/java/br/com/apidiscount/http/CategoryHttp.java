package br.com.apidiscount.http;

import br.com.apidiscount.http.resource.CategoryResource;
import br.com.apidiscount.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/category", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "API de Categoria de Descontos")
public class CategoryHttp {

    private final CategoryService service;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Salva uma categoria de desconto adicionada manualmente."),
        @ApiResponse(code = 400, message = "Valida se os campos de quantidade mínima, máxima de pedidos e desconto respeitam as regras."),
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CategoryResource request) {
        service.create(request);
        return ResponseEntity.created(URI.create("api/v1/category")).build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista todas as categorias existentes.")
    })
    @GetMapping
    public List<CategoryResource> list() {
        return service.list();
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Deleta a categoria por ID."),
        @ApiResponse(code = 400, message = "Retorna categoria não existente para um ID inválido.")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
