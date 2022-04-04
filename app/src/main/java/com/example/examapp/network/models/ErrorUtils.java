package com.example.examapp.network.models;

import java.io.IOException;
import java.lang.annotation.Annotation;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ErrorUtils {
    static Retrofit retrofit;
    public static APIError parseError(Response<?> response) {
        Converter<okhttp3.ResponseBody, APIError> converter = retrofit.responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new APIError("Произошло ошибка.");
        }

        return error;
    }
}

