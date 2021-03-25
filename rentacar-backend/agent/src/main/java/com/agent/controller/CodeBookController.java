package com.agent.controller;

import com.agent.dto.CodesDTO;
import com.agent.dto.EntityDTO;
import com.agent.model.*;
import com.agent.service.CodeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://localhost:4201")
public class CodeBookController {


    @Autowired
    private CodeBookService codeBookService;


    @GetMapping(value = "codes")
    public ResponseEntity<CodesDTO> getCodes() {
        CodesDTO codesDTO= codeBookService.formCodeBookDTO();
        return ResponseEntity.ok(codesDTO);
    }

    @GetMapping(value = "model")
    @PreAuthorize("hasAuthority('codebook')")
    public ResponseEntity getModels() {
        return ResponseEntity.ok(codeBookService.getModels());
    }

    @GetMapping(value = "brand")
    @PreAuthorize("hasAuthority('codebook')")
    public ResponseEntity getBrands() {
        return ResponseEntity.ok(codeBookService.getBrands());
    }

    @GetMapping(value = "fuel")
    @PreAuthorize("hasAuthority('codebook')")
    public ResponseEntity getFuels() {
        return ResponseEntity.ok(codeBookService.getFuels());
    }

    @GetMapping(value = "gear-shift")
    @PreAuthorize("hasAuthority('codebook')")
    public ResponseEntity getGearShift() {
        return ResponseEntity.ok(codeBookService.getGearShifts());
    }

    @GetMapping(value = "car-class")
    @PreAuthorize("hasAuthority('codebook')")
    public ResponseEntity getCarClass() {
        return ResponseEntity.ok(codeBookService.getCarClasses());
    }

    @PostMapping(value = "model")
    public ResponseEntity addModel(@RequestBody EntityDTO model) {
        Model newModel = codeBookService.addModel(new Model(model.getName()));
        return ResponseEntity.ok(newModel);
    }

    @PostMapping(value = "brand")
    public ResponseEntity addBrand(@RequestBody EntityDTO brand) {
        Brand newBrand = codeBookService.addBrand(new Brand(brand.getName()));
        return ResponseEntity.ok(newBrand);
    }

    @PostMapping(value = "car-class")
    public ResponseEntity addCarClass(@RequestBody EntityDTO carClass) {
        CarClass newCarClass = codeBookService.addClass(new CarClass(carClass.getName()));
        return ResponseEntity.ok(newCarClass);
    }

    @PostMapping(value = "fuel")
    public ResponseEntity addFuel(@RequestBody EntityDTO fuel) {
        Fuel newFuel = codeBookService.addFuel(new Fuel(fuel.getName()));
        return ResponseEntity.ok(newFuel);
    }

    @PostMapping(value = "gear-shift")
    public ResponseEntity addGearShift(@RequestBody EntityDTO gearShift) {
        GearShift newGearShift = codeBookService.addGearShift(new GearShift(gearShift.getName()));
        return ResponseEntity.ok(newGearShift);
    }

    @DeleteMapping(value = "model/{id}")
    public void deleteModel(@PathVariable Long id) {
        codeBookService.deleteModel(id);
    }

    @DeleteMapping(value = "brand/{id}")
    public void deleteBrand(@PathVariable Long id) {
        codeBookService.deleteBrand(id);
    }

    @DeleteMapping(value = "car-class/{id}")
    public void deleteCarClass(@PathVariable Long id) {
        codeBookService.deleteClass(id);
    }

    @DeleteMapping(value = "fuel/{id}")
    public void deleteFuel(@PathVariable Long id) {
        codeBookService.deleteFuel(id);
    }

    @DeleteMapping(value = "gear-shift/{id}")
    public void deleteGearShift(@PathVariable Long id) {
        codeBookService.deleteGearShift(id);
    }

}


