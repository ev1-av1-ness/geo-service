package guru.qa.geoservice.service;

import guru.qa.geoservice.data.GeoRepository;
import guru.qa.geoservice.data.entity.GeoEntity;
import guru.qa.geoservice.exception.GeoCodeNotFoundException;
import guru.qa.geoservice.model.Geo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GeoService {
    private final GeoRepository geoRepository;

    @Autowired
    public GeoService(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    @Transactional
    public List<GeoEntity> getAllGeo() {
        return (List<GeoEntity>) geoRepository.findAll();
    }

    @Transactional
    public GeoEntity addGeo(Geo geo) {
        GeoEntity geoEntity = new GeoEntity();
        geoEntity.setName(geo.name());
        geoEntity.setCode(geo.code());
        geoEntity.setCoordinates(geo.coordinates());
        return geoRepository.save(geoEntity);
    }

    @Transactional
    public GeoEntity updateName(String code, String name) {
        Optional<GeoEntity> geoEntityOptional = geoRepository.findByCode(code);
        if (geoEntityOptional.isPresent()) {
            GeoEntity geoEntity = geoEntityOptional.get();
            geoEntity.setName(name);
            return geoRepository.save(geoEntity);
        }
        throw new GeoCodeNotFoundException("Geo with code " + code + " not found.");
    }
}
