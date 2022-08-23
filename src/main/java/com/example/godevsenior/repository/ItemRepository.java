package com.example.godevsenior.repository;

import com.example.godevsenior.model.ItemModel;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<ItemModel, Integer>{
}
