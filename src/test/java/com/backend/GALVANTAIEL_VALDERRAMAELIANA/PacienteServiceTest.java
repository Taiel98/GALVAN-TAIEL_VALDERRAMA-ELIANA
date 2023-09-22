package com.backend.GALVANTAIEL_VALDERRAMAELIANA;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.modificacion.PacienteModificacionEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.paciente.PacienteSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl.PacienteService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaInsertarUnPacienteDeNombreJuanConId1(){
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Pedro", "Sanchez", 111111, LocalDate.of(2023, 12, 9), new DomicilioEntradaDto("Balcarce", 78, "Monserrat", "CABA"));

        PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);

        assertEquals("Pedro", pacienteSalidaDto.getNombre());
        assertEquals(1, pacienteSalidaDto.getId());
    }

    @Test
    @Order(2)
    void deberiaRetornarseUnaListaNoVaciaDePacientes(){
        assertTrue(pacienteService.listarPacinetes().size() > 0);
    }

    @Test
    void alIntentarActualizarElPacienteId2_deberiaLanzarseUnaResourceNotFoundException(){
        PacienteModificacionEntradaDto pacienteModificacionEntradaDto = new PacienteModificacionEntradaDto();
        pacienteModificacionEntradaDto.setId(2L);
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.modificarPaciente(pacienteModificacionEntradaDto));
    }

    @Test
    @Order(3)
    void alIntentarEliminarUnPacienteYaEliminado_deberiaLanzarseUnResourceNotFoundException(){
        try{
            pacienteService.eliminarPaciente(1L);
        } catch (Exception e){
            e.printStackTrace();
        }
        assertThrows(ResourceNotFoundException.class, () -> pacienteService.eliminarPaciente(1L));
    }

}