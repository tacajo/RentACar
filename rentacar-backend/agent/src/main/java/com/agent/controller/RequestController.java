package com.agent.controller;

import com.agent.dto.OwnerRequestDTO;
import com.agent.dto.RentACarDTO;
import com.agent.dto.RequestDTO;
import com.agent.dto.UserDTO;
import com.agent.model.RentACar;
import com.agent.model.Request;
import com.agent.model.User;
import com.agent.service.RentACarService;
import com.agent.service.RequestService;
import com.agent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/request")
@CrossOrigin(origins = "https://localhost:4201")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private UserService userService;


    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/createReserved")
    private void createRequestRESERVED(@RequestBody RentACarDTO rentACarDTO) {
        System.out.println(rentACarDTO);
        requestService.createRequestRESERVED(rentACarDTO);
    }


    @PostMapping
    private void createRequests(@RequestBody RequestDTO requestDTO) {
        System.out.println(requestDTO);
        List<RentACar> rentACarList = new ArrayList<>();
        for (Long id : requestDTO.getRentACars()) {
            rentACarList.add(rentACarService.findById(id));
        }
        requestService.createRequests(rentACarList, requestDTO.isBundle());
    }

    @GetMapping(value = "/owner")
    private List<OwnerRequestDTO> getOwnerRequests() {
        List<OwnerRequestDTO> ret = new ArrayList<>();
        List<Request> requests = requestService.getOwnersRequest();
        for (Request request : requests) {

            User user = userService.findById(request.getUserID()).get();
            OwnerRequestDTO dto = new OwnerRequestDTO().builder()
                    .id(request.getId())
                    .startDate(request.getStartDate())
                    .user(UserDTO.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .build())
                    .rentACars(request.getRentACars())
                    .status(request.getStatus().name())
                    .build();
            ret.add(dto);
        }
        return ret;
    }

    @GetMapping(value = "/user")
    private List<OwnerRequestDTO> getUserRequests() {
        List<OwnerRequestDTO> ret = new ArrayList<>();
        List<Request> requests = requestService.getUserRequest();
        for (Request request : requests) {
            User user = userService.findById(request.getUserID()).get();
            OwnerRequestDTO dto = new OwnerRequestDTO().builder()
                    .id(request.getId())
                    .startDate(request.getStartDate())
                    .paidDate(request.getEndPaid())
                    .user(UserDTO.builder()
                            .id(user.getId())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .build())
                    .rentACars(request.getRentACars())
                    .status(request.getStatus().name())
                    .build();
            ret.add(dto);
        }
        return ret;
    }

    @GetMapping(value = "/pay/{id}")
    private void payRequest(@PathVariable Long id) {
        requestService.payRequest(id);
    }

}
