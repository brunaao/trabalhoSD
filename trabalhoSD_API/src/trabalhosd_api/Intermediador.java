/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhosd_api;

/**
 *
 * @author br
 */
public class Intermediador {
    
    static Servidor s;
    static{
        s = new Servidor();
    }

    public Tupla salvar(Long k, Long ts, byte[] data) {
        return s.set(k, ts, data);
    }

    @Override
    public String toString() {
        return s.toString();
    }
}
