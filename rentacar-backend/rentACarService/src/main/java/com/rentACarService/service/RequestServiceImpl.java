package com.rentACarService.service;


import com.rentACarService.dto.LightSearchDTO;
import com.rentACarService.enums.RequestStatus;
import com.rentACarService.model.Ad;
import com.rentACarService.model.RentACar;
import com.rentACarService.model.Request;
import com.rentACarService.model.User;
import com.rentACarService.repository.AdRepository;
import com.rentACarService.repository.RentACarRepository;
import com.rentACarService.repository.RequestRepository;
import com.rentACarService.repository.UserRepository;
import com.rentACarService.security.CustomUserDetails;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private RentACarRepository rentACarRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Long> getAdsLightSearch(LightSearchDTO lightSearchDTO) {

        System.out.println("usao u rentacar servis u getadslightsearch");

        String paramTimeFrom = lightSearchDTO.getStartDate().split("T")[1];
        String paramDateFrom = lightSearchDTO.getStartDate().split("T")[0];
        String paramTimeTo = lightSearchDTO.getEndDate().split("T")[1];
        String paramDateTo = lightSearchDTO.getEndDate().split("T")[0];
        LocalTime timeFrom = LocalTime.parse(paramTimeFrom);
        LocalDate dateFrom = LocalDate.parse(paramDateFrom);
        LocalTime timeTo = LocalTime.parse(paramTimeTo);
        LocalDate dateTo = LocalDate.parse(paramDateTo);

        List<Long> unavailableAds = new ArrayList<>();

        List<Request> req = requestRepository.findAll();
        if (req == null) {
            return lightSearchDTO.getAds();
        }
        for (Request request : req) {
            if (request.getStatus().equals(RequestStatus.PAID) || request.getStatus().equals(RequestStatus.RESERVED)) {
                for (RentACar rc : request.getRentACars()) {
                    for (Long adId : lightSearchDTO.getAds()) {
                        if (adId.equals(rc.getAd())) {
                            System.out.println(rc.getEndDate().toString());

                            String paramTimeRentACarFrom = rc.getStartDate().toString().split(" ")[1].split(":")[0] + ":" + rc.getStartDate().toString().split(" ")[1].split(":")[1];
                            String paramDateRentACarFrom = rc.getStartDate().toString().split(" ")[0];
                            String paramTimeRentACarTo = rc.getEndDate().toString().split(" ")[1].split(":")[0] + ":" + rc.getEndDate().toString().split(" ")[1].split(":")[1];
                            String paramDateRentACarTo = rc.getEndDate().toString().split(" ")[0];
                            LocalTime timeRentACarFrom = LocalTime.parse(paramTimeRentACarFrom);
                            LocalDate dateRentACarFrom = LocalDate.parse(paramDateRentACarFrom);
                            LocalTime timeRentACarTo = LocalTime.parse(paramTimeRentACarTo);
                            LocalDate dateRentACarTo = LocalDate.parse(paramDateRentACarTo);

                            if (dateFrom.isAfter(dateRentACarFrom) && dateFrom.isBefore(dateRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateTo.isAfter(dateRentACarFrom) && dateTo.isBefore(dateRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isBefore(dateRentACarFrom) && dateTo.isAfter(dateRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isEqual(dateRentACarFrom) && timeFrom.isAfter(timeRentACarFrom)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isEqual(dateRentACarFrom) && timeFrom.isBefore(timeRentACarFrom) && dateTo.isAfter(dateRentACarFrom)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isEqual(dateRentACarFrom) && timeFrom.isBefore(timeRentACarFrom) && dateTo.isEqual(dateRentACarFrom) && timeTo.isAfter(timeRentACarFrom)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isBefore(dateRentACarFrom) && dateTo.isEqual(dateRentACarFrom) && timeTo.isAfter(timeRentACarFrom)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateFrom.isEqual(dateRentACarTo) && timeFrom.isBefore(timeRentACarFrom)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateTo.isEqual(dateRentACarTo) && timeTo.isBefore(timeRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateTo.isEqual(dateRentACarTo) && timeTo.isAfter(timeRentACarTo) && dateFrom.isBefore(dateRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            } else if (dateTo.isEqual(dateRentACarTo) && timeTo.isAfter(timeRentACarTo) && dateFrom.isEqual(dateRentACarTo) && timeFrom.isBefore(timeRentACarTo)) {
                                if (!unavailableAds.contains(adId)) {
                                    unavailableAds.add(adId);
                                }
                            }
                        }
                    }
                }
            }
        }
        List<Long> reqID = new ArrayList<>();
        for (Long id : lightSearchDTO.getAds()) {
            reqID.add(id);
        }

        System.out.println(unavailableAds);
        for (Long id : lightSearchDTO.getAds()) {
            for (Long idUnavailable : unavailableAds) {
                if (id.equals(idUnavailable)) {
                    reqID.remove(id);
                    break;
                }
            }
        }
        System.out.println(reqID);

        return reqID;
    }


    public void createRequests(List<RentACar> rentACarList, boolean bundle) {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        Set<RentACar> rentACarSet = new HashSet<RentACar>();
        Request request = new Request().builder()
                .startDate(new Date())
                .endDate(DateUtils.addHours(new Date(), 24))
                .status(RequestStatus.PENDING)
                .rentACars(rentACarSet)
                .userID(userDB.getId())
                .build();

        if (bundle) {
            List<RentACar> sortedRentACars = rentACarList.stream()
                    .sorted(Comparator.comparing(RentACar::getAdOwner))
                    .collect(Collectors.toList());
            Ad ad = adRepository.findById(sortedRentACars.get(0).getAd()).get();
            Long id = ad.getUserID();
            for (RentACar rentACar : sortedRentACars) {
                if (id != rentACar.getAdOwner()) {
                    id = rentACar.getAdOwner();
                    requestRepository.save(request);
                    rentACarSet = new HashSet<>();
                    request = new Request().builder()
                            .startDate(new Date())
                            .endDate(DateUtils.addHours(new Date(), 24))
                            .status(RequestStatus.PENDING)
                            .rentACars(rentACarSet)
                            .userID(userDB.getId())
                            .build();
                }

                request.getRentACars().add(rentACar);
                rentACar.setRequest(request);
            }
            requestRepository.save(request);
            Timer timer = new Timer("Timer");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    endDate24H();
                }
            };
            timer.schedule(timerTask, DateUtils.addHours(request.getStartDate(), 24));

        } else {
            for (RentACar rentACar : rentACarList) {
                rentACarSet = new HashSet<RentACar>();
                rentACarSet.add(rentACar);
                request = new Request().builder()
                        .startDate(new Date())
                        .endDate(DateUtils.addHours(new Date(), 24))
                        .status(RequestStatus.PENDING)
                        .userID(userDB.getId())
                        .rentACars(rentACarSet)
                        .build();
                rentACar.setRequest(request);
                requestRepository.save(request);
                Timer timer = new Timer("Timer");
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        endDate24H();
                    }
                };
                timer.schedule(timerTask, DateUtils.addHours(request.getStartDate(), 24));
            }
        }
    }

    public List<RentACar> getUserCart(Long id) {
        User userDB = userRepository.findById(id).get();
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

    public List<Request> getOwnersRequest() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> ret = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            System.out.println(request.getId() + " ima ovoliko rentacarova " + request.getRentACars().size());
            System.out.println(userDB.getId());
            if (request.getRentACars().iterator().next().getAdOwner() == userDB.getId()) {
                ret.add(request);
            }
        }
        return ret;
    }

    public List<Request> getUserRequest() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = userRepository.findByUsername(user.getUsername());

        List<Request> ret = new ArrayList<>();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getUserID() == userDB.getId()) {
                ret.add(request);
            }
        }
        return ret;
    }

    public void cancelRequest(Long id) {
        Request request = requestRepository.findById(id).get();
        request.setStatus(RequestStatus.CANCELED);
        requestRepository.save(request);
    }

    public void acceptRequest(Long id) {
        Request myRequest = requestRepository.findById(id).get();
        List<Request> requests = requestRepository.findAll();
        boolean success = true;
        String status = "";

        for (RentACar myRentACar : myRequest.getRentACars()) {
            if (success) {
                for (Request request : requests) {
                    if (request.getStatus().equals(RequestStatus.PAID) || request.getStatus().equals(RequestStatus.RESERVED) && success) {
                        for (RentACar rentACar : request.getRentACars()) {
                            if (rentACar.getAd() == myRentACar.getAd() && success) {
                                System.out.println("nasao request sa istim oglasom");
                                if (!((rentACar.getStartDate().before(myRentACar.getStartDate()) && rentACar.getEndDate().before(myRentACar.getStartDate())) ||
                                        (rentACar.getStartDate().after(myRentACar.getEndDate()) && rentACar.getEndDate().after(myRentACar.getEndDate())))) {
                                    System.out.println("nasao datum koji se poklapa");
                                    success = false;
                                    status = request.getStatus().toString();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (success) {
            System.out.println("uspesno prosao kroz sve oglase");
            myRequest.setStatus(RequestStatus.RESERVED);
            myRequest.setReservedDate(new Date());
            myRequest.setEndPaid(DateUtils.addHours(myRequest.getReservedDate(), 12));
            requestRepository.save(myRequest);
        } else {
            if (status.equals("PAID")) {
                myRequest.setStatus(RequestStatus.CANCELED);
                requestRepository.save(myRequest);
            }
        }
        Timer timer = new Timer("Timer");
        System.out.println(myRequest.getReservedDate());
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               reservation12H();
            }
        };
        timer.schedule(timerTask, DateUtils.addHours(myRequest.getReservedDate(), 12));
    }

    public void reservation12H() {
        Date reservedDate = new Date();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getEndPaid().equals(reservedDate) && request.getStatus().equals(RequestStatus.RESERVED)) {
                request.setStatus(RequestStatus.CANCELED);
                requestRepository.save(request);
                break;
            }
        }
    }

    public void endDate24H() {
        Date reservedDate = new Date();
        List<Request> requests = requestRepository.findAll();
        for (Request request : requests) {
            if (request.getEndDate().equals(reservedDate) && request.getStatus().equals(RequestStatus.PENDING)) {
                request.setStatus(RequestStatus.CANCELED);
                requestRepository.save(request);
                break;
            }
        }
    }

    public void payRequest(Long id) {
        Request myRequest = requestRepository.findById(id).get();
        List<Request> requests = requestRepository.findAll();

        for (RentACar myRentACar : myRequest.getRentACars()) {
            for (Request request : requests) {
                for (RentACar rentACar : request.getRentACars()) {
                    if (rentACar.getAd() == myRentACar.getAd()) {
                        if ((rentACar.getStartDate().before(myRentACar.getStartDate()) && rentACar.getEndDate().before(myRentACar.getStartDate())) ||
                                (rentACar.getStartDate().after(myRentACar.getEndDate()) && rentACar.getEndDate().after(myRentACar.getEndDate()))) {
                            request.setStatus(RequestStatus.CANCELED);
                            break;
                        }
                    }
                }
            }
        }

        myRequest.setStatus(RequestStatus.PAID);
        requestRepository.save(myRequest);

    }
}

