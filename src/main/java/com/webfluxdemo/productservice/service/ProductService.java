package com.webfluxdemo.productservice.service;

import com.webfluxdemo.productservice.dto.ProductDto;
import com.webfluxdemo.productservice.repository.ProductRepository;
import com.webfluxdemo.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts() {
        return this.productRepository.findAll()
                .map(EntityDtoUtil::toProductDto);
    }

    public Mono<ProductDto> getProductById(String id) {
        return this.productRepository.findById(id)
                .map(EntityDtoUtil::toProductDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDto) {
        return productDto.map(EntityDtoUtil::toProductEntity)
                .flatMap(this.productRepository::insert)
                .map(EntityDtoUtil::toProductDto);
    }

    public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDto) {
        return productRepository.findById(id)
                .flatMap(u ->
                        productDto.map(EntityDtoUtil::toProductEntity)
                                .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(EntityDtoUtil::toProductDto);

    }

    //Mono<Void>  as you have to subscribe that
    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
}
