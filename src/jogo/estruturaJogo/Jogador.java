package jogo.estruturaJogo;

import exceptions.ValorDeAtributoInvalido;

import java.io.*;
import java.util.*;

public class Jogador implements Serializable{

    private String nome;
    private long tempoMillis;

    public Jogador() {
        this.tempoMillis = 0;
        this.nome = "";
    }

    public Jogador(String nome, long tempoSeg) {
        this.tempoMillis = tempoSeg;
        this.nome = nome;
    }

    public void setNome(String nome) throws ValorDeAtributoInvalido {
		if(nome == null || nome == "")
			throw new ValorDeAtributoInvalido("Erro pois valor para nome é inválido");
		else
			this.nome = nome;
    }

    public void setTempoMillis(long tempoMillis) throws ValorDeAtributoInvalido {
        if(tempoMillis < 0)
            throw new ValorDeAtributoInvalido("Erro pois foi passado um valor negativo para o tempo");
        else this.tempoMillis = tempoMillis;

    }
    public long getTempoMillis() {
        return tempoMillis;
    }



    public String StringRecorde(){
        String recorde = "TEMPO   |   JOGADOR\n";

        for(Jogador jogador : recordeParaLista()){
            double tempo = (double)jogador.getTempoMillis()/1000;
            recorde = recorde + String.format("%.3f  |  %s\n", tempo,jogador.nome);
        }

        return recorde;
    }

    //Sistema de arquivos ----------------------------------------------------------------------
    //Salva recorde no tipo TreeMap
    public void salvarRecorde( ){
        TreeMap<Long,List<String>> jogadoresRanking = resgatarRecorde();
        try (FileOutputStream fos = new FileOutputStream(Arquivos.RANKING.getEndereco());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            Jogador jogador = this;

            addRecordeAoMap(jogador.nome,jogador.tempoMillis,jogadoresRanking);

            oos.writeObject(jogadoresRanking);
            System.out.println("Jogador Salvo");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    //Renomeei esse método em comparação ao checkpoint passado, pois tinha feito a lógica de pontuação errada
    public List<Jogador> recordeParaLista(){
        TreeMap<Long,List<String>> jogadoresRanking = resgatarRecorde();
        List<Jogador> jogadores = new ArrayList<>();

        for (Long key : jogadoresRanking.keySet()){
            List<String> nomes = jogadoresRanking.get(key);
            for (String nome : nomes){
                if(jogadores.size() < 10){
                    jogadores.add(new Jogador(nome,key));
                }
            }
        }

        return jogadores;
    }
    //Retorna um TreeMap do arquivo, ou cria uma nova instancia de TreeMap.
    private TreeMap resgatarRecorde(){
        TreeMap<Long,List<String>> jogadorRanking;
        try (FileInputStream fis = new FileInputStream(Arquivos.RANKING.getEndereco());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            jogadorRanking = (TreeMap<Long, List<String>>) ois.readObject();
            return jogadorRanking;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Arquivo de recorde não foi encontrado, criando novo save...");
            criarNovoSaveDeRecorde();

        }
        return new TreeMap<Long,List<String>>();
    }

    //Adiciona um recorde ao treeMap do parametro, pois tem chances(improvável) de ter mais de 1 jogador com o mesmo tempo
    private void addRecordeAoMap(String nome, long tempo, Map<Long,List<String>> jogadoresRanking){
        if(jogadoresRanking.containsKey(tempo)){
            List<String> nomes = jogadoresRanking.get(tempo);
            nomes.add(nome);
        }else{
            List<String> newNameList = new ArrayList<>();
            newNameList.add(nome);
            jogadoresRanking.put(tempo, newNameList);
        }
    }

    private void criarNovoSaveDeRecorde(){

        try (FileOutputStream fos = new FileOutputStream(Arquivos.RANKING.getEndereco());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            TreeMap<Long,List<String>> jogadoresRankingVazio = new TreeMap<>();
            oos.writeObject(jogadoresRankingVazio);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            System.out.println("Arquivo de ranking criado!");
        }
    }
    //----------------------------------------------------------------------------------



}
