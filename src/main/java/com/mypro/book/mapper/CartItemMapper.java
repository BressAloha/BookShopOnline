package com.mypro.book.mapper;

import com.mypro.book.pojo.CartItem;
import com.mypro.book.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CartItemMapper {
    Integer addCartItem(CartItem cartItem);
    Integer updateCartItem(CartItem cartItem);
    List<CartItem> getCartItemList(User user);
    Integer delCartItem(CartItem cartItem);
}
