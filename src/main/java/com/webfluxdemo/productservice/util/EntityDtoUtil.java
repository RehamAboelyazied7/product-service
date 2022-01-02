package com.webfluxdemo.productservice.util;

import com.webfluxdemo.productservice.dto.ProductDto;
import com.webfluxdemo.productservice.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {
    public static ProductDto toProductDto(ProductEntity productEntity) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
//        productDto.setDescription(productEntity.getDescription());
//        productDto.setPrice(productDto.getPrice());
        return productDto;
    }

    public static ProductEntity toProductEntity(ProductDto productDto) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productDto, productEntity);
//        productEntity.setDescription(productDto.getDescription());
//        productEntity.setPrice(productDto.getPrice());
        return productEntity;

    }
}
