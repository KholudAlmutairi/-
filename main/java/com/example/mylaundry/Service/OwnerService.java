package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Owner;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Repositroy.CustomerRepository;
import com.example.mylaundry.Repositroy.OrderRepository;
import com.example.mylaundry.Repositroy.OwnerRepository;
import com.example.mylaundry.Repositroy.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;

    public List<Owner> getAllOwner(){
        return ownerRepository.findAll();
    }

    public void addOwner(Owner owner){
        ownerRepository.save(owner);
    }

    public void updateOwner(Integer id,Owner owner){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        o.setName(owner.getName());
        o.setPhoneNumber(owner.getPhoneNumber());
        o.setEmail(owner.getEmail());
        o.setPassword(owner.getPassword());

        ownerRepository.save(o);
    }

    public void deleteOwner(Integer id){
        Owner o =ownerRepository.findOwnerById(id);
        if(o==null){
            throw new ApiException("owner not found");
        }
        ownerRepository.delete(o);
    }

    //-----------------------   end crud   ------------------------------

    //-----------------------   1 endPoint   ------------------------------

    public void discountForServiceType(Integer servicetypeid,Double discount){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(servicetypeid);
        if(sl==null){
            throw new ApiException("service not found");
        }
        sl.setPrice(sl.getPrice()-discount);


    }

    //-----------------------   2 endPoint   ------------------------------

    public List<String> getAllCustomerComment(){
//        List<String> allComment=new ArrayList<>();
//        List<Order> orders=new ArrayList<>();
//
//        for (Order o:orders){
//
//            List<String> orderComment=o.getComment();
//            allComment.addAll(orderComment);
//        }
//        return allComment;
        return orderRepository.findAllComments();
    }







}
