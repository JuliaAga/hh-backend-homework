package ru.hh.school.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

public class ExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    public static Response handleException(Callable<Response> callable) {
        try {
            return callable.call();
        } catch (HhException hex) {
            logger.warn("HH answer on request " + hex.getMessage());
            return Response.status(Response.Status.fromStatusCode(Integer.parseInt(hex.getMessage()))).build();
        } catch (Exception ex) {
            logger.error("HHResources failed with exception " + ex + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
