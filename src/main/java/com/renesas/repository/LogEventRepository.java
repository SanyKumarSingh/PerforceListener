/**
 * 
 */
package com.renesas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renesas.model.LogEvents;

/**
 * @author a5143522
 *
 */
@Repository
public interface LogEventRepository extends JpaRepository<LogEvents, Long> {

}
