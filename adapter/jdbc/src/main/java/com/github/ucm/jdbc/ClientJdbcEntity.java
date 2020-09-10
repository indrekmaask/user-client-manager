package com.github.ucm.jdbc;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("client")
public class ClientJdbcEntity {

  private UserJdbcEntity user;
}
