# validator-core

Biblioteca de domínio para validação de documentos financeiros. Não executa sozinha — seu objetivo é fornecer ativos reutilizáveis para outros projetos da empresa.

## Domínio

O modelo de documentos utiliza **Sealed Interfaces** e **Records** do Java 21:

```java
// Sealed Interface — contrato fechado; somente Cpf, Cnpj e Ssn podem implementar
public sealed interface Documento permits Cpf, Cnpj, Ssn {
    String numero();
    boolean isValido();
}

// Records — estruturas imutáveis com validação no construtor compacto
public record Cpf(String numero)  implements Documento { ... }
public record Cnpj(String numero) implements Documento { ... }
public record Ssn(String numero)  implements Documento { ... }
```

## Motor de Validação

`ValidadorDocumentoService` usa **Pattern Matching** com switch exaustivo (Java 21):

```java
public ResultadoValidacao validar(Documento documento) {
    return switch (documento) {
        case Cpf cpf   -> validarCpf(cpf);
        case Cnpj cnpj -> validarCnpj(cnpj);
        case Ssn ssn   -> validarSsn(ssn);
    };
}
```

O compilador garante exaustividade: qualquer novo subtipo adicionado à sealed interface causa erro de compilação se não for tratado no switch.

## Como Usar em Outro Projeto

### 1. Instalar no repositório local Maven

```bash
cd core
mvn clean install
```

O artefato `validator-core-1.0.0.jar` será instalado em `~/.m2`.

### 2. Declarar como dependência no `pom.xml`

```xml
<dependency>
    <groupId>com.validador</groupId>
    <artifactId>validator-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

### 3. Exemplo de uso

```java
ValidadorDocumentoService service = new ValidadorDocumentoService();

// Validar CPF
ResultadoValidacao r1 = service.validar(new Cpf("123.456.789-00"));
System.out.println(r1.valido());   // true
System.out.println(r1.mensagem()); // "CPF válido"

// Validar CNPJ
ResultadoValidacao r2 = service.validar(new Cnpj("12.345.678/0001-90"));
System.out.println(r2.valido());   // true

// Validar SSN
ResultadoValidacao r3 = service.validar(new Ssn("123-45-6789"));
System.out.println(r3.valido());   // true
```

## Pré-requisitos

- Java 21
- Maven 3.6+

## Build

```bash
cd core
mvn clean install
```

## Estrutura

```
core/
└── src/main/java/com/validador/core/
    ├── domain/
    │   ├── Documento.java                  # Sealed interface
    │   ├── Cpf.java                        # Record — CPF (11 dígitos)
    │   ├── Cnpj.java                       # Record — CNPJ (14 dígitos)
    │   └── Ssn.java                        # Record — Social Security Number (9 dígitos)
    └── service/
        ├── ValidadorDocumentoService.java  # Motor de validação com Pattern Matching
        └── ResultadoValidacao.java         # Record de retorno (valido, mensagem, tipo, numero)
```

## Coordenadas Maven

| Campo      | Valor            |
|------------|------------------|
| groupId    | com.validador    |
| artifactId | validator-core   |
| version    | 1.0.0            |
| packaging  | jar              |
