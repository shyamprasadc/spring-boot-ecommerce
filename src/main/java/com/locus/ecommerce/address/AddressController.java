package com.locus.ecommerce.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping
    public List<Address> getAddressByUser() {
        return addressService.getAddressByUser();
    }

    @PostMapping
    public void addAddress(@RequestBody Map<String, Object> reqBody) {
        String address = (String) reqBody.get("address");
        String city = (String) reqBody.get("city");
        String postcode = (String) reqBody.get("postcode");
        addressService.addAddress(address, city, postcode);
    }

    @PutMapping(path = "{addressId}")
    public void updateAddress(@PathVariable("addressId") Long addressId, @RequestBody Map<String, Object> reqBody) {
        String address = (String) reqBody.get("address");
        String city = (String) reqBody.get("city");
        String postcode = (String) reqBody.get("postcode");
        addressService.updateAddress(addressId, address, city, postcode);
    }

    @DeleteMapping(path = "{addressId}")
    public void deleteAddress(@PathVariable("addressId") Long addressId) {
        addressService.deleteAddress(addressId);
    }
}
