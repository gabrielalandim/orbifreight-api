<div align="center">

<img src="https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/>
<img src="https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"/>
<img src="https://img.shields.io/badge/Oracle-DB-F80000?style=for-the-badge&logo=oracle&logoColor=white"/>
<img src="https://img.shields.io/badge/Azure-Deploy-0078D4?style=for-the-badge&logo=microsoftazure&logoColor=white"/>
<img src="https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/>

# 🛰️ Orbifreight API

### Plataforma Inteligente de Monitoramento de Cargas em Trânsito

> API RESTful desenvolvida em Java com Spring Boot para a **Global Solution — Java Advanced | FIAP**  
> Monitoramento em tempo real de temperatura, umidade e localização de cargas sensíveis via IoT

---

</div>

## 🔗 Links Oficiais do Projeto

| Recurso | Link |
|---|---|
| 🚀 **Deploy (API em produção)** | [https://orbifreight-bud3csaaadddfxdq.eastus-01.azurewebsites.net](https://orbifreight-bud3csaaadddfxdq.eastus-01.azurewebsites.net) |
| 📖 **Documentação Swagger/OpenAPI** | [https://orbifreight-bud3csaaadddfxdq.eastus-01.azurewebsites.net/swagger-ui/index.html](https://orbifreight-bud3csaaadddfxdq.eastus-01.azurewebsites.net/swagger-ui/index.html) |
| 🎬 **Vídeo Pitch (3 minutos)** | [https://youtu.be/lgvnZS5-yeg](https://youtu.be/lgvnZS5-yeg) |
| 🎥 **Vídeo de Apresentação (10 minutos)** | [https://youtu.be/-CYGdHebzt8?si=DAfZRWNAL8H0eaQq](https://youtu.be/-CYGdHebzt8?si=DAfZRWNAL8H0eaQq) |
| 💻 **Repositório GitHub** | [https://github.com/gabrielalandim/orbifreight-api](https://github.com/gabrielalandim/orbifreight-api) |

---

## 📋 Sumário

- [Sobre o Projeto](#-sobre-o-projeto)
- [Arquitetura](#-arquitetura-do-sistema)
- [Tecnologias](#-tecnologias-utilizadas)
- [Modelagem de Dados](#-modelagem-de-dados-der)
- [Endpoints da API](#-endpoints-da-api)
- [Segurança — JWT](#-segurança--autenticação-jwt)
- [Como Executar Localmente](#-como-executar-localmente)
- [Integrantes](#-integrantes)

---

## 🎯 Sobre o Projeto

O **Orbifreight** é uma API REST para gerenciamento inteligente de cargas sensíveis em transporte (farmacêuticos, alimentos perecíveis, cargas críticas). A solução endereça o problema de **perda de carga por falha de monitoramento** durante o transporte, oferecendo:

- **Cadastro e rastreamento** de cargas com parâmetros de temperatura e umidade permitidos
- **Alertas automáticos** categorizados por nível de criticidade (BAIXO → CRÍTICO)
- **Telemetria IoT** — endpoint dedicado para recebimento de leituras de sensores embarcados
- **Dashboard analítico** com contagem de cargas ativas e alertas abertos em tempo real
- **Herança de usuários** com papéis distintos: Gestor (ADMIN) e Motorista (USER)

---

## 🏗️ Arquitetura do Sistema

A API segue uma arquitetura em **camadas desacopladas**, respeitando o princípio da responsabilidade única:

```
┌─────────────────────────────────────────────────────────┐
│                      CLIENT / SWAGGER                    │
└────────────────────────┬────────────────────────────────┘
                         │ HTTP Request + Bearer Token
┌────────────────────────▼────────────────────────────────┐
│              SPRING SECURITY + JWT FILTER                │
│         (SecurityFilter → TokenService → Valida)        │
└────────────────────────┬────────────────────────────────┘
                         │ Requisição autorizada
┌────────────────────────▼────────────────────────────────┐
│                   CONTROLLER LAYER                       │
│   AuthController │ CargaController │ AlertaController   │
│   TipoCargaController │ DashboardController │ IoTCtrl   │
│              (HATEOAS links em todas respostas)         │
└────────────────────────┬────────────────────────────────┘
                         │ Chama Service + valida DTOs
┌────────────────────────▼────────────────────────────────┐
│                    SERVICE LAYER                         │
│   CargaService │ AlertaService │ TipoCargaService        │
│        DashboardService │ AuthorizationService           │
│           (lógica de negócio + @Transactional)          │
└────────────────────────┬────────────────────────────────┘
                         │ JPA / ORM
┌────────────────────────▼────────────────────────────────┐
│                  REPOSITORY LAYER                        │
│        JpaRepository estendido por cada entidade        │
└────────────────────────┬────────────────────────────────┘
                         │
┌────────────────────────▼────────────────────────────────┐
│                 ORACLE DATABASE (Azure)                  │
└─────────────────────────────────────────────────────────┘
```

### Diagrama de Arquitetura (Mermaid)

```mermaid
graph TB
    Client["🖥️ Cliente (Swagger/Insomnia)"]
    SecFilter["🔐 SecurityFilter (JWT)"]
    Auth["AuthController\n/auth/login\n/auth/register"]
    Carga["CargaController\n/cargas"]
    Alerta["AlertaController\n/alertas"]
    TipoCarga["TipoCargaController\n/tipos-carga"]
    Dashboard["DashboardController\n/dashboard"]
    IoT["IoTController\n/api/iot/leituras"]
    CS["CargaService"]
    AS["AlertaService"]
    TCS["TipoCargaService"]
    DS["DashboardService"]
    TS["TokenService"]
    CR["CargaRepository"]
    AR["AlertaRepository"]
    TCR["TipoCargaRepository"]
    UR["UsuarioRepository"]
    DB[("🗄️ Oracle DB")]

    Client --> SecFilter
    SecFilter --> Auth
    SecFilter --> Carga
    SecFilter --> Alerta
    SecFilter --> TipoCarga
    SecFilter --> Dashboard
    SecFilter --> IoT
    Auth --> TS
    Auth --> UR
    Carga --> CS --> CR --> DB
    Alerta --> AS --> AR --> DB
    TipoCarga --> TCS --> TCR --> DB
    Dashboard --> DS --> CR & AR
```

---

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia | Versão |
|---|---|---|
| Linguagem | Java | 17 |
| Framework Principal | Spring Boot | 3.3.5 |
| Persistência | Spring Data JPA + Hibernate | 3.3.5 |
| Segurança | Spring Security + JWT (Auth0) | 4.4.0 |
| Documentação | SpringDoc OpenAPI / Swagger UI | 2.6.0 |
| Banco de Dados (Prod) | Oracle Database | 19c+ |
| Banco de Dados (Dev/Test) | H2 in-memory | — |
| Build | Maven | 3.9+ |
| Produtividade | Lombok + DevTools | — |
| HATEOAS | Spring HATEOAS | — |
| Cloud | Microsoft Azure App Service | — |
| Comunicação HTTP | OpenFeign (Spring Cloud) | — |
| Validação | Spring Validation (Jakarta) | — |

---

## 🗃️ Modelagem de Dados (DER)

### Diagrama Entidade-Relacionamento

```mermaid
erDiagram
    USUARIO {
        Long id PK
        String nome
        String email UK
        String senha
        String cargo
        String tipo_usuario
        String departamento
        String cnh
    }

    TIPO_CARGA {
        Long id PK
        String nome
        Double temp_min
        Double temp_max
        Double umidade_max
        Integer prazo_max_horas
    }

    CARGA {
        Long id PK
        Long tipo_id FK
        Long veiculo_id
        Long motorista_id
        String placa_veiculo
        String origem
        String destino
        Double temp_min
        Double temp_max
        Double umidade_max
        String status
    }

    ALERTA {
        Long id PK
        Long carga_id FK
        String titulo
        String descricao
        String nivel
        String status
        LocalDateTime data_criacao
    }

    SENSOR_LEITURA {
        Long id PK
        Long carga_id FK
        Double temperatura
        Double umidade
        Double latitude
        Double longitude
        LocalDateTime data_hora_leitura
    }

    PONTO_PARADA {
        Long carga_id PK,FK
        Integer ordem_parada PK
        String localidade
        LocalDateTime data_chegada
    }

    TIPO_CARGA ||--o{ CARGA : "classifica"
    CARGA ||--o{ ALERTA : "gera"
    CARGA ||--o{ SENSOR_LEITURA : "emite"
    CARGA ||--o{ PONTO_PARADA : "possui"
```

### Destaques de Modelagem Avançada

| Recurso | Implementação | Localização |
|---|---|---|
| **Herança (SINGLE_TABLE)** | `Usuario` → `Gestor` (ADMIN) e `Motorista` (USER) | `models/Usuario.java` |
| **Chave Composta** | `PontoParadaId` com `@EmbeddedId` | `models/PontoParadaId.java` |
| **Embedded** | `CoordenadaGPS` (latitude/longitude) dentro de `SensorLeitura` | `models/CoordenadaGPS.java` |
| **Múltiplos relacionamentos** | `@OneToMany`, `@ManyToOne`, `@MapsId` | Todas as entidades |

---

## 📡 Endpoints da API

### 🔑 Autenticação — `/auth`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| POST | `/auth/register` | Registra novo usuário | ❌ |
| POST | `/auth/login` | Login e retorna token JWT | ❌ |

### 📦 Cargas — `/cargas`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| GET | `/cargas` | Lista todas as cargas | ✅ |
| GET | `/cargas/{id}` | Busca carga por ID | ✅ |
| POST | `/cargas` | Cria nova carga | ✅ |
| PUT | `/cargas/{id}` | Atualiza carga existente | ✅ |
| DELETE | `/cargas/{id}` | Remove carga | ✅ |

### 🚨 Alertas — `/alertas`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| GET | `/alertas` | Lista todos os alertas | ✅ |
| GET | `/alertas/{id}` | Busca alerta por ID | ✅ |
| POST | `/alertas` | Cria novo alerta | ✅ |
| PUT | `/alertas/{id}` | Atualiza alerta | ✅ |
| DELETE | `/alertas/{id}` | Remove alerta | ✅ |

### 🏷️ Tipos de Carga — `/tipos-carga`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| GET | `/tipos-carga` | Lista tipos de carga | ✅ |
| GET | `/tipos-carga/{id}` | Busca tipo por ID | ✅ |
| POST | `/tipos-carga` | Cria tipo de carga | ✅ |
| PUT | `/tipos-carga/{id}` | Atualiza tipo de carga | ✅ |
| DELETE | `/tipos-carga/{id}` | Remove tipo de carga | ✅ |

### 📊 Dashboard — `/dashboard`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| GET | `/dashboard` | Estatísticas gerais (cargas, alertas) | ✅ |

### 🌡️ IoT — `/api/iot`
| Método | Endpoint | Descrição | Auth? |
|---|---|---|---|
| POST | `/api/iot/leituras` | Recebe telemetria de sensor embarcado | ✅ |

> Todos os endpoints protegidos retornam links HATEOAS para navegação da API.

---

## 🔐 Segurança — Autenticação JWT

O fluxo de autenticação segue o padrão **Stateless JWT**:

```
1. POST /auth/register  → cadastra usuário com senha encriptada (BCrypt)
2. POST /auth/login     → retorna { token, id, nome }
3. Bearer {token}       → header Authorization em todas as requisições protegidas
4. SecurityFilter       → intercepta, valida e injeta o usuário no SecurityContext
```

**Exemplo de fluxo no Swagger:**
1. Execute `POST /auth/login` com suas credenciais
2. Copie o valor do campo `token` da resposta
3. Clique em **Authorize 🔓** no topo do Swagger
4. Cole `Bearer SEU_TOKEN_AQUI` e confirme

---

## ▶️ Como Executar Localmente

### Pré-requisitos
- Java 17+
- Maven 3.9+
- (Opcional) Oracle Database — o projeto usa H2 por padrão em perfil local

### Passo a Passo

```bash
# 1. Clone o repositório
git clone https://github.com/gabrielalandim/orbifreight-api.git
cd orbifreight-api

# 2. Execute com Maven (usa H2 in-memory por padrão)
./mvnw spring-boot:run

# 3. Acesse a documentação
# http://localhost:8080/swagger-ui/index.html
```

### Variáveis de Ambiente (Produção Oracle)

```properties
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@<host>:1521/<service>
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
JWT_SECRET=sua_chave_secreta_jwt
```

### Testando a API

**1. Registrar usuário:**
```json
POST /auth/register
{
  "nome": "Gestor Teste",
  "email": "gestor@orbifreight.com",
  "senha": "senha123",
  "cargo": "ADMIN"
}
```

**2. Fazer login e obter token:**
```json
POST /auth/login
{
  "email": "gestor@orbifreight.com",
  "senha": "senha123"
}
```

**3. Criar tipo de carga (com Bearer token):**
```json
POST /tipos-carga
Authorization: Bearer <token>
{
  "nome": "Farmacêutico Refrigerado",
  "tempMin": 2.0,
  "tempMax": 8.0,
  "umidadeMax": 60.0,
  "prazoMaxHoras": 48
}
```

---

## 👩‍💻 Integrantes

| Nome | RM | Turma  |
|---|---|--------|
| Maria Gabriela Landim Severo | RM565146 | 2TDSR  |
| Eduarda Weiss Ventura | RM564434 | 2TDPX  |
| Samara Porto Souza | RM559072 | 2TDSR  |
| Lucas Nunes Soares | RM566503 | 2TDSPX |
| Camilly Vitoria Pereira Maciel | RM566520 | 2TDSPX |

---

<div align="center">

**Global Solution 2026 — Java Advanced | FIAP**

</div>
