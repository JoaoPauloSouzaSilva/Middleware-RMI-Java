import java.rmi.registry.*;
import java.util.Scanner;

public class HelloClient {
	public static void main(String[] args) {
		try(Scanner scanner = new Scanner(System.in)) {
			  // Obtendo o registro RMI no servidor
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Procurando o objeto remoto
            Hello stub = (Hello) registry.lookup("Servidor");

            // Leitura dos valores a, b e c pelo cliente
            System.out.println("Digite o valor de a: ");
            double a = scanner.nextDouble();

            System.out.println("Digite o valor de b: ");
            double b = scanner.nextDouble();

            System.out.println("Digite o valor de c: ");
            double c = scanner.nextDouble();

            // Invocando o método remoto para calcular o delta
            System.out.println("Invocando metodo do servidor para calcular delta...");
            double delta = stub.calculaDelta(a, b, c);
            System.out.println("O valor de delta retornado pelo servidor é: " + delta);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}