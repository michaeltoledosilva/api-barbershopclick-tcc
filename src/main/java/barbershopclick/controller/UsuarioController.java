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

import barbershopclick.dto.UsuarioDto;
import barbershopclick.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

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
	public ResponseEntity<Object> salvar(@RequestBody UsuarioDto dto) {
		try {
			return new ResponseEntity<>(this.service.save(dto), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<Object> update(@RequestBody UsuarioDto dto) {
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

	@GetMapping("/loginValidation")
	public ResponseEntity<Object> loginValidation(@RequestParam(required = false) String nome,
			@RequestParam(required = false) String usuario, @RequestParam(required = false) String senha,
			@RequestParam(required = false) String isGoogle, HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.loginValidation(nome, usuario, senha, isGoogle), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
