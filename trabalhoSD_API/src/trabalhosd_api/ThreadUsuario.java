/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd_api;

import java.util.Random;

/**
 *
 * @author br
 */
public class ThreadUsuario extends Thread {

    private Thread t;
    private String usuNome;
    private String acao;
    private Long k;
    private Tripla instancia;
    private Servidor serv;
 
    public ThreadUsuario(String usuNome, String acao, Long k, Tripla instancia) {
        this.usuNome = usuNome;
        this.acao = acao;
        this.k = k;
        this.instancia = instancia;
    }

    public ThreadUsuario(String usuNome, Servidor s) {
        this.usuNome = usuNome;
        this.serv = s;

    }

    public void executarAcao(String acao, Long k, Tripla instancia) {
        System.out.println("O usuario " + usuNome + " esta fazendo a acao de " + acao);
        this.acao = acao;
        this.k = k;
        this.instancia = instancia;
        t = new Thread(this, usuNome);
        t.start();

        
    }

    @Override
    public void run() {
        System.out.println("Salvando os dados: " + instancia + " No usuario:" + usuNome);
        try{
        
            if ("SALVAR".equals(acao)) {
                Tupla resultado = serv.set(k, instancia.getTs(), instancia.getData());
                System.out.println("users:" + usuNome + " e:" + resultado.getE() + " tripla:" + resultado.getResult());
            } else {
                System.out.println("Acao invalida");
            }
        }catch(NullPointerException e){
            System.out.println("Erro " + e);
        }
            
        //k = gerador.nextLong();
        //instancia.setTs(gerador.nextLong());
        //gerador.nextBytes(b);
        //instancia.setData(b);

    }

    @Override
    public void start() {
        System.out.println("O usuario " + usuNome + " esta tendo " + acao);
        t = new Thread(this, usuNome);
        t.start();

    }

}
