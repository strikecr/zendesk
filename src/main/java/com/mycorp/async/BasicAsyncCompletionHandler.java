package com.mycorp.async;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycorp.async.ZendeskAsyncCompletionHandler;
import com.mycorp.mappers.ZenObjectMapper;
import com.mycorp.Zendesk;
import com.mycorp.ZendeskException;
import com.ning.http.client.Response;

public class BasicAsyncCompletionHandler<T> extends ZendeskAsyncCompletionHandler<T> {
    private final Class<T> clazz;
    private final String name;
    private final Class[] typeParams;
    private final Logger logger;
    private final ObjectMapper mapper;

    public BasicAsyncCompletionHandler(Class clazz, String name, Class... typeParams) {
        this.clazz = clazz;
        this.name = name;
        this.typeParams = typeParams;
        this.logger = LoggerFactory.getLogger(Zendesk.class);
        this.mapper = ZenObjectMapper.obtainObjectMapper();
    }

    @Override
    public T onCompleted(Response response) throws Exception {
        logResponse(response);
        if (isStatus2xx(response)) {
            if (typeParams.length > 0) {
                JavaType type = mapper.getTypeFactory().constructParametricType(clazz, typeParams);
                return mapper.convertValue(mapper.readTree(response.getResponseBodyAsStream()).get(name), type);
            }
            return mapper.convertValue(mapper.readTree(response.getResponseBodyAsStream()).get(name), clazz);
        } else if (isRateLimitResponse(response)) {
            throw new ZendeskException(response.toString());
        }
        if (response.getStatusCode() == 404) {
            return null;
        }
        throw new ZendeskException(response.toString());
    }
    
    
    private boolean isStatus2xx(Response response) {
        return response.getStatusCode() / 100 == 2;
    }

    
    private void logResponse(Response response) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("Response HTTP/{} {}\n{}", response.getStatusCode(), response.getStatusText(),
                    response.getResponseBody());
        }
        if (logger.isTraceEnabled()) {
            logger.trace("Response headers {}", response.getHeaders());
        }
    }

    private boolean isRateLimitResponse(Response response) {
        return response.getStatusCode() == 429;
    }
}