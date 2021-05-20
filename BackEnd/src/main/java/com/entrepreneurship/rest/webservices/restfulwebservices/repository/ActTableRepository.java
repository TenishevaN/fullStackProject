/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.ActTable;

/*
 * It is a storage, retrieval, and search mechanism for an ActTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface ActTableRepository extends CrudRepository<ActTable, Long>{

		
	 @Query(nativeQuery = true, value = "select a_t.id, a_t.id_act, a_t.quantity, a_t.price, a_t.measure, a_t.description from ACT_TABLE  a_t where a_t.id_act = :id")
	 List<ActTable> getActTableById(@Param("id") Long id);
	 
	 @Query(nativeQuery = true, value = "select a_t.id, a_t.id_act, a_t.quantity, a_t.price, a_t.measure, a_t.description from ACT_TABLE a_t left join act a on a.id = a_t.id_act left join contract c on c.id= a.id_contract where c.id= :id")
	 List<ActTable> getActTableByContractId(@Param("id") Long id);
	 
	 @Query(nativeQuery = true, value = "select a_t.* from ACT_TABLE a_t left join act a on a.id = a_t.id_act  where a_t.id_act = :id")
	 List<ActTable> getActTableByActId(@Param("id") Long id);


}
