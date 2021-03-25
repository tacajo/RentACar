package com.adService.controller;


import com.adService.dto.EntityDTO;
import com.adService.model.*;
import com.adService.service.AdminService;
import com.adService.dto.CodesDTO;
import com.adService.service.CodeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CodeBookController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CodeBookService codeBookService;

    @PostMapping(value = "model")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addModel(@RequestBody EntityDTO model) {
        Model newModel = adminService.addModel(new Model(model.getName()));
        return ResponseEntity.ok(newModel);
    }

    @PostMapping(value = "brand")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addBrand(@RequestBody EntityDTO brand) {
        Brand newBrand = adminService.addBrand(new Brand(brand.getName()));
        return ResponseEntity.ok(newBrand);
    }

    @PostMapping(value = "car-class")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addCarClass(@RequestBody EntityDTO carClass) {
        CarClass newCarClass = adminService.addClass(new CarClass(carClass.getName()));
        return ResponseEntity.ok(newCarClass);
    }

    @PostMapping(value = "fuel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addFuel(@RequestBody EntityDTO fuel) {
        Fuel newFuel = adminService.addFuel(new Fuel(fuel.getName()));
        return ResponseEntity.ok(newFuel);
    }

    @PostMapping(value = "gear-shift")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity addGearShift(@RequestBody EntityDTO gearShift) {
        GearShift newGearShift = adminService.addGearShift(new GearShift(gearShift.getName()));
        return ResponseEntity.ok(newGearShift);
    }

    @DeleteMapping(value = "model/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteModel(@PathVariable Long id) {
        adminService.deleteModel(id);
    }

    @DeleteMapping(value = "brand/{id}")
    public void deleteBrand(@PathVariable Long id) {
        adminService.deleteBrand(id);
    }

    @DeleteMapping(value = "car-class/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCarClass(@PathVariable Long id) {
        adminService.deleteClass(id);
    }

    @DeleteMapping(value = "fuel/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteFuel(@PathVariable Long id) {
        adminService.deleteFuel(id);
    }

    @DeleteMapping(value = "gear-shift/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGearShift(@PathVariable Long id) {
        adminService.deleteGearShift(id);
    }

    @GetMapping(value = "codes")
    public ResponseEntity<CodesDTO> getCodes() {
        CodesDTO codesDTO= codeBookService.formCodeBookDTO();
        return ResponseEntity.ok(codesDTO);
    }

    @GetMapping(value = "model")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getModels() {
        return ResponseEntity.ok(codeBookService.getModels());
    }

    @GetMapping(value = "brand")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getBrands() {
        return ResponseEntity.ok(codeBookService.getBrands());
    }

    @GetMapping(value = "fuel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getFuels() {
        return ResponseEntity.ok(codeBookService.getFuels());
    }

    @GetMapping(value = "gear-shift")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getGearShift() {
        return ResponseEntity.ok(codeBookService.getGearShifts());
    }

    @GetMapping(value = "car-class")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getCarClass() {
        return ResponseEntity.ok(codeBookService.getCarClasses());
    }

}


