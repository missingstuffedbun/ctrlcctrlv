package com.zhizoo.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * Created by temp on 2016/7/18.
 */
public class JSONUtils {
    protected static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    static {
        JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
    }

    public static <T extends JsonNode> T valueToTree(Object fromValule) {
        return JSON_MAPPER.valueToTree(fromValule);
    }

    public static String marshal(Object obj) throws JsonProcessingException {
        return JSON_MAPPER.writeValueAsString(obj);
    }

    public static String marshal (Object obj, String defaultValue) {
        if (obj==null) {
            return defaultValue;
        }
        try {
            return marshal(obj);
        } catch (JsonProcessingException e) {
            e.toString();
        }
        return defaultValue;
    }

    public static Object unmarshal(String jsonStr) throws IOException {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        Class<?> c = Map.class;
        for (int i = 0; i < jsonStr.length(); i++) {
            if (Character.isSpaceChar(jsonStr.charAt(i))) {
                continue;
            }
            if ('['==jsonStr.charAt(i)) {
                c = Collection.class;
            }
            break;
        }
        return JSON_MAPPER.readValue(jsonStr,c);
    }

    public static <T> T unmarshalByType(String jsonStr, Class<T> clazz) throws IOException {
        return JSON_MAPPER.readValue(jsonStr,clazz);
    }

    public static Object unmarshal(String jsonStr, Object defaultValue) {
        Class<?> c = Map.class;
        for (int i = 0; i < jsonStr.length(); i++) {
            if (Character.isSpaceChar(jsonStr.charAt(i))) {
                continue;
            }
            if ('['==jsonStr.charAt(i)) {
                c = Collection.class;
            }
            break;
        }
        try {
            return JSON_MAPPER.readValue(jsonStr,c);
        } catch (IOException e) {
            return defaultValue;
        }
    }

}
