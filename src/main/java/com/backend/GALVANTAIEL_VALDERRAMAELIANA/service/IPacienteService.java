package com.backend.GALVANTAIEL_VALDERRAMAELIANA.service;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.paciente.PacienteSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    List<PacienteSalidaDto> listarPacinetes();

    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException;
}
