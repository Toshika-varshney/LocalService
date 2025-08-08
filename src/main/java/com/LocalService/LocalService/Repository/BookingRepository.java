package com.LocalService.LocalService.Repository;

import com.LocalService.LocalService.Entity.Booking;
import com.LocalService.LocalService.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long userId);
    List<Booking> findByStatus(BookingStatus status);
    List<Booking> findByVendorIdAndStatus(Long vendorId, BookingStatus status);
    List<Booking> findByVendorId(Long vendorId);
   //
//   @Query("SELECT SUM(b.price) FROM Booking b WHERE b.vendorId = :vendorId AND b.status = :status")
//   Double getUpcomingIncome(@Param("vendorId") Long vendorId, @Param("status") BookingStatus status);
//
//    @Query("SELECT SUM(b.price) FROM Booking b WHERE b.vendorId = :vendorId AND b.status = :status")
//    Double getTotalIncome(@Param("vendorId") Long vendorId, @Param("status") BookingStatus status);
//
//    @Query("SELECT MONTH(b.bookingDate), SUM(b.price) FROM Booking b WHERE b.vendorId = :vendorId AND b.status = :status GROUP BY MONTH(b.bookingDate)")
//    List<Object[]> getMonthlyIncome(@Param("vendorId") Long vendorId, @Param("status") BookingStatus status);



}
