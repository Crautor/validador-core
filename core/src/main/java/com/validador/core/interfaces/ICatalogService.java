package com.validador.core.interfaces;

import java.util.List;
import com.validador.core.catalog.dto.ProductDTO;

/**
 * PROVIDES (Interface de Catálogo)
 * 
 * Responsabilidades:
 * - Listar produtos disponíveis
 * - Buscar produtos por ID
 * - Pesquisar produtos por critério
 * 
 * Componente: CATALOG
 */
public interface ICatalogService {
    
    /**
     * Retorna todos os produtos disponíveis
     * 
     * @return lista de produtos
     */
    List<ProductDTO> getProducts();
    
    /**
     * Busca um produto específico por ID
     * 
     * @param productId identificador único do produto
     * @return ProductDTO ou null se não encontrado
     */
    ProductDTO getProductById(String productId);
    
    /**
     * Valida se um produto existe e está disponível
     * 
     * @param productId identificador do produto
     * @return true se disponível
     */
    boolean isProductAvailable(String productId);
    
    /**
     * Pesquisa produtos por termo
     * 
     * @param searchTerm termo de busca
     * @return lista de produtos que correspondem
     */
    List<ProductDTO> searchProducts(String searchTerm);
    
    /**
     * Obtém o preço atual de um produto
     * 
     * @param productId identificador do produto
     * @return preço em decimal
     */
    Double getProductPrice(String productId);
    
    /**
     * Cria um novo produto no catálogo
     * 
     * @param product dados do produto a ser criado
     * @return ProductDTO do produto criado
     */
    ProductDTO createProduct(ProductDTO product);
}
