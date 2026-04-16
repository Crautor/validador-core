# validador-core

### 📦 Componentes

- **🔐 AUTH**: Autenticação e validação de usuários
- **📦 CATALOG**: Gerenciamento de catálogo de produtos
- **🛒 CART**: Gerenciamento de carrinho de compras
- **💳 PAYMENT**: Processamento de pagamentos
- **📧 EMAIL**: Envio de notificações por e-mail

### 🔗 Dependências Entre Componentes

```
CART → CATALOG (consulta produtos)
PAYMENT → CART (obtém dados do carrinho)
PAYMENT → EMAIL (envia confirmação)
```

---

## 🚀 Como Executar

### Pré-requisitos
- Java 21
- Maven 3.6+

### Comando de Execução
```bash
cd core
mvn spring-boot:run
```

A aplicação ficará disponível em: `http://localhost:8080`

---

## 📡 API Endpoints

### 🔐 **AUTH (Autenticação)**
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/login` | Faz login com email/senha |
| `GET` | `/api/auth/validate/{token}` | Valida se um token JWT é válido |

**Exemplo de uso:**
```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"teste@example.com","password":"123"}'

# Validar token
curl http://localhost:8080/api/auth/validate/token-exemplo
```

### 📦 **CATALOG (Catálogo de Produtos)**
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/catalog/products` | Lista todos os produtos disponíveis |
| `GET` | `/api/catalog/products/{productId}` | Busca um produto específico por ID |
| `POST` | `/api/catalog/products` | Cria um novo produto no catálogo |

**Exemplo de uso:**
```bash
# Listar produtos
curl http://localhost:8080/api/catalog/products

# Buscar produto específico
curl http://localhost:8080/api/catalog/products/prod-1

# Criar novo produto
curl -X POST http://localhost:8080/api/catalog/products \
  -H "Content-Type: application/json" \
  -d '{
    "id": "prod-2",
    "name": "Novo Produto",
    "description": "Descrição do novo produto",
    "price": 149.99,
    "quantity": 25,
    "category": "Eletrônicos",
    "imageUrl": "https://example.com/prod-2.jpg",
    "available": true
  }'
```

### 🛒 **CART (Carrinho de Compras)**
*Requer: CATALOG (para validar produtos)*

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/cart/{userId}` | Retorna o carrinho do usuário |
| `POST` | `/api/cart/{userId}/add/{productId}` | Adiciona produto ao carrinho |
| `GET` | `/api/cart/{userId}/total` | Calcula o total do carrinho |

**Exemplo de uso:**
```bash
# Ver carrinho
curl http://localhost:8080/api/cart/user123

# Adicionar produto (quantidade padrão = 1)
curl -X POST http://localhost:8080/api/cart/user123/add/prod-1

# Adicionar produto com quantidade específica
curl -X POST "http://localhost:8080/api/cart/user123/add/prod-1?quantity=3"

# Ver total
curl http://localhost:8080/api/cart/user123/total
```

### 💳 **PAYMENT (Processamento de Pagamento)**
*Requer: CART (para obter dados) + EMAIL (para confirmação)*

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/payment/{userId}` | Processa pagamento do carrinho |
| `GET` | `/api/payment/transaction/{transactionId}` | Consulta status de uma transação |

**Exemplo de uso:**
```bash
# Processar pagamento
curl -X POST http://localhost:8080/api/payment/user123 \
  -H "Content-Type: application/json" \
  -d '{
    "cardNumber": "1234567890123456",
    "cardholderName": "João Silva",
    "expiryMonth": "12",
    "expiryYear": "2026",
    "cvv": "123",
    "billingEmail": "joao@example.com"
  }'

# Verificar status da transação
curl http://localhost:8080/api/payment/transaction/txn-123456789
```

### 📧 **EMAIL (Envio de E-mails)**
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/email/test/{userId}` | Envia e-mail de confirmação de teste |

**Exemplo de uso:**
```bash
# Enviar e-mail de teste
curl -X POST http://localhost:8080/api/email/test/user123
```

### 🏥 **Health Check**
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/health` | Verifica se todos os componentes estão funcionando |

**Exemplo de uso:**
```bash
curl http://localhost:8080/api/health
# Resposta: "Sistema funcionando! Componentes: AUTH, CATALOG, CART, PAYMENT, EMAIL"
```

---

## 🧪 Como Testar com Postman

### 📥 Importar Coleção
1. Baixe o arquivo `postman_collection.json` da raiz do projeto
2. Abra o Postman
3. Clique em **"Import"** 
4. Selecione **"File"** e escolha o `postman_collection.json`
5. A coleção "Validador Core API" será importada com todas as requests organizadas

