package com.example.godevsenior.controller;

import com.example.godevsenior.model.DescontoModel;
import com.example.godevsenior.model.ItemPedidoModel;
import com.example.godevsenior.repository.ItemPedidoRepository;
import com.example.godevsenior.repository.ItemRepository;
import com.example.godevsenior.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemPedidoController {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping(path = "/api/orders/{idPedido}/items/{idItemPedido}")
    public ResponseEntity consultar(@PathVariable("idPedido") Integer idPedido,
                                    @PathVariable("idItemPedido") Integer idItemPedido) {
        return itemPedidoRepository.findById(idItemPedido)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/orders/{idItemPedido}/items")
    public List<ItemPedidoModel> consultar() {
        return (List<ItemPedidoModel>) itemPedidoRepository.findAll();
    }

    @PostMapping(path = "/api/orders/items")
    public ItemPedidoModel salvar(@RequestBody ItemPedidoModel ItemPedidoModel)  {
        return itemPedidoRepository.save(ItemPedidoModel);
    }

    @DeleteMapping(value = "/api/orders/{idPedido}/items/{idItemPedido}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "idPedido") Integer idPedido)
    {
        Optional<ItemPedidoModel> pedido = itemPedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            itemPedidoRepository.deleteById(idPedido);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "api/orders/{idPedido}/items/{idItemPedido}")
    public ResponseEntity<ItemPedidoModel> atualizar(
            @PathVariable(value = "idPedido") Integer idPedido,
            @PathVariable(value = "idItemPedido") Integer idItemPedido,
                                                @RequestBody ItemPedidoModel newItemPedido)
    {
        Optional<ItemPedidoModel> oldPedido = itemPedidoRepository.findById(idPedido);
        if(oldPedido.isPresent()){
            ItemPedidoModel itemPedido = oldPedido.get();
            itemPedido.setQuantity(newItemPedido.quantity);
            itemPedido.setTotalValue(newItemPedido.totalValue);
            itemPedido.setIdItem(newItemPedido.idItem);
            itemPedido.setIdPedido(newItemPedido.idPedido);
            itemPedidoRepository.save(itemPedido);
            return new ResponseEntity<ItemPedidoModel>(itemPedido, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping(path = "/api/orders/{idPedido}/close")
    public ResponseEntity consultar(@PathVariable("idPedido") Integer idPedido,
                                    @RequestBody DescontoModel descontoModel) {
        double totalValueWithDiscount = pedidoRepository.findById(idPedido).get().totalValue;
        if(itemRepository.findById(idPedido).get().type == 'P')
        { totalValueWithDiscount -= totalValueWithDiscount /100*descontoModel.percentualDiscount;}
        return pedidoRepository.findById(idPedido)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }
}