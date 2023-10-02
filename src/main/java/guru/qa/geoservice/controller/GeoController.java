package guru.qa.geoservice.controller;

import guru.qa.geoservice.data.entity.GeoEntity;
import guru.qa.geoservice.model.Geo;
import guru.qa.geoservice.model.GeoJson;
import guru.qa.geoservice.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping
    public List<Geo> getAllCountries() {
        return geoService.getAllGeo().stream()
                .map(entity -> new Geo(entity.getName(), entity.getCode(), entity.getCoordinates()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Geo> addGeo(@RequestBody Geo geo) {
        GeoEntity geoEntity = geoService.addGeo(geo);
        Geo createdGeo = new Geo(geoEntity.getName(), geoEntity.getCode(), geoEntity.getCoordinates());
        return new ResponseEntity<>(createdGeo, HttpStatus.CREATED);
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Geo> changeCountry(@PathVariable String code,
                                          @RequestBody GeoJson geo){
        try {
            GeoEntity updatedEntity = geoService.updateName(code, geo.getName());
            Geo updatedGeo = new Geo(updatedEntity.getName(), updatedEntity.getCode(), updatedEntity.getCoordinates());
            return new ResponseEntity<>(updatedGeo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
