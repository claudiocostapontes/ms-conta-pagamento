package controller;

import aggregate.InsufficientFundsException;
import aggregate.PaymentAccount;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AccountManagementService;
import service.LoanService;

@RestController
@RequestMapping("/api/v1/accounts") // Padronizado para o seu prefixo v1
@RequiredArgsConstructor
@Tag(name = "Core Banking", description = "Gestão de Contas, Transações e Empréstimos")
public class AccountController<LoanRequest> {

    private final AccountManagementService service;
    private final LoanService loanService; // Injeção do serviço de crédito

    @PostMapping
    @Operation(summary = "Abertura de Conta de Pagamento")
    public ResponseEntity<PaymentAccount> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(service.openAccount(request.taxId()));
    }

    @PostMapping("/{id}/transactions")
    @Operation(summary = "Lançamento de Débito/Crédito com Idempotência")
    public ResponseEntity<Void> processTransaction(
            @PathVariable String id,
            @RequestHeader("X-Idempotency-Key") String idempotencyKey,
            @RequestBody TransactionRequest request) {

        try {
            service.processTransaction(id, request.amount(), request.type(), idempotencyKey);
            return ResponseEntity.ok().build();
        } catch (InsufficientFundsException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // --- Integração dos Silos Legados (CDC, Cartão, Pessoal, Consignado, Garantia) ---

    @PostMapping("/{accountId}/loans")
    @Operation(summary = "Contratação de Empréstimos (Silos Legados)")
    public ResponseEntity<String> createLoan(
            @PathVariable Long accountId,
            @RequestBody LoanRequest request) throws InsufficientFundsException {

        // Validação básica de segurança: ID da URL deve ser o mesmo do corpo
        if (!accountId.equals(request.getClass())) {
            return ResponseEntity.badRequest().body("Account ID mismatch");
        }

        loanService.processLoan((service.LoanRequest) request);
        return ResponseEntity.ok("Processamento de empréstimo iniciado com sucesso.");
    }
}