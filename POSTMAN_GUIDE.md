# 🧪 Guia Completo: Testando a API com Postman

## 📋 Pré-requisitos
- Postman instalado ([baixe aqui](https://www.postman.com/downloads/))
- Aplicação Spring Boot rodando (`mvn spring-boot:run`)
- Arquivos baixados:
  - `postman_collection.json` (coleção de requests)
  - `postman_environment.json` (variáveis de ambiente)

## 📥 Passo 1: Importar Coleção e Ambiente

### Importar Coleção:
1. Abra o Postman
2. Clique no botão **"Import"** (canto superior esquerdo)
3. Selecione a aba **"File"**
4. Clique em **"Upload Files"**
5. Selecione o arquivo `postman_collection.json`
6. Clique em **"Import"**

### Importar Ambiente:
1. Clique novamente em **"Import"**
2. Selecione a aba **"File"**
3. Clique em **"Upload Files"**
4. Selecione o arquivo `postman_environment.json`
5. Clique em **"Import"**

### Selecionar Ambiente:
1. No canto superior direito, clique no dropdown **"No Environment"**
2. Selecione **"Validador Core - Local"**
3. Agora as variáveis estarão disponíveis em todos os requests

## ⚙️ Passo 2: Configurar Variáveis

### Ambiente já configurado:
O arquivo `postman_environment.json` já vem com todas as variáveis necessárias:
- `baseUrl`: `http://localhost:8080`
- `userId`: `user123`
- `productId`: `prod-1`
- `transactionId`: `txn-123456789`
- `authToken`: (será preenchido após login)

### Verificar configuração:
1. No canto superior direito, verifique se **"Validador Core - Local"** está selecionado
2. Clique no ícone de olho ao lado para ver todas as variáveis
3. Todas devem estar preenchidas automaticamente

## 🏥 Passo 3: Teste Inicial (Health Check)

### Executar Health Check:
1. Abra a pasta **"🏥 Health Check"**
2. Clique no request **"Verificar saúde do sistema"**
3. Clique em **"Send"**

### Resposta Esperada:
```json
"Sistema funcionando! Componentes: AUTH, CATALOG, CART, PAYMENT, EMAIL"
```

✅ **Se funcionar**: Sua API está pronta para testes!

## 🔄 Passo 4: Fluxo Completo de Compra

### Ordem Recomendada de Execução:

#### 1. **Health Check** ✅
- Request: `GET /api/health`
- Verifica se todos os componentes estão funcionando

#### 2. **Login do Cliente**
- Request: `POST /api/auth/login`
- Body: `{"email":"cliente@example.com","password":"senha123"}`
- **Salve o token retornado** para usar em requests futuros

#### 3. **Ver Produtos Disponíveis**
- Request: `GET /api/catalog/products`
- Lista todos os produtos do catálogo

#### 4. **Adicionar Produto ao Carrinho**
- Request: `POST /api/cart/user123/add/prod-1`
- Adiciona o produto `prod-1` ao carrinho do usuário `user123`

#### 5. **Ver Carrinho Atualizado**
- Request: `GET /api/cart/user123`
- Mostra todos os itens no carrinho

#### 6. **Verificar Total**
- Request: `GET /api/cart/user123/total`
- Calcula o valor total do carrinho

#### 7. **Processar Pagamento**
- Request: `POST /api/payment/user123`
- Body com dados do cartão de crédito
- **IMPORTANTE**: Usa dados do CART e dispara EMAIL automaticamente

#### 8. **Verificar Status da Transação**
- Request: `GET /api/payment/transaction/txn-123456789`
- Consulta o status do pagamento processado

## 📁 Estrutura Detalhada da Coleção

### 🏥 Health Check
- **Verificar saúde do sistema**: `GET /api/health`

### 🔐 AUTH - Autenticação
- **Login**: `POST /api/auth/login` (com body JSON)
- **Validar Token**: `GET /api/auth/validate/{token}`

### 📦 CATALOG - Catálogo de Produtos
- **Listar Produtos**: `GET /api/catalog/products`
- **Buscar Produto por ID**: `GET /api/catalog/products/{productId}`
- **Criar Novo Produto**: `POST /api/catalog/products` ⭐ **NOVO**

#### Como Criar um Produto:
1. **Method**: POST
2. **URL**: `http://localhost:8080/api/catalog/products`
3. **Headers**: `Content-Type: application/json`
4. **Body** (JSON):
```json
{
  "id": "prod-2",
  "name": "Novo Produto",
  "description": "Descrição do novo produto",
  "price": 149.99,
  "quantity": 25,
  "category": "Eletrônicos",
  "imageUrl": "https://example.com/prod-2.jpg",
  "available": true
}
```

#### Campos Obrigatórios:
- `id`: Identificador único do produto
- `name`: Nome do produto
- `price`: Preço em decimal
- `quantity`: Quantidade em estoque
- `available`: true/false se está disponível

#### Campos Opcionais:
- `description`: Descrição detalhada
- `category`: Categoria do produto
- `imageUrl`: URL da imagem

### 🛒 CART - Carrinho de Compras
- **Ver Carrinho**: `GET /api/cart/{userId}`
- **Adicionar Produto (padrão)**: `POST /api/cart/{userId}/add/{productId}`
- **Adicionar Produto (com quantidade)**: `POST /api/cart/{userId}/add/{productId}?quantity=3`
- **Calcular Total**: `GET /api/cart/{userId}/total`

### 💳 PAYMENT - Processamento de Pagamento
- **Processar Pagamento**: `POST /api/payment/{userId}` (com dados do cartão)
- **Consultar Status**: `GET /api/payment/transaction/{transactionId}`

### 📧 EMAIL - Envio de E-mails
- **Enviar E-mail de Teste**: `POST /api/email/test/{userId}`

### 🔄 Fluxo Completo de Compra
- **8 requests numerados** (1-8) seguindo o fluxo completo
- Cada request tem descrição clara do passo
- Dados de exemplo já preenchidos

## 💡 Dicas de Uso

### 🔄 Testes Repetitivos:
- Use diferentes `userId` para testar cenários isolados
- Exemplo: `user123`, `user456`, `cliente001`

### 📊 Dados de Teste:
```json
// Login válido
{
  "email": "teste@example.com",
  "password": "123"
}

// Cartão de teste
{
  "cardNumber": "4111111111111111",
  "cardholderName": "Cliente Teste",
  "expiryMonth": "12",
  "expiryYear": "2026",
  "cvv": "123",
  "billingEmail": "cliente@example.com"
}
```

### 🐛 Debugging:
- Verifique sempre o **Status Code** da resposta
- Leia o **Response Body** para mensagens de erro
- Use o **Console** do Postman (View → Show Postman Console)

### 🔗 Dependências entre Componentes:
- **CART** requer **CATALOG** (validação de produtos)
- **PAYMENT** requer **CART** (dados do carrinho) + **EMAIL** (confirmação)

## 🚀 Próximos Passos

Após testar com Postman:
1. ✅ Implemente autenticação real (JWT)
2. ✅ Conecte com banco de dados
3. ✅ Implemente integrações reais (pagamento, email)
4. ✅ Adicione validações e tratamento de erros
5. ✅ Implemente testes automatizados

---

**📝 Nota**: Esta coleção foi criada para facilitar os testes da arquitetura modular. Todos os endpoints seguem os contratos das interfaces definidas no sistema.