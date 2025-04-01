package co.edu.uceva.ovaservice.controllers;


import co.edu.uceva.ovaservice.models.entities.Ova;
import co.edu.uceva.ovaservice.models.services.IOvaService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/OvaService")
public class OvaRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final IOvaService ovaService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String OVA = "ova";
    private static final String OVAS = "ovas";


    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public OvaRestController(IOvaService ovaService) {

        this.ovaService = ovaService;
    }

    @GetMapping("/ova")
    public ResponseEntity<Map<String, Object>> getOvas(){
        Map<String, Object> response = new HashMap<>();

        try {
            List<Ova> ovas = ovaService.findAll();

            if (ovas.isEmpty()) {
                response.put(MENSAJE, "No hay ovas en la base de datos.");
                response.put(OVAS, ovas);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put(OVAS, ovas);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    // Metodo que retorna todos los productos paginados
    @GetMapping("/ova/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);
        try {
            Page<Ova> ovas = ovaService.findAll(pageable);

            if (ovas.isEmpty()) {
                response.put(MENSAJE, "No hay ovas en la pagina solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(ovas);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la pagina de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e){
            response.put(MENSAJE, "Numero de paginas invalida.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/ovas")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Ova ova) {
        Map<String, Object> response = new HashMap<>();

        try {
            Ova nuevoOva = ovaService.save(ova);

            response.put(MENSAJE, "El Nuevo ova ha sido creado con exito.");
            response.put(OVAS, nuevoOva);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar el ova en la base de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/ovas")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Ova ova) {
        Map<String, Object> response = new HashMap<>();
        try {
            Ova ovaExistente = ovaService.findById(ova.getId());
            if (ovaExistente == null) {
               response.put(MENSAJE, "El ova ID:" + ova.getId() + " no existe en la base de datos.");
               return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            ovaService.delete(ova);
            response.put(MENSAJE, "El ova ha sido eliminado con exito.");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar el ova de la base de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/ovas")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Ova ova) {
        Map<String, Object> response = new HashMap<>();

        try {
            if (ovaService.findById(ova.getId()) == null) {
                response.put(MENSAJE, "Error: No se pudo editar, el producto ID: " + ova.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            Ova ovaActualizado = ovaService.save(ova);

            response.put(MENSAJE, "El ova ha sido actualizado con exito.");
            response.put(OVA, ovaActualizado);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al actualizar el ova de la base de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/ovas/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Ova ova = ovaService.findById(id);
            if (ova == null) {
                response.put(MENSAJE,"El ova ID:" + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            response.put(MENSAJE, "El ova ha sido actualizado con exito!.");
            response.put(OVA, ova);
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE,"Error al consultar el ova de la base de datos.");
            response.put(ERROR, e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}



