package com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.paciente.PacienteSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.entity.Paciente;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.repository.PacienteRepository;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class PacienteService implements IPacienteService{

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public List<PacienteSalidaDto> listarPacinetes() {
        List<PacienteSalidaDto> pacientes = pacienteRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();
        LOGGER.info("Listado de todos los pacientes: {}", pacientes);
        return pacientes;
    }

    @Override
    public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {
        Paciente pacGuardado = pacienteRepository.save(dtoEntradaAEntidad(paciente));
        PacienteSalidaDto pacienteSalidaDto = entidadADtoSalida(pacGuardado);
        LOGGER.info("Paciente registrado: {}", pacienteSalidaDto);
        return pacienteSalidaDto;
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;
        if (pacienteBuscado != null) {
            pacienteSalidaDto = entidadADtoSalida(pacienteBuscado);
            LOGGER.info("Paciente encontrado: {}", pacienteSalidaDto);
        } else LOGGER.error("El id no se encuentra en la base de datos");
        return pacienteSalidaDto;
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if (buscarPacientePorId(id) != null) {
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el paciente con id: {}", id);
        } else {
            LOGGER.error("No fue encontrado el paciente con id {}", id);
            throw new ResourceNotFoundException("No fue encontrado el paciente con id " + id);
        }
    }

    @Override
    public PacienteSalidaDto modificarPaciente(PacienteModificacionEntradaDto pacienteModificado) throws ResourceNotFoundException {
        Paciente pacienteRecibido = dtoModificadoAEntidad(pacienteModificado);
        Paciente pacienteAActualizar = pacienteRepository.findById(pacienteModificado.getId()).orElse(null);
        PacienteSalidaDto pacienteSalidaDto = null;
        if (pacienteAActualizar != null) {
            pacienteAActualizar = pacienteRecibido;
            pacienteRepository.save(pacienteAActualizar);
            pacienteSalidaDto = entidadADtoSalida(pacienteAActualizar);
            LOGGER.warn("Paciente actualizado: {}", pacienteSalidaDto);
        } else {
            LOGGER.error("No fue posible actualizar los datos porque el paciente no esta registrado");
            throw new ResourceNotFoundException("No fue posible actualizar los datos porque el paciente no esta registrado");
        }


        return pacienteSalidaDto;
    }

    public PacienteSalidaDto entidadADtoSalida(Paciente paciente){
        return modelMapper.map(paciente, PacienteSalidaDto.class);
    }

    private void configureMapping() {
        modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilio, Paciente::setDomicilio));
        modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class).addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto:: setDomicilio));
        modelMapper.typeMap(PacienteModificacionEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteModificacionEntradaDto::getDomicilio, Paciente::setDomicilio));
    }

    public Paciente dtoModificadoAEntidad(PacienteModificacionEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }

    public Paciente dtoEntradaAEntidad(PacienteEntradaDto pacienteEntradaDto) {
        return modelMapper.map(pacienteEntradaDto, Paciente.class);
    }
}
