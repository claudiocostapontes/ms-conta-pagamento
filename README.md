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
graph LR
    %% Estilos (Laranja e Bordas arredondadas conforme o original)
    classDef startNode fill:#faa,stroke:#333,stroke-width:2px;
    classDef processNode fill:#ffb366,stroke:#d67a00,stroke-width:2px,color:black,rx:5,ry:5;
    classDef decisionNode fill:#ffd24d,stroke:#b38f00,stroke-width:2px,color:black,rx:5,ry:5;
    classDef endNode fill:#ffcc99,stroke:#d67a00,stroke-width:2px,stroke-dasharray: 5 5,rx:5,ry:5;

    %% --- BLOCO 1: Entradas (Drivers) ---
    Start((Início)):::startNode
    
    subgraph Inputs [Drivers e Entradas]
        direction TB
        In1[Estratégia de Negócio]:::processNode
        In2[Dores dos Silos Legados]:::processNode
        In3[Regulatório BACEN/LGPD]:::processNode
        In4[Débito Técnico COBOL]:::processNode
    end

    Start --> In1 & In2 & In3 & In4

    %% Consolidação Inicial
    Assessment[Assessment da<br/>Situação Atual]:::processNode
    In1 & In2 & In3 & In4 --> Assessment

    %% --- BLOCO 2: Decisão ---
    Decisao{Aprovado para<br/>Modernizar?}:::decisionNode
    Assessment --> Decisao

    %% --- BLOCO 3: O Core (TOGAF ADM) ---
    %% Caminho Superior: Visão de Negócio
    Decisao --> BizArch[Arquitetura de<br/>Negócios]:::processNode
    BizArch --> CapMap[Mapa de<br/>Capacidades]:::processNode
    
    %% Caminho do Meio: Visão de Sistemas (O mais complexo no desenho)
    Decisao --> AppArch[Arquitetura de<br/>Sistemas]:::processNode
    AppArch --> DDD[Definição de<br/>Bounded Contexts]:::processNode
    DDD --> MicroS[Design de<br/>Microsserviços]:::processNode
    CapMap & MicroS --> Integ[Estratégia de<br/>Integração (EDA)]:::processNode

    %% Caminho Inferior: Visão Tecnológica
    Decisao --> TechArch[Arquitetura<br/>Tecnológica]:::processNode
    TechArch --> Infra[Definição Stack<br/>Java 21/Cloud]:::processNode

    %% --- BLOCO 4: Convergência e Planejamento ---
    GapAnalysis[Análise de Gaps<br/>(Gap Analysis)]:::processNode
    
    Integ --> GapAnalysis
    Infra --> GapAnalysis

    Roadmap[Roadmap de Migração<br/>(Strangler Fig)]:::processNode
    GapAnalysis --> Roadmap

    %% --- BLOCO 5: Saídas (Outputs) ---
    subgraph Outputs [Artefatos Finais]
        direction TB
        Out1[Blueprint da<br/>Arquitetura Alvo]:::endNode
        Out2[Backlog de<br/>Épicos e Histórias]:::endNode
        Out3[Estudo de<br/>Viabilidade (ROI)]:::endNode
        Out4[Matriz de<br/>Riscos e Mitigação]:::endNode
    end

    Roadmap --> Out1 & Out2 & Out3 & Out4

