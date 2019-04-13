package cn.gpa.zut.service;

import java.util.List;

import cn.gpa.zut.domain.Product;

public interface IProductService {

    public List<Product> findAll() throws Exception;

    void save(Product product) throws Exception;
}
