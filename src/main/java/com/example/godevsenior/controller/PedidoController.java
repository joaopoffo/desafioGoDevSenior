package com.example.godevsenior.controller;

import com.example.godevsenior.model.DescontoModel;
import com.example.godevsenior.model.ItemModel;
import com.example.godevsenior.model.PedidoModel;
import com.example.godevsenior.repository.ItemRepository;
import com.example.godevsenior.repository.PedidoRepository;
import com.example.godevsenior.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping(path = "/api/orders/{idPedido}")
    public ResponseEntity consultar(@PathVariable("idPedido") Integer idPedido) {
        return pedidoRepository.findById(idPedido)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/api/orders")
    public List<PedidoModel> consultar() {
        return (List<PedidoModel>) pedidoRepository.findAll();
    }

    @PostMapping(path = "/api/orders")
    public PedidoModel salvar(@RequestBody PedidoModel PedidoModel)  {
        return pedidoRepository.save(PedidoModel);
    }

    @DeleteMapping(value = "/api/orders/{idPedido}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "idPedido") Integer idPedido)
    {
        Optional<PedidoModel> pedido = pedidoRepository.findById(idPedido);
        if(pedido.isPresent()){
            pedidoRepository.deleteById(idPedido);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "api/pedido/{idPedido}")
    public ResponseEntity<PedidoModel> atualizar(@PathVariable(value = "idPedido") Integer idPedido,
                                                @RequestBody PedidoModel newPedido)
    {
        Optional<PedidoModel> oldPedido = pedidoRepository.findById(idPedido);
        if(oldPedido.isPresent()){
            PedidoModel pedido = oldPedido.get();
            pedido.setNumber(newPedido.number);
            pedido.setDate(newPedido.date);
            pedido.setPercentualDiscount(newPedido.percentualDiscount);
            pedido.setTotalValue(newPedido.totalValue);
            pedidoRepository.save(pedido);
            return new ResponseEntity<PedidoModel>(pedido, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}