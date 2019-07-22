package com.gdt.baseClient.utils;

import com.gdt.baseClient.beans.AbstractBaseJson;
import com.gdt.baseClient.beans.ICollectionJson;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class DeserializationContext {
    private Class<?> jsonDeserialize = null;

    public List<AbstractBaseJson> deserializeCollection(Response response) {
        Object deserialized = response.as(this.jsonDeserialize);
        List<AbstractBaseJson> list;
        if (deserialized.getClass().isArray()) {
            list = Arrays.asList((AbstractBaseJson[]) deserialized);
        } else {
            list = ((ICollectionJson) deserialized).getList();
        }
        return list;
    }
}
