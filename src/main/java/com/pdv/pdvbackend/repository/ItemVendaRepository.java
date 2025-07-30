package com.pdv.pdvbackend.repository;

import com.pdv.pdvbackend.model.ItemVendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVendaModel, Long> {
}