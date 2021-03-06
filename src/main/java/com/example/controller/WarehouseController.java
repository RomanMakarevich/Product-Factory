package com.example.controller;

import com.example.dto.WarehouseDTO;
import com.example.service.WarehouseService;
import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

@Log
@Data
@RestController
@RequestMapping("/product-factory-app")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PutMapping (value = "/products/{productId}")
    private  void  addProduct(@PathVariable final Long productId,
                              @RequestParam("numberOfProduct") final Long numberOfProduct){
    warehouseService.addProduct(productId, numberOfProduct);
    }
}
