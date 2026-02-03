package service;

// REMOVIDO: @Service (DTOs não devem ser beans gerenciados pelo Spring)
public class LoanRequest {

    // Campos privados para armazenar os dados JSON
    private String accountId;
    private Double amount;
    private String type;
    private String employerCnpj; // Corrigido para String (era PaymentAccount)
    private Integer installments;
    private String userId; // Adicionado para corrigir o erro "cannot find symbol method getUserId()"

    // --- GETTERS (Para o LoanService ler os dados) ---

    public String getAccountId() {
        return accountId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getEmployerCnpj() {
        return employerCnpj;
    }

    public Integer getInstallments() {
        return installments;
    }

    public String getUserId() {
        return userId;
    }

    // --- SETTERS (Para o Spring preencher os dados ao receber o JSON) ---

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEmployerCnpj(String employerCnpj) {
        this.employerCnpj = employerCnpj;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}