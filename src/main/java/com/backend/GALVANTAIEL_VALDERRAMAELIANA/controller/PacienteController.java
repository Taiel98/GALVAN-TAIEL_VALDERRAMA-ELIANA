package com.backend.GALVANTAIEL_VALDERRAMAELIANA.controller;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.paciente.PacienteSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final IPacienteService pacienteService;
    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //POST
    @PostMapping("registrar")
    public ResponseEntity<PacienteSalidaDto> registrarPaciente(@Valid @RequestBody PacienteEntradaDto paciente) {
        return new ResponseEntity<>(pacienteService.registrarPaciente(paciente), HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("actualizar")
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@Valid @RequestBody PacienteModificacionEntradaDto paciente) throws ResourceNotFoundException, ResourceNotFoundException {
        return new ResponseEntity<>(pacienteService.modificarPaciente(paciente), HttpStatus.OK);
    }

    //GET
    @GetMapping("{id}")
    public ResponseEntity<PacienteSalidaDto> obtenerPacientePorId(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes() {
        return new ResponseEntity<>(pacienteService.listarPacinetes(), HttpStatus.OK);
    }

    //DELETE
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
