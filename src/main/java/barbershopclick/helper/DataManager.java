package barbershopclick.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.ValidationException;

public class DataManager {
	private static DataManager instance;

	public DataManager() {
	}

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public String formatDate(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

	public String formatDateTime(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}

	public String formatTime(Date data) {
		return new SimpleDateFormat("HH:mm").format(data);
	}

	public String getHora(Date data) {
		return new SimpleDateFormat("HH").format(data);
	}

	public Date parseDateTime(String data, String hora) throws ValidationException {
		try {
			return (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data + " " + hora + ":00");
		} catch (Exception e) {
			throw new ValidationException("Data inválida.");
		}
	}

	public Date getDateTimeInicioDia(String data) throws ParseException {
		return (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data + " 00:00:00");
	}

	public Date getDateTimeFimDia(String data) throws ParseException {
		return (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data + " 23:59:59");
	}

}
