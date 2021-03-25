package com.rentACarService.service;

import com.rentACarService.clients.AdClient;
import com.rentACarService.dto.AdDTO;
import com.rentACarService.dto.RentACarDTO;
import com.rentACarService.dto.ReportDTO;
import com.rentACarService.enums.RequestStatus;
import com.rentACarService.model.RentACar;
import com.rentACarService.model.Report;
import com.rentACarService.model.Request;
import com.rentACarService.model.User;
import com.rentACarService.repository.*;
import com.rentACarService.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentACarServiceImpl implements RentACarService {

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdClient adClient;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RestTemplate restTemplate;


    public RentACar findById(Long id) {
        return rentACarRepository.findById(id).get();
    }

    @Override
    public void createRequest() {

    }

    @Override
    public void acceptRequest() {

    }

    @Override
    public void rejectRequest() {

    }

    @Override
    public void cancelRequest() {

    }

    @Override
    public void getByUserID() {

    }

    @Override
    public void createPaidRequest() {

    }

    @Override
    public void createReport() {

    }

    @Override
    public void createReport(ReportDTO reportDTO) {
        System.out.println(reportDTO);
        Report report = new Report();
        RentACar rentACar = rentACarRepository.findById(reportDTO.getRentACarId()).get();

        report.setAdditionalInfo(reportDTO.getAdditionalInfo());
        report.setAdID(reportDTO.getAdID());
        report.setKms(reportDTO.getKms());
        report.setRentACarId(reportDTO.getRentACarId());

        Integer kmsLimit = getAdKm(rentACar.getAd());
        Integer pricePerKm = getPricePerKm(rentACar.getAd());
        System.out.println(pricePerKm);
        if (kmsLimit < report.getKms()) {
            addObligation((report.getKms() - kmsLimit) * pricePerKm, rentACar.getRequest().getUserID());
        }
        reportRepository.save(report);
    }

    private void addObligation(double price, Long id) {
        System.out.println("price " + price);
        System.out.println("user id " + id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8084/auth/user/rt/add-obligation")
                .queryParam("price", price)
                .queryParam("id", id);

        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                entity,
                String.class);
    }

    private Integer getAdKm(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8084/ad/ad/rt/get-limit-km")
                .queryParam("id", id);

        HttpEntity<Integer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Integer.class);

        return response.getBody();
    }

    private Integer getPricePerKm(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity entity = new HttpEntity(headers);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8084/ad/ad/rt/get-price-per-km")
                .queryParam("id", id);

        HttpEntity<Integer> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Integer.class);

        return response.getBody();
    }

    @Override
    public List<RentACarDTO> getRentACarsUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> requests = requestRepository.findByUserID(userDB.getId());
        List<Request> reservedRequests = new ArrayList<>();
        for (Request req : requests) {
            if (req.getStatus().equals(RequestStatus.RESERVED)) {
                reservedRequests.add(req);
            }
        }

        List<RentACarDTO> rentACarDTOS = new ArrayList<>();

        List<AdDTO> adDTOS = adClient.getAdDTOS();

        for (Request reservedRequest : reservedRequests) {
            for (RentACar rac : reservedRequest.getRentACars()) {
                RentACarDTO rentACarDTO = new RentACarDTO();
                rentACarDTO.setStartDate(rac.getStartDate());
                rentACarDTO.setEndDate(rac.getEndDate());
                rentACarDTO.setAdId(rac.getAd());

                System.out.println(rac.getAd());
                for (AdDTO adDTO : adDTOS) {
                    if (adDTO.getId().equals(rac.getAd())) {
                        System.out.println(adDTO.getCity());
                        System.out.println(adDTO.getId());
                        rentACarDTO.setAdDTO(adDTO);
                        rentACarDTOS.add(rentACarDTO);
                        break;
                    }
                }
            }
        }
        return rentACarDTOS;
    }

    @Override
    public List<RentACarDTO> getRentACarsAgent() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> requests = requestRepository.findAll();
        List<RentACarDTO> rentACarDTOS = new ArrayList<>();

        List<Request> reservedRequests = new ArrayList<>();
        for (Request req : requests) {
            if (req.getStatus().equals(RequestStatus.RESERVED)) {
                reservedRequests.add(req);
            }
        }

        List<AdDTO> adDTOS = adClient.getAdDTOS();

        for (Request reservedRequest : reservedRequests) {
            for (RentACar rac : reservedRequest.getRentACars()) {
                for (AdDTO adDTO : adDTOS) {
                    if (adDTO.getId().equals(rac.getAd()) && adDTO.getUserID().equals(userDB.getId())) {
                        RentACarDTO rentACarDTO = new RentACarDTO();
                        rentACarDTO.setStartDate(rac.getStartDate());
                        rentACarDTO.setEndDate(rac.getEndDate());
                        rentACarDTO.setAdId(rac.getAd());
                        rentACarDTO.setUserID(reservedRequest.getUserID());

                        rentACarDTO.setAdDTO(adDTO);
                        rentACarDTOS.add(rentACarDTO);
                        break;
                    }
                }
            }
        }

        return rentACarDTOS;
    }
}
