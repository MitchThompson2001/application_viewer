package com.example.application_viewer.specifications;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.example.application_viewer.components.BillType;
import com.example.application_viewer.components.OrderType;
import com.example.application_viewer.components.ProductCategory;
import com.example.application_viewer.components.ReasonType;
import com.example.application_viewer.components.ServiceType;
import com.example.application_viewer.components.TicketStatus;
import com.example.application_viewer.models.Location;
import com.example.application_viewer.models.Patient;
import com.example.application_viewer.models.PatientOrder;
import com.example.application_viewer.models.PatientTicket;

import jakarta.persistence.criteria.Predicate;

public class PatientTicketSpecification {

    public static Specification<PatientTicket> filterBy(
        Long id,
        Patient patient,
        PatientOrder patientOrder,
        OrderType orderType,
        ProductCategory productCategory,
        TicketStatus ticketStatus,
        String lineItemNumber,
        ServiceType serviceType,
        BillType billType,
        String hcpcsCode,
        LocalDate dos,
        Integer quantity,
        LocalDate nextAction,
        Location location,
        String manufacturer,
        String manufacturerId,
        String productName,
        String lotNumber,
        String serialNumber,
        String trackingNumber,
        String notes,
        String cost,
        Boolean billable,
        ReasonType reasonType,
        String reasonDescription,
        LocalDateTime auditDate,
        String lastUpdatedBy,
        LocalDate lastUpdatedDate
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (id != null) predicates.add(cb.equal(root.get("id"), id));
            if (patient != null) predicates.add(cb.equal(root.get("patient"), patient));
            if (patientOrder != null) predicates.add(cb.equal(root.get("patientOrder"), patientOrder));
            if (orderType != null) predicates.add(cb.equal(root.get("orderType"), orderType));
            if (productCategory != null) predicates.add(cb.equal(root.get("productCategory"), productCategory));
            if (ticketStatus != null) predicates.add(cb.equal(root.get("ticketStatus"), ticketStatus));
            if (lineItemNumber != null) predicates.add(cb.equal(root.get("lineItemNumber"), lineItemNumber));
            if (serviceType != null) predicates.add(cb.equal(root.get("serviceType"), serviceType));
            if (billType != null) predicates.add(cb.equal(root.get("billType"), billType));
            if (hcpcsCode != null) predicates.add(cb.equal(root.get("hcpcsCode"), hcpcsCode));
            if (dos != null) predicates.add(cb.equal(root.get("dos"), dos));
            if (quantity != null) predicates.add(cb.equal(root.get("quantity"), quantity));
            if (nextAction != null) predicates.add(cb.equal(root.get("nextAction"), nextAction));
            if (location != null) predicates.add(cb.equal(root.get("location"), location));
            if (manufacturer != null) predicates.add(cb.like(cb.lower(root.get("manufacturer")), "%" + manufacturer.toLowerCase() + "%"));
            if (manufacturerId != null) predicates.add(cb.equal(root.get("manufacturerId"), manufacturerId));
            if (productName != null) predicates.add(cb.like(cb.lower(root.get("productName")), "%" + productName.toLowerCase() + "%"));
            if (lotNumber != null) predicates.add(cb.equal(root.get("lotNumber"), lotNumber));
            if (serialNumber != null) predicates.add(cb.equal(root.get("serialNumber"), serialNumber));
            if (trackingNumber != null) predicates.add(cb.equal(root.get("trackingNumber"), trackingNumber));
            if (notes != null) predicates.add(cb.like(cb.lower(root.get("notes")), "%" + notes.toLowerCase() + "%"));
            if (cost != null) predicates.add(cb.equal(root.get("cost"), cost));
            if (billable != null) predicates.add(cb.equal(root.get("billable"), billable));
            if (reasonType != null) predicates.add(cb.equal(root.get("reasonType"), reasonType));
            if (reasonDescription != null) predicates.add(cb.like(cb.lower(root.get("reasonDescription")), "%" + reasonDescription.toLowerCase() + "%"));
            if (auditDate != null) predicates.add(cb.equal(root.get("auditDate"), auditDate));
            if (lastUpdatedBy != null) predicates.add(cb.like(cb.lower(root.get("lastUpdatedBy")), "%" + lastUpdatedBy.toLowerCase() + "%"));
            if (lastUpdatedDate != null) predicates.add(cb.equal(root.get("lastUpdatedDate"), lastUpdatedDate));

            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
