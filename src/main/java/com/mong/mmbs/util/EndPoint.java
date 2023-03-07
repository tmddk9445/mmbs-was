package com.mong.mmbs.util;

public class EndPoint {

    // ASK
    public static final String ASK = "/apis/ask";

    public static final String ASK_POST_NULL = "/";

    public static final String ASK_GET_LIST = "/list";
    public static final String ASK_GET_ASKID = "/{askId}";
    public static final String ASK_GET_FIND = "/{askStatus}/{askDatetime}/{askSort}";

    public static final String ASK_PATCH_NULL = "/";

    public static final String ASK_DELETE_ASKID = "/{askId}";

    // CART
    public static final String CART = "/apis/cart";

    public static final String CART_POST_NULL = "/";
    
    public static final String CART_GET_NULL = "/";

    public static final String CART_PATCH_AMOUNT = "/";
    public static final String CART_PATCH_AMOUNT_ALL = "/all";

    public static final String CART_DELETE_CARTID = "/{cartId}";
    public static final String CART_DELETE_CARTUSERID = "/{cartUserId}";
    
}
