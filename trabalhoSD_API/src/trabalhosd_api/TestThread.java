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
      ThreadUsuario u1 = new ThreadUsuario("U1");
      ThreadUsuario u2 = new ThreadUsuario("U2");
      
      u1.executarAcao("SALVAR", gerador.nextLong(), tr);
      u2.executarAcao("SALVAR", gerador.nextLong(), tr);
      
       System.out.println("-------Fim do main-----");
       
       Intermediador inter = new Intermediador();
       
       System.out.println(inter);

      
   }   
}