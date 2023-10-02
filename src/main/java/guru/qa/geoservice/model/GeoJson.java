package guru.qa.geoservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import guru.qa.geoservice.data.entity.GeoEntity;

import java.util.UUID;

public class GeoJson {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("coordinates")
    private String coordinates;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public static GeoJson fromEntity(GeoEntity entity) {
        GeoJson geoJson = new GeoJson();
        geoJson.setId(entity.getId());
        geoJson.setName(entity.getName());
        geoJson.setCode(entity.getCode());
        geoJson.setCoordinates(entity.getCoordinates().toString());
        return geoJson;
    }
}
