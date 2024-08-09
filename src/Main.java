
public class Main {

	public static void main(String[] args) {
		Cliente gustavo = new Cliente();
		gustavo.setNome("Gustavo");
		
		Conta cc = new ContaCorrente(gustavo);
		Conta poupanca = new ContaPoupanca(gustavo);

		cc.depositar(100);
		cc.transferir(101, poupanca);
		
		
		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}

}
