package guru.qa.geoservice.controller;

import guru.qa.geoservice.data.entity.GeoEntity;
import guru.qa.geoservice.model.Geo;
import guru.qa.geoservice.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo")
public class GeoController {
    private final GeoService geoService;

    @Autowired
    public GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @PostMapping
    public ResponseEntity<Geo> addGeo(@RequestBody Geo geo) {
        GeoEntity geoEntity = geoService.addCountry(geo);
        Geo createdGeo = new Geo(geoEntity.getName(), geoEntity.getCode(), geoEntity.getCoordinates());
        return new ResponseEntity<>(createdGeo, HttpStatus.CREATED);
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Geo> updateName(@PathVariable String code, @RequestParam String name) {
        try {
            GeoEntity updatedEntity = geoService.updateName(code, name);
            Geo updatedGeo = new Geo(updatedEntity.getName(), updatedEntity.getCode(), updatedEntity.getCoordinates());
            return new ResponseEntity<>(updatedGeo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
