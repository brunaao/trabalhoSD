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
public class TestThread {

   public static void main(String args[]) {
      byte[] b = {0x54, 0x56};
      Tripla tr = new Tripla(1L, 1L, b);
      Random gerador = new Random();
      Servidor serv = new Servidor();
      ThreadUsuario u1 = new ThreadUsuario("U1",serv);
      ThreadUsuario u2 = new ThreadUsuario("U2",serv);
      
      System.out.println("enviando: "+ b);
      u1.executarAcao("SALVAR", 1L, tr);
      u2.executarAcao("SALVAR", 1L, tr);
      
      byte[] d = {0x30, 0x60};
      tr.setData(d);
      System.out.println("enviando: "+ d);
      u1.executarAcao("SALVAR", 3L, tr);
      
     byte[] c = {0x40, 0x10};
      tr.setData(c);
      System.out.println("enviando: "+ c);
      u2.executarAcao("SALVAR", 3L, tr);
      
      byte[] e = {0x20, 0x11};
      tr.setData(e);
      System.out.println("enviando: "+ e);
      u2.executarAcao("SALVAR", 1L, tr);
      u1.executarAcao("SALVAR", 1L, tr);
      
       System.out.println("-------Fim do main-----");
       
       System.out.println(serv);

      
   }   
}