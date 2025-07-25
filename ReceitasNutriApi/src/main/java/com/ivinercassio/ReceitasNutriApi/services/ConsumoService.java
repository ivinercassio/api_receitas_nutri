package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ConsumoDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Consumo;
import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
import com.ivinercassio.ReceitasNutriApi.repositories.ConsumoRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.PacienteReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ConsumoService {
    
    @Autowired
    ConsumoRepository consumoRepository;

    @Autowired
    PacienteReceitaRepository pacienteReceitaRepository;

    public List<ConsumoDTO> findAll() {
        List<Consumo> list = consumoRepository.findAll();
        return list.stream().map(ConsumoDTO::new).toList();
    }

    public ConsumoDTO findById (Long id){
        Consumo consumo = consumoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado com ID: " + id));
        return new ConsumoDTO(consumo);
    }

    public ConsumoDTO insert(ConsumoDTO consumoDTO) {
        PacienteReceita pacienteR = pacienteReceitaRepository.findById(consumoDTO.getIdPacienteReceita()).orElseThrow(() -> new EntityNotFoundException("Paciente-Receita não encontrado com ID: " + consumoDTO.getIdPacienteReceita()));

        Consumo novo = new Consumo();
        novo.setId(consumoDTO.getId());
        novo.setPacienteReceita(pacienteR);
        novo.setDataHora(consumoDTO.getDataHora());

        novo = consumoRepository.save(novo);
        return new ConsumoDTO(novo);
    }

    public ConsumoDTO update(ConsumoDTO consumoDTO, Long id) {   
        Consumo registro = consumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado com ID: " + id));

        PacienteReceita pacienteR = pacienteReceitaRepository.findById(consumoDTO.getIdPacienteReceita()).orElseThrow(() -> new EntityNotFoundException("Paciente-Receita não encontrado com ID: " + consumoDTO.getIdPacienteReceita()));

        registro.setPacienteReceita(pacienteR);
        registro.setDataHora(consumoDTO.getDataHora());

        Consumo salva = consumoRepository.save(registro);
        return new ConsumoDTO(salva);
    }

    @Transactional
    public void delete(Long id) {
        if (!consumoRepository.existsById(id))
            throw new IllegalArgumentException("Consumo não encontrado com ID: " + id);
        // deletar os dados do consumo
        consumoRepository.deleteById(id);;
    }

}
