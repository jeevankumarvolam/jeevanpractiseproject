package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Practise{
        public static void main(String[] args){

                List<Integer> list=new ArrayList<>();
                list.add(1);
                list.add(-1);
                list.add(-3);
                list.add(7);
                List<Integer> list2= list.stream().sorted((a,b)->a-b).collect(Collectors.toList());
                list2.forEach(i->System.out.println(i));
        }}