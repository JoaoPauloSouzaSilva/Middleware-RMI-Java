import java.rmi.*;  // Importa pacotes RMI necessários para criar a interface remota
import java.rmi.server.*;  // Importa pacotes para a implementação do servidor RMI
import java.rmi.registry.*;  // Importa pacotes para registrar o servidor RMI no registro

// A classe HelloServer implementa a interface remota Hello
// e estende UnicastRemoteObject, que permite que o objeto seja exportado para receber chamadas remotas
public class HelloServer extends UnicastRemoteObject implements Hello {

    // Serial version ID utilizado para identificar a versão da classe durante a serialização
    private static final long serialVersionUID = 1L;

    // Construtor padrão, chamado ao criar uma instância do servidor.
    // Lança RemoteException, que é necessária para classes que implementam objetos remotos.
    protected HelloServer() throws RemoteException {
        super();  // Chama o construtor da classe pai (UnicastRemoteObject)
    }

    // Implementação do método remoto definido na interface Hello.
    // Esse método recebe os coeficientes 'a', 'b' e 'c' de uma equação de segundo grau
    // e calcula suas raízes com base na fórmula de Bhaskara.
    @Override
    public String calculaRaizes(double a, double b, double c) throws RemoteException {
        // Calcula o delta (discriminante) da equação: delta = b^2 - 4ac
        double delta = (b * b) - (4 * a * c);
        
        // Variável para armazenar o resultado final que será retornado
        String resultado;

        // Verifica o valor do delta para determinar quantas e quais são as raízes
        if (delta > 0) {
            // Se delta > 0, há duas raízes reais e diferentes
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);  // Calcula a primeira raiz
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);  // Calcula a segunda raiz
            resultado = "Duas raízes reais: x1 = " + x1 + ", x2 = " + x2;  // Armazena o resultado
        } else if (delta == 0) {
            // Se delta == 0, há uma única raiz real
            double x = -b / (2 * a);  // Calcula a única raiz
            resultado = "Uma única raiz real: x = " + x;  // Armazena o resultado
        } else {
            // Se delta < 0, não há raízes reais, pois o delta é negativo
            resultado = "A equação não possui raízes reais. Delta = " + delta;  // Armazena a mensagem
        }

        // Retorna o resultado da operação para o cliente
        return resultado;
    }

    // Método main que inicializa o servidor RMI
    public static void main(String[] args) {
        try {
            // Cria uma instância do servidor HelloServer
            HelloServer server = new HelloServer();
            
            // Cria o registro RMI na porta 1098, para expor o objeto remoto
            Registry registry = LocateRegistry.createRegistry(1098);
            
            // Rebind associa o nome "Servidor" ao objeto do servidor
            // Se já houver algo registrado com esse nome, ele substitui
            registry.rebind("Servidor", server);
            
            // Exibe uma mensagem indicando que o servidor foi inicializado com sucesso
            System.out.println("Servidor RMI inicializado com sucesso na porta 1098.");
        } catch (Exception e) {
            // Em caso de erro, imprime a stack trace para ajudar no diagnóstico
            e.printStackTrace();
        }
    }
}
