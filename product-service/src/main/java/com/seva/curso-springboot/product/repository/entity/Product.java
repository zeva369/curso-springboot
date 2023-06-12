package com.seva.serviceDemo1.service.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "El nombre del campo no debe ser vacío")
    private String name;
    private String description;
    @Positive(message = "El Stock no puede ser negativo")
    private Double stock;
    private Double price;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @NotNull(message = "La categoría no puede ser vacía")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private com.seva.serviceDemo1.service.repository.entity.Category category;

}
