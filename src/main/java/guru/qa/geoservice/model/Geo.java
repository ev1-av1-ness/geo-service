package guru.qa.geoservice.model;

import java.util.List;

public record Geo(String name, String code, List<List<List<List<Double>>>> coordinates) {
}
