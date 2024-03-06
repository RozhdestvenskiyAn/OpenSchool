package ru.rozhdestvenskiy.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.rozhdestvenskiy.supplier.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{

    //    @Query(value = "select * from ticket t " +
//                   "where t.doctor_id = :doctorId and date(t.time_appointment) = :date and t.patient_id is null ",
//            nativeQuery = true)
//    List<Ticket> findAllFreeTicketsBy(Long doctorId, LocalDate date);
//
//    List<Ticket> findAllByPatient(Patient patient);
//
//    @Query(value = "select * from ticket t " +
//                   "where t.doctor_id = :doctorId and date(t.time_appointment) = :date limit 1",
//            nativeQuery = true)
//    Optional<Ticket> findTicketByDate(Long doctorId, LocalDate date);
}
