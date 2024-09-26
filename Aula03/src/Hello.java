import java.rmi.*;
 
public interface Hello extends Remote {
	// Novo método remoto que calcula o delta com base em a, b e c
    double calculaDelta(double a, double b, double c) throws RemoteException;
}