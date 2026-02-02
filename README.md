# 🏗️ Modernização de Arquitetura Bancária & Engajamento Digital

![Status](https://img.shields.io/badge/Status-Architecture_Vision-blue)
![Role](https://img.shields.io/badge/Role-Tech_Lead-red)
![Stack](https://img.shields.io/badge/Stack-Java_|_Node_|_Python-green)
![Standard](https://img.shields.io/badge/Standard-TOGAF_%26_BIAN-orange)

> **Enterprise Architecture Case Study:** Estratégia arquitetural para o Banco SP, focada em romper silos legados, habilitar a "Conta de Pagamentos" e elevar o engajamento através de uma plataforma escalável.

---

## 📑 Índice
1. [Contexto e Desafio de Negócio](#1-contexto-e-desafio-de-negócio)
2. [Estratégia & Decisão (Buy vs Build)](#2-estratégia--decisão-buy-vs-build)
3. [Arquitetura de Solução (C4 Model)](#3-arquitetura-de-solução-c4-model)
4. [Liderança Técnica & Performance](#4-liderança-técnica--performance)
5. [Plano de Migração & ADRs](#5-plano-de-migração--adrs)

---

## 1. Contexto e Desafio de Negócio

O Banco SP enfrentava baixa recorrência de uso e silos tecnológicos. A missão foi definir o **Building Block (ABB)** fundamental para transformar o banco em um ecossistema digital.

* **Objetivo:** Implementar a **Conta de Pagamentos** como fundação para capturar dados transacionais e habilitar cross-sell de crédito.
* **Impacto:** Redução do Time-to-Market para novos produtos financeiros através de serviços desacoplados.

---

## 2. Estratégia & Decisão

A recomendação técnica priorizou a **Conta de Pagamentos** sobre o Cashback devido à frequência de uso diária (Pix/Boletos), criando uma base de custódia necessária para produtos futuros.



---

## 3. Arquitetura de Solução (C4 Model)

### 3.1. Diagrama de Containers (Nível 2)
Abaixo, a decomposição da solução utilizando **Event-Driven Architecture** para garantir resiliência e baixa latência.

```mermaid
flowchart TD
    %% Processo de Avaliação de Arquitetura Empresarial e Modernização de Core Bancário
    A1["Nó Central: Matriz de Riscos Críticos (Heatmap)"]
    A1 --> B1["1.1 Risco Estratégico: Escolha do Produto<br/>Status: Severidade Crítica"]
    A1 --> B2["1.2 Risco Arquitetural: Débito Técnico e Silos<br/>Status: Severidade Crítica"]
    A1 --> B3["1.3 Risco Operacional: Core Bancário<br/>Status: Severidade Crítica"]
    A1 --> B4["1.4 Riscos Secundários (Altos/Médios)"]

    B1 --> C1["Análise de Viabilidade Técnica Prévia"]
    B1 --> C2["Abordagem MVP Paralela"]
    B1 --> C3["Uso do TOGAF ADM"]

    B2 --> C4["Mapeamento DDD (Bounded Contexts)"]
    B2 --> C5["Implementar Camada Anticorrupção (ACL)"]
    B2 --> C6["Aplicar Strangler Fig Pattern"]
    B2 --> C7["Adotar Service Mesh"]

    B3 --> C8["Análise Make vs. Buy"]
    B3 --> C9["Realizar POC com Fornecedores"]
    B3 --> C10["Adotar Solução Híbrida"]
    B3 --> C11["Incluir Cláusula de Exit Strategy"]

    B4 --> C12["Risco Regulatório: Mitigar com Conformidade"]
    B4 --> C13["Risco Integração: Padrão Saga e EDA"]
    B4 --> C14["Risco Organizacional: OKRs e Change Management"]
    B4 --> C15["Risco Financeiro: Reserva de Contingência 30%"]
    B4 --> C16["Risco Segurança: Zero Trust, mTLS, Threat Modeling"]

    C1 & C2 & C3 & C4 & C5 & C6 & C7 & C8 & C9 & C10 & C11 & C12 & C13 & C14 & C15 & C16 --> D1["Avaliação de Complexidade Técnica e Time-to-Market"]

    D1 --> E1{Decisão de Produto Inicial}
    E1 --Conta de Pagamentos--> F1["Complexidade: Muito Alta<br/>Tempo: 12-18 meses<br/>Barreira: Alta (BACEN)<br/>Reaproveitamento: Alto<br/>Impacto: Transformacional"]
    E1 --Cashback--> F2["Complexidade: Média<br/>Tempo: 6-9 meses<br/>Barreira: Baixa<br/>Reaproveitamento: Médio<br/>Impacto: Incremental"]

    F1 & F2 --> G1["Identificação dos 5 Grandes Silos (Estado Atual)"]

    subgraph SG_SILOS["Silos Legados (Estado Atual)"]
        direction LR
        SG_Start --> H1["Silo 1: Empréstimos CDC<br/>Tec: Mainframe/COBOL<br/>Dados: Base A Isolada"]
        H1 --> H2["Silo 2: Gestão de Cartão<br/>Tec: Alta Latência<br/>Dados: Base B Isolada"]
        H2 --> H3["Silo 3: Consignado<br/>Tec: Processamento Batch<br/>Dep: Averbadoras Externas"]
        H3 --> H4["Silo 4: Crédito Pessoal<br/>Dados: Histórico Fragmentado"]
        H4 --> H5["Silo 5: Garantias & Consórcios<br/>Problema: Regras de Colateral Presas"]
        H5 --> SG_End
    end

    G1 --> SG_Start
    SG_End --> I1["Problema: The Spaghetti Mesh<br/>- Conexões Ponto-a-Ponto<br/>- Acoplamento Oculto<br/>- Ausência de API Gateway/ESB<br/>- Latência Síncrona"]

    I1 --> I2["Fluxo de Falha: Customer 360º Fragmentado<br/>Ex: Cliente 'João' com dados inconsistentes em 3 silos"]

    I2 --> J1["Estratégia de Modernização: Refatoração dos Silos"]
    J1 --> K1["Abordagem: Strangler Fig Pattern (Substituição Gradual)"]
    J1 --> K2["Definir Pilha Tecnológica Alvo (Target Stack)"]

    K2 --> L1["Linguagem: Java 21"]
    K2 --> L2["Framework: Spring Boot 4.0.1"]
    K2 --> L3["Gerenciamento: Maven"]
    K2 --> L4["Containerização: Docker"]
    K2 --> L5["Persistência: Spring Data JPA / Hibernate"]
    K2 --> L6["Segurança: Spring Security"]
    K2 --> L7["APIs: Spring Web / WebFlux"]
    K2 --> L8["Arquitetura: Spring Cloud (Microsserviços)"]

    L1 & L2 & L3 & L4 & L5 & L6 & L7 & L8 --> M1["Visão de Arquitetura: Novo Core Bancário (Baseado em TOGAF)"]

    subgraph SG_TOGAF["Ciclo TOGAF ADM para Novo Core"]
        direction TB
        T_Start --> N1["Fase Preliminar: Escopo e Governança"]
        N1 --> N2["Fase A: Visão de Arquitetura<br/>- Objetivo: Adaptabilidade<br/>- Escopo: CDC, Cartão, Crédito, Consignado, Garantias"]
        N2 --> N3["Artefatos: Mapeamento de Capacidades (Core vs. Context)"]
        N3 --> N4["Artefatos: Engenharia de Requisitos (Funcionais e Não-Funcionais)"]
        N4 --> N5["Artefatos: Building Blocks (ABBs e SBBs)"]
        N5 --> N6["Artefatos: Value Stream (Solicitação até Liberação)"]
        N6 --> N7["Decisões: Padrões (DDD Bounded Contexts, Decomposição por Subdomínio)"]
        N7 --> N8["Decisões: Estilos (Event-Driven Architecture - EDA)"]
        N8 --> N9["Roadmap: Definir Arquitetura Alvo e Arquiteturas Intermediárias"]
        N9 --> T_End
    end
    M1 --> T_Start
    T_End --> O1["Resultado: Novo Core Bancário (Funcionalidade Emergente)<br/>Orquestra a atualização gradual dos produtos legados"]
