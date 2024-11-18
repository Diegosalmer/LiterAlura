package com.diegosalazar.literAlura.service;

import com.diegosalazar.literAlura.model.Autor;
import com.diegosalazar.literAlura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public List<Autor> buscarAutoresVivosPorAnio(int anio) {
        return autorRepository.findAutoresVivosPorAnio(anio);
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }
}