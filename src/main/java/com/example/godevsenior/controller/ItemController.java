package com.example.godevsenior.controller;

import com.example.godevsenior.model.ItemModel;
import com.example.godevsenior.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(path = "/api/orders/{idItem}")
    public ResponseEntity consultar(@PathVariable("idItem") Integer idItem) {
        return itemRepository.findById(idItem)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/orders")
    public List<ItemModel> consultar() {
        return (List<ItemModel>) itemRepository.findAll();
    }

    @PostMapping(path = "/api/orders")
    public ItemModel salvar(@RequestBody ItemModel ItemModel)  {
        return itemRepository.save(ItemModel);
    }

    @DeleteMapping(value = "/api/orders/{idItem}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "idItem") Integer idItem)
    {
        Optional<ItemModel> item = itemRepository.findById(idItem);
        if(item.isPresent()){
            itemRepository.deleteById(idItem);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "api/item/{idItem}")
    public ResponseEntity<ItemModel> atualizar(@PathVariable(value = "idItem") Integer idItem,
                                                @RequestBody ItemModel newItem)
    {
        Optional<ItemModel> oldItem = itemRepository.findById(idItem);
        if(oldItem.isPresent()){
            ItemModel item = oldItem.get();
            item.setDescription(newItem.description);
            item.setValue(newItem.value);
            item.setType(newItem.type);
            itemRepository.save(item);
            return new ResponseEntity<ItemModel>(item, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}