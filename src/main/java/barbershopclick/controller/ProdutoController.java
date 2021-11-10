package barbershopclick.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import barbershopclick.dto.ProdutoDto;
import barbershopclick.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping("/findByFilters")
	public ResponseEntity<Object> findByFilters(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) Boolean listarInativos, HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findByFilters(descricao, listarInativos), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody ProdutoDto dto) {
		try {
			return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody ProdutoDto dto) {
		try {
			this.service.save(dto);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/disableOrEnableById/{id}")
	public ResponseEntity<Object> disableOrEnableById(@PathVariable("id") Integer id) {
		try {
			return new ResponseEntity<>(this.service.disableOrEnableById(id), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/generateCsv")
	public ResponseEntity<Object> generateCsv(@RequestParam(required = false) String descricao,
			@RequestParam(required = false) Boolean listarInativos, HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.generateCsv(descricao, listarInativos), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
