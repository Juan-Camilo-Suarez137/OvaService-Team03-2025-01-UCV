package co.edu.uceva.ovaservice.controllers;


import co.edu.uceva.ovaservice.models.entities.Ova;
import co.edu.uceva.ovaservice.models.services.IOvaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/OvaService")
public class OvaRestController {

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    private IOvaService ovaService;

    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    @Autowired
    public OvaRestController(IOvaService ovaService) {this.ovaService = ovaService;}

    // Metodo que retorna todos los productos
    @GetMapping("/ova")
    public List<Ova> getOva() {return ovaService.findAll();}

    // Metodo que guarda un producto pasandolo por el cuerpo de la petición
    @PostMapping("/ova")
    public Ova save(@RequestBody Ova ova) {return ovaService.save(ova);}

    // Metodo que elimina un producto pasandolo por el cuerpo de la petición
    @DeleteMapping("/ova")
    public void delete(@RequestBody Ova ova) {ovaService.delete(ova);}

    // Metodo que actualiza un producto pasandolo por el cuerpo de la petición
    @PutMapping("ova")
    public Ova update(@RequestBody Ova ova) {return ovaService.update(ova);}

    // Metodo que retorna un producto por su id pasado por la URL
    @GetMapping("/ova/{id}")
    public Ova findById(@PathVariable("id") long id) {return ovaService.findById(id);}
}
