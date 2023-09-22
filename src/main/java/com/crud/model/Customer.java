package com.crud.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter

@Entity
@Table(name = "customers")
public class Customer {
  @Id @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column
  private String name;

  @Column
  private String document;

  @Column
  private String email;

  @Column
  private String telephone;
}
