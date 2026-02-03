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
    %% Estilos para ficar igual ao diagrama (Laranja e Bordas)
    classDef startNode fill:#faa,stroke:#333,stroke-width:2px;
    classDef processNode fill:#ffb366,stroke:#d67a00,stroke-width:2px,color:black;
    classDef decisionNode fill:#ffd24d,stroke:#b38f00,stroke-width:2px,color:black;
    classDef endNode fill:#ffb366,stroke:#d67a00,stroke-width:2px,stroke-dasharray: 5 5;

    %% --- BLOCO 1: Início e Ramificação Inicial ---
    Start((Início)):::startNode
    
    %% Coluna de processos paralelos (Esquerda)
    subgraph Grupo_Entrada [Entradas]
        direction TB
        A1[Atividade 1]:::processNode
        A2[Atividade 2]:::processNode
        A3[Atividade 3]:::processNode
        A4[Atividade 4]:::processNode
        A5[Atividade 5]:::processNode
        A6[Atividade 6]:::processNode
        A7[Atividade 7]:::processNode
    end

    %% Conexões do Início
    Start --> A1 & A2 & A3 & A4 & A5 & A6 & A7

    %% Consolidação 1
    Consolidador1[Consolidação Inicial]:::processNode
    A1 & A2 & A3 & A4 & A5 & A6 & A7 --> Consolidador1

    %% --- BLOCO 2: Decisão e Caminhos Complexos ---
    Decisao{Decisão?}:::decisionNode
    Consolidador1 --> Decisao

    %% Caminho Superior
    Decisao --> Sup1[Processo Superior 1]:::processNode
    Sup1 --> Sup2[Processo Superior 2]:::processNode

    %% Caminho do Meio (Complexo)
    Decisao --> Meio1[Processo Central 1]:::processNode
    Meio1 --> MeioSub1[Sub-processo A]:::processNode
    Meio1 --> MeioSub2[Sub-processo B]:::processNode
    MeioSub1 & MeioSub2 --> Meio2[Processo Central 2]:::processNode

    %% Caminho Inferior
    Decisao --> Inf1[Processo Inferior 1]:::processNode
    Inf1 --> Inf2[Processo Inferior 2]:::processNode

    %% --- BLOCO 3: Convergência Central ---
    Unificador[Unificação dos Caminhos]:::processNode
    
    Sup2 --> Unificador
    Meio2 --> Unificador
    Inf2 --> Unificador

    %% --- BLOCO 4: Ramificação Secundária ---
    subgraph Grupo_Analise [Análise Detalhada]
        direction TB
        B1[Item Análise 1]:::processNode
        B2[Item Análise 2]:::processNode
        B3[Item Análise 3]:::processNode
        B4[Item Análise 4]:::processNode
        B5[Item Análise 5]:::processNode
        B6[Item Análise 6]:::processNode
    end

    Unificador --> B1 & B2 & B3 & B4 & B5 & B6

    %% Consolidação 2
    Consolidador2[Validação Final]:::processNode
    B1 & B2 & B3 & B4 & B5 & B6 --> Consolidador2

    %% --- BLOCO 5: Saída Final ---
    Saida[Entrega Final]:::processNode
    Consolidador2 --> Saida

    subgraph Grupo_Saida [Resultados]
        direction TB
        F1[Resultado 1]:::endNode
        F2[Resultado 2]:::endNode
        F3[Resultado 3]:::endNode
        F4[Resultado 4]:::endNode
        F5[Resultado 5]:::endNode
    end

    Saida --> F1 & F2 & F3 & F4 & F5
