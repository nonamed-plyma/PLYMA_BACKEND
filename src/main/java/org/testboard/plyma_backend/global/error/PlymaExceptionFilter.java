package org.testboard.plyma_backend.global.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.testboard.plyma_backend.global.error.exception.BaseException;

import java.io.IOException;

public class PlymaExceptionFilter extends OncePerRequestFilter{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        try{
            filterChain.doFilter(request, response);
        }catch (BaseException exception){
            response.setStatus(exception.getErrorCode().getStatusCode());

            response.setCharacterEncoding("UTF-8");
            response.setContentType("applycation/json");

            ErrorRespons errorRespons = new ErrorRespons(exception.getErrorCode().getStatusCode(), exception.getErrorCode().getErrorMessage());
            response.getWriter().write(objectMapper.writeValueAsString(errorRespons));
        }
    }
}
