package de.ithoc.datafetching.openweathermap.repositories;

import de.ithoc.datafetching.openweathermap.model.Sys;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRepository extends JpaRepository<Sys, Long>  {
}
