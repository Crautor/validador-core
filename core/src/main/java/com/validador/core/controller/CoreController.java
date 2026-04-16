package com.validador.core.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.validador.core.auth.dto.AuthResponse;
import com.validador.core.auth.dto.LoginRequest;
import com.validador.core.cart.dto.CartDTO;
import com.validador.core.catalog.dto.ProductDTO;
import com.validador.core.interfaces.IAuthService;
import com.validador.core.interfaces.ICartService;
import com.validador.core.interfaces.ICatalogService;
import com.validador.core.interfaces.IEmailService;
import com.validador.core.interfaces.IPaymentService;
import com.validador.core.payment.dto.PaymentRequest;
import com.validador.core.payment.dto.PaymentResponse;
import com.validador.core.payment.dto.TransactionStatus;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CoreController {

    private final IAuthService authService;
    private final ICatalogService catalogService;
    private final ICartService cartService;
    private final IPaymentService paymentService;
    private final IEmailService emailService;

    public CoreController(IAuthService authService, ICatalogService catalogService,
                         ICartService cartService, IPaymentService paymentService,
                         IEmailService emailService) {
        this.authService = authService;
        this.catalogService = catalogService;
        this.cartService = cartService;
        this.paymentService = paymentService;
        this.emailService = emailService;
    }

    // AUTH endpoints
    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/validate/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable String token) {
        boolean valid = authService.validateToken(token);
        return ResponseEntity.ok(valid);
    }

    // CATALOG endpoints
    @GetMapping("/catalog/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> products = catalogService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/catalog/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String productId) {
        ProductDTO product = catalogService.getProductById(productId);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping("/catalog/products")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        try {
            ProductDTO createdProduct = catalogService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // CART endpoints
    @GetMapping("/cart/{userId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable String userId) {
        CartDTO cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
    }

    @PostMapping("/cart/{userId}/add/{productId}")
    public ResponseEntity<CartDTO> addToCart(@PathVariable String userId,
                                           @PathVariable String productId,
                                           @RequestParam(defaultValue = "1") Integer quantity) {
        CartDTO cart = cartService.addItem(userId, productId, quantity);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/cart/{userId}/total")
    public ResponseEntity<Double> getCartTotal(@PathVariable String userId) {
        Double total = cartService.calculateTotal(userId);
        return ResponseEntity.ok(total);
    }

    // PAYMENT endpoints
    @PostMapping("/payment/{userId}")
    public ResponseEntity<PaymentResponse> processPayment(@PathVariable String userId,
                                                        @RequestBody PaymentRequest request) {
        PaymentResponse response = paymentService.processPayment(userId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment/transaction/{transactionId}")
    public ResponseEntity<TransactionStatus> getTransactionStatus(@PathVariable String transactionId) {
        TransactionStatus status = paymentService.getTransactionStatus(transactionId);
        return ResponseEntity.ok(status);
    }

    // EMAIL endpoints
    @PostMapping("/email/test/{userId}")
    public ResponseEntity<String> sendTestEmail(@PathVariable String userId) {
        boolean sent = emailService.sendOrderConfirmation(userId, "test-txn-123");
        return ResponseEntity.ok(sent ? "Email enviado com sucesso" : "Falha no envio");
    }

    // Health check
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Sistema funcionando! Componentes: AUTH, CATALOG, CART, PAYMENT, EMAIL");
    }
}