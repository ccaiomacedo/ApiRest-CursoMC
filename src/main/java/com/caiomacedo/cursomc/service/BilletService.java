package com.caiomacedo.cursomc.service;

import com.caiomacedo.cursomc.domain.BilletPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BilletService {

    //metodo pra acrescentar uma data de vencimento no pagamento
    public void preencherPagamentoComBoleto(BilletPayment pagto, Date instanteDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);//definindo a data do calendário com a data passada como parâmetro
        cal.add(Calendar.DAY_OF_MONTH,7);//Acrescentando 7 dias na data
        pagto.setDataVencimento(cal.getTime());
    }

}
