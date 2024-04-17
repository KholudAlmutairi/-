package com.example.mylaundry.Service;

import com.example.mylaundry.Api.ApiException;
import com.example.mylaundry.Model.*;
import com.example.mylaundry.Repositroy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final DriverRepository driverRepository;
    private final ServiceLaundryRepository serviceLaundryRepository;
    private final BranchRepository branchRepository;
    private final LaundryRepository laundryRepository;

    // Get All Customers
    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }


    //• Add new Customer
    public void addCustomer(Customer customers) {

        customerRepository.save(customers);
    }


    //• Update Customer
    public void updateCustomer(Integer id, Customer customers) {
        Customer c = customerRepository.findCustomersById(id);

        if (c == null) {
            throw new ApiException("Wrong id");
        }
        c.setName(customers.getName());
        c.setProfile(customers.getProfile());

        customerRepository.save(c);

    }

    //• Delete Customer
    public void deleteCustomer(Integer id) {
        Customer c = customerRepository.findCustomersById(id);
        if (c == null) {
            throw new ApiException("Wrong id");
        }
        customerRepository.delete(c);
    }

    //-----------------------   end crud   ------------------------------


//    public void order(Integer customerId, Integer serviceId , Integer quantity ) {
//        Order order1 = new Order();
//        Customer customer1 = customerRepository.findCustomersById(customerId);
//        if (customer1 == null) {
//            throw new ApiException("customer id not found");
//        }
//        ServiceLaundry service=serviceLaundryRepository.findServiceLaundryById(serviceId);
//        if(service==null){
//            throw new ApiException("service laundry found");
//        }
//
//        order1.setQuantity(quantity);
//        order1.setCustomerId(customerId);
//        //   ordersService.taxCalculation(product1.getPrice());
//       // order1.setTax(ordersService.taxCalculation(product1.getPrice()));
//        order1.setTotalPrice(service.getPrice() + order1.getDeliveryPrice());
//        order1.setDeliveryPrice(order1.getDeliveryPrice());
//        order1.setStatus("Waiting");
//        service.getLaundry().;
//
//
//        orderService.addOrders(order1);
//        order.add(order1);
//        delivery.add(order1);
//
//    }
//
//


    //-----------------------   2 endPoint   ------------------------------

    public String statusOfOrder(Integer orderId) {
        Order orders1 = orderRepository.findOrderById(orderId);
        if (orders1 == null) {
            throw new ApiException("order id not found");
        }

        return orders1.getStatus();
    }

    //-----------------------   3 endPoint   ------------------------------

    public List<Order> currentOrders(Integer customerId) {
        ArrayList<Order> orders2 = new ArrayList<>();
        Customer c = customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        List<Order> orders3 = orderRepository.findOrdersByCustomerId(customerId);
        if (orders3 == null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("received stuff") || orders1.getStatus().equalsIgnoreCase("received to laundry") || orders1.getStatus().equalsIgnoreCase("accepted") || orders1.getStatus().equalsIgnoreCase("Waiting")) {
                orders2.add(orders1);
            }
        }

        return orders2;
    }

    //-----------------------   4 endPoint   ------------------------------

    public List<Order> previousOrders(Integer customerId) {
        ArrayList<Order> orders2 = new ArrayList<>();
        Customer c = customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        List<Order> orders3 = orderRepository.findOrdersByCustomerId(customerId);
        if (orders3 == null) {
            throw new ApiException(" orders not found");
        }
        for (Order orders1 : orders3) {
            if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
                orders2.add(orders1);
            }
        }

        return orders2;
    }


//-----------------------   5 endPoint   ------------------------------

