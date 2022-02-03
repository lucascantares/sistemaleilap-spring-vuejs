package br.edu.ifg.sistemaleilao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifg.sistemaleilao.model.Lance;
import br.edu.ifg.sistemaleilao.repositoy.LanceRepository;

@Service
public class LanceService {

	@Autowired
	LanceRepository repository;

	public List<Lance> findAll() {
		return repository.findAll();
	}

	public List<Lance> findByLeilao(Long id) {
		return repository.findByLeilao_Id(id);
	}

	public ResponseEntity<Lance> check(Lance lance) {

		List<Lance> listaLances = repository.findAll();
		
		double valor = 0;
		if(lance.getValor() < lance.getLeilao().getLanceMinimo()) {
			System.out.println("Menor que o valor inicial");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		for (Lance lance2 : listaLances) {
			if(lance2.getLeilao().getId() == lance.getLeilao().getId()) {
				if(lance.getValor() < lance2.getLeilao().getLanceMinimo()) {
					valor = lance2.getLeilao().getLanceMinimo();
					System.out.println("Valor do lance Minimo: " + valor);
				}
				System.out.println("Valor do leilão lance2: "+lance2.getLeilao().getId());
				System.out.println("Valor do leilão lance: "+ lance.getLeilao().getId());
				if (valor <= lance2.getValor()) {
					valor = lance2.getValor();
					System.out.println("Valor correto para inserção: " + valor);
				}
			}
		}

		for (Lance lance2 : listaLances) {
			if (lance2.getLeilao().getId() == lance.getLeilao().getId()) {
				if (lance.getValor() <= valor) {
					System.out.println("último valor: " + valor);
					System.out.println(lance.getValor());
					System.out.println("Menor que o último valor");
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
			}
		}
		
		if(repository.findTopByOrderByIdDesc() == null) {
			System.out.println("Repositório nulo");
			save(lance);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		if(lance.getLeilao().getId() == repository.findTopByOrderByIdDesc().getLeilao().getId() 
				&& lance.getParticipante().getId() == repository.findTopByOrderByIdDesc().getParticipante().getId()) {
			System.out.println("ID IGUAL");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<Lance> listaParcitipanteID = repository.findTop2ByOrderByIdDesc();
		for (Lance lance2 : listaParcitipanteID) {
			if(lance.getParticipante().getId() == repository.findTopByOrderByIdDesc().getParticipante().getId() 
					&& lance2.getLeilao().getId() == lance.getLeilao().getId()) {
				System.out.println("ID IGUAL");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} 
		
		save(lance);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	public Lance save(Lance lance) {
		return repository.save(lance);
	}

	public void delete(Lance lance) {
		repository.delete(lance);
	}

}
