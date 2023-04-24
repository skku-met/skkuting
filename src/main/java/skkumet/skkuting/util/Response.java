package skkumet.skkuting.util;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.MultiValueMap;

import skkumet.skkuting.util.SuccessResponse.SuccessCode;

public class Response<T> extends ResponseEntity<SuccessResponse<T>> {

    public Response(HttpStatusCode status) {
        super(status);
    }

    public Response(@Nullable SuccessResponse<T> body, HttpStatusCode status) {
        super(body, status);
    }

    public Response(MultiValueMap<String, String> headers, HttpStatusCode status) {
        super(headers, status);
    }

    public Response(@Nullable SuccessResponse<T> body, @Nullable MultiValueMap<String, String> headers,
            HttpStatusCode status) {
        super(body, headers, status);
    }

    public Response(@Nullable SuccessResponse<T> body, @Nullable MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }

    public Response(@Nullable T body, SuccessCode code) {
        this(new SuccessResponse<T>(code, body), code.getStatus());
    }

}
