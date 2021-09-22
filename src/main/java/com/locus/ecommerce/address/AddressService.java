package com.locus.ecommerce.address;

import com.locus.ecommerce.auth.AuthService;
import com.locus.ecommerce.exception.ApiRequestException;
import com.locus.ecommerce.user.User;
import com.locus.ecommerce.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    public Optional<Address> getOneAddress(Long addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        if (address.isEmpty()) {
            throw new ApiRequestException("Address Not Found");
        }
        return address;
    }

    public List<Address> getAddressByUser() {
        User currentUser = authService.getCurrentUser();
        return addressRepository.findAllByUser(currentUser);
    }

    public void addAddress(String address, String city, String postcode) {
        User currentUser = authService.getCurrentUser();
        Address newAddress = new Address(currentUser, address, city, postcode);
        addressRepository.save(newAddress);
    }

    @Transactional
    public void addAddressOfUser(String email, String address, String city, String postcode) {
        Optional<User> user = userRepository.findByEmail(email);
        Address newAddress = new Address(user.get(), address, city, postcode);
        addressRepository.save(newAddress);
    }

    @Transactional
    public void updateAddress(Long addressId, String address, String city, String postcode) {
        Address existingAddress = addressRepository.findById(addressId).orElseThrow(() -> new ApiRequestException("Address Not Found"));

        if (address != null && address.length() > 0 && !Objects.equals(existingAddress.getAddress(), address)) {
            existingAddress.setAddress(address);
        }
        if (city != null && city.length() > 0 && !Objects.equals(existingAddress.getCity(), city)) {
            existingAddress.setCity(city);
        }
        if (postcode != null && postcode.length() > 0 && !Objects.equals(existingAddress.getPostcode(), postcode)) {
            existingAddress.setPostcode(postcode);
        }
    }

    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
