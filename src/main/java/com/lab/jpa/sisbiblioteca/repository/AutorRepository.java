package com.lab.jpa.sisbiblioteca.repository;
import com.lab.jpa.sisbiblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
 List<Autor> findByNomeContainingIgnoreCase(String nome);
}
