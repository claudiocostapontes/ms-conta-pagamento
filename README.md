# Modernização de Arquitetura Bancária & Engajamento Digital (Banco SP)

![Status](https://img.shields.io/badge/Status-Architecture_Vision-blue)
![Domain](https://img.shields.io/badge/Domain-Banking-green)
![Standard](https://img.shields.io/badge/Standard-TOGAF_%26_BIAN-orange)
![Style](https://img.shields.io/badge/Architecture_Style-Event_Driven_Microservices-blueviolet)

> **Enterprise Architecture Case Study:** Definição da estratégia arquitetural para romper silos legados, habilitar a "Conta de Pagamentos" e aumentar o engajamento do cliente.

---

## 📑 Índice
1. [Contexto e Desafio de Negócio](#1-contexto-e-desafio-de-negócio)
2. [Estratégia de Negócio & Cadeia de Valor](#2-estratégia-de-negócio--cadeia-de-valor)
3. [Domain-Driven Design (DDD) Estratégico](#3-domain-driven-design-ddd-estratégico)
4. [Visão de Arquitetura (TO-BE)](#4-visão-de-arquitetura-to-be)
   - [Diagrama de Contexto (C4 Nível 1)](#diagrama-de-contexto-c4-nível-1)
   - [Diagrama de Containers (C4 Nível 2)](#diagrama-de-containers-c4-nível-2)
5. [Padrões de Decomposição de Microsserviços](#5-padrões-de-decomposição-de-microsserviços)
6. [Plano de Migração (Strangler Fig)](#6-plano-de-migração-strangler-fig)
7. [Architecture Decision Records (ADRs)](#7-architecture-decision-records-adrs)

---

## 1. Contexto e Desafio de Negócio

O Banco SP, uma instituição tradicional focada em crédito (CDC, Consignado, Cartões), enfrenta um desafio de **engajamento**. A arquitetura atual é composta por **silos tecnológicos** que impedem a inovação rápida e a visão única do cliente.

**Objetivos do Projeto:**
1.  Aumentar o engajamento do cliente através da expansão do portfólio.
2.  Modernizar a plataforma tecnológica para acelerar o *Time-to-Market*.
3.  Decidir estrategicamente entre dois produtos alavancadores: **Conta de Pagamentos** ou **Cashback**.

---

## 2. Estratégia de Negócio & Cadeia de Valor

### 2.1. Decisão Estratégica: Priorização da Conta de Pagamentos
A recomendação da Arquitetura Corporativa é a implementação imediata da **Conta de Pagamentos**.

| Critério | Conta de Pagamentos | Cashback |
| :--- | :--- | :--- |
| **Frequência de Uso** | **Diária** (Pagamentos, Pix, Boletos) | Esporádica (Pós-compra) |
| **Captura de Dados** | Alta (Visão completa do fluxo financeiro) | Média (Apenas consumo) |
| **Valor Arquitetural** | **Fundação (ABB)**: Cria a base de custódia e movimentação necessária para qualquer outro produto. | Dependência: Necessita de uma conta/carteira para operar. |

### 2.2. Mapa de Cadeia de Valor (Value Stream)
O fluxo abaixo demonstra como a Conta de Pagamentos atua como o elo entre a aquisição do cliente e a rentabilização via crédito.

```mermaid
graph LR
    subgraph Aquisicao [Jornada de Entrada]
        MKT[Marketing] --> OB[Onboarding Digital & KYC]
    end

    subgraph Engajamento [Core - Recorrência]
        OB --> CP[Abertura de Conta Pagamento]
        CP --> TX[Transacionalidade Diária (Pix/Bill Pay)]
        TX --> EDU[Educação Financeira / PFM]
    end

    subgraph Monetizacao [Cross-Sell]
        TX --> ANALISE[Análise de Perfil (Data-Driven)]
        ANALISE --> CRED[Oferta de Crédito Contextual]
    end

    style Engajamento fill:#e1f5fe,stroke:#01579b,stroke-width:2px
