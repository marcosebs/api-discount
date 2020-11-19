package br.com.apidiscount.http;

import br.com.apidiscount.http.request.UserDiscountRequest;
import br.com.apidiscount.http.resource.UserResource;
import br.com.apidiscount.http.response.UserDiscountResponse;
import br.com.apidiscount.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "API de Usuários")
public class UserHttp {

    private final UserService service;

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Calcula o valor final do produto para o usuário e incrementa 1 ao número de pedidos efetuados."),
    })
    @PostMapping("calculate")
    public UserDiscountResponse calculate(@RequestBody UserDiscountRequest request) {
        return service.calculate(request);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Adiciona um novo usuário à base de dados."),
    })
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserResource request) {
        UserResource resource = service.create(request);
        return ResponseEntity.created(URI.create("api/v1/user/" + resource.getId())).build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Edita um usuário na base de dados."),
        @ApiResponse(code = 400, message = "Retorna Usuário não existente para um ID inválido."),
        @ApiResponse(code = 400, message = "Retorna inválido para ordersCount nulo."),
    })
    @PutMapping
    public UserResource edit(@RequestBody UserResource request) {
        return service.edit(request);
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Exclui um usuário na base de dados."),
        @ApiResponse(code = 400, message = "Retorna Usuário não existente para um ID inválido."),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um usuário da base de dados por ID."),
        @ApiResponse(code = 400, message = "Retorna Usuário não existente para um ID inválido."),
    })
    @GetMapping("{id}")
    public UserResource find(@PathVariable Long id) {
        return service.find(id);
    }

}
