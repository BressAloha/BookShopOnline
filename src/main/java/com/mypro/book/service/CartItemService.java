package com.mypro.book.service;

import com.mypro.book.pojo.Cart;
import com.mypro.book.pojo.CartItem;
import com.mypro.book.pojo.User;

import java.util.List;

public interface CartItemService {
    Integer addCartItem(CartItem cartItem);
    Integer updateCartItem(CartItem cartItem);
    Integer addOrUpdateCartItem(CartItem cartItem, Cart cart);
    Cart getCart(User user);
    List<CartItem> getCartItemList(User user);
    Integer delCartItem(CartItem cartItem);
}
