package br.edu.ifg.sistemaleilao.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ifg.sistemaleilao.model.Leilao;
import br.edu.ifg.sistemaleilao.model.StatusSituacao;
import br.edu.ifg.sistemaleilao.service.LeilaoService;

@RestController
@RequestMapping(value = "/leilao")
public class LeilaoController {
	
	@Autowired
	LeilaoService service; 

	@GetMapping("/leiloes")
	public List<Leilao> listaLeiloes(){
		return service.findAll();
	}
	
	@GetMapping("/leiloes/{status}")
	public List<Leilao> listaLeilaoPorStatus(@PathVariable StatusSituacao status){
		return service.findByStatus(status);
	}
	
	@PostMapping("/leilao")
	public Leilao salvarLeilao(@RequestBody @Valid Leilao leilao) {
		return service.save(leilao);
	}
	
	@DeleteMapping("/leilao")
	public void deletaLeilao(@RequestBody Leilao leilao) {
		service.delete(leilao);
	}
	
	@PutMapping("/leilao")
	public Leilao updateLeilao(@RequestBody @Valid Leilao leilao) {
		return service.save(leilao);
	}
}
