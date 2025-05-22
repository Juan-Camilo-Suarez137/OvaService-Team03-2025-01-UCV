package co.edu.uceva.ovaservice.domain.services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.Map;
@FeignClient(name = "cursoservice")
public interface ICursoCliente {
    @GetMapping("api/v1/curso-service/cursos")
    ResponseEntity<Map<String, Object>> getCursos() ;
}