package barbershopclick.controller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import barbershopclick.service.AtendimentoProdutoService;

@RestController
@RequestMapping("/atendimentoProduto")
public class AtendimentoProdutoController {

	@Autowired
	private AtendimentoProdutoService service;

	@GetMapping("/generateCsv")
	public ResponseEntity<Object> generateCsv(@RequestParam(required = false) Integer idAtendimento,
			HttpServletRequest req) {
		try {
			return new ResponseEntity<>(this.service.generateCsv(idAtendimento), HttpStatus.OK);
		} catch (ValidationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}