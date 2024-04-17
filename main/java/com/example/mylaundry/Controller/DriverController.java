package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Driver;
import com.example.mylaundry.Service.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/driver")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity getDriver(){
        return ResponseEntity.status(200).body(driverService.getDriver());
    }

    @PostMapping("/add")
    public ResponseEntity addDriver(@RequestBody @Valid Driver driver){
        driverService.addDriver(driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver added"));

    }
    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateDriver(@PathVariable Integer id, @RequestBody @Valid Driver driver){
        driverService.updateDriver(id,driver);
        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable Integer id){
        driverService.deleteDriver(id);
        return ResponseEntity.ok().body(new ApiResponse("Driver Deleted"));

    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------

    @GetMapping("/getEvaluationOfDriver/{driverId}")
    public ResponseEntity getEvaluationOfDriver(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.getEvaluationOfDriver(driverId));
    }

    //-----------------------   2 endPoint   ------------------------------

    @GetMapping("/findOrders")
    public ResponseEntity findOrders(){

        return ResponseEntity.status(200).body(driverService.findOrders());
    }

    //-----------------------   3endPoint   ------------------------------
//    @PutMapping("/delivery/{driverId}/{orderId}")
//    public ResponseEntity delivery(@PathVariable Integer driverId ,@PathVariable Integer orderId ){
//
//        driverService.delivery(driverId,orderId);
//        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
//    }

    //-----------------------   4endPoint   ------------------------------
    @GetMapping("/previousOrders/{driverId}")
    public ResponseEntity previousOrders(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.previousOrders(driverId));
    }

    //-----------------------   5 endPoint   ------------------------------

    @GetMapping("/currentOrders/{driverId}")
    public ResponseEntity  currentOrders(@PathVariable Integer driverId){

        return ResponseEntity.status(200).body(driverService.currentOrders(driverId));
    }

    //-----------------------   6 endPoint   ------------------------------
//    @PutMapping("/delivery/{driverId}/{orderId}")
//    public ResponseEntity  updateStatus(@PathVariable Integer driverId ,@PathVariable Integer orderId ){
//
//        driverService.updateStatus(driverId,orderId);
//        return ResponseEntity.ok().body(new ApiResponse("Driver Update"));
//    }


}