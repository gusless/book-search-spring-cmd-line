package com.gusgus.literbooks.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface iConvert {
    <T> T getData(String json, Class<T> tClass) throws JsonProcessingException;
}
