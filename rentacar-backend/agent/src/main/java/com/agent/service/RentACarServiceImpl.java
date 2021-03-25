package com.agent.service;


import com.agent.dto.AdDTO;
import com.agent.dto.RentACarDTO;
import com.agent.dto.ReportDTO;
import com.agent.enums.RequestStatus;
import com.agent.model.*;
import com.agent.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentACarServiceImpl implements RentACarService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private AdService adService;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private PriceListRepository priceListRepository;

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
    public void createReport(ReportDTO reportDTO) {
        Report report = new Report();

        report.setAdditionalInfo(reportDTO.getAdditionalInfo());
        report.setAdID(reportDTO.getAdID());
        report.setKms(reportDTO.getKms());
        report.setRentACarId(reportDTO.getRentACarId());
        System.out.println(report);
        report = reportRepository.save(report);
        RentACar rentACar = rentACarRepository.findById(report.getRentACarId()).get();
        Ad ad = adRepository.findById(rentACar.getAd()).get();
        User user = userRepository.findById(rentACar.getRequest().getUserID()).get();
        if (ad.getCar().getLimitKms() < report.getKms()) {
            user.setObligation((report.getKms() - ad.getCar().getLimitKms()) * ad.getPriceList().getPricePerKm());
            user.setDisableReservation(true);
        }
        logger.info(String.format("Report with id '%s' is created.", report.getId()));
        ;
    }

    public RentACar findById(Long id) {
        return rentACarRepository.findById(id).get();
    }

    @Override
    public List<RentACarDTO> getRentACarsUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> requests = requestRepository.findByUserID(userDB.getId());
        List<Request> reservedRequests = new ArrayList<>();
        for (Request req : requests) {
            if (req.getStatus().equals(RequestStatus.RESERVED)) {
                reservedRequests.add(req);
            }
        }

        List<RentACarDTO> rentACarDTOS = new ArrayList<>();

        List<AdDTO> adDTOS = adService.getAdDTOS();

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> requests = requestRepository.findAll();
        List<RentACarDTO> rentACarDTOS = new ArrayList<>();

        List<Request> reservedRequests = new ArrayList<>();
        for (Request req : requests) {
            if (req.getStatus().equals(RequestStatus.RESERVED)) {
                reservedRequests.add(req);
            }
        }

        List<AdDTO> adDTOS = adService.getAdDTOS();

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

    public List<RentACar> getUserCart() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());
        List<RentACar> userCart = userDB.getRentACars();
        List<RentACar> ret = new ArrayList<>();
        System.out.println(userCart.size());
        for (RentACar rentACar : userCart) {
            if (rentACar.getRequest() == null)
                ret.add(rentACar);
        }
        System.out.println(ret.size());
        return ret;
    }
}
