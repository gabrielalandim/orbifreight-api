<div align="center">

<img src="https://img.shields.io/badge/FIAP-Global_Solution_2025-ED1C24?style=for-the-badge&logoColor=white" />

# 🚚 OrbiFreight API

### *Do espaço para a estrada — monitoramento inteligente de cargas perecíveis*

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![Oracle](https://img.shields.io/badge/Oracle-DB-F80000?style=for-the-badge&logo=oracle&logoColor=white)](https://www.oracle.com/database/)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI_3-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)
[![IoT](https://img.shields.io/badge/IoT-ESP32_+_DHT22-00979D?style=for-the-badge&logo=arduino&logoColor=white)]()

---

[🌐 Deploy](#-links-do-projeto) • [📖 Swagger](#-documentação-da-api-swagger) • [🚀 Como Executar](#-como-executar) • [🔐 Autenticação](#-autenticação-jwt) • [📡 Endpoints](#-endpoints) • [🧪 Testes](#-testes-com-curl) • [🏗️ Arquitetura](#️-arquitetura-do-projeto)

</div>

---

## 🛸 A Visão — Por Que OrbiFreight?

As mesmas tecnologias que um dia garantirão que carne e vacinas não estraguem numa base em Marte — sensores contínuos, dados de satélite e automação de alertas — já existem hoje e podem resolver um problema crítico aqui na Terra: **o desperdício alimentar no transporte brasileiro**.

O Brasil perde cerca de **R$ 60 bilhões por ano** em alimentos deteriorados durante o transporte. A causa principal é simples: falta de monitoramento em tempo real das condições da carga. Um caminhão de carne refrigerada atravessa 800km sem nenhum sistema que alerte se a temperatura subiu 2 graus acima do limite.

O **OrbiFreight** conecta sensores IoT embarcados no baú do caminhão, dados climáticos de satélite (Open-Meteo), alertas de focos de incêndio da NASA FIRMS e inteligência artificial (Claude AI) para **prever e prevenir** a deterioração antes que ela aconteça — não depois.

---

## 👥 Equipe

| Nome                             | RM       | GitHub                                        |
|----------------------------------|----------|-----------------------------------------------|
| [Maria Gabriela Landim Severo]   | RM565146 | [@usuario](https://github.com/gabrielalandim) |
| [Eduarda Weiss Ventura]          | RM564434 | [@usuario](https://github.com/ewventura)      |
| [Samara Porto Souza]             | RM559072 | [@usuario](https://github.com/ssamaraps)      |
| [Lucas Nunes Soares]             | RM566503 | [@usuario](https://github.com/Hayzer3)        |
| [Camilly Vitoria Pereira Maciel] | RM566520 | [@usuario](https://github.com/camiexemplar)   |

**Turma:** [2TDSR e 2TDSPX] — FIAP 2025

---

## 🔗 Links do Projeto

| Recurso | Link |
|---------|------|
| 🌐 **API pública (deploy)** | `https://SEU-DEPLOY-URL.com` |
| 📖 **Swagger UI** | `https://SEU-DEPLOY-URL.com/swagger-ui/index.html` |
| 🎥 **Vídeo de Apresentação** (até 10 min) | `https://youtu.be/SEU-VIDEO` |
| 🎯 **Video Pitch** (até 3 min) | `https://youtu.be/SEU-PITCH` |
| 💻 **Repositório GitHub** | `https://github.com/gabrielalandim/orbifreight-api` |

---

## 🌐 Ecossistema do Projeto

O OrbiFreight é um sistema multidisciplinar. Esta API Java é o **núcleo central** que integra todos os módulos:

| Matéria | Tecnologia | Repositório |
|---------|-----------|-------------|
| ☕ **Java Advanced** (este repo) | Spring Boot 4 + Oracle | — |
| 🔷 **Advanced Business Development (.NET)** | ASP.NET Core 8 + PostgreSQL | [Link do repo] |
| 🐳 **DevOps & Cloud** | Docker + docker-compose | [Link do repo] |
| 🔌 **IoT & IA Generativa** | ESP32 + DHT22 + Wokwi | [Link do repo] |
| 🗄️ **Oracle PL/SQL** | Procedures + Triggers + Package | [Link do repo] |
| 📱 **Mobile (React Native)** | Expo + TypeScript | [Link do repo] |
| 📐 **Compliance & QA** | ArchiMate + TOGAF | [Link do repo] |

---

## ⚙️ Como o Sistema Funciona — Do Sensor ao Alerta

```
┌─────────────────────────────────────────────────────────────────────┐
│                        FLUXO COMPLETO                               │
│                                                                     │
│  1. Gestor cadastra a carga no App Mobile                           │
│     (tipo, limites de temperatura/umidade, veículo, rota)           │
│                          │                                          │
│                          ▼                                          │
│  2. ESP32 no baú lê DHT22 a cada 30 segundos                        │
│     → Exibe no LCD 16x2 → LED verde aceso                           │
│                          │                                          │
│                          ▼                                          │
│  3. ESP32 envia JSON via Wi-Fi para a API Java                       │
│     POST /api/iot/leituras                                          │
│     { "carga_id": 42, "temperatura": 9.2, "umidade": 78 }          │
│                          │                                          │
│                          ▼                                          │
│  4. API Java consulta dados externos em paralelo                    │
│     ├── Open-Meteo → temperatura/umidade externa na rota            │
│     └── NASA FIRMS → focos de incêndio próximos                     │
│                          │                                          │
│                          ▼                                          │
│  5. RiskCalculatorService gera score 0–100                          │
│     Se score > 70 → chama Claude AI                                 │
│     Claude responde em português com recomendação de rota           │
│                          │                                          │
│                          ▼                                          │
│  6. Trigger Oracle dispara automaticamente                          │
│     → Insere ALERTA sem intervenção humana                          │
│                          │                                          │
│                          ▼                                          │
│  7. OSRM calcula rota alternativa mais segura                        │
│                          │                                          │
│                          ▼                                          │
│  8. App Mobile → notificação push + mapa atualizado                 │
│     ESP32 → LED vermelho + buzzer acionado na carga                 │
│                          │                                          │
│                          ▼                                          │
│  9. Tudo registrado no Oracle para auditoria completa               │
└─────────────────────────────────────────────────────────────────────┘
```

---

## 🛠️ Tecnologias Utilizadas

### Backend (este repositório)

| Tecnologia | Versão | Finalidade |
|------------|--------|-----------|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework principal |
| Spring Data JPA | — | ORM e persistência |
| Spring Security | — | Autenticação e autorização |
| Spring Validation | — | Validação de dados de entrada |
| Spring HATEOAS | — | Links de navegação nas respostas REST |
| Spring Cloud OpenFeign | 2025.1.1 | Chamadas a Open-Meteo e NASA FIRMS |
| Spring Boot DevTools | — | Produtividade no desenvolvimento |
| Auth0 Java JWT | 4.4.0 | Geração e validação de tokens JWT |
| Springdoc OpenAPI | 2.8.4 | Documentação Swagger/OpenAPI 3 |
| Lombok | — | Redução de boilerplate |
| Oracle OJDBC11 | — | Driver banco de dados Oracle |
| Maven | — | Gerenciador de dependências |

### Integrações externas

| Serviço | Finalidade |
|---------|-----------|
| 🌤️ **Open-Meteo API** | Temperatura e umidade externas nas coordenadas da rota |
| 🔥 **NASA FIRMS API** | Focos de incêndio ativos no raio da rota |
| 🤖 **Claude AI (Anthropic)** | Geração de texto de alerta em português com recomendação de rota |
| 🗺️ **OSRM** | Cálculo de rota alternativa segura |

---

## 🏗️ Arquitetura do Projeto

```
orbifreight-api/
├── src/main/java/br/com/fiap/orbifreight/
│   ├── OrbifreightApplication.java
│   ├── config/
│   │   ├── SecurityConfig.java        # Spring Security + CORS
│   │   ├── SecurityFilter.java        # Filtro JWT por requisição
│   │   └── TokenService.java          # Geração/validação JWT (HMAC256)
│   ├── controllers/
│   │   ├── AuthController.java        # POST /auth/login, /auth/register
│   │   ├── CargaController.java       # CRUD /cargas (com HATEOAS)
│   │   ├── TipoCargaController.java   # CRUD /tipos-carga (com HATEOAS)
│   │   ├── AlertaController.java      # CRUD /alertas (com HATEOAS)
│   │   ├── DashboardController.java   # GET /dashboard
│   │   └── IoTController.java         # POST /api/iot/leituras
│   ├── dtos/                          # Java Records (Request/Response)
│   │   ├── CargaRequestDTO.java
│   │   ├── CargaResponseDTO.java
│   │   ├── AlertaRequestDTO.java
│   │   ├── AlertaResponseDTO.java
│   │   ├── TipoCargaRequestDTO.java
│   │   ├── TipoCargaResponseDTO.java
│   │   ├── DashboardResponseDTO.java
│   │   ├── LoginRequestDTO.java
│   │   ├── LoginResponseDTO.java
│   │   ├── RegisterRequestDTO.java
│   │   ├── LeituraIoTRequest.java
│   │   └── ErrorResponseDTO.java
│   ├── exceptions/
│   │   └── GlobalExceptionHandler.java  # @RestControllerAdvice
│   ├── models/
│   │   ├── Usuario.java                 # Implements UserDetails
│   │   ├── Gestor.java                  # extends Usuario (@Inheritance)
│   │   ├── Motorista.java               # extends Usuario (@Inheritance)
│   │   ├── Carga.java
│   │   ├── TipoCarga.java
│   │   ├── Alerta.java                  # Com @PrePersist
│   │   ├── SensorLeitura.java
│   │   └── CoordenadaGPS.java           # @Embeddable
│   ├── repositories/
│   │   ├── CargaRepository.java
│   │   ├── TipoCargaRepository.java
│   │   ├── AlertaRepository.java
│   │   └── UsuarioRepository.java
│   └── services/
│       ├── CargaService.java
│       ├── TipoCargaService.java
│       ├── AlertaService.java           # Com @Transactional
│       ├── DashboardService.java
│       ├── RiskCalculatorService.java   # Score 0–100 + integração Claude AI
│       └── AuthorizationService.java    # UserDetailsService
└── src/main/resources/
    └── application.properties
```

### Fluxo de uma requisição autenticada

```
Cliente HTTP
    │
    ▼
SecurityFilter ──── valida JWT do header Authorization
    │
    ▼
Controller ─────── @Valid valida RequestDTO + @Operation documenta
    │
    ▼
Service ─────────── lógica de negócio + @Transactional nos escritos
    │
    ▼
Repository ─────── JpaRepository → Oracle DB
    │
    ▼
ResponseEntity ─── EntityModel<DTO> com links HATEOAS
                      ├── 201 Created  (POST)
                      ├── 200 OK       (GET, PUT)
                      └── 204 No Content (DELETE)
```

---

## 🗄️ Modelagem do Banco de Dados

### Diagrama de Entidades

```
USUARIO (superclasse — herança SINGLE_TABLE)
  id (PK) │ nome │ email (UNIQUE) │ senha (BCrypt) │ cargo │ dtype
       ├── GESTOR  (dtype = 'GESTOR')
       └── MOTORISTA (dtype = 'MOTORISTA')

TIPO_CARGA                    CARGA
  id (PK)          1──────N    id (PK)
  nome                         tipo_id (FK)
  temp_min                     veiculo_id
  temp_max                     motorista_id
  umidade_max                  placa_veiculo
  prazo_max_horas              origem / destino
                               temp_min / temp_max
                               umidade_max
                               status
                                   │ 1
                                   │
                        ┌──────────┴──────────┐
                        │ N                   │ N
                     ALERTA            SENSOR_LEITURA
                      id (PK)            id (PK)
                      carga_id (FK)      carga_id (FK)
                      titulo             temperatura
                      descricao          umidade
                      nivel              latitude  ┐ @Embedded
                      status             longitude ┘ CoordenadaGPS
                      data_criacao       data_hora_leitura
```

### Recursos de modelagem avançada

| Recurso JPA | Onde é usado |
|-------------|--------------|
| `@Inheritance(SINGLE_TABLE)` | `Usuario` → `Gestor`, `Motorista` |
| `@Embeddable` / `@Embedded` | `CoordenadaGPS` dentro de `SensorLeitura` |
| `@ManyToOne` | `Carga → TipoCarga`, `Alerta → Carga`, `SensorLeitura → Carga` |
| `@OneToMany` + `cascade` + `orphanRemoval` | `Carga → List<Alerta>` |
| `@PrePersist` | `Alerta.dataCriacao` — timestamp automático |
| `UserDetails` | `Usuario` implementa a interface do Spring Security |
| `FetchType.LAZY` | Todos os `@ManyToOne` |

---

## 🚀 Como Executar

### Pré-requisitos

- [Java 21+](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/)
- Acesso ao banco Oracle FIAP
- [Git](https://git-scm.com/)

### 1. Clone o repositório

```bash
git clone https://github.com/gabrielalandim/orbifreight-api.git
cd orbifreight-api
```

### 2. Configure o banco de dados

Edite `src/main/resources/application.properties`:

```properties
# Conexão Oracle FIAP
spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Secret
api.security.token.secret=sua-chave-secreta-forte-aqui
```

> ⚠️ **Nunca versione credenciais no GitHub.** Use variáveis de ambiente em produção.

### 3. Execute

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### 4. Acesse

| Recurso | URL |
|---------|-----|
| API Base | `http://localhost:8080` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |

---

## 🔐 Autenticação JWT

A API utiliza **JWT stateless** com Spring Security. Todos os endpoints, exceto `/auth/login` e `/auth/register`, exigem token válido no header `Authorization`.

### Roles disponíveis

| Cargo | Roles |
|-------|-------|
| `ADMIN` | `ROLE_ADMIN`, `ROLE_USER` |
| `GESTOR` | `ROLE_USER` |
| `MOTORISTA` | `ROLE_USER` |

### Fluxo

```
1. POST /auth/register  →  cria usuário (senha salva com BCrypt)
2. POST /auth/login     →  valida e retorna token JWT (expira em 2h)
3. Demais endpoints     →  Authorization: Bearer <token>
```

---

## 📡 Endpoints

### 🔑 Autenticação — `/auth`

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `POST` | `/auth/register` | Registra novo usuário | ❌ Público |
| `POST` | `/auth/login` | Autentica e retorna JWT | ❌ Público |

---

### 📦 Cargas — `/cargas`

| Método | Endpoint | Descrição | Auth | Status |
|--------|----------|-----------|------|--------|
| `POST` | `/cargas` | Cria nova carga | ✅ JWT | `201` |
| `GET` | `/cargas` | Lista todas as cargas | ✅ JWT | `200` |
| `GET` | `/cargas/{id}` | Busca carga por ID | ✅ JWT | `200` |
| `PUT` | `/cargas/{id}` | Atualiza carga por ID | ✅ JWT | `200` |
| `DELETE` | `/cargas/{id}` | Remove carga por ID | ✅ JWT | `204` |

**Status disponíveis:** `ATIVA`, `EM_TRANSITO`, `ENTREGUE`, `CANCELADA`

**Exemplo de resposta com HATEOAS:**
```json
{
  "id": 1,
  "tipoId": 1,
  "placaVeiculo": "ABC-1234",
  "origem": "São Paulo - SP",
  "destino": "Rio de Janeiro - RJ",
  "tempMin": 0.0,
  "tempMax": 7.0,
  "umidadeMax": 85.0,
  "status": "ATIVA",
  "_links": {
    "self": { "href": "http://localhost:8080/cargas/1" },
    "cargas": { "href": "http://localhost:8080/cargas" },
    "alertas": { "href": "http://localhost:8080/alertas" }
  }
}
```

---

### 🏷️ Tipos de Carga — `/tipos-carga`

| Método | Endpoint | Descrição | Auth | Status |
|--------|----------|-----------|------|--------|
| `POST` | `/tipos-carga` | Cria tipo de carga | ✅ JWT | `201` |
| `GET` | `/tipos-carga` | Lista todos os tipos | ✅ JWT | `200` |
| `GET` | `/tipos-carga/{id}` | Busca tipo por ID | ✅ JWT | `200` |
| `PUT` | `/tipos-carga/{id}` | Atualiza tipo por ID | ✅ JWT | `200` |
| `DELETE` | `/tipos-carga/{id}` | Remove tipo por ID | ✅ JWT | `204` |

---

### 🚨 Alertas — `/alertas`

| Método | Endpoint | Descrição | Auth | Status |
|--------|----------|-----------|------|--------|
| `POST` | `/alertas` | Cria novo alerta | ✅ JWT | `201` |
| `GET` | `/alertas` | Lista todos os alertas | ✅ JWT | `200` |
| `GET` | `/alertas/{id}` | Busca alerta por ID | ✅ JWT | `200` |
| `PUT` | `/alertas/{id}` | Atualiza alerta por ID | ✅ JWT | `200` |
| `DELETE` | `/alertas/{id}` | Remove alerta por ID | ✅ JWT | `204` |

**Níveis:** `BAIXO`, `MEDIO`, `ALTO`, `CRITICO`  
**Status:** `ABERTO`, `RESOLVIDO`

---

### 📊 Dashboard — `/dashboard`

| Método | Endpoint | Descrição | Auth | Status |
|--------|----------|-----------|------|--------|
| `GET` | `/dashboard` | Estatísticas gerais em tempo real | ✅ JWT | `200` |

---

### 🌡️ IoT — `/api/iot`

| Método | Endpoint | Descrição | Auth | Status |
|--------|----------|-----------|------|--------|
| `POST` | `/api/iot/leituras` | Recebe leitura de sensor ESP32 | ✅ JWT | `200` |

Chamado automaticamente pelo **ESP32** a cada 30 segundos com dados do DHT22.

---

## 🧪 Testes com cURL

Siga a sequência abaixo para testar o fluxo completo da API.

> **Dica:** após o login, salve o token numa variável:
> ```bash
> export TOKEN="cole-seu-token-aqui"
> ```

---

### 🔑 Etapa 1 — Autenticação

#### Registrar usuário

```bash
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Admin OrbiFreight",
    "email": "admin@orbifreight.com",
    "senha": "admin123",
    "cargo": "ADMIN"
  }'
```

**Resposta:** `200 OK`

---

#### Login e obtenção do token

```bash
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@orbifreight.com",
    "senha": "admin123"
  }'
```

**Resposta `200 OK`:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "id": 1,
  "nome": "Admin OrbiFreight"
}
```

---

### 🏷️ Etapa 2 — Tipos de Carga

#### Criar — Carne Bovina

```bash
curl -X POST http://localhost:8080/tipos-carga \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Carne Bovina Refrigerada",
    "tempMin": 0.0,
    "tempMax": 7.0,
    "umidadeMax": 85.0,
    "prazoMaxHoras": 48
  }'
```

**Resposta `201 Created`:**
```json
{
  "id": 1,
  "nome": "Carne Bovina Refrigerada",
  "tempMin": 0.0,
  "tempMax": 7.0,
  "umidadeMax": 85.0,
  "prazoMaxHoras": 48,
  "_links": {
    "self": { "href": "http://localhost:8080/tipos-carga/1" },
    "tipos-carga": { "href": "http://localhost:8080/tipos-carga" }
  }
}
```

---

#### Criar — Vacinas

```bash
curl -X POST http://localhost:8080/tipos-carga \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Vacinas Imunológicas",
    "tempMin": 2.0,
    "tempMax": 8.0,
    "umidadeMax": 60.0,
    "prazoMaxHoras": 24
  }'
```

---

#### Criar — Laticínios

```bash
curl -X POST http://localhost:8080/tipos-carga \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Laticínios",
    "tempMin": 1.0,
    "tempMax": 6.0,
    "umidadeMax": 80.0,
    "prazoMaxHoras": 36
  }'
```

---

#### Listar todos

```bash
curl -X GET http://localhost:8080/tipos-carga \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Buscar por ID

```bash
curl -X GET http://localhost:8080/tipos-carga/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Atualizar

```bash
curl -X PUT http://localhost:8080/tipos-carga/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "nome": "Carne Bovina Refrigerada Premium",
    "tempMin": 0.0,
    "tempMax": 6.0,
    "umidadeMax": 85.0,
    "prazoMaxHoras": 48
  }'
```

---

#### Deletar

```bash
curl -X DELETE http://localhost:8080/tipos-carga/3 \
  -H "Authorization: Bearer $TOKEN"
```

**Resposta:** `204 No Content`

---

### 📦 Etapa 3 — Cargas

#### Criar

```bash
curl -X POST http://localhost:8080/cargas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "tipoId": 1,
    "veiculoId": 10,
    "motoristaId": 5,
    "placaVeiculo": "ABC-1234",
    "origem": "São Paulo - SP",
    "destino": "Rio de Janeiro - RJ",
    "tempMin": 0.0,
    "tempMax": 7.0,
    "umidadeMax": 85.0,
    "status": "ATIVA"
  }'
```

**Resposta `201 Created`:**
```json
{
  "id": 1,
  "tipoId": 1,
  "veiculoId": 10,
  "motoristaId": 5,
  "placaVeiculo": "ABC-1234",
  "origem": "São Paulo - SP",
  "destino": "Rio de Janeiro - RJ",
  "tempMin": 0.0,
  "tempMax": 7.0,
  "umidadeMax": 85.0,
  "status": "ATIVA",
  "_links": {
    "self": { "href": "http://localhost:8080/cargas/1" },
    "cargas": { "href": "http://localhost:8080/cargas" },
    "alertas": { "href": "http://localhost:8080/alertas" }
  }
}
```

---

#### Listar todas

```bash
curl -X GET http://localhost:8080/cargas \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Buscar por ID

```bash
curl -X GET http://localhost:8080/cargas/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Atualizar status para EM_TRANSITO

```bash
curl -X PUT http://localhost:8080/cargas/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "tipoId": 1,
    "veiculoId": 10,
    "motoristaId": 5,
    "placaVeiculo": "ABC-1234",
    "origem": "São Paulo - SP",
    "destino": "Rio de Janeiro - RJ",
    "tempMin": 0.0,
    "tempMax": 7.0,
    "umidadeMax": 85.0,
    "status": "EM_TRANSITO"
  }'
```

---

#### Deletar

```bash
curl -X DELETE http://localhost:8080/cargas/1 \
  -H "Authorization: Bearer $TOKEN"
```

**Resposta:** `204 No Content`

---

### 🚨 Etapa 4 — Alertas

#### Criar alerta crítico

```bash
curl -X POST http://localhost:8080/alertas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "cargaId": 1,
    "titulo": "Temperatura crítica detectada",
    "descricao": "A temperatura atingiu 9.2°C, ultrapassando o limite de 7°C. Temperatura externa de 38°C prevista nos próximos 40km. Foco de incêndio a 28km da rota. Desvio pela BR-116 recomendado.",
    "nivel": "CRITICO",
    "status": "ABERTO"
  }'
```

**Resposta `201 Created`:**
```json
{
  "id": 1,
  "cargaId": 1,
  "titulo": "Temperatura crítica detectada",
  "descricao": "A temperatura atingiu 9.2°C...",
  "nivel": "CRITICO",
  "status": "ABERTO",
  "dataCriacao": "2025-06-07T14:30:00",
  "_links": {
    "self": { "href": "http://localhost:8080/alertas/1" },
    "alertas": { "href": "http://localhost:8080/alertas" },
    "carga": { "href": "http://localhost:8080/cargas/1" }
  }
}
```

---

#### Resolver alerta

```bash
curl -X PUT http://localhost:8080/alertas/1 \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "cargaId": 1,
    "titulo": "Temperatura crítica detectada",
    "descricao": "Temperatura normalizada após desvio de rota. Carga dentro dos limites.",
    "nivel": "CRITICO",
    "status": "RESOLVIDO"
  }'
```

---

#### Listar todos

```bash
curl -X GET http://localhost:8080/alertas \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Buscar por ID

```bash
curl -X GET http://localhost:8080/alertas/1 \
  -H "Authorization: Bearer $TOKEN"
```

---

#### Deletar

```bash
curl -X DELETE http://localhost:8080/alertas/1 \
  -H "Authorization: Bearer $TOKEN"
```

**Resposta:** `204 No Content`

---

### 📊 Etapa 5 — Dashboard

```bash
curl -X GET http://localhost:8080/dashboard \
  -H "Authorization: Bearer $TOKEN"
```

**Resposta `200 OK`:**
```json
{
  "totalCargas": 5,
  "cargasAtivas": 3,
  "alertasAbertos": 2,
  "alertasCriticos": 1
}
```

---

### 🌡️ Etapa 6 — Simulação de Leitura IoT (ESP32)

```bash
curl -X POST http://localhost:8080/api/iot/leituras \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "carga_id": 1,
    "temperatura": 9.5,
    "umidade": 72.3,
    "risco": "ALTO"
  }'
```

**Resposta `200 OK`:**
```
"Dados recebidos com sucesso pelo Java!"
```

> Após esta leitura com `temperatura 9.5°C > tempMax 7.0°C`, o Trigger Oracle dispara automaticamente e insere um registro em `ALERTA` sem nenhuma intervenção humana.

---

### ❌ Testando Validações

#### Corpo incompleto — campos obrigatórios

```bash
curl -X POST http://localhost:8080/cargas \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{ "tipoId": 1 }'
```

**Resposta `400 Bad Request`:**
```json
{
  "placaVeiculo": "A placa do veículo é obrigatória",
  "origem": "O ponto de origem é obrigatório",
  "destino": "O ponto de destino é obrigatório",
  "status": "O status da carga não pode estar em branco",
  "veiculoId": "O ID do veículo é obrigatório",
  "motoristaId": "O ID do motorista é obrigatório"
}
```

---

#### Recurso inexistente

```bash
curl -X GET http://localhost:8080/cargas/99999 \
  -H "Authorization: Bearer $TOKEN"
```

**Resposta `404 Not Found`:**
```json
{
  "timestamp": "2025-06-07T14:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Carga não encontrada com o ID: 99999",
  "path": "/cargas/99999"
}
```

---

#### Acesso sem token

```bash
curl -X GET http://localhost:8080/cargas
```

**Resposta:** `403 Forbidden`

---

## 📖 Documentação da API (Swagger)

A documentação interativa completa está disponível via **Swagger UI**:

```
# Local
http://localhost:8080/swagger-ui/index.html

# Produção
https://SEU-DEPLOY-URL.com/swagger-ui/index.html
```

Todos os controllers possuem anotações `@Tag`, `@Operation` e `@ApiResponse`, gerando documentação detalhada com descrição de cada endpoint, schemas de request/response e exemplos de uso.

Para autenticar no Swagger UI:
1. Faça login em `POST /auth/login`
2. Copie o token da resposta
3. Clique no botão **Authorize 🔒** no topo da página
4. Cole `Bearer <seu-token>` e confirme

---

## 🔒 Segurança

| Aspecto | Implementação |
|---------|--------------|
| Criptografia de senhas | BCrypt (Spring Security) |
| Autenticação | JWT assinado com HMAC256 |
| Expiração do token | 2 horas |
| Sessão | Stateless — sem estado no servidor |
| Rotas públicas | Apenas `/auth/login` e `/auth/register` |
| Autorização | `ROLE_ADMIN` e `ROLE_USER` |
| CORS | Configurado para qualquer origem |
| Injeção SQL | Prevenida via JPA/Hibernate parametrizado |

---

## ⚙️ Variáveis de Ambiente (Produção)

| Variável | Descrição |
|----------|-----------|
| `SPRING_DATASOURCE_URL` | URL JDBC do banco Oracle |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |
| `API_SECURITY_TOKEN_SECRET` | Chave secreta do JWT |

**Exemplo no Railway / Render:**
```
SPRING_DATASOURCE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
SPRING_DATASOURCE_USERNAME=rm000000
SPRING_DATASOURCE_PASSWORD=sua-senha
API_SECURITY_TOKEN_SECRET=chave-super-secreta-2025
```

---

<div align="center">

**OrbiFreight** — Desenvolvido com ☕ Java e 🚀 Spring Boot

FIAP — Global Solution 2025

*"Do espaço para a estrada."*

</div>