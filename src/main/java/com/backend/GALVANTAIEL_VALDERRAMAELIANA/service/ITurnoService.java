package com.backend.GALVANTAIEL_VALDERRAMAELIANA.service;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.TurnoModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.turno.TurnoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.turno.TurnoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.BadRequestException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;

import java.util.List;
public interface ITurnoService {

    TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoModificacionEntradaDto turnoModificacionEntradaDto) throws ResourceNotFoundException;

}