//        public void evaluationDriver(Integer customerId, Integer orderId, Double evaluation) {
//
//            Customer customer1 = customerRepository.findCustomersById(customerId);
//            if (customer1 == null) {
//                throw new ApiException("customer id not found");
//            }
//            Order orders1 = orderRepository.findOrderById(orderId);
//            if (orders1 == null) {
//                throw new ApiException(" Order id not found");
//            }
//            if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
//                if (orders1.getCustomerId().equals(customerId)){
//
//                    Driver driver = driverRepository.findDriversById(orders1.getDriverId());
//
//                    Double e =  ( (driver.getEvaluation()+evaluation) / driver.getDeliveryOrders());
//                    driverRepository.updateDriverEvaluationById(driver.getId(),e);
//                    driverRepository.save(driver);
//
//
//                }}}


    //-----------------------   kh1 endPoint   ------------------------------
    //  get rating for laundry(laundryid) 6

    public Double getRatingForLaundry(Integer laundryId) {
        ServiceLaundry laundry = serviceLaundryRepository.findServiceLaundryById(laundryId);
        if (laundry == null) {
            throw new ApiException("Laundry not found");
        }

        // جمع تقييمات جميع الطلبات التي تم تسليمها من هذه المغسلة
        List<Order> deliveredOrders = orderRepository.findDeliveredOrdersByLaundryId(laundryId);
        if (deliveredOrders.isEmpty()) {
            throw new ApiException("No delivered orders found for this laundry");
        }

        double totalRating = 0.0;
        int numOfOrders = 0;
        for (Order order : deliveredOrders) {
            if (order.getRating() != null) {
                totalRating += order.getRating();
                numOfOrders++;
            }
        }

        // حساب المتوسط للتقييمات
        if (numOfOrders == 0) {
            throw new ApiException("No ratings found for delivered orders from this laundry");
        }
        return totalRating / numOfOrders;
    }


    //-----------------------   kh2 endPoint   ------------------------------

    //rating for laundry(laundryid,orderid,rating) 10
    // العميل يقوم بتقييم المغسلة بعد ما يتم الطلب ثم تتغير حالة التقييم التي في كلاس الطلب

//    public void rateLaundry(Integer laundryId, Integer orderId,Integer rating) {
//        ServiceLaundry laundry = serviceLaundryRepository.findServiceLaundryById(laundryId);
//        if (laundry == null) {
//            throw new ApiException("Laundry not found");
//        }
//
//        Order order = orderRepository.findOrderById(orderId);
//
//        if (order == null) {
//            throw new ApiException("Order not found");
//        }
//        //            if (orders1.getStatus().equalsIgnoreCase("Delivered")) {
////                if (orders1.getCustomerId().equals(customerId)){
////
////                    Driver driver = driverRepository.findDriversById(orders1.getDriverId());
////
////                    Double e =  ( (driver.getEvaluation()+evaluation) / driver.getDeliveryOrders());
////                    driverRepository.updateDriverEvaluationById(driver.getId(),e);
////                    driverRepository.save(driver);
////
//
//        if (!order.getLaundryId().equals(laundryId)) {
//            throw new ApiException("Order does not belong to this laundry");
//        }
//
//        if (!order.getStatus().equalsIgnoreCase("Delivered")) {
//            throw new ApiException("Order is not delivered yet");
//        }
//
////        if(order.getLaundryId().equals(laundryId)){
////            Laundry laundry1=laundryRepository.findLaundryById(order.getLaundryId());
////            Integer r=((laundry.get))
////        }
//        // تحديث تقييم المغسلة في الطلب
//        order.setRating(rating);
//
//        orderRepository.save(order);
//    }

    //get all laundry near(string district) 9
    // ميثود تستعرض كل المغاسل القريبة من المستخدم باستخدام المنطقة
    public List<Laundry> getLaundryNear(Integer customerId) {
        Customer c = customerRepository.findCustomersById(customerId);
        if (c == null) {
            throw new ApiException("customerId not found");
        }
        return laundryRepository.findLaundriesByDistrict(c.getProfile().getDistrict());

    }


    public void rateLaundry(Integer orderId,Integer rating) {

        Order order = orderRepository.findOrderById(orderId);

        if (order == null) {
            throw new ApiException("Order not found");
        }
        if(order.getRating()==0){
            Double e =  ( (order.get.getEvaluation()+evaluation) / driver.getDeliveryOrders());
            driverRepository.updateDriverEvaluationById(driver.getId(),e);
            driverRepository.save(driver);
        }

        if (!order.getStatus().equalsIgnoreCase("Delivered")) {
            throw new ApiException("Order is not delivered yet");
        }

        // تحديث تقييم المغسلة في الطلب
        order.setRating(rating);
        branch.setEvaluation(evaluation);

        orderRepository.save(order);
    }








}