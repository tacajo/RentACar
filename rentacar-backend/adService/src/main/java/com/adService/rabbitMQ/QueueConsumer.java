package com.adService.rabbitMQ;


import com.adService.dto.LocationDTO;
import com.adService.model.Location;
import com.adService.repository.LocationRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueConsumer {

    @Autowired
    private LocationRepository locationRepository;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message) {
        logger.info("Received (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        logger.info("Received (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) {
        try {
            System.out.println("Radi rabbitMQ!");
            LocationDTO locationDTO = new ObjectMapper().readValue(message, LocationDTO.class);
            System.out.println(locationDTO);
            Location location = new Location().builder()
                    .latitude(locationDTO.getLatitude())
                    .longitude(locationDTO.getLongitude())
                    .ad(locationDTO.getAd())
                    .build();
            locationRepository.save(location);
        } catch (JsonParseException e) {
            logger.warn("Bad JSON in message: " + message);
        } catch (JsonMappingException e) {
            logger.warn("cannot map JSON to NotificationRequest: " + message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
