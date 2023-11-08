package com.example.wrapper;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountWrapper implements Serializable {
    private int count;
}