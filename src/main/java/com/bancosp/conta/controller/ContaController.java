package com.bancosp.conta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController {

    // Simulação de endpoint de criação de conta (Fase 1 do Case)
    @PostMapping
    public ResponseEntity<Map<String, Object>> criarConta(@RequestBody Map<String, String> dadosCliente) {
        
        // Java 21 style: uso de 'var' para inferência de tipos
        var novoNumeroConta = "7700" + (int)(Math.random() * 1000);
        var cliente = dadosCliente.getOrDefault("nome", "Cliente Anônimo");
        var contaId = UUID.randomUUID().toString();

        // Construção da resposta
        var resposta = new HashMap<String, Object>();
        resposta.put("contaId", contaId);
        resposta.put("numeroConta", novoNumeroConta);
        resposta.put("titular", cliente);
        resposta.put("status", "ATIVA");
        resposta.put("ambiente", "Java 21 / Spring Boot 3.2");
        resposta.put("dataCriacao", LocalDateTime.now());

        System.out.println("✅ Nova conta criada para: " + cliente + " | ID: " + contaId);

        return ResponseEntity.status(201).body(resposta);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("MS Conta Pagamento está ONLINE e rodando com Java 21 ☕");
    }
}