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
    private static Map<Long, Tripla> dadosBd;
    
    public Servidor() {
        dadosBd = new HashMap<>();
    }

    public synchronized Tupla set(Long k, Long ts, byte[] d) {
        Tupla retorno = new Tupla("SUCCESS"); //Começa com sucesso para n precisar instanciar v'
        Tripla inst = new Tripla(1L, ts, d);
        
        //Verifica se n contem a chave, caso n tenha, adiciona e retorna sucesso
        if (!dadosBd.containsKey(k) && !dadosBd.containsValue(inst)) {
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

    public synchronized void salvar() {
        //TODO
    }

    public synchronized void puxar() {
        //TODO
    }

}
