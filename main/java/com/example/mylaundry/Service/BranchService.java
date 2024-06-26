package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Branch;
import com.example.mylaundry.Model.Laundry;
import com.example.mylaundry.Model.Order;
import com.example.mylaundry.Repositroy.BranchRepository;
import com.example.mylaundry.Repositroy.LaundryRepository;
import com.example.mylaundry.Repositroy.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {
    private final BranchRepository branchRepository;
    private final LaundryRepository laundryRepository;
    private final OrderRepository orderRepository;


    public List<Branch> getBranch() {
        return branchRepository.findAll();
    }

    public void addBranch( Integer LaundryId, Branch branch) {
        Laundry laundry = laundryRepository.findLaundryById(LaundryId);
        if (laundry == null) {
            throw new ApiException("LaundryId not found");
        }
        branch.setLaundry(laundry);
        branchRepository.save(branch);
    }
    public void updateBranch(Integer id, Branch branch) {
        Branch branch1 = branchRepository.findBranchById(id);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        branch1.setDistrict(branch.getDistrict());
        branch1.setStreet(branch.getStreet());
        branchRepository.save(branch1);

    }

    public void deleteBranch(Integer id) {
        Branch branch1 = branchRepository.findBranchById(id);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        branchRepository.delete(branch1);
    }


    //-----------------------   end crud   ------------------------------




    //-----------------------   1 endPoint   ------------------------------

    public void updateStatus(Integer orderId ) {
        Order order1 = orderRepository.findOrderById(orderId);
        if (order1 == null) {
            throw new ApiException("order id not found");
        }

        if (order1.getStatus().equalsIgnoreCase("waiting")) {
            order1.setStatus("accepted");
            orderRepository.save(order1);
        } else if (order1.getStatus().equalsIgnoreCase("received stuff")) {
            order1.setStatus("Delivered");
            orderRepository.save(order1);

        }



    }}