package com.validador.core.catalog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import com.validador.core.catalog.dto.ProductDTO;
import com.validador.core.interfaces.ICatalogService;

@Service
public class CatalogServiceImpl implements ICatalogService {

    // Lista estática para armazenar produtos criados dinamicamente
    private static final List<ProductDTO> products = new ArrayList<>();
    
    static {
        // Produto padrão
        products.add(new ProductDTO("prod-1", "Produto Exemplo", "Descrição do produto", new BigDecimal("99.90"), 10, "Categoria", "https://example.com/img.png", true));
    }

    @Override
    public List<ProductDTO> getProducts() {
        // Retorna todos os produtos (padrão + criados dinamicamente)
        return new ArrayList<>(products);
    }

    @Override
    public ProductDTO getProductById(String productId) {
        // Busca produto por ID na lista
        return products.stream()
                .filter(p -> productId.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isProductAvailable(String productId) {
        ProductDTO product = getProductById(productId);
        return product != null && Boolean.TRUE.equals(product.getAvailable()) && product.getQuantity() != null && product.getQuantity() > 0;
    }

    @Override
    public List<ProductDTO> searchProducts(String searchTerm) {
        // TODO: implementar pesquisa real
        if (searchTerm == null || searchTerm.isBlank()) {
            return getProducts();
        }
        List<ProductDTO> products = new ArrayList<>();
        products.add(getProductById("prod-1"));
        return products;
    }

    @Override
    public Double getProductPrice(String productId) {
        ProductDTO product = getProductById(productId);
        return product != null && product.getPrice() != null ? product.getPrice().doubleValue() : null;
    }
    
    @Override
    public ProductDTO createProduct(ProductDTO product) {
        // Validação básica
        if (product == null || product.getId() == null || product.getId().isBlank()) {
            throw new IllegalArgumentException("ID do produto é obrigatório");
        }
        
        // Verifica se produto já existe
        if (products.stream().anyMatch(p -> product.getId().equals(p.getId()))) {
            throw new IllegalArgumentException("Produto com ID " + product.getId() + " já existe");
        }
        
        // Adiciona à lista
        products.add(product);
        return product;
    }
}
