package com.example.demomonitor.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import lombok.*;

@Data
public class TV extends Monitor implements Telecomando, GoogleHome{
    private LocalTime orarioTV = LocalTime.of(9, 0);
    private ArrayList<Canale> listaCanali = new ArrayList<Canale>();
    private HashMap<String, String> app = new HashMap<String, String>();

    public TV(){ super(); }

    public TV(String colore, String marca,
              ArrayList<Canale> listaCanali,
              HashMap<String, String> app,
              LocalTime orarioTV){
        super(colore, marca);
        this.listaCanali = listaCanali;
        this.app = app;
        this.orarioTV = orarioTV;
    }

    public void risintonizza(){
        Canale canale = listaCanali.get(0);
        canale.setStato(true);
        listaCanali.set(0, canale);
        for(int i = 1; i < listaCanali.size(); i++){
            canale = listaCanali.get(i);
            canale.setStato(false);
            listaCanali.set(i, canale);
        }
    }

    public String info(){
        for (Canale canale: listaCanali) {
            if(canale.isStato()){
                return canale.toString();
            }
        }
        return null;
    }

    public void addChannel(Canale canale){
        try{
            for(Canale can : listaCanali){
                if(can.getNome().equals(canale.getNome())){
                    throw new Exception("Canale già esistente");
                }
            }
            listaCanali.add(canale);
        }

        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void removeChannel(String nome){
        for(int i = 0; i < listaCanali.size(); i++){
            if(listaCanali.get(i).getNome().equals(nome)){
                listaCanali.remove(i);
            }
        }
    }

    public void addApp(String nome, String versione){
        try{
            for(String key : app.keySet()) {
                if (key.equals(nome)) {
                    throw new Exception("AlreadyExistingApp");
                }
            }
            app.put(nome, versione);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void deleteApp(String nome){
        app.remove(nome);
    }
    @Override
    public void settaCanale(String nomeCanale){
        attivaCanale(nomeCanale);
    }
    @Override
    public void settaCanaleOrario(String nomeCanale, LocalTime orario){
        if(orarioTV.equals(orario)){
            attivaCanale(nomeCanale);
        }
    }

    public void attivaCanale(String nomeCanale){
        Canale canale;
        Canale checkpoint = null;
        boolean find = false;
        for(int i = 0; i < listaCanali.size(); i++){
            canale = listaCanali.get(i);
            if(canale.getNome().equals(nomeCanale)){
                find = true;
                checkpoint = canale;
                if(canale.isStato()){
                    break;
                }
                else{
                    canale.setStato(true);
                    listaCanali.set(i, canale);
                }
            }

            else{
                canale.setStato(false);
            }
        }
        if(find){
            System.out.println(checkpoint.toString());
        }
        else{
            System.out.println("Canale non trovato");
        }
    }
}
