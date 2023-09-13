package com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.OdontologoModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.entity.Odontologo;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.repository.OdontologoRepository;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologos = odontologoRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OdontologoSalidaDto.class)).toList();
        return odontologos;
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {
        Odontologo odGuardado = odontologoRepository.save(dtoEntradaAEntidad(odontologo));
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odGuardado, OdontologoSalidaDto.class);
        return odontologoSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;
        if (odontologoBuscado != null) {
            odontologoSalidaDto = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
        }

        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if (buscarOdontologoPorId(id) != null) {
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id " + id);
        }
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionEntradaDto odontologoModificacionEntradaDto) throws ResourceNotFoundException {
        Odontologo odontologoRecibido = modelMapper.map(odontologoModificacionEntradaDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontologoModificacionEntradaDto.getId()).orElse(null);
        OdontologoSalidaDto odontologoSalidaDto = null;
        if (odontologoAActualizar != null) {
            odontologoAActualizar = odontologoRecibido;
            odontologoRepository.save(odontologoAActualizar);
            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoSalidaDto.class);
        } else {
            throw new ResourceNotFoundException("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }
        return odontologoSalidaDto;
    }

    private Odontologo dtoEntradaAEntidad(OdontologoEntradaDto odontologoEntradaDto) {
        return modelMapper.map(odontologoEntradaDto, Odontologo.class);
    }
}
