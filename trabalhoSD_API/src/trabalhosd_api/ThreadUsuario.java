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

    public ThreadUsuario(String usuNome, String acao, Long k, Tripla instancia) {
        this.usuNome = usuNome;
        this.acao = acao;
        this.k = k;
        this.instancia = instancia;
    }

    public ThreadUsuario(String usuNome) {
        t = new Thread(this, usuNome);
        this.usuNome = usuNome;

    }

    public void executarAcao(String acao, Long k, Tripla instancia) {
        System.out.println("O usuario " + usuNome + " esta tendo " + acao);
        this.acao = acao;
        this.k = k;
        this.instancia = instancia;
        t.start();
    }

    @Override
    public void run() {
        //Random gerador = new Random();
        //byte[] b = {0x54, 0x56};

        System.out.println("Salvando os dados: " + instancia.getData() + " No usuario:" + usuNome);
        Intermediador inter = new Intermediador();
        synchronized (inter) {
            if ("SALVAR".equals(acao)) {
                Tupla t = inter.salvar(k, instancia.getTs(), instancia.getData());
                System.out.println("users:" + usuNome + " e:" + t.getE() + " tripla:" + t.getResult());
            } else {
                System.out.println("Acao invalida");
            }
        }
        //k = gerador.nextLong();
        //instancia.setTs(gerador.nextLong());
        //gerador.nextBytes(b);
        //instancia.setData(b);

    }

    @Override
    public void start() {
        System.out.println("O usuario " + usuNome + " esta tendo " + acao);
        if (t == null) {
            t = new Thread(this, usuNome);
            t.start();
        }
    }

}
