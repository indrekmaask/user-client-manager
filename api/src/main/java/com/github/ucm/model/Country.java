package com.github.ucm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class Country {
  @Id
  private String code;
  private String name;
}
