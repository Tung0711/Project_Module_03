package conn.ra.Service.Address;

import conn.ra.Model.Dto.Request.AddressRequest;
import conn.ra.Model.Entity.Address;
import conn.ra.Model.Entity.User;
import conn.ra.Repository.AddressRepository;
import conn.ra.Service.Auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserService userService;
    @Override
    public Address add(AddressRequest addressRequest, Long userId) {
        User user = userService.findById(userId);

        Address address = Address.builder()
                .user(user)
                .address(addressRequest.getAddress())
                .phone(addressRequest.getPhone())
                .receiveName(addressRequest.getReceiveName ())
                .build();
        return addressRepository.save(address);
    }

    @Override
    public void delete(Long addressId, Long userId) {
        addressRepository.deleteByIdAndUserId(addressId, userId);
    }

    @Override
    public List<Address> getAll(Long id) {
        return addressRepository.findAllByUserId(id);
    }

    @Override
    public Address findById(Long addressId, Long userId) {
        return addressRepository.findByIdAndUserId(addressId, userId);
    }
}
