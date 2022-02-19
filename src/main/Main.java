package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {
	
	
	public static ArrayList<String> dominio = new ArrayList<>();
	public static ArrayList<String> codominio = new ArrayList<>();
	public static HashMap<String , String> pares=new HashMap<String , String >();
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		start();
		
		
	}
	
	public static void start() {
		dominio.clear();
		codominio.clear();
		pares.clear();
		String read="";
		System.out.println("Digita los valores del dominio (// para finalizar)");
		read = sc.next();
		while(!read.equals("//")) {
			dominio.add(read);
			read = sc.next();
		}
		
		System.out.println("Digita los valores del codominio (// para finalizar)");
		read = sc.next();
		while(!read.equals("//")) {
			codominio.add(read);
			read = sc.next();
		}
		
		if (!dominio.isEmpty()&&!codominio.isEmpty()) {
			 
			String x = "";
			String y = "";
			String fin = "";
			System.out.println("Digtaremos los valores de los pares ordenados");
			do {
				System.out.println("Digita el valor de x ");
				x = sc.next();
				System.out.println("Digita el valor de y ");
				y = sc.next();
				pares.put(x,y);
				System.out.println("// para finalizar");
				fin = sc.next();
			}while(!fin.equals("//"));
			
			menu();
			
		}
		
	}
	
	public static void menu() {
		if(isFunction(dominio,codominio,pares)) {
			System.out.println("Escribe a continuacion una opcion para decir si la funcion es:");
			System.out.println("1) Inyectiva");
			System.out.println("2) Sobreyectiva");
			System.out.println("3) Biyectiva");
			int op = sc.nextInt();
			while(op>3 || op<1) {
				System.out.println("No es una opcion valida");
				op = sc.nextInt();
			}
			section(op);
		}else {
			System.out.println("Digita 'y' si deseas volver a intentarlo");
			String ans = sc.next();
			if(ans.equalsIgnoreCase("y")) {
				start();
			}
		}
	}
	
	public static boolean isFunction(ArrayList<String> dominio,ArrayList<String> codominio,HashMap<String , String> pares) {
		Boolean bool = false;
		Boolean finish = false;
		while(!finish) {
			if(!dominio.isEmpty()) {
				for (int i = 0;i<dominio.size() && !finish; i++){ 
					for (int j = 0;j<dominio.size() && !finish; j++) {
						if(i!=j) {
							if(dominio.get(i).equals(dominio.get(j))) {
								finish = true;
								System.out.println("No es funcion porque el valor "+dominio.get(i)+" esta repetido en el dominio");
							}
						}
						
						
					}
				}
				if(!codominio.isEmpty() && !finish) {
					for (int i = 0;i<codominio.size() && !finish; i++){ 
						for (int j = 0;j<codominio.size() && !finish; j++) {
							if(i!=j) {
								if(codominio.get(i).equals(codominio.get(j))) {
									finish = true;
									System.out.println("No es funcion porque el valor "+codominio.get(i)+" esta repetido en el codominio");
								}
							}
							
							
						}
					}
					for (int i = 0; i<dominio.size() && !finish;i++) {
						
						if (!pares.containsKey(dominio.get(i))) {
							System.out.println("No es funcion porque hay valores del dominio sin imagen");
							finish = true;
						}
						
					}
					
					if (pares.values().containsAll(codominio) && !finish) {
						bool = true;
						finish = true;
						
					}else {
						Boolean ex;
						for (int i = 0; i<pares.size() && !finish;i++) {
							ex = false;
							for (int j = 0; j<codominio.size() && !ex;j++) {
								
								if(pares.values().toArray()[i].equals(codominio.get(j))){
									ex = true;
								}
								
							}
							if (!ex) {
								System.out.println("No es funcion porque hay valores del rango que no pertenecen al codominio");
								finish = true;
							}
							
						}
						if(!finish) {
							bool = true;
							finish = true;
						}
					}
					
					
				}else {
					if(!finish) {
						System.out.println("No es funcion porque el codominio es vacio");
						finish = true;
					}
				}
			}else {
				System.out.println("No es funcion porque el dominio es vacio");
				finish = true;
			}
		}
		return bool;
	}
	
	public static void section(int op) {
		switch(op){
			case 1:
				inyectiva();
				menu();
				break;
			case 2:
				sobreyectiva();
				menu();
				break;
			case 3:
				biyectiva();
				menu();
				break;
		}
	}
	
	public static Boolean inyectiva() {
		Boolean b = false;
		if(codominio.size()==pares.size()) {
			System.out.println("La funcion es inyectiva!!");
			b=true;
		}else{
			System.out.println("La funcion no es inyectiva");
			System.out.println("digita r para volver a intentar");
			String ans = sc.next();
			if(ans.equalsIgnoreCase(ans)) {
				menu();
			}
		}
		return b;
	}
	
	public static Boolean sobreyectiva() {
		
		Boolean sobre = true;
		
		for(int i = 0; i<pares.size();i++) { 
			for(int j = 0; j<pares.size();j++) {
				if(j!=i) {
					if(pares.values().toArray()[j].equals(pares.values().toArray()[i])) {
						sobre = false;
					}
				}
			}
		}
		
		if(sobre) {
			System.out.println("La funcion es sobreyectiva!!");
		}else{
			System.out.println("La funcion no es sobreyectiva");
			System.out.println("digita r para volver a intentar");
			String ans = sc.next();
			if(ans.equalsIgnoreCase(ans)) {
				menu();
			}
		}
		return sobre;
	}
	
	public static void biyectiva() {
		if (sobreyectiva() && inyectiva()) {
			System.out.println("La funcion es biyectiva!!");
		}else{
			System.out.println("La funcion no es biyectiva");
			System.out.println("digita r para volver a intentar");
			String ans = sc.next();
			if(ans.equalsIgnoreCase(ans)) {
				menu();
			}
		}
	}
}
