/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherservice;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jouni
 */
@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Cacheable("locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Cacheable(cacheNames = "locations", key = "#id")
    public Location getOneLocationById(Long id) {
        return locationRepository.getOne(id);
    }
}
