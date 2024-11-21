package com.MarketPet.MarketPet.Controller;


import com.MarketPet.MarketPet.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;


    @GetMapping("/daily-sales")
    public ResponseEntity<List<Map<String, Object>>> getDailySales() {
        return ResponseEntity.ok(dashboardService.getDailySales());
    }


    @GetMapping("/top-products")
    public ResponseEntity<List<Map<String, Object>>> getTopProducts() {
        return ResponseEntity.ok(dashboardService.getTopProducts());
    }


    @GetMapping("/top-sellers")
    public ResponseEntity<List<Map<String, Object>>> getTopSellers() {
        return ResponseEntity.ok(dashboardService.getTopSellers());
    }


    @GetMapping("/product-distribution")
    public ResponseEntity<List<Map<String, Object>>> getProductDistribution() {
        return ResponseEntity.ok(dashboardService.getProductDistribution());
    }


    @GetMapping("/average-rating-evolution")
    public ResponseEntity<List<Map<String, Object>>> getAverageRatingEvolution() {
        return ResponseEntity.ok(dashboardService.getAverageRatingEvolution());
    }


    @GetMapping("/seller-performance-comparison")
    public ResponseEntity<List<Map<String, Object>>> getSellerPerformanceComparison() {
        return ResponseEntity.ok(dashboardService.getSellerPerformanceComparison());
    }


    @GetMapping("/sales-vs-ratings")
    public ResponseEntity<List<Map<String, Object>>> getSalesVsRatings() {
        return ResponseEntity.ok(dashboardService.getSalesVsRatings());
    }
}
