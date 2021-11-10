package barbershopclick.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import barbershopclick.dto.AtendimentoDto;
import barbershopclick.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController {

	@Autowired
	private AtendimentoService service;

	@GetMapping("/findByFilters")
	public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) String data, HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findByFilters(descricao, data), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody AtendimentoDto dto) {
		try {
			return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody AtendimentoDto dto) {
		try {
			this.service.save(dto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {

		try {
			this.service.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/generateCsv")
	public ResponseEntity<Object> generateCsv(@RequestParam(required = false) String descricao,
			HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.generateCsv(descricao), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}