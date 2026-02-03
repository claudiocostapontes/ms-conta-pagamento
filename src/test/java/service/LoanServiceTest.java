package service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private EventPublisher eventPublisher;

    @InjectMocks
    private LoanService loanService;

    @Test
    @DisplayName("Deve processar empréstimo e notificar evento com sucesso")
    void shouldProcessLoanSuccessfully() {
        // 1. Cenario (Arrange)
        LoanRequest request = new LoanRequest();
        request.setUserId("user-123");
        request.setAmount(1000.00);
        request.setType("PERSONAL_LOAN"); // Simula um tipo válido do seu Enum

        // 2. Ação (Act)
        loanService.processLoan(request);

        // 3. Verificação (Assert)
        // Confirma que o 'publish' foi chamado 1 vez com o tópico correto
        verify(eventPublisher, times(1)).publish(
                eq("LOAN_PROCESSED"),
                eq("Empréstimo processado para user: user-123")
        );
    }

    @Test
    @DisplayName("Não deve quebrar se o tipo do produto for desconhecido")
    void shouldHandleUnknownProductType() {
        // 1. Cenario
        LoanRequest request = new LoanRequest();
        request.setUserId("user-999");
        request.setAmount(500.00);
        request.setType("TIPO_ALIENIGENA"); // Tipo que não existe no Enum

        // 2. Ação
        loanService.processLoan(request);

        // 3. Verificação
        // O serviço deve "sobreviver" ao erro e ainda publicar o evento final
        verify(eventPublisher, times(1)).publish(anyString(), anyString());
    }
}