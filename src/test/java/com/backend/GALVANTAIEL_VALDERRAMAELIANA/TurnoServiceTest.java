package com.backend.GALVANTAIEL_VALDERRAMAELIANA;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.entrada.turno.TurnoEntradaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.paciente.PacienteSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.dto.salida.turno.TurnoSalidaDto;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.BadRequestException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.exceptions.ResourceNotFoundException;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl.PacienteService;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl.OdontologoService;
import com.backend.GALVANTAIEL_VALDERRAMAELIANA.service.impl.TurnoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static PacienteEntradaDto paciente;
    private static OdontologoEntradaDto odontologo;


    @BeforeAll
    public static void init() {
        paciente = new PacienteEntradaDto("Cosme", "Fulanito", 12345678, LocalDate.of(2023, 9, 22), new DomicilioEntradaDto("Wallaby", 42, "Sidney", "New South Wales"));
        odontologo = new OdontologoEntradaDto("AD-564656546456", "Patrich", "Sherman");

    }

    @Test
    @Order(1)
    void deberiaRegistrarseUnTurnoAsignadoAOdontologoYPacienteExistentes() throws BadRequestException {
        PacienteSalidaDto pacienteResponseDto = pacienteService.registrarPaciente(paciente);
        OdontologoSalidaDto odontologoResponseDto = odontologoService.registrarOdontologo(odontologo);

        TurnoSalidaDto turnoResponseDto = turnoService.registrarTurno(new TurnoEntradaDto(pacienteResponseDto.getId(), odontologoResponseDto.getId(), LocalDateTime.of(LocalDate.of(2023, 10, 01), LocalTime.of(12, 30))));
        Assertions.assertNotNull(turnoResponseDto);
        Assertions.assertNotNull(turnoResponseDto.getId());
        Assertions.assertEquals("Cosme", turnoResponseDto.getPacienteTurnoSalidaDto().getNombre());
    }

    @Test
    @Order(2)
    void cuandoNoExisteElPaciente_noDeberiaInsertarElTurno() {
        OdontologoSalidaDto odontologoResponseDto = odontologoService.registrarOdontologo(odontologo);
        Assertions.assertThrows(Exception.class, () -> turnoService.registrarTurno(new TurnoEntradaDto(2L, odontologoResponseDto.getId(), LocalDateTime.of(LocalDate.of(2023, 07, 01), LocalTime.of(12, 30)))));

    }


    @Test
    @Order(3)
    void deberiaEncontrarTurnoId1() {
        TurnoSalidaDto turnoResponseDto = turnoService.buscarTurnoPorId(1L);
        Assertions.assertNotNull(turnoResponseDto);
        Assertions.assertNotNull(turnoResponseDto.getFechaYHora());

    }

    @Test
    @Order(4)
    void deberiaEliminarTurnoId1() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> turnoService.eliminarTurno(1L));

    }


    @Test
    @Order(5)
    void deberiaInformarUnaListaDeUnElemento() {
        List<TurnoSalidaDto> turnoResponseDtoList = turnoService.listarTurnos();
        Assertions.assertNotEquals(1, turnoResponseDtoList.size());
    }

}