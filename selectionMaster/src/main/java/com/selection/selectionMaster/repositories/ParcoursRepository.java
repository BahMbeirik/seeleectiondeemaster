package com.selection.selectionMaster.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.selection.selectionMaster.models.Parcours;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
  List<Parcours> findAll();
}