### 📁 Estrutura da Coleção
```
Validador Core API/
├── 🏥 Health Check
├── 🔐 AUTH - Autenticação
├── 📦 CATALOG - Catálogo de Produtos
├── 🛒 CART - Carrinho de Compras
├── 💳 PAYMENT - Processamento de Pagamento
├── 📧 EMAIL - Envio de E-mails
└── 🔄 Fluxo Completo de Compra (8 passos)
```

### ⚙️ Configuração
- **Base URL**: `http://localhost:8080` (configurada como variável)
- **Headers**: `Content-Type: application/json` (já configurado nos requests POST)
- **Corpo das Requests**: JSON já preenchido com exemplos

### 🚀 Como Usar
1. Certifique-se que a aplicação está rodando (`mvn spring-boot:run`)
2. Execute os requests na ordem do "Fluxo Completo de Compra"
3. Cada request tem descrição e corpo já configurado
4. Use as variáveis do Postman para personalizar os dados

---

## 🔄 Fluxo Completo de Compra

```bash
# 0. OPCIONAL: Criar um novo produto
curl -X POST http://localhost:8080/api/catalog/products \
  -H "Content-Type: application/json" \
  -d '{
    "id": "prod-2",
    "name": "Smartphone XYZ",
    "description": "Smartphone de última geração",
    "price": 1999.99,
    "quantity": 50,
    "category": "Eletrônicos",
    "imageUrl": "https://example.com/smartphone.jpg",
    "available": true
  }'

# 1. Health check
curl http://localhost:8080/api/health

# 2. Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"cliente@example.com","password":"senha123"}'

# 3. Ver produtos disponíveis
curl http://localhost:8080/api/catalog/products

# 4. Adicionar produto ao carrinho
curl -X POST http://localhost:8080/api/cart/user123/add/prod-2

# 5. Ver carrinho atualizado
curl http://localhost:8080/api/cart/user123

# 6. Verificar total
curl http://localhost:8080/api/cart/user123/total

# 7. Processar pagamento (PAYMENT usa CART e EMAIL)
curl -X POST http://localhost:8080/api/payment/user123 \
  -H "Content-Type: application/json" \
  -d '{
    "cardNumber": "4111111111111111",
    "cardholderName": "Cliente Teste",
    "expiryMonth": "12",
    "expiryYear": "2026",
    "cvv": "123",
    "billingEmail": "cliente@example.com"
  }'

# 8. Verificar status da transação
curl http://localhost:8080/api/payment/transaction/txn-123456789
```

---

## 🏛️ Arquitetura de Componentes

### Interfaces (Contratos)
- `IAuthService` - Contrato de autenticação
- `ICatalogService` - Contrato de catálogo
- `ICartService` - Contrato de carrinho
- `IPaymentService` - Contrato de pagamento
- `IEmailService` - Contrato de e-mail

### Implementações
- `AuthServiceImpl` - Implementa `IAuthService`
- `CatalogServiceImpl` - Implementa `ICatalogService`
- `CartServiceImpl` - Implementa `ICartService` (requer `ICatalogService`)
- `PaymentServiceImpl` - Implementa `IPaymentService` (requer `ICartService` + `IEmailService`)
- `EmailServiceImpl` - Implementa `IEmailService`

### DTOs (Data Transfer Objects)
- `auth/dto/` - `LoginRequest`, `AuthResponse`
- `catalog/dto/` - `ProductDTO`
- `cart/dto/` - `CartDTO`, `CartItemDTO`
- `payment/dto/` - `PaymentRequest`, `PaymentResponse`, `TransactionStatus`
- `email/dto/` - `EmailRequest`, `EmailResponse`

---

## 📊 Documentação Técnica

- **[ARCHITECTURE.md](./ARCHITECTURE.md)** - Documentação técnica completa
- **[REDESIGN.md](./REDESIGN.md)** - Guia prático em português- **[POSTMAN_GUIDE.md](./POSTMAN_GUIDE.md)** - Guia completo para testar com Postman- **[SUMMARY.md](./SUMMARY.md)** - Sumário executivo
- **[INDEX.md](./INDEX.md)** - Índice completo de arquivos

### 📁 Arquivos para Postman
- **`postman_collection.json`** - Coleção completa com todos os requests organizados
- **`postman_environment.json`** - Variáveis de ambiente pré-configuradas
- **`POSTMAN_GUIDE.md`** - Guia passo-a-passo detalhado

---

## 🧪 Como Testar

### Testes Unitários
```bash
mvn test
```

### Testes de Integração
```bash
mvn verify
```

### Build Completo
```bash
mvn clean package
```

---

## 📚 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 4.0.5**
- **Spring Web** (para REST API)
- **Maven** (gerenciamento de dependências)
- **JUnit** (testes)

