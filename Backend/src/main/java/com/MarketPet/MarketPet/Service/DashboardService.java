package com.MarketPet.MarketPet.Service;


import com.MarketPet.MarketPet.Repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class DashboardService {


    @Autowired
    private DashboardRepository dashboardRepository;


    public List<Map<String, Object>> getDailySales() {
        return dashboardRepository.getDailySales();
    }


    public List<Map<String, Object>> getTopProducts() {
        return dashboardRepository.getTopProducts();
    }


    public List<Map<String, Object>> getTopSellers() {
        return dashboardRepository.getTopSellers();
    }


    public List<Map<String, Object>> getProductDistribution() {
        return dashboardRepository.getProductDistribution();
    }


    public List<Map<String, Object>> getAverageRatingEvolution() {
        return dashboardRepository.getAverageRatingEvolution();
    }


    public List<Map<String, Object>> getSellerPerformanceComparison() {
        return dashboardRepository.getSellerPerformanceComparison();
    }


    public List<Map<String, Object>> getSalesVsRatings() {
        return dashboardRepository.getSalesVsRatings();
    }
}
