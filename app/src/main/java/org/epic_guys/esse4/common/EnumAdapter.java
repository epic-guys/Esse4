package org.epic_guys.esse4.common;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public abstract class EnumAdapter<T extends Enum<T>> extends TypeAdapter<T> {

    public abstract Class<T> getTClass();

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.value(value.toString());
    }

    @Override
    public T read(JsonReader in) throws IOException {
        in.beginObject();
        in.nextName();
        String value = in.nextString();
        in.endObject();
        return T.valueOf(getTClass(), value);
    }
}
