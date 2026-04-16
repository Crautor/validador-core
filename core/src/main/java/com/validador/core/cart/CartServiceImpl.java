package com.validador.core.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.validador.core.cart.dto.CartDTO;
import com.validador.core.cart.dto.CartItemDTO;
import com.validador.core.catalog.dto.ProductDTO;
import com.validador.core.interfaces.ICartService;
import com.validador.core.interfaces.ICatalogService;

@Service
public class CartServiceImpl implements ICartService {

    private final ICatalogService catalogService;

    public CartServiceImpl(ICatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @Override
    public CartDTO getCart(String userId) {
        // TODO: buscar carrinho real do usuário
        List<CartItemDTO> items = new ArrayList<>();
        items.add(new CartItemDTO("item-1", "prod-1", "Produto Exemplo", 1, new BigDecimal("99.90"), new BigDecimal("99.90")));
        return new CartDTO("cart-" + userId, userId, items, new BigDecimal("99.90"), BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("99.90"));
    }

    @Override
    public CartDTO addItem(String userId, String productId, Integer quantity) {
        ProductDTO product = catalogService.getProductById(productId);
        if (product == null || !catalogService.isProductAvailable(productId)) {
            throw new IllegalArgumentException("Produto indisponível: " + productId);
        }
        // TODO: adicionar item ao carrinho real
        return getCart(userId);
    }

    @Override
    public CartDTO removeItem(String userId, String cartItemId) {
        // TODO: remover item real do carrinho
        return getCart(userId);
    }

    @Override
    public CartDTO updateItemQuantity(String userId, String cartItemId, Integer quantity) {
        // TODO: atualizar quantidade real do item no carrinho
        return getCart(userId);
    }

    @Override
    public Double calculateTotal(String userId) {
        CartDTO cart = getCart(userId);
        return cart.getTotal() != null ? cart.getTotal().doubleValue() : 0.0;
    }

    @Override
    public void clearCart(String userId) {
        // TODO: limpar carrinho real do usuário
    }

    @Override
    public List<CartItemDTO> getCartItems(String userId) {
        return getCart(userId).getItems();
    }
}
