# Estratégia de Modernização e Stack Tecnológica

Este documento detalha as escolhas arquiteturais e a pilha tecnológica adotada para a modernização do Core Bancário, visando alta performance, escalabilidade e segurança.

---

## 1. Abordagem de Migração

### 🔄 Strangler Fig Pattern (Substituição Gradual)
A estratégia de migração foge do modelo "Big Bang", optando por um estrangulamento gradual do legado.

* **Baixo Risco:** Substitui partes do sistema legado incrementalmente em vez de uma "reescrita total", reduzindo drasticamente o risco de falhas catastróficas.
* **Entrega de Valor Contínua:** Permite lançar novas funcionalidades em microsserviços modernos enquanto o legado ainda funciona em paralelo.
* **Flexibilidade:** Se uma parte do novo sistema falhar, o legado continua a operar como fallback, facilitando a reversão (rollback).

---

## 2. Pilha Tecnológica (Target Stack)

### ☕ Linguagem: Java 21 (LTS)
Escolha baseada na robustez e nas novas features de concorrência.
* **Virtual Threads (Project Loom):** A maior vantagem competitiva desta versão. Permite lidar com milhões de requisições concorrentes com baixo consumo de memória, tornando a I/O bloqueante extremamente eficiente.
* **Record Patterns e Pattern Matching:** Reduz drasticamente o código boilerplate (getters, setters, equals), facilitando a criação de DTOs imutáveis e tornando o código mais legível e seguro.
* **Suporte Longo (LTS):** Estabilidade garantida pela Oracle até 2028 (suporte principal) e 2031 (estendido), essencial para sistemas bancários.

### 🍃 Framework Principal: Spring Boot 4.0.1
* **Alta Performance:** Otimizado para Java 17-21, com inicialização 25-40% mais rápida e redução de 30% no uso de memória heap.
* **Auto-configuração:** Simplifica o setup do projeto, detectando automaticamente dependências e configurando o ambiente sem necessidade de XML complexos.
* **Observabilidade Nativa:** Inclui suporte nativo a OpenTelemetry para métricas e tracing distribuído, crucial para monitoramento de microsserviços.
* **HTTP Service Clients:** Novas anotações (como `@HttpExchange`) facilitam a criação de clientes REST declarativos, removendo código repetitivo de implementação.

### 📦 Gerenciamento de Dependências: Maven
* **Reprodutibilidade:** Garante que o projeto compile da mesma forma em qualquer ambiente (local, desenvolvimento ou esteiras de CI/CD).
* **Centralização:** Gerencia bibliotecas e versões no arquivo `pom.xml`, facilitando a governança de atualizações e segurança.
* **Gerenciamento Transitivo:** Resolve automaticamente a árvore de dependências, evitando o "JAR Hell" e conflitos de versão.

### 🐳 Containerização: Docker
* **Consistência:** Resolve o problema de "funciona na minha máquina". Empacota a aplicação e suas dependências, garantindo paridade entre dev e prod.
* **Isolamento:** Cada microsserviço roda em seu próprio container, evitando conflitos de versão de bibliotecas ou configurações de SO entre serviços.
* **Portabilidade:** Agnóstico de infraestrutura, podendo rodar em qualquer nuvem (AWS, Azure, GCP) ou servidor on-premise.

### 💾 Persistência de Dados: Spring Data JPA / Hibernate
* **Abstração de SQL:** Reduz drasticamente o código de acesso a dados (CRUD) através de interfaces `JpaRepository`, diminuindo a necessidade de escrita manual de SQL.
* **Produtividade:** Realiza o Mapeamento Objeto-Relacional (ORM) automático entre classes Java e tabelas do banco de dados.
* **Performance:** Utiliza cache de primeiro e segundo nível nativos do Hibernate, otimizando a performance de leitura e reduzindo hits no banco.

### 🔒 Segurança/Autenticação: Spring Security 6
* **Segurança Robusta:** Padrão industrial para implementação de autenticação (JWT, OAuth2, SAML) e controle de acesso granular.
* **Proteção Nativa:** Protege automaticamente contra vetores de ataque comuns como CSRF, Session Fixation e Clickjacking.
* **Integração:** Projetado para integrar facilmente com Identity Providers modernos (Keycloak, Auth0, Azure AD).

### 🌐 Exposição de APIs: Spring Web / WebFlux
* **Flexibilidade:** Oferece suporte tanto ao modelo síncrono (Spring MVC) para simplicidade quanto ao modelo reativo não-bloqueante (WebFlux) para alta concorrência.
* **APIs REST:** Facilita a criação de endpoints RESTful eficientes, padronizados e fáceis de documentar.

### ☁️ Arquitetura Distribuída: Spring Cloud
* **Service Discovery:** Gerencia o endereço e localização dinâmica dos microsserviços automaticamente (ex: via Eureka ou Consul).
* **Resiliência (Circuit Breaker):** Implementa padrões de tolerância a falhas (via Resilience4j), evitando que o erro de um serviço derrube todo o ecossistema (efeito cascata).
* **Configuração Centralizada:** Gerencia configurações de todos os microsserviços em um repositório central (Spring Cloud Config), permitindo alterações sem redeploy.