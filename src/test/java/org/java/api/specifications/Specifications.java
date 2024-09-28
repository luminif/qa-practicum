package org.java.api.specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.java.api.utils.Utils;

public class Specifications {
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
            .setBaseUri(Utils.getBaseUrl())
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .build();
    }
}
