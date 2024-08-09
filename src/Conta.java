import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected LocalDateTime dataCriação;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.dataCriação = LocalDateTime.now();
	}

	@Override
	public void sacar(double valor) {
		if(this.saldo>=valor) {
			saldo -= valor;
			System.out.println("Saque de R$ "+valor+" efetuado com sucesso!");
			System.out.println("Saldo atual de R$ "+this.saldo);
		}else {
			System.out.println("Saldo de: R$ "+saldo+", insuficiente parao saque de: R$ "+valor);
		}
	}

	@Override
	public void depositar(double valor) {
		saldo += valor;
		System.out.println("Deposito de R$ "+valor+" efetuado com sucesso!");
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		if(this.saldo>= valor) {
			this.sacar(valor);
			contaDestino.depositar(valor);
			
		}else {
			System.out.println("Saldo insufucuente! Transferencia não efetivada");
		}
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Data de abertura: %s", formatarData(this.dataCriação)));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
	
	private String formatarData(LocalDateTime data) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	    String dataFormatada = data.format(formatter); 
	    return dataFormatada; 
	}
}
