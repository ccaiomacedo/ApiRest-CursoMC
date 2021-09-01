package com.caiomacedo.cursomc.resource.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

//as classes utils são utilitários para ajudar a trabalhar com meus recursos
public class URL {

    //esse método serve para descodificar um parâmetro
    //quando vc passa um parâmetro com espaço ele vem "óla mundo, óla%20mundo" dessa forma, com esse método vai descodificar para vir normal
    public static String decodeParam(String s){
        try {
            return URLDecoder.decode(s,"UTF-8");
        }catch (UnsupportedEncodingException e){
            return "";
        }
    }


    //vai pegar a String da url e converter em uma lista de números inteiros
    public static List<Integer> decodeIntList(String s) {
        String[] vet = s.split(",");// o split é uma função que pega o String e recorta em pedaços a partir do caracter que passou
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));//converte o elemento da posição i para inteiro e adiciona na lista
        }
        return list;
        //return Arrays.asList(s.split()).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList()); mesma coisa só que em lambda
    }
}


