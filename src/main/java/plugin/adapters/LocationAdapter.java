package plugin.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import plugin.entities.LocationBean;

import java.io.IOException;

public class LocationAdapter extends TypeAdapter<LocationBean> {

    @Override
    public void write(JsonWriter writer, LocationBean locationBean) throws IOException {
        writer.beginObject();

        writer.name("worldName");
        writer.value(locationBean.getWorldName());

        writer.name("playerName");
        writer.value(locationBean.getPlayerName());

        writer.name("x");
        writer.value(locationBean.getX());

        writer.name("y");
        writer.value(locationBean.getY());

        writer.name("z");
        writer.value(locationBean.getZ());

        writer.endObject();
    }

    @Override
    public LocationBean read(JsonReader reader) throws IOException {
        LocationBean locationBean = new LocationBean();
        reader.beginObject();
        String fieldName = null;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            if ("worldName".equals(fieldName)) {
                token = reader.peek();
                locationBean.setWorldName(reader.nextString());
            }

            if ("playerName".equals(fieldName)) {
                token = reader.peek();
                locationBean.setPlayerName(reader.nextString());
            }

            if ("x".equals(fieldName)) {
                token = reader.peek();
                locationBean.setX(reader.nextDouble());
            }

            if ("y".equals(fieldName)) {
                token = reader.peek();
                locationBean.setY(reader.nextDouble());
            }

            if ("z".equals(fieldName)) {
                token = reader.peek();
                locationBean.setZ(reader.nextDouble());
            }
        }
        reader.endObject();
        return locationBean;
    }
}
