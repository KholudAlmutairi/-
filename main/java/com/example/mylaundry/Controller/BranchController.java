package com.example.mylaundry.Controller;

import com.example.mylaundry.Api.ApiResponse;
import com.example.mylaundry.Model.Branch;
import com.example.mylaundry.Service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vi/branch")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;

    @GetMapping("/get")
    public ResponseEntity getBranch(){
        return ResponseEntity.status(200).body(branchService.getBranch());
    }

    @PostMapping("/add/{merchantId}")
    public ResponseEntity addBranch(@PathVariable Integer LaundryId , @RequestBody @Valid Branch branch){
        branchService.addBranch(LaundryId,branch);
        return ResponseEntity.ok().body(new ApiResponse("Branch added"));

    }


    @PutMapping("/Update/{id}")
    public ResponseEntity UpdateBranch(@PathVariable Integer id, @RequestBody @Valid Branch branch){
        branchService.updateBranch(id,branch);

        return ResponseEntity.ok().body(new ApiResponse("Branch Update"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok().body(new ApiResponse("Branch Deleted"));

    }

    //-----------------------   end crud   ------------------------------

    @PutMapping("/updatestatus/{orderId}")
    public ResponseEntity updateStatus(@PathVariable Integer orderId){
        branchService.updateStatus(orderId);
        return ResponseEntity.ok().body(new ApiResponse("status Updated"));
    }

}