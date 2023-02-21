package com.example.graphQL.input;

import com.example.graphQL.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInput {
    private String name;
    private String age;
    private Gender gender;
}
