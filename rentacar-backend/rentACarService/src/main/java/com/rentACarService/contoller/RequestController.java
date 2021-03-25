package com.rentACarService.contoller;

import com.rentACarService.clients.AuthClient;
import com.rentACarService.dto.OwnerRequestDTO;
import com.rentACarService.dto.RequestDTO;
import com.rentACarService.dto.UserDTO;
import com.rentACarService.model.RentACar;
import com.rentACarService.model.Request;
import com.rentACarService.service.RentACarService;
import com.rentACarService.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/request")
@CrossOrigin(origins = "http://localhost:4200")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RentACarService rentACarService;

    @Autowired
    private AuthClient authClient;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
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
            UserDTO user = authClient.getUser(request.getUserID());
            OwnerRequestDTO dto = new OwnerRequestDTO().builder()
                    .id(request.getId())
                    .startDate(request.getStartDate())
                    .user(user)
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
            UserDTO user = authClient.getUser(request.getUserID());
            OwnerRequestDTO dto = new OwnerRequestDTO().builder()
                    .id(request.getId())
                    .startDate(request.getStartDate())
                    .paidDate(request.getEndPaid())
                    .user(user)
                    .rentACars(request.getRentACars())
                    .status(request.getStatus().name())
                    .build();
            ret.add(dto);
        }
        return ret;
    }

    @GetMapping(value = "/cancel/{id}")
    private void cancelRequest(@PathVariable Long id) {
        requestService.cancelRequest(id);
    }

    @GetMapping(value = "/accept/{id}")
    private void acceptRequest(@PathVariable Long id) {
        requestService.acceptRequest(id);
    }

    @GetMapping(value = "/pay/{id}")
    private void payRequest(@PathVariable Long id) {
        requestService.payRequest(id);
    }

}
