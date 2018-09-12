package herramientas;

import java.text.ParseException;
import java.util.ArrayList;

public class Pruebas {
	public static void main(String args[]) throws InterruptedException, ParseException {
		Pruebas prueb = new Pruebas();
		if (prueb.buscarCodeError("ana") == true) {
			System.out.println("Te encontre");
		}
		ArrayList<String> aListReplace = new ArrayList<String>();
		String a = "175";
		String b = "176,177";
		prueb.agregarArreglo(aListReplace, a, b);
		for (int i = 0; i <= aListReplace.size() - 1; i++) {
			System.out.println(aListReplace.get(i));
		}
		System.out.println(aListReplace.size());
		aListReplace.clear();
		System.out.println(aListReplace.size());
		a = "175";
		b = "176";
		prueb.agregarArreglo(aListReplace, a, b);
		for (int i = 0; i <= aListReplace.size() - 1; i++) {
			System.out.println(aListReplace.get(i));
		}

		String pruebita ="";
		if (!pruebita.trim().equals("")) {
			System.out.println("NO ta vacio");
		}else {
			System.out.println("Ta vacio");
		}
	}
	

	public boolean buscarCodeError(String datoBuscado) {
		String[] codigosErrores = { "johan", "joel", "ana", "fiorella" };

		boolean bEncontrado = false;
		for (int n = 0; n < codigosErrores.length; n++) {
			if (datoBuscado.equals(codigosErrores[n])) {
				bEncontrado = true;
				break;
			} else {
				bEncontrado = false;
			}
		}
		return bEncontrado;
	}

	public void agregarArreglo(ArrayList<String> lista, String a, String b) {
		String[] separado = b.trim().split(",");
		if (separado.length == 1) {
			for (int i = 0; i < separado.length; i++) {
				lista.add(separado[i]);
			}
		} else {
			for (int i = 0; i < separado.length; i++) {
				lista.add(separado[i]);
				lista.add(a);
			}
		}
	}
}
