package com.conteiner.repository;

import com.conteiner.entity.Conteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteinerRepository extends JpaRepository<Conteiner, String> {
}
