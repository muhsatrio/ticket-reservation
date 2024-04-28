package com.app.project.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.project.repository.CustomerRepository;
import com.app.project.repository.entity.Customer;
import com.app.project.service.CustomerService;
import com.app.project.serviceImpl.vo.CustomerVO;

import io.beanmapper.config.BeanMapperBuilder;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void creates(List<CustomerVO> customerVOs) {
        repository.saveAll(new BeanMapperBuilder().build().map(customerVOs, Customer.class));
    }
    
}
