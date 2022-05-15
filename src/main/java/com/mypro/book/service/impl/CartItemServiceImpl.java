package com.mypro.book.service.impl;

import com.mypro.book.mapper.CartItemMapper;
import com.mypro.book.pojo.Cart;
import com.mypro.book.pojo.CartItem;
import com.mypro.book.pojo.User;
import com.mypro.book.service.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    Cart cart;
    @Override
    public Integer delCartItem(CartItem cartItem) {
        return cartItemMapper.delCartItem(cartItem);
    }

    @Override
    public Integer addCartItem(CartItem cartItem) {

        System.out.println(cartItem);
        return cartItemMapper.addCartItem(cartItem);
    }

    @Override
    public Integer updateCartItem(CartItem cartItem) {
        if(cartItem.getBuyCount()<=0)
            return cartItemMapper.delCartItem(cartItem);
        return cartItemMapper.updateCartItem(cartItem);
    }

    @Override
    public Integer addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        return null;
    }

    @Override
    public Cart getCart(User user) {

        List<CartItem> cartItems=cartItemMapper.getCartItemList(user);

        cart.setCartItems(cartItems);

        return cart;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return null;
    }
}
