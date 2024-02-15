package com.digital.moviesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is a pre-made "Exception" class, i.e. an error :(
 *
 * When you come to build APIs that work on a specific item (e.g. update movie #5), you need to handle situations
 * where the client tries to update a movie that doesn't exist (e.g. update movie #999).
 *
 * For that, we send back an error (Which this class will build nicely for you)
 *
 * To give you a hint, search for:
 *  .orElseThrow()
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFoundException( String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}