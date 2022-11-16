package com.samtuga.rmufilemanagement.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@Getter
@Setter
@AllArgsConstructor
public class UpdateRequest {
    private String name;
    private String type;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] document;
}
