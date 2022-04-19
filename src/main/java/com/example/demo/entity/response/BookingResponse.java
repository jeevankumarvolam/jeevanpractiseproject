package com.example.demo.entity.response;

import com.example.demo.entity.Links;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class BookingResponse implements Serializable {
    private String id;
    private String accId;
    private String bookingSeason;
    private Integer bookingYear;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Links links;
    @Transient
    private transient  String transientVariable;


}
