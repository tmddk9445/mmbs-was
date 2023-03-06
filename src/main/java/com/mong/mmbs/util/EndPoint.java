package com.mong.mmbs.util;

public class EndPoint {
    public static final String ASK_POST_NULL = "/";
    
    public static final String ASK = "/apis/ask";
    public static final String ASK_GET_LIST = "/list";
    public static final String ASK_GET_ASKID = "/{askId}";
    public static final String ASK_GET_FIND = "/{askStatus}/{askDatetime}/{askSort}";

    public static final String ASK_PATCH_NULL = "/";

    public static final String ASK_DELETE_ASKID = "/{askId}";
}
