package conn.ra.Model.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    private String fullName;
    @NotNull(message = "Không được null")
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    private String username;
    @NotNull(message = "Không được null")
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    private String password;
    @NotNull(message = "Không được null")
    @NotEmpty(message = "Không được rỗng")
    @NotBlank(message = "Không được để trống")
    private String address;
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "không đúng định dạng")
    private String email;
    @Pattern(regexp = "/(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\\\b/\n", message = "Không đúng định dạng")
    private String phone;
}