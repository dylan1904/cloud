package co.com.poli.project.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
    public static Response success(){
        return Response.builder()
                .data(HttpStatus.OK)
                .build();
    }

    public static Response success(Object data){
        return Response.builder()
                .data(data)
                .build();
    }

    public static Response successCreated(Object data) {
        return Response.builder()
                .data(data)
                .build();
    }

    public static Response failed(Object data){
        return Response.builder()
                .data(data)
                .build();
    }
}
