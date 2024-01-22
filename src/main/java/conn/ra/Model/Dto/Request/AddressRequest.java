package conn.ra.Model.Dto.Request;

import conn.ra.Model.Entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressRequest {
    private String address;
    private String phone;
    private String receiveName;
}
