package ru.itis.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.demo.models.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SupportForm {
    private String text;
    private Long user_id;
    private Long to_user;
}
