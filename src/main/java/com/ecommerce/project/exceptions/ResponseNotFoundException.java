package com.ecommerce.project.exceptions;

public class ResponseNotFoundException extends RuntimeException{
 String resourceName;
 String Field;
 String FieldName;

 Long FieldId;

    public ResponseNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s : %d",
                resourceName, field, fieldId));

        this.resourceName = resourceName;
        this.Field = field;
        this.FieldId = fieldId;
    }

    public ResponseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %d", resourceName,field,fieldId));
        this.resourceName = resourceName;
        Field = field;
        FieldId = fieldId;
    }
}
