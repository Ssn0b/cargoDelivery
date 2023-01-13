package filters;

import com.example.cargodelivery.controller.filters.EncodingFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import utils.MyRequest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestEncodingFilter {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final FilterChain chain = mock(FilterChain.class);
    private final FilterConfig config = mock(FilterConfig.class);

    @Test
    void testDoFilter() throws ServletException, IOException {
        when(config.getInitParameter("encoding")).thenReturn("UTF-8");
        EncodingFilter filter = new EncodingFilter();
        filter.init(config);
        MyRequest myRequest = new MyRequest(request);
        filter.doFilter(myRequest, response, chain);
        assertEquals("UTF-8", myRequest.getCharacterEncoding());
    }
}
