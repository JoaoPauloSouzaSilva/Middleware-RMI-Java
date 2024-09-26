import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class HelloServer extends UnicastRemoteObject implements Hello {

    private static final long serialVersionUID = 1L;

    protected HelloServer() throws RemoteException {
        super();
    }

    @Override
    public double calculaDelta(double a, double b, double c) throws RemoteException {
        double delta = (b * b) - (4 * a * c);
        return delta;
    }

    public static void main(String[] args) {
        try {
            HelloServer server = new HelloServer();
            Registry registry = LocateRegistry.createRegistry(1090);

            // Use rebind em vez de bind para evitar exceções se o nome já estiver registrado
            registry.rebind("Servidor", server);

            System.out.println("Servidor RMI inicializado com sucesso na porta 1099.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}