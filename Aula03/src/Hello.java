import java.rmi.*;  // Importa o pacote necessário para suportar a invocação remota

// Definição da interface Hello, que estende Remote
// Toda interface remota deve estender Remote para que os métodos possam ser chamados remotamente
public interface Hello extends Remote {

    // Declaração de um novo método remoto para calcular as raízes da equação de segundo grau
    // O método retorna uma String com o resultado das raízes (ou uma mensagem informando que não há raízes reais)
    // O método lança uma RemoteException, que é necessária para lidar com problemas de comunicação RMI
    String calculaRaizes(double a, double b, double c) throws RemoteException;
}
