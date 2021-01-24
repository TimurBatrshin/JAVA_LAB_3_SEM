package ru.itis.javalab.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String first_name;
    private String last_name;
    private Boolean isDeleted;
}
