package controller;

import aggregate.PaymentAccount;
import aggregate.InsufficientFundsException;
import service.AccountManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Tag(name = "Core Banking", description = "Gestão de Contas e Transações")
public class AccountController {

    private final AccountManagementService service;

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
}