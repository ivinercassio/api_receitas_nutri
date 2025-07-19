package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ConsumoDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Consumo;
import com.ivinercassio.ReceitasNutriApi.entities.Paciente;
import com.ivinercassio.ReceitasNutriApi.repositories.ConsumoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ConsumoService {
    
    @Autowired
    ConsumoRepository consumoRepository;

    public List<ConsumoDTO> findAll() {
        List<Consumo> list = consumoRepository.findAll();
        return list.stream().map(ConsumoDTO::new).toList();
    }

    public ConsumoDTO findById (Long id){
        Consumo consumo = consumoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado com ID: " + id));
        return new ConsumoDTO(consumo);
    }

    public ConsumoDTO insert(ConsumoDTO consumoDTO) {
        Paciente paciente = new Paciente();
        paciente.setId(consumoDTO.getPaciente().getId());
        paciente.setNome(consumoDTO.getPaciente().getNome());
        paciente.setEmail(consumoDTO.getPaciente().getEmail());

        Consumo novo = new Consumo();
        novo.setId(consumoDTO.getId());
        novo.setPaciente(paciente);
        novo.setDataHora(consumoDTO.getDataHora());

        novo = consumoRepository.save(novo);
        return new ConsumoDTO(novo);
    }

    public ConsumoDTO update(ConsumoDTO consumoDTO, Long id) {   
        Consumo registro = consumoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Consumo não encontrado com ID: " + id));

        Paciente paciente = new Paciente();
        paciente.setId(consumoDTO.getPaciente().getId());
        paciente.setNome(consumoDTO.getPaciente().getNome());
        paciente.setEmail(consumoDTO.getPaciente().getEmail());

        registro.setPaciente(paciente);
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
