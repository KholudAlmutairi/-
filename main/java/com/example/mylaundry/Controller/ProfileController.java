package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.DTO.ProfileDTO;
import com.example.mylaundry.Service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;


    @GetMapping("/get")
    public ResponseEntity getAllProfiles(){
        return ResponseEntity.status(200).body( profileService.getAllProfiles());
    }


    @PostMapping("/add")
    public ResponseEntity addProfile(@RequestBody @Valid ProfileDTO profileDTO){
        profileService.addProfiler(profileDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Profile Added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProfile(@RequestBody @Valid ProfileDTO profileDTO){
        profileService.updateProfile(profileDTO);
        return ResponseEntity.status(200).body(new ApiResponse( "Profile updated"));
    }

    //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProfile(@PathVariable Integer id){
        profileService.deleteProfile(id);
        return ResponseEntity.status(200).body(new ApiResponse("Profile deleted!"));
    }

    //-----------------------   end crud   ------------------------------
    //kh
    @GetMapping("/check/{customerId}/{phoneNumber}/{email}")
    public ResponseEntity verifyPhoneNumberMatchesEmail(@PathVariable Integer customerId ,@PathVariable String phoneNumber,@PathVariable String email){
        profileService.verifyPhoneNumberMatchesEmail(customerId, phoneNumber, email);
        return ResponseEntity.ok().body(new ApiResponse("customer found"));
    }





}