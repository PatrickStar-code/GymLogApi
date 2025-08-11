package com.Gymlog.Adapters;// src/main/java/com/seuprojeto/adapters/LocalDateTimeAdapter.java


import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }

    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateStr = json.getAsString();

        if (dateStr.endsWith("Z")) {
            dateStr = dateStr.substring(0, dateStr.length() - 1);
        }

        // Também remove os milissegundos, se quiser (opcional)
        // Ou deixe como está para aceitar fração de segundos

        return LocalDateTime.parse(dateStr);
    }

}
