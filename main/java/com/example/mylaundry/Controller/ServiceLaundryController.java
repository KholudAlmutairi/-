package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Order;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Service.OrderService;
import com.example.mylaundry.Service.ServiceLaundryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/servicelaundry")
public class ServiceLaundryController {

    private final ServiceLaundryService serviceLaundryService;


    @GetMapping("/get")
    public ResponseEntity getAllServiceLaundry(){

        return ResponseEntity.status(200).body(serviceLaundryService.getAllServiceLaundry());
    }


    @PostMapping("/add")
    public ResponseEntity addServiceLaundry(@RequestBody @Valid ServiceLaundry serviceLaundry){

        serviceLaundryService.addServiceLaundry(serviceLaundry);
        return ResponseEntity.status(200).body(new ApiResponse("Service Laundry added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateServiceLaundry(@PathVariable Integer id,@RequestBody @Valid ServiceLaundry serviceLaundry){
        serviceLaundryService.updateServiceLaundry(id, serviceLaundry);
        return ResponseEntity.status(200).body(new ApiResponse("Service Laundry added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteServiceLaundry(@PathVariable Integer id){
        serviceLaundryService.deleteServiceLaundry(id);
        return ResponseEntity.status(200).body(new ApiResponse("ServiceLaundry deleted"));
    }

    //-----------------------   end crud   ------------------------------

    @PutMapping("/assign/{branch_id}/{serviceLaundry_id}")
    public ResponseEntity assignBranchToServiceLaundry(@PathVariable Integer branch_id,@PathVariable Integer serviceLaundry_id){
        serviceLaundryService.assignBranchToServiceLaundry(branch_id, serviceLaundry_id);
        return ResponseEntity.status(200).body(new ApiResponse("assign done"));
    }

    //-----------------------   1 endPoint   ------------------------------

    @GetMapping("/previousOrders/{laundryid}")
    public ResponseEntity previousOrders(@PathVariable Integer laundryid){
        return ResponseEntity.status(200).body(serviceLaundryService.previousOrders(laundryid));
    }

    //-----------------------   2 endPoint   ------------------------------


    @GetMapping("/currentOrders/{laundryid}")
    public ResponseEntity currentOrders(@PathVariable Integer laundryid){
        return ResponseEntity.status(200).body(serviceLaundryService.currentOrders(laundryid));
    }








}