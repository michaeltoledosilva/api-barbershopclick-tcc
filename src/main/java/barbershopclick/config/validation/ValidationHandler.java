package barbershopclick.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.i18n.LocaleContextHolder;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handle(MethodArgumentNotValidException exception) {
		List<String> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			dto.add(this.messageSource.getMessage(e, LocaleContextHolder.getLocale()));
		});

		return dto;
	}

}