/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd_api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author br
 */
public class Servidor {
    private Map<Long, Tripla> dadosBd; 

    public Servidor() {
        dadosBd = new HashMap<>();
    }
   
   
   public Tupla set(Long k,Long ts, byte[] d){
       Tupla retorno = new Tupla("SUCCESS"); //Começa com sucesso para n precisar instanciar v'
       
       //Verifica se n contem a chave, caso n tenha, adiciona e retorna sucesso
       if (!dadosBd.containsKey(k)){
           Tripla inst = new Tripla(1L,ts,d);
           dadosBd.put(k,inst);
           return retorno;
       }

       Tripla inst = dadosBd.get(k);
       
       //Verifica se o dado é igual ao do banco, se n for att o banco, se for retorna erro
       if (!(inst.getData() == d)){
           inst.setData(d);
           inst.setTs(ts);
           inst.setVer(inst.getVer()+1L);
           return retorno;
       }
       retorno.setResult(inst);
       retorno.setE("ERRO");
       return retorno;
   }
   
   public Tupla get(Long k){
       return dadosBd.containsKey(k)? new Tupla("SUCESSO", dadosBd.get(k)) : new Tupla("ERRO");
   } 
   
   public Tupla del(Long k){
       if (dadosBd.containsKey(k)){
           Tripla inst = dadosBd.get(k);
           dadosBd.remove(k);
           return new Tupla("SUCESSO", inst);
       }
       return new Tupla("ERRO");
   }
   
   public Tupla del(Long k, Long ver){
       if (dadosBd.containsKey(k)){
           Tripla inst = dadosBd.get(k);
           if (Objects.equals(inst.getVer(), ver)){
               dadosBd.remove(k);
               return new Tupla("SUCESSO", inst); 
           }
           return new Tupla("ERROR_WV", inst);
       }
       return new Tupla("ERROR_NE");
   }
    
   public Tupla testAndSet(Long k, Tripla v, Long ver){
       if (dadosBd.containsKey(k)){
           Tripla inst = dadosBd.get(k);
           if (Objects.equals(inst.getVer(), ver)){
               dadosBd.put(k, v);
               return new Tupla("SUCESSO", inst); 
           }
           return new Tupla("ERROR_WV", inst);
       }
       return new Tupla("ERROR_NE");
   }  
   
   public void salvar(){
       //TODO
   }
   
   public void puxar(){
       //TODO
   }
   
}
   
