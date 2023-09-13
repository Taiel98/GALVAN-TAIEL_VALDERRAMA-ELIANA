package com.backend.GALVANTAIEL_VALDERRAMAELIANA.controller;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.turno.TurnoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.turno.TurnoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.BadRequestException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    @Autowired
    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> registrarTurno(@Valid @RequestBody TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        return new ResponseEntity<>(turnoService.registrarTurno(turnoEntradaDto), HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("actualizar")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@Valid @RequestBody TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(turnoService.modificarTurno(turnoModificacionEntradaDto), HttpStatus.OK);
    }

    //GET
    @GetMapping("{id}")
    public ResponseEntity<TurnoSalidaDto> obtenerTurnoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }

    //LIST
    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.OK);
    }
}
