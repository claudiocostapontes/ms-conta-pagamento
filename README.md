# 🏗️ Modernização de Arquitetura Bancária & Engajamento Digital

![Status](https://img.shields.io/badge/Status-Architecture_Vision-blue)
![Role](https://img.shields.io/badge/Role-Tech_Lead-red)
![Stack](https://img.shields.io/badge/Stack-Java_|_Node_|_Python-green)
![Standard](https://img.shields.io/badge/Standard-TOGAF_%26_BIAN-orange)

> **Enterprise Architecture Case Study:** Estratégia arquitetural para o Banco SP, focada em romper silos legados, habilitar a "Conta de Pagamentos" e elevar o engajamento através de uma plataforma escalável.

---

## 📑 Documentação e Arquitetura

Para detalhes da estratégia de modernização e acesso ao case completo, utilize os links abaixo:

* [**📄 Documentação Técnica (PDF)**](./docs/Case%20para%20avaliação%20Enterprise%20Architecture1.pdf)
* [**🛠️ Stack Tecnológica e Modernização**](./docs/tecnologias.md)

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
    %% Início: Matriz de Riscos
    Start([Enterprise Architecture: Matriz de Riscos Críticos]) --> R_Estrat[Risco Estratégico: Escolha do Produto]
    Start --> R_Arqui[Risco Arquitetural: Débito Técnico e Legado de Silos]
    Start --> R_Desenv[Desenvolvimento de Core Bancário Próprio]
    Start --> R_Sec[Riscos Secundários Altas/Médios]

    %% Detalhamento de Riscos e Mitigações
    R_Estrat --> AvaliacaoComp[Avaliação de Complexidade Técnica - Avaliação do Time de Enterprise Architect]
    R_Arqui --> AvaliacaoComp
    R_Desenv --> AvaliacaoComp
    R_Sec --> AvaliacaoComp

    %% Itens da Avaliação Técnica
    AvaliacaoComp --- AnaliseVia[Análise de Viabilidade Técnica Prévia]
    AvaliacaoComp --- AbordagemMVP[Abordagem MVP Paralela]
    AvaliacaoComp --- UsoTOGAF[Uso do TOGAF ADM]
    AvaliacaoComp --- MapeamentoDDD[Mapeamento DDD Bounded Contexts]
    AvaliacaoComp --- CamadaACL[Implementar Camada Anticorrupção ACL]
    AvaliacaoComp --- Strangler[Aplicar Strangler Fig Pattern]
    AvaliacaoComp --- ServiceMesh[Adotar Service Mesh]
    AvaliacaoComp --- AnaliseMakeBuy[Análise Make vs. Buy]
    AvaliacaoComp --- POC[Realizar POC com Fornecedores]
    AvaliacaoComp --- SoluHibrida[Adotar Solução Híbrida]
    AvaliacaoComp --- ExitStrategy[Incluir Cláusula de Exit Strategy]

    %% Decisão e Silos
    AvaliacaoComp --> Decisao{Decisão de Produto Inicial}
    
    Decisao --> IdentSilos[Identificação dos 5 Grandes Silos: CDC, Cartão, Pessoal, Consignado, Imóvel]
    
    IdentSilos --> ContasPag[CONTAS DE PAGAMENTO: Tempo 12-18 meses - Impacto Transformacional]
    IdentSilos --> Cashback[CASHBACK: Complexidade Média - Impacto Incremental]
    
    %% Detalhamento dos Silos
    IdentSilos --> Silo1[Silo 1: CDC - Tec: Mainframe/COBOL]
    IdentSilos --> Silo2[Silo 2: Cartão de Crédito - Tec: Alta Latência]
    IdentSilos --> Silo3[Silo 3: Crédito Pessoal - Dep: Averbadoras Externas]
    IdentSilos --> Silo4[Silo 4: Consignados - Dados: Histórico Fragmentado]
    IdentSilos --> Silo5[Silo 5: Empréstimos com Garantia]

    %% Estratégia de Modernização
    ContasPag --> EstratMod[Estratégia de Modernização: Refatoração dos Silos]
    Silo1 & Silo2 & Silo3 & Silo4 & Silo5 --> EstratMod
    
    EstratMod --> StranglerApp[Abordagem: Strangler Fig Pattern Substituição Gradual]
    EstratMod --> Spaghetti[Problema: The Spaghetti Mess - Conexões Ponto-a-Ponto]

    %% Target Stack
    StranglerApp --> TargetStack[Definir Pilha Tecnológica Alvo Target State]
    Spaghetti --> TargetStack

    subgraph "Pilha Tecnológica Alvo"
        TargetStack --- Java21[Linguagem: Java 21]
        TargetStack --- SpringBoot[Framework: Spring Boot 3.3.x]
        TargetStack --- Nuvem[Gerenciamento: Nuvem]
        TargetStack --- Docker[Containerização: Docker]
        TargetStack --- JPA[Persistência: Spring Data JPA / Hibernate]
        TargetStack --- Security[Segurança: Spring Security]
        TargetStack --- WebFlux[APIs: Spring Web / WebFlux]
        TargetStack --- Cloud[Arquitetura: Spring Cloud Microservices]
    end

    %% Transição para TOGAF
    Java21 & SpringBoot & Nuvem & Docker & JPA & Security & WebFlux & Cloud --> VisaoCore[Visão de Arquitetura: Novo Core Bancário Baseado em TOGAF]
    
    VisaoCore --> TStart[T_Start]
    TStart --> FasePrelim[Fase Preliminar: Escopo e Governança]

    %% Artefatos Finais
    FasePrelim --> FaseA[Fase A: Visão de Arquitetura - Objetivos e Metas]
    FasePrelim --> ArtCap[Artefatos: Mapeamento de Capacidades Core vs Context]
    FasePrelim --> ArtReq[Artefatos: Engenharia de Requisitos]
    FasePrelim --> ArtBB[Artefatos: Building Blocks ABBs e SBBs]
    FasePrelim --> ArtValue[Artefatos: Value Stream]
    FasePrelim --> DecDDD[Decisões: Padrões DDD Bounded Contexts]
    FasePrelim --> DecEDA[Decisões: Estilos Event-Driven Architecture EDA]
    FasePrelim --> Roadmap[Roadmap: Definir Arquitetura Alvo e Intermediária]
    FasePrelim --> TEnd[T_End]
    FasePrelim --> Resultado[Resultado: Novo Core Bancário Modernizado]

    %% Estilos
    style Start fill:#f96,stroke:#333
    style Decisao fill:#f96,stroke:#333
    style FasePrelim fill:#f96,stroke:#333
    style TargetStack fill:#f96,stroke:#333


