package conn.ra.Service.Address;

import conn.ra.Model.Dto.Request.AddressRequest;
import conn.ra.Model.Entity.Address;

import java.util.List;

public interface AddressService {
    Address add(AddressRequest addressRequest, Long userId);
    void delete(Long addressId, Long userId);
    List<Address> getAll(Long id);
    Address findById(Long addressId, Long userId);
}
