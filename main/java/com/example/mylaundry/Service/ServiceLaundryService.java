package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.Branch;
import com.example.mylaundry.Model.Laundry;
import com.example.mylaundry.Model.Order;
import com.example.mylaundry.Model.ServiceLaundry;
import com.example.mylaundry.Repositroy.BranchRepository;
import com.example.mylaundry.Repositroy.LaundryRepository;
import com.example.mylaundry.Repositroy.OrderRepository;
import com.example.mylaundry.Repositroy.ServiceLaundryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceLaundryService {

    private final ServiceLaundryRepository serviceLaundryRepository;
    private final LaundryRepository laundryRepository;
    private final BranchRepository branchRepository;
    private final OrderRepository orderRepository;

    public List<ServiceLaundry> getAllServiceLaundry(){
        return serviceLaundryRepository.findAll();
    }

    public void addServiceLaundry(ServiceLaundry serviceLaundry){
        serviceLaundryRepository.save(serviceLaundry);
    }


    public void updateServiceLaundry(Integer id, ServiceLaundry serviceLaundry){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(id);
        if(sl==null){
            throw new ApiException("service laundry found");
        }

        sl.setCategory(serviceLaundry.getCategory());
        sl.setName(serviceLaundry.getName());
        sl.setServiceType(serviceLaundry.getServiceType());
        sl.setPrice(serviceLaundry.getPrice());

        serviceLaundryRepository.save(sl);
    }

    public void deleteServiceLaundry(Integer id){
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(id);
        if(sl==null){
            throw new ApiException("service laundry found");
        }
        serviceLaundryRepository.delete(sl);
    }

    //-----------------------   end crud   ------------------------------



    public void  assignBranchToServiceLaundry(Integer branch_id,Integer serviceLaundry_id){
        Branch b=branchRepository.findBranchById(branch_id);
        ServiceLaundry sl=serviceLaundryRepository.findServiceLaundryById(serviceLaundry_id);

        if(b ==null || sl==null){
            throw new ApiException("cannot assign");
        }

        sl.setBranch(b);

        serviceLaundryRepository.save(sl);
    }

    //-----------------------   1 endPoint   ------------------------------

    public List<Order> previousOrders(Integer laundryid) {
        ArrayList<Order> orders2 = new ArrayList<Order>();
        Laundry l=laundryRepository.findLaundryById(laundryid);
        if (l == null) {
            throw new ApiException("laundry id not found");
        }
        List<Order> orders3 = orderRepository.findOrderByServiceLaundriesAAndLaundryId(laundryid);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    //-----------------------   2 endPoint   ------------------------------


    public List<Order> currentOrders(Integer laundryid) {
        ArrayList<Order> orders2 = new ArrayList<>();
        Laundry l=laundryRepository.findLaundryById(laundryid);
        if (l == null) {
            throw new ApiException("laundry id not found");
        }
        List<Order> orders3 = orderRepository.findOrderByServiceLaundriesAAndLaundryId(laundryid);
        if (orders3== null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry")){
                orders2.add(orders1);
            }}

        return orders2;
    }

    //22 get highrating servicetype in specific laundry (laundryid)

//    public String getMostRequestedServiceTypeInLaundry(Integer laundryId) {
//        // الحصول على جميع الطلبات المرتبطة بالمغسلة
//        List<Order> orders = orderRepository.findOrderByLaundryId(laundryId);
//        if (orders.isEmpty()) {
//            throw new ApiException("No orders found for this laundry");
//        }
//
//        // تخزين عدد الطلبات لكل نوع من الخدمات في قائمة
//        List<String> serviceTypes = new ArrayList<>();
//        for (Order order : orders) {
//            serviceTypes.add(order.getServiceLaundry().getServiceType());
//        }
//
//        // الحصول على النوع الأكثر طلبًا
//        String mostRequestedServiceType = null;
//        int maxCount = 0;
//        for (String serviceType : serviceTypes) {
//            int count = Collections.frequency(serviceTypes, serviceType);
//            if (count > maxCount) {
//                maxCount = count;
//                mostRequestedServiceType = serviceType;
//            }
//        }
//
//        return mostRequestedServiceType;
//    }

//kh     في برانش معين ترجع عدد  الخدمات داخل الكاتقري
    public Integer numberofservic(Integer brunchid,String Categry) {
        Integer counter=0;
        Branch branch1 = branchRepository.findBranchById(brunchid);
        if (branch1 == null) {
            throw new ApiException("id not found");
        }
        List<ServiceLaundry> s=serviceLaundryRepository.findServiceLaundriesByCategory(Categry);
        for(ServiceLaundry serviceLaundry:s){
            if(serviceLaundry.getBranch().getId()==brunchid);{
                counter++;
            }
        }

        return counter;
    }








}