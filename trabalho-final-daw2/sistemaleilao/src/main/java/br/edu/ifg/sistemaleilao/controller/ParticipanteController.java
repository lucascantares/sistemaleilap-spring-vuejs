package br.edu.ifg.sistemaleilao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifg.sistemaleilao.model.Participante;
import br.edu.ifg.sistemaleilao.service.ParticipanteService;

@RestController
@RequestMapping("/leilao")
public class ParticipanteController {
	
	@Autowired
	ParticipanteService service;

	@GetMapping("/participantes")
	public List<Participante> listaParticipantes(){
		return service.findAll();
	}
	
	@PostMapping("/participante")
	public Participante salvarParticipante(@RequestBody @Valid Participante participante) {
		return service.save(participante);
	}
	
	@DeleteMapping("/participante")
	public void deletaParticipante(@RequestBody Participante participante) {
		service.delete(participante);
	}
	
	@PutMapping("/participante")
	public Participante updateParticipante(@RequestBody @Valid Participante participante) {
		return service.save(participante);
	}
}
