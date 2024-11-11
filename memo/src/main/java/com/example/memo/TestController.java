package com.example.memo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        String sql = """
                SELECT * FROM "ITEMS"
                WHERE "STATUS" = "ON_sale"
                ORDER BY "PRICE"
                """;

        return sql;

    }

}
