package com.backend.GALVANTAIEL_VALDERRAMAELIANA.repository;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
}
