import java.rmi.registry.*;  // Importa pacotes necessários para localizar o registro RMI
import java.util.Scanner;  // Importa a classe Scanner para permitir a entrada do usuário

public class HelloClient {
    public static void main(String[] args) {
        // Bloco try-with-resources para garantir que o Scanner seja fechado automaticamente após o uso
        try (Scanner scanner = new Scanner(System.in)) {
            // Obtém o registro RMI que está sendo executado no servidor, no caso "localhost" na porta 1098
            // "localhost" indica que o servidor está na mesma máquina que o cliente
            Registry registry = LocateRegistry.getRegistry("localhost", 1098);

            // Faz a busca no registro RMI pelo objeto remoto associado ao nome "Servidor"
            // O objeto é retornado como um stub (representação do objeto remoto no cliente)
            Hello stub = (Hello) registry.lookup("Servidor");

            // Leitura dos valores a, b e c que compõem a equação de segundo grau
            System.out.println("Digite o valor de a: ");
            double a = scanner.nextDouble();  // Usuário insere o valor de 'a'

            System.out.println("Digite o valor de b: ");
            double b = scanner.nextDouble();  // Usuário insere o valor de 'b'

            System.out.println("Digite o valor de c: ");
            double c = scanner.nextDouble();  // Usuário insere o valor de 'c'

            // Invoca o método remoto "calculaRaizes" no servidor usando os valores fornecidos
            // O método é executado remotamente e retorna o resultado
            System.out.println("Invocando método do servidor para calcular as raízes...");
            String resultado = stub.calculaRaizes(a, b, c);

            // Exibe o resultado retornado pelo servidor
            System.out.println("Resultado: " + resultado);
        } catch (Exception ex) {
            // Caso ocorra algum erro (como problemas de comunicação com o servidor), a stack trace é exibida
            ex.printStackTrace();
        }
    }
}
