package com.locus.ecommerce.user;

import com.locus.ecommerce.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long userId;
   private String name;
   private String email;
   private String phone;
   private String password;
   @ManyToMany(fetch = FetchType.EAGER)
   private Collection<Role> roles = new ArrayList<>();
   private int status;
   private String address;
   private String city;
   private String postcode;
   @CreationTimestamp
   private Date createdAt;
   @UpdateTimestamp
   private Date updatedAt;

   public User(String name, String email, String phone, String password, Collection<Role> roles, int status,
         String address, String city, String postcode) {
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.password = password;
      this.roles = roles;
      this.status = status;
      this.address = address;
      this.city = city;
      this.postcode = postcode;
   }
}
