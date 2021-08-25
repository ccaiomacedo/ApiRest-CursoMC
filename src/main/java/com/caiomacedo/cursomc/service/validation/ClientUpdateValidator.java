package com.caiomacedo.cursomc.service.validation;


import com.caiomacedo.cursomc.domain.Client;

import com.caiomacedo.cursomc.dto.ClientDTO;

import com.caiomacedo.cursomc.repository.ClientRepository;
import com.caiomacedo.cursomc.resource.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;//ele permite ter o parâmetro da uri

    @Autowired
    private ClientRepository cr;

    @Override
    public void initialize(ClientUpdate ann) {
    }
    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")//para a mensagem amarela de error sair
        Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);//serve para pegar um map de atributos da uri
        Integer uriID = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client aux = cr.findByEmail(objDto.getEmail());
        if(aux!=null && !aux.getId().equals(uriID)){//se o id que vai editar é igual ao id da uri,o email pode ser igual, se for diferente email já existe
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