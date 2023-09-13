package com.backend.GALVANTAIEL_VALDERRAMAELIANA.service;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;

import java.util.List;
public interface IOdontologoService {

    List<OdontologoSalidaDto> listarOdontologos();


    OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);

    OdontologoSalidaDto buscarOdontologoPorId(Long id);

    void eliminarOdontologo(Long id) throws ResourceNotFoundException;

    OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException;

}
