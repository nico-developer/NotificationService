package org.notification.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.notification.entity.Rules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    static ObjectMapper objectMapper = new ObjectMapper();
    static List<Rules> rulesList = new ArrayList<>();
    static {
        try(InputStream is = JsonUtils.class.getClassLoader().getResourceAsStream("rules-config.json")){
            if (is == null) {
                LOGGER.error("Resource not found. Check the file path.");
            }else {
                rulesList = objectMapper.readValue(is, new TypeReference<List<Rules>>(){});
                LOGGER.info("initialize Rules: {}",rulesList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public static List<Rules> getRules() {
        return rulesList;

    }

}
