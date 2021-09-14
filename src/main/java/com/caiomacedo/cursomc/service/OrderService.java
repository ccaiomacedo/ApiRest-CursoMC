 package com.caiomacedo.cursomc.service;

 import com.caiomacedo.cursomc.domain.*;
 import com.caiomacedo.cursomc.domain.enums.PaymentStatus;
 import com.caiomacedo.cursomc.repository.*;
 import com.caiomacedo.cursomc.services.exceptions.ObjectNotFoundException;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import java.util.Date;
 import java.util.Optional;

 @Service
 public class OrderService {
     @Autowired
     private OrderRepository or;

     @Autowired
     private BilletService billetService;

     @Autowired
     private PaymentRepository pr;

     @Autowired
     private ProductService ps;

     @Autowired
     private OrderItemRepository oir;

     @Autowired
     private ClientService cs;

     @Autowired
     private EmailService es;


     public Orders find(Integer id){
         Optional<Orders> obj =or.findById(id);
         return obj.orElseThrow(() -> new ObjectNotFoundException(// se n receber um objeto existente, vai retornar isso
                 "Objeto não encontrado! Id: "+id+" , Tipo: "+Category.class.getName()));//tipo retorna o nome da classe
     }
     @Transactional
     public Orders insert(Orders obj) {
         obj.setId(null);//Para garantir que n vai passar um id existente
         obj.setInstante(new Date());//Cria uma nova data com o instante atual
         obj.setClient(cs.find(obj.getClient().getId()));//estou pegando do banco de dados o cliente inteiro e setando como objeto associado ao obj
         obj.getPagamento().setEstado(PaymentStatus.PENDENTE);
         obj.getPagamento().setPedido(obj);//o Pagamento tem que conhecer o pedido(associação)
         if (obj.getPagamento() instanceof BilletPayment) {//se o pagamento for igual a pagamento com boleto
             BilletPayment pagto = (BilletPayment) obj.getPagamento();
             billetService.preencherPagamentoComBoleto(pagto, obj.getInstante());
         }
         obj = or.save(obj);
         pr.save(obj.getPagamento());
         for (OrderItem ip : obj.getItens()){
             ip.setDesconto(0.0);
             ip.setProduto(ps.find(ip.getProduto().getId()));//estou pegando o id e buscando no banco de dados o produto inteiro,
             ip.setPreco(ip.getProduto().getPrice());//para setar o preço do item do pedido com o do produto
             ip.setPedido(obj);//associando o item do pedido com o pedido que to inserindo
         }
         oir.saveAll(obj.getItens());
         es.sendOrderConfirmationHtmlEmail(obj);
         return obj;
     }

 }
