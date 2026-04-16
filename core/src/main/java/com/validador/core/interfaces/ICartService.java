package com.validador.core.interfaces;

import java.util.List;
import com.validador.core.cart.dto.CartDTO;
import com.validador.core.cart.dto.CartItemDTO;

/**
 * PROVIDES (Interface de Carrinho)
 * 
 * Responsabilidades:
 * - Gerenciar itens no carrinho
 * - Calcular total do carrinho
 * - Recuperar dados do carrinho
 * 
 * REQUIRES (Dependências)
 * - ICatalogService: para obter informações de produtos
 * 
 * Componente: CART
 */
public interface ICartService {
    
    /**
     * Retorna o carrinho do usuário
     * 
     * @param userId identificador do usuário
     * @return CartDTO com itens atuais
     */
    CartDTO getCart(String userId);
    
    /**
     * Adiciona um item ao carrinho
     * 
     * @param userId identificador do usuário
     * @param productId identificador do produto
     * @param quantity quantidade desejada
     * @return CartDTO atualizado
     */
    CartDTO addItem(String userId, String productId, Integer quantity);
    
    /**
     * Remove um item do carrinho
     * 
     * @param userId identificador do usuário
     * @param cartItemId identificador do item no carrinho
     * @return CartDTO atualizado
     */
    CartDTO removeItem(String userId, String cartItemId);
    
    /**
     * Atualiza a quantidade de um item
     * 
     * @param userId identificador do usuário
     * @param cartItemId identificador do item
     * @param quantity nova quantidade
     * @return CartDTO atualizado
     */
    CartDTO updateItemQuantity(String userId, String cartItemId, Integer quantity);
    
    /**
     * Calcula o total do carrinho (com impostos e frete se aplicável)
     * 
     * @param userId identificador do usuário
     * @return valor total
     */
    Double calculateTotal(String userId);
    
    /**
     * Limpa todos os itens do carrinho
     * 
     * @param userId identificador do usuário
     */
    void clearCart(String userId);
    
    /**
     * Retorna lista de itens no carrinho
     * 
     * @param userId identificador do usuário
     * @return lista de CartItemDTO
     */
    List<CartItemDTO> getCartItems(String userId);
}
