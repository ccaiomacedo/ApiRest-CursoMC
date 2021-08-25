package com.caiomacedo.cursomc.service.validation;


import com.caiomacedo.cursomc.domain.Client;
import com.caiomacedo.cursomc.domain.enums.ClientType;
import com.caiomacedo.cursomc.dto.ClientNewDTO;
import com.caiomacedo.cursomc.repository.ClientRepository;
import com.caiomacedo.cursomc.resource.exception.FieldMessage;
import com.caiomacedo.cursomc.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    private ClientRepository cr;

    @Override
    public void initialize(ClientInsert ann) {
    }
    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(objDto.getTipo().equals(ClientType.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
        }
        if(objDto.getTipo().equals(ClientType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
        }

        Client aux = cr.findByEmail(objDto.getEmail());
        if(aux!=null){
            list.add(new FieldMessage("email","Email já existente"));
        }

        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getNome()).addConstraintViolation();//está permitindo transformar minha lista de erros para o framework
        }
        return list.isEmpty();//se tiver uma lista de erros o método não vai estar vazio e vai retornar falso
    }
}