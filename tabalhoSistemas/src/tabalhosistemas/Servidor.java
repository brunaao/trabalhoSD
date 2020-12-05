/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabalhosistemas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author br
 */
public class Servidor {
   
    private static Map<Long, Tripla> dadosBd;
    private Timestamp ultimaVezSalvo;

    public Servidor() {
        dadosBd =  java.util.Collections.synchronizedMap(new HashMap<>());
        ultimaVezSalvo = new Timestamp(System.currentTimeMillis());
    }

    public synchronized Tupla set(Long k, Long ts, Byte[] d) {
        Tupla retorno = new Tupla("SUCCESS"); //Começa com sucesso para n precisar instanciar v'
        Tripla inst = new Tripla(1L, ts, d);

        //Verifica se n contem a chave, caso n tenha, adiciona e retorna sucesso
        if (!dadosBd.containsKey(k)) {
            dadosBd.put(k, inst);
            return retorno;
        }

        inst = dadosBd.get(k);

        //Verifica se o dado é igual ao do banco, se n for att o banco, se for retorna erro
        if (!(inst.getData().equals(d))) {
            inst.setData(d);
            inst.setTs(ts);
            inst.setVer(inst.getVer() + 1L);
            return retorno;
        }

        retorno.setResult(inst);
        retorno.setE("ERRO");
        return retorno;
    }

    public synchronized Tupla get(Long k) {
        return dadosBd.containsKey(k) ? new Tupla("SUCESSO", dadosBd.get(k)) : new Tupla("ERRO");
    }

    public synchronized Tupla del(Long k) {
        if (dadosBd.containsKey(k)) {
            Tripla inst = dadosBd.get(k);
            dadosBd.remove(k);
            return new Tupla("SUCESSO", inst);
        }
        return new Tupla("ERRO");
    }

    public synchronized Tupla del(Long k, Long ver) {
        if (dadosBd.containsKey(k)) {
            Tripla inst = dadosBd.get(k);
            if (Objects.equals(inst.getVer(), ver)) {
                dadosBd.remove(k);
                return new Tupla("SUCESSO", inst);
            }
            return new Tupla("ERROR_WV", inst);
        }
        return new Tupla("ERROR_NE");
    }

    public synchronized Tupla testAndSet(Long k, Tripla v, Long ver) {
        if (dadosBd.containsKey(k)) {
            Tripla inst = dadosBd.get(k);
            if (Objects.equals(inst.getVer(), ver)) {
                dadosBd.put(k, v);
                return new Tupla("SUCESSO", inst);
            }
            return new Tupla("ERROR_WV", inst);
        }
        return new Tupla("ERROR_NE");
    }

    public synchronized void salvar() throws IOException {
        FileWriter csvWriter = new FileWriter("/D:/Users/br/Documents/NetBeansProjects/tabalhoSistemas/dadosBD_2020.csv");
        for (Long codDado : dadosBd.keySet()) {
            Tripla c = dadosBd.get(codDado);
            Byte[] b = c.getData();
            String arrayb = "";
            for(int i = 0; i<b.length;i++){
                if (i+1 == b.length){
                    arrayb += b[i];
                }
                else{
                    arrayb += b[i] + ";";
                }
            }
            csvWriter.append(codDado+","+c.getVer()+","+c.getTs()+","+arrayb);
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
        ultimaVezSalvo = new Timestamp(System.currentTimeMillis());

    }

    public synchronized void puxar() throws FileNotFoundException, IOException {
        try (BufferedReader csvReader = new BufferedReader(new FileReader("D:/Users/br/Documents/NetBeansProjects/tabalhoSistemas/dadosBD_2020.csv"))) {
            dadosBd =  java.util.Collections.synchronizedMap(new HashMap<>());
            String row;
            Tripla dado;
            Long k;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                k = Long.parseLong(data[0]);
                String[] dadosS = data[3].split(";");
                
                List<Byte> dlb = new ArrayList<>(); 
                for(String d: dadosS){
                    dlb.add(Byte.parseByte(d));
                }
                
                Byte[] dadoB = (Byte[]) dlb.toArray();
                dado = new Tripla(Long.parseLong(data[1]),Long.parseLong(data[2]),dadoB);
                dadosBd.put(k, dado);
            }
        }catch(FileNotFoundException e){
            System.out.println("Banco n encontrado");
            this.salvar();
            
        }
    }

    public Timestamp getUltimaVezSalvo() {
        return ultimaVezSalvo;
    }

}
