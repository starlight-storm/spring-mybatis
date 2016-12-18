package com.example.service.repository.orderer;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.service.domain.Orderer;

@Mapper
public interface OrdererRepository {
	List<Orderer> findOrderers();
	void createOrderer(Orderer orderer);
}