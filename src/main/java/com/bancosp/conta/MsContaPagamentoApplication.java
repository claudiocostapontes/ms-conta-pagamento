package com.bancosp.conta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsContaPagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsContaPagamentoApplication.class, args);
		System.out.println("🚀 MS Conta Pagamento (Java 21) iniciado na porta 8080!");
	}

}