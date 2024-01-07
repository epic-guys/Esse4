package org.epic_guys.esse4.exceptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.epic_guys.esse4.models.ApiError;

public class ApiException extends RuntimeException {
    
    private final ApiError apiError;
    
    public ApiException(@NonNull ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiException(@NonNull ApiError apiError, @NonNull Throwable cause) {
        super(cause);
        this.apiError = apiError;
    }

    @NonNull
    public ApiError getApiError() {
        return apiError;
    }


    @Nullable
    @Override
    public String getMessage() {
        return apiError.getErrorMessage();
    }
}

