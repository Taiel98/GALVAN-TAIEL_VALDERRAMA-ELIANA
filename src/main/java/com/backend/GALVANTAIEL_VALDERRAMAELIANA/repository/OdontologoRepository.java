package com.backend.GALVANTAIEL_VALDERRAMAELIANA.repository;

import com.backend.GALVANTAIEL_VALDERRAMAELIANA.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long>{
}
