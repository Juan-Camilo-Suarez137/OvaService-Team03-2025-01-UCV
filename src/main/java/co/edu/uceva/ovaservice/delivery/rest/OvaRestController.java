package co.edu.uceva.ovaservice.delivery.rest;


import co.edu.uceva.ovaservice.domain.exception.NoHayOvasException;
import co.edu.uceva.ovaservice.domain.exception.PaginaSinOvasException;
import co.edu.uceva.ovaservice.domain.exception.OvaNoEncontradoException;
import co.edu.uceva.ovaservice.domain.exception.ValidationException;
import co.edu.uceva.ovaservice.domain.model.Ova;
import co.edu.uceva.ovaservice.domain.services.IOvaService;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/OvaService")
public class OvaRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final IOvaService ovaService;

    // Constantes para los mensajes de respuesta
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "ova";
    private static final String OVAS = "ovas";


    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public OvaRestController(IOvaService ovaService) {this.ovaService = ovaService;}

    @GetMapping("/ova")
    public ResponseEntity<Map<String, Object>> getOvas(){
        List<Ova> ovas = ovaService.findAll();

        if (ovas.isEmpty()) {
            throw new NoHayOvasException();
        }
        Map<String, Object> response = new HashMap<>();
        response.put(OVAS , ovas);
        return ResponseEntity.ok(response);
    }




// Metodo que retorna todos los productos paginados
@GetMapping("/ova/page/{page}")
public ResponseEntity<Object> index(@PathVariable Integer page) {
    Pageable pageable = PageRequest.of(page, 4);
    Page<Ova> ovas = ovaService.findAll(pageable);
    if (ovas.isEmpty()) {
        throw new PaginaSinOvasException(page);
    }
    return ResponseEntity.ok(ovas);
}

@PostMapping("/ovas")
public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody Ova ova, BindingResult result) {
    if (result.hasErrors()) {
        throw new ValidationException(result);
    }
    Map<String, Object> response = new HashMap<>();
    Ova nuevoOva = ovaService.save(ova);
    response.put(MENSAJE, "El ova ha sido creado con exito!" );
    response.put(OVA, nuevoOva);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


@DeleteMapping("/ovas")
public ResponseEntity<Map<String, Object>> delete(@RequestBody Ova ova) {
   ovaService.findById(ova.getId())
      .orElseThrow(() -> new OvaNoEncontradoException(ova.getId()));
    ovaService.delete(ova);
    Map<String, Object> response = new HashMap<>();
    response.put(MENSAJE, "El ova ha sido eliminado con exito!" );
    response.put(OVA, null);
    return ResponseEntity.ok(response);
}

@PutMapping("/ovas")
public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Ova ova, BindingResult result) {
    if (result.hasErrors()) {
        throw new ValidationException(result);
    }
    ovaService.findById(ova.getId())
            .orElseThrow(() -> new OvaNoEncontradoException(ova.getId()));
    Map<String, Object> response = new HashMap<>();
    Ova ovaActualizado = ovaService.update(ova);
    response.put(MENSAJE, "El ova ha sido actualizado con exito!" );
    response.put(OVA, ovaActualizado);
    return ResponseEntity.ok(response);
}

@GetMapping("/ovas/{id}")
public ResponseEntity<Map<String, Object>> findById(@PathVariable long id) {
        Ova ova = ovaService.findById(id)
                .orElseThrow(() -> new OvaNoEncontradoException(id));
        Map<String, Object> response = new HashMap<>();
        response.put(MENSAJE, "El ova ha sido encontrado con exito!" );
        response.put(OVA, ova);
        return ResponseEntity.ok(response);

    }
}



