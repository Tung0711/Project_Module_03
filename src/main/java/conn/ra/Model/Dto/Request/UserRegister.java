package conn.ra.Model.Dto.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegister {
    @NotNull(message = "Không được null")
    private String fullName;

    @Size(min = 6, max = 100, message = "số kí tự không chính xác")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "không chứa kí tự đặc biệt")
    private String userName;

    @NotNull(message = "Không được null")
    private String password;

    @NotNull(message = "Không được null")
    private String address;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email không đúng định dạng")
    private String email;

    @Pattern(regexp = "^0[1-9]\\d{8}$", message = "Số điện thoại không đúng định dạng")
    private String phone;
}