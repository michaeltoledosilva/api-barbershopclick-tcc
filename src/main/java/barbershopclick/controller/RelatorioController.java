package barbershopclick.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import barbershopclick.service.RelatorioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@Autowired
	private RelatorioService service;

	@GetMapping("/findProdutosVendidos")
	public ResponseEntity<Object> findProdutosVendidos(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findProdutosVendidos(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findValoresProdutos")
	public ResponseEntity<Object> findValoresProdutos(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findValoresProdutos(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findAtendimentosClientes")
	public ResponseEntity<Object> findAtendimentosClientes(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findAtendimentosClientes(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findValoresClientes")
	public ResponseEntity<Object> findValoresClientes(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findValoresClientes(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findAtendimentosFuncionarios")
	public ResponseEntity<Object> findAtendimentosFuncionarios(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findAtendimentosFuncionarios(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/findValoresFuncionarios")
	public ResponseEntity<Object> findValoresFuncionarios(HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.findValoresFuncionarios(), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
