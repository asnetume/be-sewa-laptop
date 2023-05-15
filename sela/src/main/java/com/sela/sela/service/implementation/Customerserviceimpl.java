package com.sela.sela.service.implementation;

import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sela.sela.dto.Customerdto;
import com.sela.sela.model.Customer;
import com.sela.sela.repository.Customerrepository;
import com.sela.sela.service.Customerservice;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Customerserviceimpl implements Customerservice {
    @Autowired
    private Customerrepository customerrepository;

    @Override
    public List<Customer> index() {
        List<Customer> index = customerrepository.findAll();
        return index;
    }

    @Override
    public Customer register(Customer customer) {
        customer.setPassword(passEncript(customer.getPassword()));
        return customerrepository.save(customer);    
    }

    @Override
    public Customer show(Long id) {
        Optional<Customer> bOptional = customerrepository.findById(id);
        if (bOptional.isPresent()) {
            return bOptional.get();
        }
        return null;
    }

    @Override
    public Customer update(Long id, Customer customer) {
        Customer data = this.show(id);
        if (data == null) {
            return null;
        }
        data.setName(customer.getName());
        data.setEmail(customer.getEmail());
        data.setPassword(passEncript(customer.getPassword()));
        data.setPhone(customer.getPhone());
        data.setAddress(customer.getAddress());
        return data;
    }

    @Override
    public Customer delete(Long id) {
        Customer data = this.show(id);
        if (data == null) {
            return null;
        }
        customerrepository.delete(data);
        return data;
    }
    
    private String passEncript(String pass) {
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }

    @Override
    public Customerdto login(Customer dataRequest) throws Exception {
        Customer cs = customerrepository.findByEmail(dataRequest.getEmail());
        if (cs == null) {
            throw new Exception("Invalid email or password.");
        } 
        if (!dataRequest.getPassword().equals(cs.getPassword())){
            throw new Exception("Invalid email or password.");
        }

        String token = Jwts.builder()
        .setSubject(dataRequest.getEmail())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Token berlaku selama 24 jam
        .signWith(SignatureAlgorithm.HS512, "SecretKey") // Ganti dengan secret key yang lebih aman
        .compact();
        return Customerdto.builder().token(token).id(cs.getId()).name(cs.getName()).email(cs.getEmail()).phone(cs.getPhone()).address(cs.getAddress()).build();
    }
    
}
