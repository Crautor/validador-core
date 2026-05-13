# validator-core

Biblioteca Maven para validação de documentos financeiros (CPF, CNPJ, SSN).  
Não possui framework — é importada como dependência por outros projetos.

## Como instalar

```bash
mvn clean install
```

O artefato fica disponível no repositório local (`~/.m2`) para outros projetos Maven.

## Como declarar como dependência

```xml
<dependency>
    <groupId>com.validador</groupId>
    <artifactId>validator-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Domínio

### Sealed Interface + Records (Java 21)

```java
public sealed interface Documento permits Cpf, Cnpj, Ssn {
    String numero();
    boolean isValido();
}

public record Cpf(String numero)  implements Documento { ... }
public record Cnpj(String numero) implements Documento { ... }
public record Ssn(String numero)  implements Documento { ... }
```

A sealed interface garante que apenas `Cpf`, `Cnpj` e `Ssn` possam implementar `Documento`.  
Os records são imutáveis e validam o número no construtor compacto.

### Motor de Validação — Pattern Matching (Java 21)

```java
public ResultadoValidacao validar(Documento documento) {
    return switch (documento) {
        case Cpf cpf   -> validarCpf(cpf);
        case Cnpj cnpj -> validarCnpj(cnpj);
        case Ssn ssn   -> validarSsn(ssn);
    };
}
```

O switch é **exaustivo**: o compilador exige que todos os subtipos da sealed interface sejam tratados.

## Exemplo de uso

```java
ValidadorDocumentoService service = new ValidadorDocumentoService();

ResultadoValidacao r = service.validar(new Cpf("123.456.789-00"));
System.out.println(r.valido());    // true
System.out.println(r.mensagem());  // "CPF válido"

r = service.validar(new Cnpj("00.000.000/0000-00"));
System.out.println(r.valido());    // false
System.out.println(r.mensagem());  // "CNPJ inválido: deve conter 14 dígitos numéricos"
```

## Estrutura

```
src/main/java/com/validador/core/
├── domain/
│   ├── Documento.java                  # Sealed interface
│   ├── Cpf.java                        # Record — 11 dígitos
│   ├── Cnpj.java                       # Record — 14 dígitos
│   └── Ssn.java                        # Record — 9 dígitos
└── service/
    ├── ValidadorDocumentoService.java  # Pattern Matching Java 21
    └── ResultadoValidacao.java         # Record de retorno
```

## Requisitos

- Java 21
- Maven 3.6+
