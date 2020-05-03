package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Funcionario;

public class ExercicioFixacao {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter full file path: ");
		String caminho = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(caminho))){
			List<Funcionario> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Funcionario(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salario = sc.nextDouble();
						
			List<String> email = list.stream()
					.filter(func -> func.getSalario() > salario)
					.map(func -> func.getEmail())
					.sorted()
					.collect(Collectors.toList());

			System.out.println("Email of people whose salary is more than " + String.format("%.2f", salario) + ": ");
			email.forEach(System.out::println);
			
			double soma = list.stream()
					.filter(func -> func.getNome().charAt(0) == 'M')
					.map(func -> func.getSalario())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", soma));
					
		}  catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}		
		
		sc.close();
	}
}
